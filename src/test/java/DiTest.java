import com.guzx.chapter2.config.AppConfig;
import com.guzx.chapter2.pojo.BussinessPerson;
import com.guzx.chapter2.pojo.definition.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;

public class DiTest {

    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
//        logger.info("测试");
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Person person = applicationContext.getBean(BussinessPerson.class);
        person.animalService();
        logger.info("结束");*/
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


       /* DataSource dataSource = (DataSource) annotationConfigApplicationContext.getBean("dataSource");
        try {
            Connection connection = dataSource.getConnection();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/

        annotationConfigApplicationContext.close();
    }
}
