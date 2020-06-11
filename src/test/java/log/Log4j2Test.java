package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Test {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
        logger.trace("trace...");
        logger.debug("debug...");
        logger.error("error...");
        logger.info("info...");
        logger.trace("trace...");
    }
}
