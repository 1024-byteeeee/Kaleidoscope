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

package top.byteeeee.kaleidoscope.mixin.biomeColor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.world.biome.BiomeEffects;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import top.byteeeee.kaleidoscope.config.KaleidoscopeConfig;

@Environment(EnvType.CLIENT)
@Mixin(BiomeEffects.class)
public abstract class BiomeEffectsMixin {
    @Inject(method = "getSkyColor", at = @At("HEAD"), cancellable = true)
    private void getSkyColor(CallbackInfoReturnable<Integer> cir) {
        if (KaleidoscopeConfig.skyConfig.displaySwitch) {
            cir.setReturnValue(KaleidoscopeConfig.skyConfig.color);
        }
    }

    @Inject(method = "getFogColor", at = @At("HEAD"), cancellable = true)
    private void getFogColor(CallbackInfoReturnable<Integer> cir) {
        if (KaleidoscopeConfig.fogConfig.displaySwitch) {
            cir.setReturnValue(KaleidoscopeConfig.fogConfig.color);
        }
    }
}
