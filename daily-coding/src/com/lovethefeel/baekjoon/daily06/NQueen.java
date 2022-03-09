package com.lovethefeel.baekjoon.daily06;

import java.io.*;
import java.util.StringTokenizer;

public class NQueen {
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

    static int N, ans;
    static int[] col;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        col = new int[N + 1];
    }

    static boolean attackable(int r1, int c1, int r2, int c2) {
        if (c1 == c2) {
            return true;
        }
        // 행 - 열 이 같은 경우 왼쪽 위 대각선 공격이 가능
        if (r1 - c1 == r2 - c2) {
            return true;
        }
        // 행 + 열 이 같은 경우 오른쪽 위 대각선 공격이 가능
        if (r1 + c1 == r2 + c2) {
            return true;
        }
        return false;
    }

    static boolean validity_check() {
        for (int i = 1; i <= N; i++) {
            // (i, col[i]
            for (int j = 1; j < i; j++) {
                // (j, col[j]
                if (attackable(i, col[i], j, col[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    static void rec_func(int row) {
        if (row == N + 1) {
            ans++;
        } else {
            for (int c = 1; c <= N; c++) {
                boolean possible = true;
                // if (validity_check()) {
                for (int i = 1; i <= row - 1; i++) {
                    // (i, col[i]
                    if (attackable(row, c, i, col[i])) {
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    col[row] = c;
                    rec_func(row + 1);
                    col[row] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();

        rec_func(1);
        System.out.println("ans = " + ans);
    }
}
