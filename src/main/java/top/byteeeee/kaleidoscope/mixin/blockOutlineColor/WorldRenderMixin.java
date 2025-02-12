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
import net.minecraft.util.shape.VoxelShape;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import top.byteeeee.annotationtoolbox.annotation.GameVersion;

import top.byteeeee.kaleidoscope.config.KaleidoscopeConfig;

@GameVersion(version = "Minecraft < 1.21.4")
@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRenderMixin implements WorldRendererAccessor {
    @WrapOperation(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/WorldRenderer;drawBlockOutline(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/entity/Entity;DDDLnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V"
        )
    )
    private void renderBlockOutline(
        WorldRenderer worldRenderer, MatrixStack matrices, VertexConsumer vertexConsumer, Entity entity,
        double cameraX, double cameraY, double cameraZ,
        BlockPos pos, BlockState state, Operation<Void> original
    ) {
        if (KaleidoscopeConfig.blockOutlineConfigData.displaySwitch) {
            float red = KaleidoscopeConfig.blockOutlineConfigData.red / 255.0F;
            float green = KaleidoscopeConfig.blockOutlineConfigData.green / 255.0F;
            float blue = KaleidoscopeConfig.blockOutlineConfigData.blue / 255.0F;
            float alpha = KaleidoscopeConfig.blockOutlineConfigData.alpha / 255.0F;
            double X = pos.getX() - cameraX;
            double Y = pos.getY() - cameraY;
            double Z = pos.getZ() - cameraZ;
            VoxelShape shape = state.getOutlineShape(this.getWorld(), pos, ShapeContext.of(entity));
            drawOutline(matrices, vertexConsumer, shape, X, Y, Z, red, green, blue, alpha);
        } else {
            original.call(worldRenderer, matrices, vertexConsumer, entity, cameraX, cameraY, cameraZ, pos, state);
        }
    }

    @Unique
    private static void drawOutline(MatrixStack matrices, VertexConsumer vertexConsumer, VoxelShape shape, double X, double Y, double Z, float red, float green, float blue, float alpha) {
        WorldRenderer.drawShapeOutline(matrices, vertexConsumer, shape, X, Y, Z, red, green, blue, alpha);
    }
}

