package com.lovethefeel.baekjoon.sort.daily01;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [국영수](https://www.acmicpc.net/problem/10825)
 */
public class KoreanEnglishMath {
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

    static class Elem implements Comparable<Elem> {

        public String name;
        public int korean, english, math;

        @Override
        public int compareTo(Elem other) {
            // TODO
            // 국어, 영어, 수학, 이름 값을 가지고 정렬 기준 정의 하기
            // 국어 점수 내림차순
            if (korean != other.korean) {
                return other.korean- korean;
            }
            // 국어 점수가 같은 경우, 영어 점수 오름차순
            if (english != other.english) {
                return english - other.english;
            }
            // 영어 점수가 같은 경우, 수학 점수 내림차순
            if (math != other.math) {
                return other.math - math;
            }
            // 수학 점수가 같은 경우, 이름으로 오름차순
            return name.compareTo(other.name);
        }
    }

    static int N;
    static Elem[] a;

    static void input() {
        N = scan.nextInt();
        a = new Elem[N];
        for (int i = 0; i < N; i++) {
            a[i] = new Elem();
            a[i].name = scan.next();
            a[i].korean = scan.nextInt();
            a[i].english = scan.nextInt();
            a[i].math = scan.nextInt();
        }
    }

    static void pro() {
        // TODO
        // 기준을 통해 정렬하기
        Arrays.sort(a);

        // 정답 출력하기
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i].name).append('\n');
        }
        System.out.println("sb = " + sb.toString());
    }

    public static void main(String[] args) {
        input();
        pro();
    }
}
