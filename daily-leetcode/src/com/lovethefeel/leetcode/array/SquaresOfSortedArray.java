package com.lovethefeel.leetcode.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SquaresOfSortedArray {

    /**
     * 정렬된 배열의 사각형
     * 감소하지 않는 순서로 정렬된 정수 배열 숫자가 주어지면 감소하지 않는 순서로 정렬된 각 숫자의 사각형 배열을 반환합니다.
     * ex) Input: nums = [-4,-1,0,3,10]
     *    Output: [0,1,9,16,100]
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        // 요청받은 배열사이즈만큼 결과값을 저장하기 위해 배열 생성
        int[] result = new int[nums.length];
        // 요청받은 배열을 순회하면서
        for (int i = 0; i < nums.length; i++) {
            // 자기 자신을 곱하여서 결과 배열에 순서대로 저장
            result[i] = nums[i] * nums[i];
        }
        // 정렬하기 위해서 List 변환하여 오름차순 정렬
        List<Integer> numList = Arrays.stream(result).boxed().collect(Collectors.toList());
        Collections.sort(numList);
        // 오름차순 정렬된 List를 결과 배열에 순서대로 저장
        for (int i = 0; i < numList.size(); i++) {
            result[i] = numList.get(i);
        }
        // 결과값 리턴
        return result;
    }

    public static void main(String[] args) {
        final int[] numArrays = {-4,-1,0,3,10};
        final SquaresOfSortedArray sosa = new SquaresOfSortedArray();
        final long beforeTime = System.currentTimeMillis();
        sosa.sortedSquares(numArrays);
        final long afterTime = System.currentTimeMillis();
        System.out.println("수행시간 = " + (afterTime - beforeTime));
    }
}
