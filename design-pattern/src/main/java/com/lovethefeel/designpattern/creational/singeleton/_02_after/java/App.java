package com.lovethefeel.designpattern.creational.singeleton._02_after.java;

public class App {
    public static void main(String[] args) {
        EagerInitSettings settings1 = EagerInitSettings.getInstance();
        EagerInitSettings settings2 = EagerInitSettings.getInstance();

        System.out.println(settings1 == settings2);
    }
}
