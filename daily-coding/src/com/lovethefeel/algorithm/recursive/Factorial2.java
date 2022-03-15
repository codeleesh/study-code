package com.lovethefeel.algorithm.recursive;

public class Factorial2 {
    static int rec_func(int num) {
        if (num <= 1) {
            return 1;
        }
        return num * rec_func(num - 1);
    }

    public static void main(String[] args) {
        int result = rec_func(4);
        System.out.println("result = " + result);
    }
}