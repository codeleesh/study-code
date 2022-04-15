package com.lovethefeel.designpattern.creational.singeleton._02_after.java;

public class DoubleCheckedLockingSettings {
    private static volatile DoubleCheckedLockingSettings instance;

    private DoubleCheckedLockingSettings() {}

    public static DoubleCheckedLockingSettings getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSettings.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSettings();
                }
            }
        }
        return instance;
    }
}