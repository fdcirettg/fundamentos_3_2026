package fd_3.fundamentos_3_2026.HanoiTowers.src;

import java.util.Scanner;

// Author: Federico Cirett Galán
// Date: 2026-09-15
public class HanoiTowers {
    static Scanner sc = new Scanner(System.in);
    static int numDiscos = 3;
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
    public static void jugarManual() {}
    public static void mostrarSolucion() {}
}
