package com.lovethefeel.baekjoon.bruteforce.daily07;

import java.io.*;
import java.util.StringTokenizer;

/**
 * [부분 수열의 합](https://www.acmicpc.net/problem/1182)
 */
public class SumOfSubsequences {
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

    static int N, S, ans;
    static int[] nums;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        S = scan.nextInt();
        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = scan.nextInt();
        }
    }

    // k번째 원소를 포함시킬지 정하는 함수
    // value := k-1 번째 원소까지 골라진 원소들의 합
    static void rec_func(int k, int value) {
        if (k == N + 1) { // 부분 수열을 하나 완성시킨 상태
            // value가 S랑 같은지 확인하기
            if (value == S) {
                ans++;
            }
        } else {
            // k 번째 원소를 포함시킬지 결정하고 재귀호출해주기
            // Include
            rec_func(k + 1, value + nums[k]);
            // Not Include
            rec_func(k + 1, value);
        }
    }

    public static void main(String[] args) {
        input();
        // 1 번째 원소부터 M 번째 원소를 조건에 맞게 고르는 모든 방법을 탐색해줘
        rec_func(1, 0);
        // ans 가 정말 " 부분집합"만 다루는 지 확인하기
        if (S == 0) {
            ans--;
        }
        System.out.println("ans = " + ans);
    }
}
