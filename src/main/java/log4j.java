import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.logging.ErrorManager;

public class log4j {

    public static ErrorManager log;
    static  Logger logger= LogManager.getLogger(log4j.class);


    public static void main(String[] args) {


        logger.info("this is info");
        logger.error("this is error");
        logger.fatal("this is fatal");
        logger.warn("this is warn");


    }
}
