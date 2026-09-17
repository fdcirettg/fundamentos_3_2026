package fd_3.fundamentos_3_2026.HanoiTowers.src;

import java.util.Scanner;
import java.util.Stack;

// Author: Federico Cirett Galán
// Date: 2026-09-15
public class HanoiTowers {
    static Scanner sc = new Scanner(System.in);
    static int numDiscos = 3;
    static Stack<Integer>[]  torres = new Stack[3]; //Arreglo de pilas para representar las torres
    public static void main(String[] args) {
        menuPrincipal();
    }
    static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingresa un numero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }
    public static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n===== TORRES DE HANOI =====");
            System.out.println("Número de discos actual: " + numDiscos);
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
        } while (opcion !=4);
    }
    static void inicializaTorres() {
        for (int i = 0; i < 3; i++) {
            torres[i] = new Stack<>();
        }
        // Inicializa la torre A con los discos
        for (int i = numDiscos; i >= 1; i--) {
            torres[0].push(i);
        }
    }
    static void despliegaTorre(int torre) {
        System.out.print("Torre " + (char)('A' + torre) + ": ");
        for (int disco : torres[torre]) {
            System.out.print(disco + " ");
        }
        System.out.println();
    }
    static void despliegaTorres() {
        for (int i = 0; i < 3; i++) {
            despliegaTorre(i);
        }
    }

    public static void elegirNumeroDiscos() {
        int n;
        do {
            System.out.print("Ingresa el numero de discos (3-8): ");
            n = leerEntero();
            if (n < 3 || n > 8) {
                System.out.println("Numero invalido. Debe estar entre 3 y 8.");
            }
        } while (n < 3 || n > 8);
        numDiscos = n;
    }
    public static void jugarManual() {
        inicializaTorres();
        
        while(true) {
            // Lógica para jugar manualmente
            despliegaTorres();
            
        }
    }
    public static String eligeTorre(String mensaje) {
        String torre;
        do {
            System.out.print(mensaje);
            torre = sc.next().toUpperCase();
            if (!torre.equals("A") && !torre.equals("B") && !torre.equals("C")) {
                System.out.println("Torre invalida. Debe ser A, B o C.");
            }
        } while (!torre.equals("A") && !torre.equals("B") && !torre.equals("C"));
        return torre;
    }
    public static void mostrarSolucion() {}
}
