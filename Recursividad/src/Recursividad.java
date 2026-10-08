public class Recursividad {
    public static void main(String[] args) {
        // Tiempo inicial
        long startTime = System.nanoTime();
        System.out.println("Fibonacci de 7: " + fibonacci(7));
        //System.out.println("Factorial de 32: " + factorial(32));
        // Tiempo final
        long endTime = System.nanoTime();
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) / 1000000.0 + " ms"));
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
