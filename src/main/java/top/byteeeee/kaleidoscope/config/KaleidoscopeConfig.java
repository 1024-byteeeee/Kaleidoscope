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
import top.byteeeee.kaleidoscope.config.skyColor.SkyConfigData;
import top.byteeeee.kaleidoscope.config.fogColor.FogConfigData;
import top.byteeeee.kaleidoscope.config.blockOutlineColor.BlockOutlineConfigData;
import top.byteeeee.kaleidoscope.config.waterColor.WaterConfigData;
import top.byteeeee.kaleidoscope.config.waterFogColor.WaterFogConfigData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class KaleidoscopeConfig {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("kaleidoscope");
    private static final String CONFIG_FILE = CONFIG_PATH.resolve("colors.json").toString();

    public static SkyConfigData skyConfigData = new SkyConfigData();
    public static FogConfigData fogConfigData = new FogConfigData();
    public static WaterConfigData waterConfigData = new WaterConfigData();
    public static WaterFogConfigData waterFogConfigData = new WaterFogConfigData();
    public static BlockOutlineConfigData blockOutlineConfigData = new BlockOutlineConfigData();

    public static void loadFromConfig() {
        createConfigPath();
        try (Reader reader = new FileReader(CONFIG_FILE)) {
            ConfigData config = gson.fromJson(reader, ConfigData.class);
            if (config != null) {
                loadConfig(config);
            }
        } catch (IOException e) {
            KaleidoscopeClientMod.LOGGER.warn("Failed to load configuration" + e);
        }
    }

    public static void saveToConfig() {
        createConfigPath();
        ConfigData config = new ConfigData();
        saveConfig(config);
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

    private static void loadConfig(ConfigData config) {
        skyConfigData.loadFromConfig(config);
        fogConfigData.loadFromConfig(config);
        waterConfigData.loadFromConfig(config);
        waterFogConfigData.loadFromConfig(config);
        blockOutlineConfigData.loadFromConfig(config);
    }

    private static void saveConfig(ConfigData config) {
        skyConfigData.saveToConfig(config);
        fogConfigData.saveToConfig(config);
        waterConfigData.saveToConfig(config);
        waterFogConfigData.saveToConfig(config);
        blockOutlineConfigData.saveToConfig(config);
    }

    public static class ConfigData {
        public SkyConfigData skyConfigData = new SkyConfigData();
        public FogConfigData fogConfigData = new FogConfigData();
        public WaterConfigData waterConfigData = new WaterConfigData();
        public WaterFogConfigData waterFogConfigData = new WaterFogConfigData();
        public BlockOutlineConfigData blockOutlineConfigData = new BlockOutlineConfigData();
    }
}