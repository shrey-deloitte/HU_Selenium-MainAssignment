import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class log4jDemo {

    static  Logger logger= LogManager.getLogger(log4jDemo.class);


    public static void main(String[] args) {

        System.out.println("hii");
        logger.info("this is info");
        logger.error("this is error");
        logger.fatal("this is fatal");
        logger.warn("this is warn");


    }
}
