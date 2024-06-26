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

package top.byteeeee.kaleidoscope.config.skyColor;

import top.byteeeee.kaleidoscope.config.KaleidoscopeConfig;

public class SkyConfigData {
    public Integer color = 4772300;
    public boolean displaySwitch = false;

    public void loadFromConfig(KaleidoscopeConfig.ConfigData config) {
        color = config.skyConfigData.color;
        displaySwitch = config.skyConfigData.displaySwitch;
    }

    public void saveToConfig(KaleidoscopeConfig.ConfigData config) {
        config.skyConfigData.color = color;
        config.skyConfigData.displaySwitch = displaySwitch;
    }
}