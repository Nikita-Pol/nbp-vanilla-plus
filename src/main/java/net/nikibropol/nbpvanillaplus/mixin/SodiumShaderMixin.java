package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "net.caffeinemc.mods.sodium.client.render.chunk.ShaderChunkRenderer")
public class SodiumShaderMixin {
    @ModifyArg(method = "createShader",
            at = @At(value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;withFragmentShader(Lnet/minecraft/resources/Identifier;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;"),
            index = 0)
    private Identifier nbp$fragment(Identifier original) {
        System.out.println("NBP shader mixin: " + original);
        return Identifier.fromNamespaceAndPath("nbp-vanilla-plus", "blocks/block_layer_opaque");
    }
}