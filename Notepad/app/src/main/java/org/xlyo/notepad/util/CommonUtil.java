package org.xlyo.notepad.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class CommonUtil {
    public static String convertTime(String time) {
        String[] split = time.split("/");
        String[] s2 = split[2].split(" ");
        return split[0] + "年" + split[1] + "月" + s2[0] + "日 " + s2[1];
    }
}
