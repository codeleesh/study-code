package com.lovethefeel.designpattern.creational.singeleton._02_after.java;

public class SynchronizedSettings {
    private static SynchronizedSettings instance;

    public static synchronized SynchronizedSettings getInstance() {
        if (instance == null) {
            instance = new SynchronizedSettings();
        }
        return instance;
    }
}
