package com.lovethefeel.leetcode.array;

public class RemoveElement {
    /**
     * 요소 제거
     * 정수 배열 Num과 정수 val이 주어지면 num의 모든 val을 in-place에서 제거합니다.
     * 원소의 상대적인 순서가 변경될 수 있습니다. 일부 언어에서는 배열 길이를 변경할 수 없으므로 결과를 배열 번호의 첫 번째 부분에 배치해야 합니다.
     * 더 형식적으로, 중복을 제거한 후에 k개의 원소가 있다면, 숫자의 첫 번째 k개의 원소는 최종 결과를 가져야 한다.
     * 첫 번째 k 원소 뒤에 무엇을 남겨두든 중요하지 않습니다. 첫 번째 k개의 숫자 슬롯에 최종 결과를 넣은 후 k를 반환합니다.
     * 다른 배열에 추가 공간을 할당하지 마십시오. O(1) 추가 메모리로 입력 배열을 수정하여 이 작업을 수행해야 합니다.
     * ex) Input: nums = [3,2,2,3], val = 3
     *    Output: 2, nums = [2,2,_,_]
     * ex) Input: nums = [0,1,2,2,3,0,4,2], val = 2
     *    Output: 5, nums = [0,1,4,0,3,_,_,_]
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        // val과 매칭되지 않을때 증가시킬 인덱스 변수 생성
        int index = 0;

        // nums을 순회하며
        for (int i = 0; i < nums.length; i++) {
            // nums 배열의 값과 val 동일하면 넘어가고 동일하지 않다면
            if (nums[i] != val) {
                // 이동없이 인덱스 증가
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public static void main(String[] args) {
        final int[] numArrays = {3,2,2,3};
        final int val = 3;

        final RemoveElement re = new RemoveElement();
        final long beforeTime = System.currentTimeMillis();
        re.removeElement(numArrays, val);
        final long afterTime = System.currentTimeMillis();
        System.out.println("수행시간 = " + (afterTime - beforeTime));
    }
}
