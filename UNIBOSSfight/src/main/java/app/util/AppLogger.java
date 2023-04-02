package app.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * This utility class implements a logger.
 */
public final class AppLogger extends Logger {

    private AppLogger() {
        super(AppLogger.GLOBAL_LOGGER_NAME, null);

        Handler handler;

        try {
            handler = new FileHandler("./log/app.log", true);
            handler.setFormatter(new LogFormatter());
        } catch (IOException e) {
            handler = new ConsoleHandler();
        }

        handler.setLevel(Level.ALL);

        this.setUseParentHandlers(false);
        this.addHandler(handler);
        this.setLevel(Level.ALL);
    }

    /**
     * Returns the logger.
     *
     * @return the logger
     */
    public static Logger getLogger() {
        return LazyLoggerHolder.LOGGER;
    }

    private static class LazyLoggerHolder {
        private static final Logger LOGGER = new AppLogger();
    }

    private static class LogFormatter extends Formatter {
        @Override
        public String format(final LogRecord record) {
            return LocalDateTime.now() + " "
                    + record.getLevel() + ": "
                    + record.getSourceMethodName() + " in "
                    + record.getSourceClassName() + " caused \""
                    + record.getMessage() + "\"\n";
        }
    }
}
