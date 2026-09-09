package fd_3.fundamentos_3_2026.Rover.src;

public class Rover {
    public static void main(String[] args) {
        String cadena = args.length > 0 ? args[0] : "path1.txt";
        ArrayList<String> lineas = leerArchivo(cadena);
    }
    public static ArrayList<String> leerArchivo(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }
    
}
