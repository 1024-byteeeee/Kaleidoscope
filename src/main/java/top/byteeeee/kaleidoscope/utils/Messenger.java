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

package top.byteeeee.kaleidoscope.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.text.BaseText;
import top.byteeeee.kaleidoscope.utils.compat.MessengerCompatFactory;

@Environment(EnvType.CLIENT)
public class Messenger {
    public static BaseText s(Object text) {
        return MessengerCompatFactory.LiteralText(text.toString());
    }

    public static BaseText tr(String key, Object... args) {
        return MessengerCompatFactory.TranslatableText(key, args);
    }
}
