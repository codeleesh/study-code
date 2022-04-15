package com.lovethefeel.algorithm.sort.insertionsort;

/**
 * 데이터가 두 개일 때 삽입 정렬 알고리즘 방식으로 정렬
 */
public class Ex1 {
    static int[] arrays;

    static void swap(int[] a, int f, int r) {
        int temp;
        temp = a[f];
        a[f] = a[r];
        a[r] = temp;
    }

    static void sort() {
        for (int index = 0; index < arrays.length - 1; index++) {
            for (int index2 = index + 1; index2 > 0; index2--) {
                if (arrays[index2] < arrays[index2 - 1]) {
                    swap(arrays, index2 - 1, index2);
                }
            }
        }
    }

    static void print() {
        for (int i = 0; i < arrays.length; i++) {
            System.out.println(arrays[i]);
        }
    }

    public static void main(String[] args) {
        arrays = new int[3];
        arrays[0] = 9;
        arrays[1] = 5;
        arrays[2] = 4;

        sort();
        print();
    }
}
