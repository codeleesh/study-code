package com.lovethefeel.designpattern.creational.singeleton.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringExample {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        String hello1 = ac.getBean("hello", String.class);
        String hello2 = ac.getBean("hello", String.class);
        System.out.println(hello1 == hello2);
    }
}
