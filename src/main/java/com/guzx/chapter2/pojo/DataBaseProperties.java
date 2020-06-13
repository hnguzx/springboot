package com.guzx.chapter2.pojo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DataBaseProperties {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Value("${logging.config}")
    private String driverName = null;
//    @Value("${database.url}")
    private String url = null;
//    @Value("${database.username}")
    private String username = null;
//    @Value("${database.password}")
    private String password = null;

    public void setDriverName(String driverName) {
//        System.out.println(driverName);
        logger.info(driverName);
        this.driverName = driverName;
    }

    public void setUrl(String url) {
//        System.out.println(url);
        logger.info(url);
        this.url = url;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
//        System.out.println(username);
        logger.info(username);
        this.username = username;
    }

    public void setPassword(String password) {
//        System.out.println(password);
        logger.info(password);
        this.password = password;
    }
}
