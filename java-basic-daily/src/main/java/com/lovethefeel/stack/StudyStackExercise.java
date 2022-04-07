package com.lovethefeel.stack;

import java.util.ArrayList;
import java.util.List;

public class StudyStackExercise<T> {

    private List<T> stack = new ArrayList<>();

    private void push(T item) {
        stack.add(item);
    }

    private T pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public static void main(String[] args) {
        StudyStackExercise<Integer> stack = new StudyStackExercise<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
