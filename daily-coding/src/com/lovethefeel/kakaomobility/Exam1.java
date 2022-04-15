package com.lovethefeel.kakaomobility;

import java.io.*;
import java.util.StringTokenizer;

/**
 * John Doe, Peter Parker, Mary Jane Watson-Parker, James Doe, John Elvis Doe, Jane Doe, Penny Parker
 * example
 */
public class Exam1 {
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

    static String S, C;
    static String[] names, emails;

    static void input() {
        FastReader scan = new FastReader();
        S = scan.nextLine();
        C = scan.next();
    }

    static String getInitial(String...str) {
        String result = "";
        for (String s : str) {
            result += s.substring(0, 1).toLowerCase();
        }
        return result;
    }

    static String getLast(String str) {
        String changeStr = str.replaceAll("-", "").toLowerCase();
        if (changeStr.length() < 8) {
            return changeStr;
        }
        return changeStr.substring(0, 8);
    }

    static String getEmailForm(String C) {
        return "@" + C.toLowerCase() + ".com";
    }

    static String proc(String S, String C) {
        names = S.split(", ");
        emails = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            int count = 0;
            String name = names[i];
            for (int j = 0; j < name.length(); j++) {
                if (name.charAt(j) == ' ') {
                    count++;
                }
            }

            String[] temp = name.split(" ");
            String email = "";
            if (count == 1) {
                email = getInitial(temp[0]) + getLast(temp[1]);
            } else if (count == 2) {
                email = getInitial(temp[0], temp[1]) + getLast(temp[2]);
            }
            emails[i] = email;
        }

        for (int i = 0; i < emails.length; i++) {
            int count = 0;
            for (int j = 0; j < emails.length; j++) {
                if (emails[i].equals(emails[j])) {
                    if (i == j) continue;
                    emails[i] += count;
                    count++;
                }
            }
            emails[i] += getEmailForm(C);
        }
        return print();
    }

    static String print() {
        String result = "";
        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]).append(" <").append(emails[i]).append(">, ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    public static void main(String[] args) {
        input();
        proc(S, C);
        System.out.println("args = " + print());
    }
}
