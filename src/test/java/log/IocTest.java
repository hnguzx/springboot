package log;


import com.guzx.chapter2.config.AppConfig;
import com.guzx.chapter2.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocTest {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = (User) applicationContext.getBean("user");
        logger.info(user.getUserName());
    }
}
