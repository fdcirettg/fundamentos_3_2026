public class grafos {
    public static void main(String[] args) {
        String texto = cargaArchivo("/Users/federicocirettgalan/Documents/proyectos_vscode/fundamentos_computacion3/fd_3/fundamentos_3_2026/grafos/grafo_salon.txt");
        System.out.println("Contenido del archivo:\n" + texto);
    }
    public static String cargaArchivo(String ruta) {
        String contenido = "";
        try {
            java.io.File archivo = new java.io.File(ruta);
            java.util.Scanner scanner = new java.util.Scanner(archivo);
            while (scanner.hasNextLine()) {
                contenido += scanner.nextLine() + "\n";
            }
            scanner.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + ruta);
        } 
        return contenido;
    }
}
