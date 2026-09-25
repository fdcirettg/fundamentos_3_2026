package fd_3.fundamentos_3_2026.Expedientespersonas.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Personas {
    private String nombre;
    private String expediente;
    private int edad;

    public Personas(String nombre, String expediente, int edad) {
        this.nombre = nombre;
        this.expediente = expediente;
        this.edad = edad;
    }


    public String getNombre() {
        return nombre;
    }

    public String getExpediente() {
        return expediente;
    }

    public int getEdad() {
        return edad;
    }
}

public class ExpedientesPersonas {
    public static void main(String[] args) {
        String nombreArchivo = "fd_3/fundamentos_3_2026/Expedientespersonas/listado_personas_expediente.csv"; // Cambia esto al nombre de tu archivo
        ArrayList<Personas> personas = cargaArchivoPersonas(nombreArchivo);
        if (personas.size() > 10) {
            Personas personaEncontrada = personas.get(10);
            System.out.println("Nombre: " + personaEncontrada.getNombre());
            System.out.println("Expediente: " + personaEncontrada.getExpediente());
            System.out.println("Edad: " + personaEncontrada.getEdad());
        } else {
            System.out.println("No hay suficientes registros en el archivo.");
        }
    }

    public static ArrayList<Personas> cargaArchivoPersonas(String nombreArchivo) {
        ArrayList<Personas> personas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String expediente = partes[1].trim();
                    int edad = Integer.parseInt(partes[2].trim());
                    personas.add(new Personas(nombre, expediente, edad));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return personas;
    }
}

