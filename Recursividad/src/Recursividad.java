import java.util.HashMap;
import java.util.Map;

class Nodo {
    int valor;
    Nodo izquierda;
    Nodo derecha;
    public Nodo(int valor) {
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
    }
}

class ArbolBinario {
    Nodo raiz;
    public ArbolBinario() {
        this.raiz = null;
    }
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }
    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }
        if (valor < nodo.valor) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = insertarRecursivo(nodo.derecha, valor);
        }
        return nodo;
    }
    public void preOrden() {
        preOrdenRecursivo(raiz);
    }

    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preOrdenRecursivo(nodo.izquierda);
            preOrdenRecursivo(nodo.derecha);
        }
    }
    public void inOrden() {
        inOrdenRecursivo(raiz);
    }
    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierda);
            System.out.print(nodo.valor + " ");
            inOrdenRecursivo(nodo.derecha);
        }
    }
}

public class Recursividad {
    static Map<Integer, Long> memo = new HashMap<>();
    public static void main(String[] args) {
        // Tiempo inicial
        //int[] arr = {7, 15, 30, 45, 50};
        /*
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
        }*/
        //System.out.println("Factorial de 32: " + factorial(32));
        // Tiempo final
        int[] array  = {20,21,22,23,24,25,26,27,28,29,30,102,55,21,7,225,633,4};
        int x = 30;
        int indice = buscar(array, x, 0, array.length - 1);
        if (indice != -1) {
            System.out.println("Elemento " + x + " encontrado en el índice: " + indice);
        } else {
            System.out.println("Elemento " + x + " no encontrado.");
        }
        // Creación de Arbol Binario
        ArbolBinario ArbolB = new ArbolBinario();
        for(int a: array) {
            ArbolB.insertar(a);
        }
        ArbolB.preOrden();
        System.out.println();
        ArbolB.inOrden();

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
    static int buscar(int[] a, int x, int inicio, int fin) {
        if (inicio > fin) {
            return -1; // No encontrado
        }
        int medio = (inicio + fin) / 2;
        if (a[medio] == x) {
            return medio; // Elemento encontrado
        } else if (a[medio] > x) {
            return buscar(a, x, inicio, medio - 1); // Buscar en la mitad izquierda
        } else {
            return buscar(a, x, medio + 1, fin); // Buscar en la mitad derecha
        }
    }
}
