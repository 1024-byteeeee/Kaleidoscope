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

package top.byteeeee.kaleidoscope.commands.KaleidoscopeCommand;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

import top.byteeeee.kaleidoscope.config.KaleidoscopeConfig;

public class KaleidoscopeCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
            ClientCommandManager.literal("kaleidoscope")

            // Set sky color
            .then(ClientCommandManager.literal("sky")
            .then(ClientCommandManager.argument("switch", BoolArgumentType.bool())
            .executes(
                context -> {
                    KaleidoscopeConfig.skyConfig.displaySwitch = context.getArgument("switch", Boolean.class);
                    KaleidoscopeConfig.saveToConfig();
                    return 1;
                }
            ))
            .then(ClientCommandManager.argument("value", StringArgumentType.string())
            .executes(context -> {
                String value = context.getArgument("value", String.class);
                KaleidoscopeConfig.skyConfig.color = Integer.parseInt(value, 16);
                KaleidoscopeConfig.saveToConfig();
                return 1;
            })))

            // Set fog color
            .then(ClientCommandManager.literal("fog")
            .then(ClientCommandManager.argument("switch", BoolArgumentType.bool())
            .executes(
                context -> {
                    KaleidoscopeConfig.fogConfig.displaySwitch = context.getArgument("switch", Boolean.class);
                    KaleidoscopeConfig.saveToConfig();
                    return 1;
                }
            ))
            .then(ClientCommandManager.argument("value", StringArgumentType.string())
            .executes(context -> {
                String value = context.getArgument("value", String.class);
                KaleidoscopeConfig.fogConfig.color = Integer.parseInt(value, 16);
                KaleidoscopeConfig.saveToConfig();
                return 1;
            })))

            // Set block outline color
            .then(ClientCommandManager.literal("blockOutline")
            .then(ClientCommandManager.argument("switch", BoolArgumentType.bool())
            .executes(
                context -> {
                    KaleidoscopeConfig.blockOutlineConfig.displaySwitch = context.getArgument("switch", Boolean.class);
                    KaleidoscopeConfig.saveToConfig();
                    return 1;
                }
            ))
            .then(ClientCommandManager.argument("red", IntegerArgumentType.integer())
            .then(ClientCommandManager.argument("green", IntegerArgumentType.integer())
            .then(ClientCommandManager.argument("blue", IntegerArgumentType.integer())
            .then(ClientCommandManager.argument("alpha", IntegerArgumentType.integer())
            .executes(context -> {
                KaleidoscopeConfig.blockOutlineConfig.red = context.getArgument("red", Integer.class);
                KaleidoscopeConfig.blockOutlineConfig.green = context.getArgument("green", Integer.class);
                KaleidoscopeConfig.blockOutlineConfig.blue = context.getArgument("blue", Integer.class);
                KaleidoscopeConfig.blockOutlineConfig.alpha = context.getArgument("alpha", Integer.class);
                KaleidoscopeConfig.saveToConfig();
                return 1;
            }))))))
        );
    }
}
