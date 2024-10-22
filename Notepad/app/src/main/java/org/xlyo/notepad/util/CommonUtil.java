package org.xlyo.notepad.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class CommonUtil {
    public static String getTimeNow() {
        String formatPattern = "yyyy年MM月dd日 HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern).withZone(ZoneId.of("+8"));
        return formatter.format(Instant.now());
    }
}
