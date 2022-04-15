package com.lovethefeel.algorithm.sort.selectionsort;

/**
 * 데이터가 세 개일 때 선택 정렬 알고리즘 방식으로 정렬
 */
public class Ex2 {
    static int[] arrays;

    static void swap(int[] a, int f, int r) {
        int temp;
        temp = a[f];
        a[f] = a[r];
        a[r] = temp;
    }

    static void sort() {
        int lowest = 0;
        for (int stand = 0; stand < arrays.length - 1; stand++) {
            lowest = stand;
            for (int index = stand + 1; index < arrays.length; index++) {
                if (arrays[lowest] > arrays[index]) {
                    swap(arrays, lowest, index);
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
        arrays[1] = 1;
        arrays[2] = 7;

        sort();
        print();
    }
}
