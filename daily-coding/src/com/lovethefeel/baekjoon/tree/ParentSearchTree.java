package com.lovethefeel.baekjoon.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ParentSearchTree {
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
    static ArrayList<Integer>[] adj;
    static int[] parent;

    static void input() {
        N = scan.nextInt();
        adj = new ArrayList[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }
    }

    static void dfs(int x, int par) {
        for (int y: adj[x]) {
            if (y == par) {
                continue;
            }
            parent[y] = x;
            dfs(y, x);
        }
    }

    static void pro() {
        dfs(1, -1);
        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append('\n');
        }
        System.out.println("sb = " + sb.toString());
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
