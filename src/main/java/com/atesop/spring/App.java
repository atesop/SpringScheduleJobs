package com.atesop.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        String configLocation = "applicationContext.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        logger.info("Application start at " + context.getStartupDate());
    }
}
