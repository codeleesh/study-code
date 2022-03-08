package com.lovethefeel.codility.lesson2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).
 *
 * The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.
 *
 * Write a function:
 *
 * class Solution { public int[] solution(int[] A, int K); }
 *
 * that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.
 *
 * For example, given
 *
 *     A = [3, 8, 9, 7, 6]
 *     K = 3
 * the function should return [9, 7, 6, 3, 8]. Three rotations were made:
 *
 *     [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
 *     [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
 *     [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
 * For another example, given
 *
 *     A = [0, 0, 0]
 *     K = 1
 * the function should return [0, 0, 0]
 *
 * Given
 *
 *     A = [1, 2, 3, 4]
 *     K = 4
 * the function should return [1, 2, 3, 4]
 *
 * Assume that:
 *
 * N and K are integers within the range [0..100];
 * each element of array A is an integer within the range [−1,000..1,000].
 * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 *
 * https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/
 */
public class CyclicRotation {
    static StringBuilder sb = new StringBuilder();

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    static int K;

    static int[] solution(int[] arr, int K) {
        int size = arr.length;
        int i = 0;
        boolean containZero = true;
        for (int j = 0; j < arr.length; j++) {
            if(arr[j] != 0) {
                containZero = false;
            }
        }
        if (containZero) {
            return arr;
        }
        while (i < K) {
            int lastNumber = arr[size-1];
            for (int j = size - 1; j>=1; j--) {
                arr[j] = arr[j - 1];
            }
            arr[0] = lastNumber;
            i++;
        }
        return arr;
    }

    static void input() {
        FastReader scan = new FastReader();
        K = scan.nextInt();
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr " + arr[i]);
        }
    }

    public static void main(String[] args) {
        input();
        int[] arr = {1, 2, 3, 4};
        int[] result = solution(arr, K);
        printArray(result);
    }
}
