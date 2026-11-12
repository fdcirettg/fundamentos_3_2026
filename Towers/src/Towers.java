package fd_3.fundamentos_3_2026.Towers.src;

import java.util.Scanner;
import java.util.Stack;

public class Towers {

    static final char[] NOMBRES = { 'A', 'B', 'C' };
    static Stack<Integer>[] torres;
    static int numDiscos = 0;
    static int contadorMovimientos = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();
    }

    // ---------------- MENU ----------------

    static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n===== TORRES DE HANOI =====");
            System.out.println("1. Elegir numero de discos (3-8)");
            System.out.println("2. Jugar manualmente");
            System.out.println("3. Mostrar solucion automatica");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    elegirNumeroDiscos();
                    break;
                case 2:
                    jugarManual();
                    break;
                case 3:
                    mostrarSolucion();
                    break;
                case 4:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 4);
    }

    static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un numero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static void elegirNumeroDiscos() {
        int n;
        do {
            System.out.print("Numero de discos (entre 3 y 8): ");
            n = leerEntero();
            if (n < 3 || n > 8) {
                System.out.println("Debe estar entre 3 y 8.");
            }
        } while (n < 3 || n > 8);

        numDiscos = n;
        inicializarTorres();
        System.out.println("Se configuraron " + n + " discos.");
        displayTowers();
    }

    static void inicializarTorres() {
        torres = new Stack[3];
        for (int i = 0; i < 3; i++) {
            torres[i] = new Stack<>();
        }
        for (int i = numDiscos; i >= 1; i--) {
            torres[0].push(i);
        }
        contadorMovimientos = 0;
    }

    // ---------------- VISUALIZACION ----------------

    // Devuelve las lineas de texto (de arriba hacia abajo) que representan UNA torre
    static String[] displayTower(int idx) {
        int ancho = 2 * numDiscos + 1;
        String[] lineas = new String[numDiscos + 2];

        // niveles[0] = disco mas abajo, niveles[numDiscos-1] = disco mas arriba (0 = vacio)
        int[] niveles = new int[numDiscos];
        for (int i = 0; i < torres[idx].size(); i++) {
            niveles[i] = torres[idx].get(i);
        }

        int fila = 0;
        for (int nivel = numDiscos - 1; nivel >= 0; nivel--) {
            int disco = niveles[nivel];
            String contenido;
            if (disco == 0) {
                contenido = centrar("|", ancho);
            } else {
                String bloque = repetir("=", 2 * disco - 1);
                contenido = centrar(bloque, ancho);
            }
            lineas[fila++] = contenido;
        }

        lineas[fila++] = repetir("-", ancho);
        lineas[fila] = centrar(String.valueOf(NOMBRES[idx]), ancho);
        return lineas;
    }

    // Muestra las 3 torres al mismo tiempo llamando a displayTower()
    static void displayTowers() {
        String[][] todas = new String[3][];
        for (int i = 0; i < 3; i++) {
            todas[i] = displayTower(i);
        }

        int filas = todas[0].length;
        for (int f = 0; f < filas; f++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(todas[i][f]).append("   ");
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    static String repetir(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    static String centrar(String texto, int ancho) {
        int espacios = ancho - texto.length();
        int izq = espacios / 2;
        int der = espacios - izq;
        return repetir(" ", izq) + texto + repetir(" ", der);
    }

    // ---------------- LOGICA DE MOVIMIENTOS ----------------

    static boolean moverDisco(int origen, int destino) {
        if (torres[origen].isEmpty()) {
            System.out.println("No hay discos en la torre " + NOMBRES[origen] + ".");
            return false;
        }
        if (!torres[destino].isEmpty() && torres[destino].peek() < torres[origen].peek()) {
            System.out.println("Movimiento invalido: no se puede poner un disco grande sobre uno chico.");
            return false;
        }
        int disco = torres[origen].pop();
        torres[destino].push(disco);
        contadorMovimientos++;
        System.out.println("Movimiento " + contadorMovimientos + ": disco " + disco
                + " de " + NOMBRES[origen] + " a " + NOMBRES[destino]);
        return true;
    }

    // ---------------- MODO MANUAL ----------------

    static void jugarManual() {
        if (numDiscos == 0) {
            System.out.println("Primero elige el numero de discos (opcion 1).");
            return;
        }

        inicializarTorres();
        System.out.println("Objetivo: mover todos los discos de la torre A a la torre C.");
        displayTowers();

        int minimo = (1 << numDiscos) - 1;

        while (torres[2].size() != numDiscos) {
            System.out.print("Torre origen (A/B/C): ");
            int origen = leerTorre();
            System.out.print("Torre destino (A/B/C): ");
            int destino = leerTorre();

            if (origen == destino) {
                System.out.println("Origen y destino no pueden ser iguales.");
                continue;
            }

            moverDisco(origen, destino);
            displayTowers();
        }

        System.out.println("Felicidades, resolviste el rompecabezas en "
                + contadorMovimientos + " movimientos.");
        System.out.println("El minimo de movimientos posible era: " + minimo);
    }

    static int leerTorre() {
        while (true) {
            String s = sc.next().toUpperCase();
            for (int i = 0; i < 3; i++) {
                if (String.valueOf(NOMBRES[i]).equals(s)) return i;
            }
            System.out.print("Torre invalida, ingresa A, B o C: ");
        }
    }

    // ---------------- SOLUCION AUTOMATICA ----------------

    static void mostrarSolucion() {
        if (numDiscos == 0) {
            System.out.println("Primero elige el numero de discos (opcion 1).");
            return;
        }

        inicializarTorres();
        System.out.println("Solucion automatica para " + numDiscos + " discos:");
        displayTowers();

        resolverHanoi(numDiscos, 0, 2, 1);

        System.out.println("Solucion completa en " + contadorMovimientos + " movimientos.");
    }

    static void resolverHanoi(int n, int origen, int destino, int auxiliar) {
        if (n == 0) return;
        resolverHanoi(n - 1, origen, auxiliar, destino);
        moverDisco(origen, destino);
        displayTowers();
        resolverHanoi(n - 1, auxiliar, destino, origen);
    }
}