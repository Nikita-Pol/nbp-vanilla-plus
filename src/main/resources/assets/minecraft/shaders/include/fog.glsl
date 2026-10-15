#version 330

layout(std140) uniform Fog {
    vec4 FogColor;
    float FogEnvironmentalStart;
    float FogEnvironmentalEnd;
    float FogRenderDistanceStart;
    float FogRenderDistanceEnd;
    float FogSkyEnd;
    float FogCloudsEnd;
    float FogPaleGrey;
};


vec3 nbp_rgb2hsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0/3.0, 2.0/3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));
    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}
// 1 - full color | 0 - gray color
float nbp_keep_mask(vec3 rgb) {
    vec3 hsv = nbp_rgb2hsv(rgb);

    float resinHue = smoothstep(0.010, 0.025, hsv.x)
    * (1.0 - smoothstep(0.090, 0.110, hsv.x)); // resin setup
    float resin = resinHue
    * smoothstep(0.70, 0.80, hsv.y)
    * smoothstep(0.20, 0.35, hsv.z);

    const float ECHO_HUE_MIN = 0.46;
    const float ECHO_HUE_MAX = 0.58;

    float coolHue = smoothstep(ECHO_HUE_MIN - 0.02, ECHO_HUE_MIN, hsv.x)
    * (1.0 - smoothstep(ECHO_HUE_MAX, ECHO_HUE_MAX + 0.02, hsv.x)); // echo-ender setup
    float cool = coolHue * smoothstep(0.45, 0.60, hsv.y);

    return clamp(max(resin, cool), 0.0, 1.0);
}

vec3 nbp_pale_grey(vec3 rgb, vec3 maskSource) {
    float grey = dot(rgb, vec3(0.299, 0.587, 0.114));
    float k = FogPaleGrey * (1.0 - nbp_keep_mask(maskSource));
    return mix(rgb, vec3(grey), k);
}

float linear_fog_value(float vertexDistance, float fogStart, float fogEnd) {
    if (vertexDistance <= fogStart) {
        return 0.0;
    } else if (vertexDistance >= fogEnd) {
        return 1.0;
    }

    return (vertexDistance - fogStart) / (fogEnd - fogStart);
}

float total_fog_value(float sphericalVertexDistance, float cylindricalVertexDistance, float environmentalStart, float environmantalEnd, float renderDistanceStart, float renderDistanceEnd) {
    return max(linear_fog_value(sphericalVertexDistance, environmentalStart, environmantalEnd), linear_fog_value(cylindricalVertexDistance, renderDistanceStart, renderDistanceEnd));
}

vec4 apply_fog(vec4 inColor, float sphericalVertexDistance, float cylindricalVertexDistance, float environmentalStart, float environmantalEnd, float renderDistanceStart, float renderDistanceEnd, vec4 fogColor) {
    float fogValue = total_fog_value(sphericalVertexDistance, cylindricalVertexDistance, environmentalStart, environmantalEnd, renderDistanceStart, renderDistanceEnd);
    return vec4(mix(inColor.rgb, fogColor.rgb, fogValue * fogColor.a), inColor.a);
}

float fog_spherical_distance(vec3 pos) {
    return length(pos);
}

float fog_cylindrical_distance(vec3 pos) {
    float distXZ = length(pos.xz);
    float distY = abs(pos.y);
    return max(distXZ, distY);
}