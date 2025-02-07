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

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.shape.VoxelShape;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import top.byteeeee.annotationtoolbox.annotation.GameVersion;

import top.byteeeee.kaleidoscope.config.KaleidoscopeConfig;

@GameVersion(version = "Minecraft >= 1.21.4")
@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRenderMixin {
    @WrapOperation(
        method = "renderTargetBlockOutline",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/WorldRenderer;drawBlockOutline(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/Entity;DDDLnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)V"
        )
    )
    private void renderBlockOutlineWrapper(
            WorldRenderer worldRenderer, MatrixStack matrices, VertexConsumer vertexConsumer, Entity entity,
            double cameraX, double cameraY, double cameraZ,
            BlockPos pos, BlockState state, int originalColor, Operation<Void> original
    ) {
        if (KaleidoscopeConfig.blockOutlineConfigData.displaySwitch) {
            int customColor = ColorHelper.getArgb(
                KaleidoscopeConfig.blockOutlineConfigData.alpha,
                KaleidoscopeConfig.blockOutlineConfigData.red,
                KaleidoscopeConfig.blockOutlineConfigData.green,
                KaleidoscopeConfig.blockOutlineConfigData.blue
            );
            VoxelShape shape = state.getOutlineShape(((WorldRendererAccessor) this).getWorld(), pos, ShapeContext.of(entity));
            VertexRendering.drawOutline(matrices, vertexConsumer, shape, pos.getX() - cameraX, pos.getY() - cameraY, pos.getZ() - cameraZ, customColor);
        } else {
            original.call(worldRenderer, matrices, vertexConsumer, entity, cameraX, cameraY, cameraZ, pos, state, originalColor);
        }
    }
}

