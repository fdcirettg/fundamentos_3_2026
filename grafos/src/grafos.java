class GrafoArrays {
    private String[] vertices;
    private int numVertices;
    private int[][] matrizAdyacencia;

    public GrafoArrays(String origen) {
        creaTablaVertices(origen);
        matrizAdyacencia = new int[numVertices][numVertices];
        populateMatrizAdyacencia(origen);
    }

    private void creaTablaVertices(String origen) {
        String[] lineas = origen.split("\\R");
        vertices = new String[lineas.length * 2];
        numVertices = 0;
        for (String linea : lineas) {
            String arista = linea.trim();
            if (arista.startsWith("{")) {
                arista = arista.substring(1).trim();
            }
            int etiqueta = arista.indexOf('[');
            if (etiqueta >= 0) {
                arista = arista.substring(0, etiqueta);
            }
            String[] extremos = arista.split("--", 2);
            if (extremos.length == 2) {
                agregarVertice(extremos[0].trim());
                agregarVertice(extremos[1].trim());
            }
        }
        // Crear método para agregar aristas después de crear la tabla de vértices populateMatrizAdyacencia(origen);
             // Dividir el archivo de texto en lineas y procesar cada línea para agregar aristas 
             // Cada linea se separa en dos vértices y un peso, luego se llama al método agregarArista para agregarlas a la matriz de adyacencia
             // Crear un método para desplegar la Matriz de adyacencia
    }
    private void populateMatrizAdyacencia(String origen) {
        String[] lineas = origen.split("\\R");
        for (String linea : lineas) {
            String arista = linea.trim();
            if (arista.startsWith("{")) {
                arista = arista.substring(1).trim();
            }
            int etiqueta = arista.indexOf('[');
            int peso = 1; // Peso por defecto
            if (etiqueta >= 0) {
                String pesoStr = arista.substring(etiqueta + 1, arista.indexOf(']', etiqueta));
                try {
                    peso = Integer.parseInt(pesoStr.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Peso inválido en la línea: " + linea);
                }
                arista = arista.substring(0, etiqueta);
            }
            String[] extremos = arista.split("--", 2);
            if (extremos.length == 2) {
                agregarArista(extremos[0].trim(), extremos[1].trim(), peso);
            }
        }
    }
    private void agregarVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return;
            }
        }
        vertices[numVertices] = vertice;
        numVertices++;
    }

    public void display() {
        System.out.println("Vértices del grafo:");
        displayVertices();
        displayMatrizAdyacencia();
    }

    private void displayVertices() {
        for (int i = 0; i < numVertices; i++) {
            System.out.println(vertices[i]);
        }
    }
    private void displayMatrizAdyacencia() {
        System.out.println("Matriz de Adyacencia:");
        // Imprimir encabezado de columnas
        System.out.print("    ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print("   " +vertices[i].substring(0,3) );
        }
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            System.out.print(vertices[i].substring(0, 4) + "    ");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrizAdyacencia[i][j] + "     ");
            }
            System.out.println();
        }
    }
    private int obtenerIndiceVertice(String vertice) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertice)) {
                return i;
            }
        }
        return -1;
    }
    private void agregarArista(String vertice1, String vertice2, int peso) {
        int indice1 = obtenerIndiceVertice(vertice1);
        int indice2 = obtenerIndiceVertice(vertice2);
        if (indice1 >= 0 && indice2 >= 0) {
            matrizAdyacencia[indice1][indice2] = peso;
            matrizAdyacencia[indice2][indice1] = peso; // Para grafos no dirigidos
        }
    }
    public void DFS(String verticeInicial, String VerticeFinal){
        boolean[] visitado = new boolean[numVertices];
        int indiceInicial = obtenerIndiceVertice(verticeInicial);
        int indiceFinal = obtenerIndiceVertice(VerticeFinal);
        if (indiceInicial == -1 || indiceFinal == -1) {
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return;
        }
        System.out.println("Recorrido DFS desde " + verticeInicial + " hasta " + VerticeFinal + ":");
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(indiceInicial);
        while (!stack.isEmpty()) {
            int indiceActual = stack.pop();
            if (!visitado[indiceActual]) {
                visitado[indiceActual] = true;
                System.out.print(vertices[indiceActual] + " ");
                if (indiceActual == indiceFinal) {
                    System.out.println("\nSe ha alcanzado el vértice final.");
                    return; 
                }
            }
            // Agregar vecinos no visitados al stack
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[indiceActual][i] != 0 && !visitado[i]) {
                    stack.push(i);
                }
            }
        }
    }
    public void BFS(String verticeInicial, String VerticeFinal){
        // Implementación del recorrido Breadth-First Search (BFS)
        boolean[] visitado = new boolean[numVertices];
        int indiceInicial = obtenerIndiceVertice(verticeInicial);
        int indiceFinal = obtenerIndiceVertice(VerticeFinal);
        if (indiceInicial == -1 || indiceFinal == -1) {
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return;
        }
        System.out.println("Recorrido BFS desde " + verticeInicial + " hasta " + VerticeFinal + ":");
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.add(indiceInicial); // Agregar el vértice inicial a la cola
        while (!queue.isEmpty()) {
            int indiceActual = queue.poll();
            if (!visitado[indiceActual]) {
                visitado[indiceActual] = true;
                System.out.print(vertices[indiceActual] + " ");
                if (indiceActual == indiceFinal) {
                    System.out.println("\nSe ha alcanzado el vértice final.");
                    return; 
                }
            }
            // Agregar vecinos no visitados a la cola
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[indiceActual][i] != 0 && !visitado[i]) {
                    queue.add(i);
                }
            }
        }
    }
}
public class grafos {
    public static void main(String[] args) {
        String texto = cargaArchivo("/Users/federicocirettgalan/Documents/proyectos_vscode/fundamentos_computacion3/fd_3/fundamentos_3_2026/grafos/grafo_salon2.txt");
        System.out.println("Contenido del archivo:\n" + texto);
        GrafoArrays grafo = new GrafoArrays(texto);
        grafo.display();
        grafo.DFS("Carol", "Diego");
        grafo.BFS("Carol", "Diego");
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
