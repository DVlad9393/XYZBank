package XYZBankTest.model;

import java.time.LocalDate;

public class TestUtils {

    int dayOfMonth = LocalDate.now().getDayOfMonth();
    int n = dayOfMonth + 1;

    int fibonacciNumber = fibonacci(n);

    public static int fibonacci(int n) {
        int fib[] = new int[n+1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n-1];
    }

    public String getFibonacciSum() {

        return String.valueOf(this.fibonacciNumber);

    }

}
