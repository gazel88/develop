package net.srook.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {
    private ExceptionUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String toStringStackTrace(Throwable exception) {
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        exception.printStackTrace();
        return errors.toString();
    }
}
