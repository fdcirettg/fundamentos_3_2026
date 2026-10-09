import java.util.HashMap;
import java.util.Map;

public class Recursividad {
    static Map<Integer, Long> memo = new HashMap<>();
    public static void main(String[] args) {
        // Tiempo inicial
        int[] arr = {7, 15, 30, 45, 50};
        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            long sTime = System.nanoTime();
            System.out.println("Fibonacci de " + arr[i] + ": " + fib(arr[i]));
            long endTime = System.nanoTime();
            System.out.println("Tiempo de ejecución: " + (endTime - sTime) / 1000000.0 + " ms");
        }
        for (int i = 0; i < arr.length; i++) {
            long sTime = System.nanoTime();
            System.out.println("Fibonacci de " + arr[i] + ": " + fib(arr[i]));
            long endTime = System.nanoTime();
            System.out.println("Tiempo de ejecución: " + (endTime - sTime) / 1000000.0 + " ms");
        }
        //System.out.println("Factorial de 32: " + factorial(32));
        // Tiempo final

    }
    public static long fib(int n) {
        if (n <= 1) return n;
        Long guardado = memo.get(n);
        if (guardado != null) return guardado;
        long resultado = fib(n - 1) + fib(n - 2);
        memo.put(n, resultado);
        return resultado;
    }
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
