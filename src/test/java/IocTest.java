import com.guzx.chapter2.config.AppConfig;
import com.guzx.chapter2.pojo.User;
import com.guzx.chapter2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

public class IocTest {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void main(String[] args) {
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = (User) applicationContext.getBean("user");
        logger.info(user.getUserName());*/

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        User u = (User) applicationContext.getBean("user");
        logger.info(u.getUserName());
        UserService service = (UserService) applicationContext.getBean("userService");
        service.printUser(u);

        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        try {
            Connection connection = dataSource.getConnection();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
