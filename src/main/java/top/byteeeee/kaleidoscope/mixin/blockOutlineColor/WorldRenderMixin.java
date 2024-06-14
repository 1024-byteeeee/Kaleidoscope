/*
 * This file is part of the Kaleidoscope project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2024 1024_byteeeee and contributors
 *
 * Kaleidoscope is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kaleidoscope is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Kaleidoscope. If not, see <https://www.gnu.org/licenses/>.
 */

package top.byteeeee.kaleidoscope.mixin.blockOutlineColor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import top.byteeeee.kaleidoscope.config.KaleidoscopeConfig;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRenderMixin implements WorldRendererAccessor{
    @Inject(method = "drawBlockOutline", at = @At("HEAD"), cancellable = true)
    private void drawBlockOutline (MatrixStack matrices, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (KaleidoscopeConfig.blockOutlineConfigData.displaySwitch) {
            float R = KaleidoscopeConfig.blockOutlineConfigData.red / 255.0F;
            float G = KaleidoscopeConfig.blockOutlineConfigData.green / 255.0F;
            float B = KaleidoscopeConfig.blockOutlineConfigData.blue / 255.0F;
            float alpha = KaleidoscopeConfig.blockOutlineConfigData.alpha / 255.0F;
            WorldRenderer.drawShapeOutline(
                matrices, vertexConsumer, state.getOutlineShape(this.getWorld(), pos, ShapeContext.of(entity)),
                (double)pos.getX() - cameraX,
                (double)pos.getY() - cameraY,
                (double)pos.getZ() - cameraZ,
                R, G, B, alpha
            );
            ci.cancel();
        }
    }
}
