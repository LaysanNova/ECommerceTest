package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import test.BaseTest;

public final class LoggerUtils {
    static final String ERROR = "❌";
    static final String SUCCESS = "✅";
    static final String WARNING = "⚠️";
    private static final String EXCEPTION = "❗️";

    private static final Logger logger = LogManager.getLogger(BaseTest.class.getSimpleName());

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logSuccess(String message) {
        logger.info(SUCCESS + message);
    }

    public static void logError(String message) {
        logger.error(ERROR + message);
    }

    public static void logFatal(String message) {
        logger.fatal(EXCEPTION + ERROR + WARNING + message);
    }

    public static void logWarning(String message) {
        logger.warn(WARNING + message);
    }
}
