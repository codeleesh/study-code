package com.lovethefeel.designpattern.creational.singeleton._01_before;

public class App {
    public static void main(String[] args) {
        Settings settings1 = new Settings();
        Settings settings2 = new Settings();

        System.out.println(settings1 == settings2);
    }
}
