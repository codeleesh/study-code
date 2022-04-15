package com.lovethefeel.algorithm.sort.bubblesort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 데이터가 세 개일 때 버를 정렬 알고리즘 방식으로 정렬해보세요
 */
public class Ex2 {
    static List<Integer> list;

    static void sort() {
        for (int i = 0; i < list.size() -1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                Collections.swap(list, i, i + 1);
            }
        }
    }

    static void print() {
        System.out.println("list = " + list);
    }

    public static void main(String[] args) {
        list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(2);

        sort();
        print();
    }
}
