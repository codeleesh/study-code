package com.lovethefeel.designpattern.creational.singeleton._02_after.java;

public class EagerInitSettings {
    private static final EagerInitSettings INSTANCE = new EagerInitSettings();

    public static EagerInitSettings getInstance() {
        return INSTANCE;
    }
}
