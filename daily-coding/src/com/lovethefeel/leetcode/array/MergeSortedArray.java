package com.lovethefeel.leetcode.array;

public class MergeSortedArray {
    /**
     * 두 개의 정수 배열 nums1과 nums2가 주어지며, 각각 nums1과 nums2의 원소 수를 나타내는 두 개의 정수 m과 n이 주어집니다.
     * nums1과 nums2를 감소하지 않는 순서로 정렬된 단일 배열로 병합합니다.
     * 마지막으로 정렬된 배열은 함수에 의해 반환되지 않고 배열 번호1 안에 저장되어야 합니다.
     * 이를 수용하기 위해 nums1은 m + n의 길이를 가지며, 여기서 첫 번째 m 원소는 병합되어야 할 원소를 나타내고 마지막 n 원소는 0으로 설정되며 무시되어야 한다.
     * nums2의 길이는 n입니다.
     * ex) Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     *    Output: [1,2,2,3,5,6]
     * ex) Input: nums1 = [1], m = 1, nums2 = [], n = 0
     *    Output: [1]
     * ex) Input: nums1 = [0], m = 0, nums2 = [1], n = 1
     *    Output: [1]
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int mIndex = m - 1;
        int nIndex = n - 1;

        while (mIndex >= 0 && mIndex >= 0) {
            if (nums1[mIndex] < nums2[nIndex]) {
                nums1[index--] = nums2[nIndex--];
            } else {
                nums1[index--] = nums1[mIndex--];
            }
        }

        while (nIndex >= 0) {
            nums1[index--] = nums2[nIndex--];
        }
    }

    public static void main(String[] args) {

    }
}
