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

package top.byteeeee.kaleidoscope.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;

import top.byteeeee.kaleidoscope.KaleidoscopeClientMod;
import top.byteeeee.kaleidoscope.config.skyColor.SkyConfig;
import top.byteeeee.kaleidoscope.config.fogColor.FogConfig;
import top.byteeeee.kaleidoscope.config.blockOutlineColor.BlockOutlineConfig;
import top.byteeeee.kaleidoscope.config.waterColor.WaterConfig;
import top.byteeeee.kaleidoscope.config.waterFogColor.WaterFogConfig;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class KaleidoscopeConfig {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("kaleidoscope");
    private static final String CONFIG_FILE = CONFIG_PATH.resolve("colors.json").toString();

    public static SkyConfig skyConfig = new SkyConfig();
    public static FogConfig fogConfig = new FogConfig();
    public static WaterConfig waterConfig = new WaterConfig();
    public static WaterFogConfig waterFogConfig = new WaterFogConfig();
    public static BlockOutlineConfig blockOutlineConfig = new BlockOutlineConfig();

    public static void loadFromConfig() {
        createConfigPath();
        try (Reader reader = new FileReader(CONFIG_FILE)) {
            ConfigData config = gson.fromJson(reader, ConfigData.class);
            if (config != null) {
                skyConfig.loadFromConfig(config);
                fogConfig.loadFromConfig(config);
                waterConfig.loadFromConfig(config);
                waterFogConfig.loadFromConfig(config);
                blockOutlineConfig.loadFromConfig(config);
            }
        } catch (IOException e) {
            KaleidoscopeClientMod.LOGGER.warn("Failed to load configuration" + e);
        }
    }

    public static void saveToConfig() {
        createConfigPath();
        ConfigData config = new ConfigData();
        skyConfig.saveToConfig(config);
        fogConfig.saveToConfig(config);
        waterConfig.saveToConfig(config);
        waterFogConfig.saveToConfig(config);
        blockOutlineConfig.saveToConfig(config);
        try (Writer writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            KaleidoscopeClientMod.LOGGER.warn("Failed to save configuration" + e);
        }
    }

    private static void createConfigPath() {
        try {
            if (!Files.exists(CONFIG_PATH)) {
                Files.createDirectories(CONFIG_PATH);
                Files.createFile(CONFIG_PATH.resolve("colors.json"));
            }
        } catch (IOException e) {
            KaleidoscopeClientMod.LOGGER.warn("Failed to creat configuration" + e);
        }
    }

    public static class ConfigData {
        public SkyConfig skyConfig = new SkyConfig();
        public FogConfig fogConfig = new FogConfig();
        public WaterConfig waterConfig = new WaterConfig();
        public WaterFogConfig waterFogConfig = new WaterFogConfig();
        public BlockOutlineConfig blockOutlineConfig = new BlockOutlineConfig();
    }
}