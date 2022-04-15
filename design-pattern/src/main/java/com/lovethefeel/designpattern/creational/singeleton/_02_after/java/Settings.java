package com.lovethefeel.designpattern.creational.singeleton._02_after.java;

public class Settings {
    private static Settings instance;

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
