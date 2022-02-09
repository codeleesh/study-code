package com.lovethefeel.leetcode.array;

public class FindNumberswithEvenNumberofDigits {

    /**
     * 짝수 숫자로 숫자 찾기
     * 정수의 배열 번호가 지정되면 짝수 숫자를 포함하는 정수 수를 반환합니다.
     * 예시) Input: nums = [12,345,2,6,7896]
     *      Output: 2
     * @param nums
     * @return
     */
    public int findNumbers(int[] nums) {
        // 결과값을 저장할 변수
        int count = 0;

        // 배열의 첫번째부터 순회
        for (int i = 0; i < nums.length; i++) {
            // 정수의 사이즈 구하기
            int length = (int)(Math.log10(nums[i])+1);
            // 정수의 사이즈를 2로 나누어서 나머지가 0이면 짝수
            if (length % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final int[] numArrays = {12,345,2,6,7896};
        final FindNumberswithEvenNumberofDigits fnend = new FindNumberswithEvenNumberofDigits();
        final long beforeTime = System.currentTimeMillis();
        final int result = fnend.findNumbers(numArrays);
        final long afterTime = System.currentTimeMillis();
        System.out.println("결과 = " + result);
        System.out.println("수행시간 = " + (afterTime - beforeTime));

        // 1
        System.out.println((int) Math.log10(10));
        // 1
        System.out.println((int) Math.log10(99));
    }
}
