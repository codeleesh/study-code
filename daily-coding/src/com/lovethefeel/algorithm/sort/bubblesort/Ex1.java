package com.lovethefeel.algorithm.sort.bubblesort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 데이터가 두 개일 때 버블 정렬 알고리즘 방식으로 정렬
 */
public class Ex1 {

    static List<Integer> list;

    static void sort() {
        if (list.get(0) > list.get(1)) {
            Collections.swap(list, 0, 1);
        }
    }

    static void print() {
        System.out.println("list = " + list);
    }

    public static void main(String[] args) {
        list = new ArrayList<>();
        list.add(4);
        list.add(2);

        sort();
        print();
    }
}
