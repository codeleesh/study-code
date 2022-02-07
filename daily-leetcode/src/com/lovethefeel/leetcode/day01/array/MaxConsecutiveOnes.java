package com.lovethefeel.leetcode.day01.array;

public class MaxConsecutiveOnes {

    /**
     * 최대 연속수 함수
     * 이진 배열 번호가 지정되면 배열에서 연속 1의 최대 수를 반환합니다.
     * ex) Input: nums = [1,1,0,1,1,1]
     *     Output: 3
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        // 중간 저장값
        int termValue = 0;
        // 결과값
        int maxConsecutiveOnes = 0;
        // 반복
        for (int i = 0; i < nums.length; i++) {
            // 배열의 값이 1이면 중간 저장값에 저장
            if(nums[i] == 1) {
                termValue++;
            }
            // 배열의 값이 1이 아니면 중간 저장값과 결과값을 비교하여 큰 값을 결과값에 저장하고 중간 저장값을 0으로 초기화
            else {
                maxConsecutiveOnes = Math.max(maxConsecutiveOnes, termValue);
                termValue = 0;
            }
        }
        // 배열의 마지막 값이 1인 경우 중간 저장값이 0으로 초기화되지 않기 때문에 중간 저장값과 결과값 비교하여 큰 값을 결과값에 저장
        if (termValue != 0) {
            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, termValue);
        }
        return maxConsecutiveOnes;
    }

    public static void main(String[] args) {
        final int[] numArrays = {1, 1, 0, 1, 1, 1};
        MaxConsecutiveOnes maxConsecutiveOnes = new MaxConsecutiveOnes();
        final long beforeTime = System.currentTimeMillis();
        final int result = maxConsecutiveOnes.findMaxConsecutiveOnes(numArrays);
        final long afterTime = System.currentTimeMillis();
        System.out.println("결과 = " + result);
        System.out.println("수행시간 = " + (afterTime - beforeTime));
    }
}
