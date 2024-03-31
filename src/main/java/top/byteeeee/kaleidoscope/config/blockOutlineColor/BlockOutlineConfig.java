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

package top.byteeeee.kaleidoscope.config.blockOutlineColor;

import top.byteeeee.kaleidoscope.config.KaleidoscopeConfig;

public class BlockOutlineConfig {
    public int red = 0;
    public int green = 0;
    public int blue = 0;
    public int alpha = 102;
    public boolean displaySwitch = false;

    public void loadFromConfig(KaleidoscopeConfig.ConfigData config) {
        red = config.blockOutlineConfig.red;
        green = config.blockOutlineConfig.green;
        blue = config.blockOutlineConfig.blue;
        alpha = config.blockOutlineConfig.alpha;
        displaySwitch = config.blockOutlineConfig.displaySwitch;
    }

    public void saveToConfig(KaleidoscopeConfig.ConfigData config) {
        config.blockOutlineConfig.red = red;
        config.blockOutlineConfig.green = green;
        config.blockOutlineConfig.blue = blue;
        config.blockOutlineConfig.alpha = alpha;
        config.blockOutlineConfig.displaySwitch = displaySwitch;
    }
}