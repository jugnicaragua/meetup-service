package org.jugni.session.util;

/**
 *
 * @author aalaniz
 */
public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty() || value.trim().isEmpty();
    }
}
