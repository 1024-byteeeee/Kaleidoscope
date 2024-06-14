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

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.client.network.ClientPlayerEntity;

import top.byteeeee.kaleidoscope.config.KaleidoscopeConfig;
import top.byteeeee.kaleidoscope.utils.MessageTextEventUtils.ClickEventUtil;
import top.byteeeee.kaleidoscope.utils.MessageTextEventUtils.HoverEventUtil;
import top.byteeeee.kaleidoscope.utils.Messenger;

public class KaleidoscopeCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
            ClientCommandManager.literal("kaleidoscope")

            // Set sky color
            .then(ClientCommandManager.literal("sky")
            .then(ClientCommandManager.argument("switch", BoolArgumentType.bool())
            .executes(
                context -> {
                    KaleidoscopeConfig.skyConfigData.displaySwitch = context.getArgument("switch", Boolean.class);
                    KaleidoscopeConfig.saveToConfig();
                    return 1;
                }
            ))
            .then(ClientCommandManager.argument("value", StringArgumentType.string())
            .executes(context -> {
                String value = context.getArgument("value", String.class);
                KaleidoscopeConfig.skyConfigData.color = Integer.parseInt(value, 16);
                KaleidoscopeConfig.saveToConfig();
                return 1;
            })))

            // Set fog color
            .then(ClientCommandManager.literal("fog")
            .then(ClientCommandManager.argument("switch", BoolArgumentType.bool())
            .executes(
                context -> {
                    KaleidoscopeConfig.fogConfigData.displaySwitch = context.getArgument("switch", Boolean.class);
                    KaleidoscopeConfig.saveToConfig();
                    return 1;
                }
            ))
            .then(ClientCommandManager.argument("value", StringArgumentType.string())
            .executes(context -> {
                String value = context.getArgument("value", String.class);
                KaleidoscopeConfig.fogConfigData.color = Integer.parseInt(value, 16);
                KaleidoscopeConfig.saveToConfig();
                return 1;
            })))

            // Set water color
            .then(ClientCommandManager.literal("water")
            .then(ClientCommandManager.argument("switch", BoolArgumentType.bool())
            .executes(
                context -> {
                    KaleidoscopeConfig.waterConfigData.displaySwitch = context.getArgument("switch", Boolean.class);
                    KaleidoscopeConfig.saveToConfig();
                    return 1;
                }
            ))
            .then(ClientCommandManager.argument("value", StringArgumentType.string())
            .executes(context -> {
                String value = context.getArgument("value", String.class);
                KaleidoscopeConfig.waterConfigData.color = Integer.parseInt(value, 16);
                KaleidoscopeConfig.saveToConfig();
                return 1;
            })))

            // Set water fog color
            .then(ClientCommandManager.literal("waterFog")
            .then(ClientCommandManager.argument("switch", BoolArgumentType.bool())
            .executes(
                context -> {
                    KaleidoscopeConfig.waterFogConfigData.displaySwitch = context.getArgument("switch", Boolean.class);
                    KaleidoscopeConfig.saveToConfig();
                    return 1;
                }
            ))
            .then(ClientCommandManager.argument("value", StringArgumentType.string())
            .executes(context -> {
                String value = context.getArgument("value", String.class);
                KaleidoscopeConfig.waterFogConfigData.color = Integer.parseInt(value, 16);
                KaleidoscopeConfig.saveToConfig();
                return 1;
            })))

            // Set block outline color
            .then(ClientCommandManager.literal("blockOutline")
            .then(ClientCommandManager.argument("switch", BoolArgumentType.bool())
            .executes(
                context -> {
                    KaleidoscopeConfig.blockOutlineConfigData.displaySwitch = context.getArgument("switch", Boolean.class);
                    KaleidoscopeConfig.saveToConfig();
                    return 1;
                }
            ))
            .then(ClientCommandManager.argument("red", IntegerArgumentType.integer())
            .then(ClientCommandManager.argument("green", IntegerArgumentType.integer())
            .then(ClientCommandManager.argument("blue", IntegerArgumentType.integer())
            .then(ClientCommandManager.argument("alpha", IntegerArgumentType.integer())
            .executes(context -> {
                KaleidoscopeConfig.blockOutlineConfigData.red = context.getArgument("red", Integer.class);
                KaleidoscopeConfig.blockOutlineConfigData.green = context.getArgument("green", Integer.class);
                KaleidoscopeConfig.blockOutlineConfigData.blue = context.getArgument("blue", Integer.class);
                KaleidoscopeConfig.blockOutlineConfigData.alpha = context.getArgument("alpha", Integer.class);
                KaleidoscopeConfig.saveToConfig();
                return 1;
            }))))))

            // help
            .then(ClientCommandManager.literal("help")
            .executes(context -> help(context.getSource().getPlayer())))
        );
    }

    private static int help(ClientPlayerEntity player) {
        final String commandHelpDoc = Messenger.tr("kaleidoscope.command.kaleidoscope.help.doc").getString();
        player.sendMessage(Messenger.s(commandHelpDoc).formatted(Formatting.LIGHT_PURPLE).append(urlButton()), false);
        return 1;
    }

    private static Text urlButton() {
        final String commandHelpUrl = Messenger.tr("kaleidoscope.command.kaleidoscope.help.url").getString();
        return
            Messenger.s(commandHelpUrl).setStyle(
                Style.EMPTY.withColor(Formatting.LIGHT_PURPLE).withUnderline(true)
                .withClickEvent(ClickEventUtil.event(ClickEventUtil.OPEN_URL, commandHelpUrl))
                .withHoverEvent(HoverEventUtil.event(HoverEventUtil.SHOW_TEXT, Messenger.s("GOTO").formatted(Formatting.YELLOW)))
            );
    }
}
