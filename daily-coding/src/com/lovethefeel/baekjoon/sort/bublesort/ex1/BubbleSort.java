package com.lovethefeel.baekjoon.sort.bublesort.ex1;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1377
 */
public class BubbleSort {
    static int N;
    static int[] A;

    static FastReader scan = new FastReader();
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

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static void swap(int[] a, int f, int r) {
        int temp;
        temp = a[f];
        a[f] = a[r];
        a[r] = temp;
    }

    static void sort() {
        for (int i = 1; i <= N + 1; i++) {
            boolean isSwap = false;

            for (int j = 1; j <= N - i; j++) {
                if (A[j] > A[j + 1]) {
                    swap(A, j, j + 1);
                    isSwap = true;
                }
            }

            if (isSwap == false) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        input();
        sort();
    }
}
