package com.lovethefeel.algorithm.sort.bubblesort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * - 특이점 찾아보기
 *   - n개의 리스트가 있는 경우 최대 n-1번의 로직을 적용한다.
 *   - 로직을 1번 적용할 때마다 가장 큰 숫자가 뒤에서부터 1개씩 결정된다.
 *   - 로직이 경우에 따라 일찍 끝날 수도 있다. 따라서 로직을 적용할 때 한 번도 데이터가 교환된 적이 없다면 이미 정렬된 상태이므로 더 이상 로직을 반복 적용할 필요가 없다.
 */
public class Ex4 {
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
        list.add(4);
        list.add(1);
        list.add(2);
        list.add(9);

        sort();
        print();
    }
}
