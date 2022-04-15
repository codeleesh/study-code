package com.lovethefeel.leetcode.array;

public class DuplicateZeros {

    /**
     * 동
     * 고정 길이 정수 배열 arr이 주어지면 0의 각 발생을 복제하여 나머지 요소를 오른쪽으로 이동시킵니다. 원래 배열의 길이를 초과하는 요소는 쓰여지지 않습니다. 입력 배열을 위와 같이 수정하고 아무 것도 반환하지 마십시오.
     * ex) Input: arr = [1,0,2,3,0,4,5,0]
     *    Output: [1,0,0,2,3,0,0,4]
     * @param arr
     */
    public int[] duplicateZeros(int[] arr) {
        // 시작점에서 순회하며
        for (int i = 0; i < arr.length; i++) {
            // 시작점이 가리키는 배열의 값이 0이면
            if (arr[i] == 0) {
                // 끝점에서 부터 시작점을 만나기전까지 순회하며
                for (int j = arr.length - 1; j > i; j--) {
                    // 오른쪽으로 한칸씩 이동한다.
                    arr[j] = arr[j - 1];
                }
                // 시작점의 위치를 오른쪽으로 한칸 이동한다.
                i++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        final int[] numArrays = {1,0,2,3,0,4,5,0};
        final DuplicateZeros dz = new DuplicateZeros();
        final long beforeTime = System.currentTimeMillis();
        dz.duplicateZeros(numArrays);
        final long afterTime = System.currentTimeMillis();
        System.out.println("수행시간 = " + (afterTime - beforeTime));
    }
}
