package com.lovethefeel.webflux.config.ext;

public class SampleConfigLibrary {

    private String name;
    private boolean status;

    private static SampleConfigLibrary instance;

    private SampleConfigLibrary() {
        this.name = "lsh";
        this.status = false;
    }

    public static SampleConfigLibrary getInstance() {
        if (instance == null) {
            instance = new SampleConfigLibrary();
        }
        return instance;
    }

    public boolean login() {
        if ("lsh".equals(this.name)) {
            this.status = true;
            return true;
        }
        return false;
    }

    public String encrypt(final String data) {
        if (this.status) {
            return data + "encrypt";
        }
        return data;
    }

    public String decrypt(final String data) {
        if (this.status) {
            return data + "decrypt";
        }
        return data;
    }

    public String getName() {
        return name;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "SampleConfigLibrary{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
