package com.lovethefeel.baekjoon.sort.daily03;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [카드](https://www.acmicpc.net/problem/11652)
 */
public class Card {
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
    static int N;
    static long[] a;

    static void input() {
        N = scan.nextInt();
        a = new long[N];
        for (int i = 0; i < N; i++) {
            a[i] = scan.nextLong();
        }
    }

    static void pro() {
        // TODO: B 배열 정렬하기
        Arrays.sort(a, 0, N);

        // mode : 최빈값, modeCnt: 최빈값의 등장 횟수, curCnt: 현재 값(a[1])의 등장 횟수
        long mode = a[0];
        int modeCnt = 1, curCnt = 1;

        for (int i = 1; i < N; i++) {
            if (a[i] == a[i-1]) {
                curCnt++;
                if (curCnt > modeCnt) {
                    mode = a[i];
                    modeCnt = curCnt;
                } else {
                    modeCnt++;
                }
            } else {
                curCnt = 1;
            }
        }

        System.out.println("mode = " + mode);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
