package com.lovethefeel.codility.lesson1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.
 *
 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.
 *
 * Write a function:
 *
 * class Solution { public int solution(int N); }
 *
 * that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
 *
 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..2,147,483,647].
 *
 * https://app.codility.com/programmers/lessons/1-iterations/binary_gap/
 */
public class Iterations {

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

    static int N;
    static int[] arr, saved, result;

    static int solution(int n) {
        String binaryString = Integer.toBinaryString(n);
        int init = 0;
        arr = new int[binaryString.length()];
        for (int i = 0; i < binaryString.length(); i++) {
            int value = Character.getNumericValue(binaryString.charAt(i));
            arr[i] = value;
            if (arr[i] == 1) {
                init++;
            }
        }

        saved = new int[init];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                saved[index] = i;
                index++;
            }
        }

        result = new int[saved.length];
        for (int i = 0; i < saved.length-1; i++) {
            result[i] = saved[i+1] - saved[i] - 1;
        }

        return Arrays.stream(result).max().getAsInt();
    }

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
    }

    public static void main(String[] args) {
        input();

        int result = solution(N);
        System.out.println("result = " + result);
    }
}
