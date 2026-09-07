package fd_3.fundamentos_3_2026.InvierteCadenas.src;

import java.util.ArrayDeque;
import java.util.Deque;

public class InvierteCadenas {
    public static void main(String[] args) {
        String cadena = args.length > 0 ? args[0] : "Hola, mundo!";
        String cadenaInvertida = invertirCadena(cadena);
        System.out.println("Cadena original: " + cadena);
        System.out.println("Cadena invertida: " + cadenaInvertida);
    }
    public static String invertirCadena(String cadena) {
        Deque<String> pila = new ArrayDeque<>();
        String[] palabras = cadena.split(" ");
        for (String palabra : palabras) {
            pila.push(palabra);
        }
        StringBuilder cadenaInvertida = new StringBuilder();
        while (!pila.isEmpty()) {
            cadenaInvertida.append(pila.pop()).append(" ");
        }
        return cadenaInvertida.toString().trim();
    }

}
