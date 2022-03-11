package com.lovethefeel.baekjoon.bruteforce.daily01;

import java.io.*;
import java.util.StringTokenizer;

/**
 * - N개 중 중복을 허용해서
 *   - M개를 순서 있게 나열하기
 *
 * [N과 M(3)](https://www.acmicpc.net/problem/15651)
 */
public class NAndM {
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

    static int N, M;
    static int[] selected;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M + 1];
    }

    // Recurrence Function (재귀 함수)
    // 만약 M개를 전부 고름 -> 조건에 맞는 탐색을 한 가지 성공한 것
    // 아직 M개를 고르지 않음 -> k 번째부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 시도한다.
    static void rec_func(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            // 1~N 까지를 k 번 원소로 한 번씩 정하고,
            // 매번 k+1 번부터 M 번 원소로 재귀호출 해주기
            for (int cand = 1; cand <= N; cand++) {
                selected[k] = cand;
                rec_func(k+1);
                selected[k] = 0;
            }
        }
    }

    public static void main(String[] args) {
        input();

        rec_func(1);
        System.out.println("sb = " + sb.toString());
    }
}
