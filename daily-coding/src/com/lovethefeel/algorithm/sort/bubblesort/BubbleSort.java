package com.lovethefeel.algorithm.sort.bubblesort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 랜덤함수를 이용하여서 숫자 100개에 대해서 BubbleSort 이용하여서 정렬
 */
public class BubbleSort {
    static List<Integer> list;

    static void sort() {
        for (int i = 0; i < list.size() -1; i++) {
            boolean swap = false;

            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    Collections.swap(list, j, j + 1);
                    swap = true;
                }
            }

            if (swap == false) {
                break;
            }
        }
    }

    static void print() {
        System.out.println("list = " + list);
    }

    public static void main(String[] args) {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add((int)(Math.random() * 100));
        }

        sort();
        print();
    }
}
