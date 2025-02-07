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

package top.byteeeee.kaleidoscope.utils.MessageTextEventUtils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.text.ClickEvent;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class ClickEventUtil {
    public static ClickEvent.Action OPEN_URL = ClickEvent.Action.OPEN_URL;
    public static ClickEvent.Action OPEN_FILE = ClickEvent.Action.OPEN_FILE;
    public static ClickEvent.Action RUN_COMMAND = ClickEvent.Action.RUN_COMMAND;
    public static ClickEvent.Action SUGGEST_COMMAND = ClickEvent.Action.SUGGEST_COMMAND;
    public static ClickEvent.Action CHANGE_PAGE = ClickEvent.Action.CHANGE_PAGE;
    public static ClickEvent.Action COPY_TO_CLIPBOARD = ClickEvent.Action.COPY_TO_CLIPBOARD;

    public static ClickEvent event(ClickEvent.Action action, String value) {
        return new ClickEvent(action, value);
    }
}

// For 1.21.5
//import net.minecraft.text.ClickEvent;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.util.function.Function;
//
//@SuppressWarnings("unused")
//public class ClickEventUtil {
//    public static final ClickEvent.Action OPEN_URL = ClickEvent.Action.OPEN_URL;
//    public static final ClickEvent.Action OPEN_FILE = ClickEvent.Action.OPEN_FILE;
//    public static final ClickEvent.Action RUN_COMMAND = ClickEvent.Action.RUN_COMMAND;
//    public static final ClickEvent.Action SUGGEST_COMMAND = ClickEvent.Action.SUGGEST_COMMAND;
//    public static final ClickEvent.Action CHANGE_PAGE = ClickEvent.Action.CHANGE_PAGE;
//    public static final ClickEvent.Action COPY_TO_CLIPBOARD = ClickEvent.Action.COPY_TO_CLIPBOARD;
//    private static final Map<ClickEvent.Action, Function<Object, ClickEvent>> CLICK_EVENT_ACTION_MAP = new HashMap<>();
//
//    public static ClickEvent event(ClickEvent.Action action, Object value) {
//        return Optional.ofNullable(CLICK_EVENT_ACTION_MAP.get(action)).map(function -> function.apply(value)).orElseThrow(() -> new IllegalArgumentException("Invalid action or value type"));
//    }
//
//    static {
//        CLICK_EVENT_ACTION_MAP.put(ClickEvent.Action.OPEN_URL, value -> {
//            if (value instanceof URI) {
//                return new ClickEvent.OpenUrl((URI) value);
//            } else if (value instanceof String) {
//                try {
//                    return new ClickEvent.OpenUrl(new URI((String) value));
//                } catch (Exception e) {
//                    throw new IllegalArgumentException("Invalid URL format", e);
//                }
//            }
//            throw new IllegalArgumentException("Expected a URI or String for OPEN_URL action");
//        });
//        CLICK_EVENT_ACTION_MAP.put(ClickEvent.Action.OPEN_FILE, value -> new ClickEvent.OpenFile((String) value));
//        CLICK_EVENT_ACTION_MAP.put(ClickEvent.Action.RUN_COMMAND, value -> new ClickEvent.RunCommand((String) value));
//        CLICK_EVENT_ACTION_MAP.put(ClickEvent.Action.SUGGEST_COMMAND, value -> new ClickEvent.SuggestCommand((String) value));
//        CLICK_EVENT_ACTION_MAP.put(ClickEvent.Action.CHANGE_PAGE, value -> new ClickEvent.ChangePage((Integer) value));
//        CLICK_EVENT_ACTION_MAP.put(ClickEvent.Action.COPY_TO_CLIPBOARD, value -> new ClickEvent.CopyToClipboard((String) value));
//    }
//}

