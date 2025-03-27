package utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class log {
    private static final Logger logger = LoggerFactory.getLogger(log.class.getName());

    // This is to print log at the start of the test case
    public static void startTestCase(String testCaseName){
        logger.info("*********************************************************************************");

        logger.info("*********************************************************************************");

        logger.info("$$$$$$$$$$$$$$$$                  "+testCaseName+"                 $$$$$$$$$$$$$$$$$$$$");

        logger.info("*********************************************************************************");

        logger.info("*********************************************************************************");


    }

    // This is to print log at the end of the test case
    public static void endTestCase(String testCaseName){
        logger.info("XXXXXXXXXXXXXXXXXXXXX            "+"-E---N---D-"+"            XXXXXXXXXXXXXXXXXXXXXXX");

        logger.info("X");

        logger.info("X");

        logger.info("X");

        logger.info("X");

    }

    public static void info(String message){
        logger.info(message);
    }

    public static void warn(String message){
        logger.warn(message);
    }

    public static void error(String message){
        logger.error(message);
    }

    public static void debug(String message){
        logger.debug(message);
    }

}
