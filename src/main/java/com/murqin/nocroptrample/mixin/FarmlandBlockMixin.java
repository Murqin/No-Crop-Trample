// src/main/java/com/example/nocroptrample/mixin/FarmlandBlockMixin.java
package com.murqin.nocroptrample.mixin;

import com.murqin.nocroptrample.NoCropTrample; // Kendi paket adın ne ise o olmalı
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin {

    @Inject(method = "onLandedUpon", at = @At("HEAD"), cancellable = true)
    private void preventTrampling(World world, BlockState state, BlockPos pos, Entity entity, double fallDistance, CallbackInfo ci) {

        if (entity instanceof PlayerEntity) {
            if (NoCropTrample.CONFIG.isPlayerTramplingDisabled()) {
                ci.cancel();
            }
        } else {
            if (NoCropTrample.CONFIG.isMobTramplingDisabled()) {
                ci.cancel();
            }
        }
    }
}
