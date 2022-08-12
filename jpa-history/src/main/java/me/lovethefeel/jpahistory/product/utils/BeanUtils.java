package me.lovethefeel.jpahistory.product.utils;

import org.springframework.context.ApplicationContext;

public class BeanUtils {

    public static Object getBean(final Class clazz) {

        final ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(clazz);
    }

    public static Object getBean(final String beanName) {

        final ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(beanName);
    }

    protected BeanUtils() {}
}
