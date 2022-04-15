package com.lovethefeel.designpattern.creational.singeleton._02_after.java;

public class StaticInnerSettings {
    private StaticInnerSettings() {}

    private static class SettingsHolder {
        private static final StaticInnerSettings INSTANCE = new StaticInnerSettings();
    }

    public static StaticInnerSettings getInstance() {
        return SettingsHolder.INSTANCE;
    }
}