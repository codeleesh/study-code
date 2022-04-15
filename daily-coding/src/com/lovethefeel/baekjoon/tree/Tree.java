package com.lovethefeel.baekjoon.tree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Tree {
    static ParentSearchTree.FastReader scan = new ParentSearchTree.FastReader();
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

    static int N, root, erased;
    static ArrayList<Integer>[] child;
    static int[] leaf;

    static void input() {
        N = scan.nextInt();
        child = new ArrayList[N];
        leaf = new int[N];
        for (int i = 0; i < N; i++) {
            child[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            int par = scan.nextInt();
            if (par == -1) {
                root = i;
                continue;
            }
            child[par].add(i);
        }
        erased = scan.nextInt();
    }

    static void dfs(int x) {
        if (child[x].isEmpty()) {
            leaf[x] = 1;
        }
        for (int y: child[x]) {
            dfs(y);
            leaf[x] += leaf[y];
        }
    }

    static void pro() {
        for (int i = 0; i < N; i++) {
            if (child[i].contains(erased)) {
                child[i].remove(child[i].indexOf(erased));
            }
        }
        if (root != erased) {
            dfs(root);
        }
        System.out.println("leaf = " + leaf[root]);
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
