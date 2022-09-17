package item6.domain;

public class AutoBoxing {

    private static long totalSum() {

        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }

        return sum;
    }

    public static void main(String[] args) {

        long result = AutoBoxing.totalSum();
    }
}
