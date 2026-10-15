#version 330 core

#moj_import <sodium:globals.glsl>
#moj_import <sodium:fog.glsl>
#moj_import <sodium:chunk_material.glsl>

in vec4 v_Color; // The interpolated vertex color
in vec2 v_TexCoord; // The interpolated block texture coordinates
in vec2 v_FragDistance; // The fragment's distance from the camera (cylindrical and spherical)
in float fadeFactor;

uniform sampler2D u_BlockTex; // The block texture

out vec4 fragColor; // The output fragment for the color framebuffer

vec4 sampleNearest(sampler2D source, vec2 uv, vec2 pixelSize, vec2 du, vec2 dv, vec2 texelScreenSize) {
    // Convert our UV back up to texel coordinates and find out how far over we are from the center of each pixel
    vec2 uvTexelCoords = uv / pixelSize;
    vec2 texelCenter = round(uvTexelCoords) - 0.5f;
    vec2 texelOffset = uvTexelCoords - texelCenter;

    // Move our offset closer to the texel center based on texel size on screen
    texelOffset = (texelOffset - 0.5f) * pixelSize / texelScreenSize + 0.5f;
    texelOffset = clamp(texelOffset, 0.0f, 1.0f);

    uv = (texelCenter + texelOffset) * pixelSize;
    return textureGrad(source, uv, du, dv);
}

vec4 sampleNearest(sampler2D source, vec2 uv, vec2 pixelSize) {
    vec2 du = dFdx(uv);
    vec2 dv = dFdy(uv);
    vec2 texelScreenSize = sqrt(du * du + dv * dv);
    return sampleNearest(source, uv, pixelSize, du, dv, texelScreenSize);
}

// Rotated Grid Super-Sampling
vec4 sampleRGSS(sampler2D source, vec2 uv, vec2 pixelSize) {
    vec2 du = dFdx(uv);
    vec2 dv = dFdy(uv);

    vec2 texelScreenSize = sqrt(du * du + dv * dv);
    float maxTexelSize = max(texelScreenSize.x, texelScreenSize.y);

    float minPixelSize = min(pixelSize.x, pixelSize.y);

    float transitionStart = minPixelSize * 1.0;
    float transitionEnd = minPixelSize * 2.0;
    float blendFactor = smoothstep(transitionStart, transitionEnd, maxTexelSize);

    float duLength = length(du);
    float dvLength = length(dv);
    float minDerivative = min(duLength, dvLength);
    float maxDerivative = max(duLength, dvLength);

    float effectiveDerivative = sqrt(minDerivative * maxDerivative);

    float mipLevelExact = max(0.0, log2(effectiveDerivative / minPixelSize));

    const vec2 offsets[4] = vec2[](
            vec2(0.125, 0.375),
            vec2(-0.125, -0.375),
            vec2(0.375, -0.125),
            vec2(-0.375, 0.125)
    );

    vec4 rgssColor = vec4(0.0);
    for (int i = 0; i < 4; ++i) {
        vec2 sampleUV = uv + offsets[i] * pixelSize;
        rgssColor += textureLod(source, sampleUV, mipLevelExact);
    }
    rgssColor *= 0.25;

    vec4 nearestColor = sampleNearest(source, uv, pixelSize, du, dv, texelScreenSize);

    return mix(nearestColor, rgssColor, blendFactor);
}
vec3 nbp_rgb2hsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0/3.0, 2.0/3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));
    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

float nbp_keep_mask(vec3 rgb) {
    vec3 hsv = nbp_rgb2hsv(rgb);

    float resinHue = smoothstep(0.010, 0.025, hsv.x)
    * (1.0 - smoothstep(0.090, 0.110, hsv.x));
    float resin = resinHue
    * smoothstep(0.70, 0.80, hsv.y)
    * smoothstep(0.20, 0.35, hsv.z);

    const float ECHO_HUE_MIN = 0.46;
    const float ECHO_HUE_MAX = 0.58;

    float coolHue = smoothstep(ECHO_HUE_MIN - 0.02, ECHO_HUE_MIN, hsv.x)
    * (1.0 - smoothstep(ECHO_HUE_MAX, ECHO_HUE_MAX + 0.02, hsv.x));
    float cool = coolHue * smoothstep(0.45, 0.60, hsv.y);

    return clamp(max(resin, cool), 0.0, 1.0);
}

vec3 nbp_pale_grey(vec3 rgb, vec3 maskSource, float paleGrey) {
    float grey = dot(rgb, vec3(0.299, 0.587, 0.114));
    float k = paleGrey * (1.0 - nbp_keep_mask(maskSource));
    return mix(rgb, vec3(grey), k);
}

void main() {
    vec4 raw = u_UseRGSS ? sampleRGSS(u_BlockTex, v_TexCoord, u_TexelSize) : sampleNearest(u_BlockTex, v_TexCoord, u_TexelSize);
    vec4 color = raw * v_Color; // Apply per-vertex color modulator

    #ifdef ALPHA_CUTOUT
    if (color.a < ALPHA_CUTOUT) {
        discard;
    }
    #endif

    float nbp_grey = clamp((1.0 - u_FogColor.a) / 0.01, 0.0, 1.0);

    color.rgb = nbp_pale_grey(color.rgb, raw.rgb, nbp_grey);

    fragColor = _linearFog(color, v_FragDistance, u_FogColor, u_EnvironmentFog, u_RenderFog, fadeFactor);
}