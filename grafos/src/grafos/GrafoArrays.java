package grafos;
public class GrafoArrays {
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
    /**
     * Calcula una ruta de costo mínimo entre dos vértices con Dijkstra.
     * La matriz usa 0 para indicar que no existe una arista; por eso los pesos
     * de las aristas deben ser positivos.
     */
    public String caminoMasCorto(String verticeInicial, String verticeFinal) {
        int inicio = obtenerIndiceVertice(verticeInicial);
        int destino = obtenerIndiceVertice(verticeFinal);
        if (inicio == -1 || destino == -1) {
            return "Uno o ambos vértices no existen en el grafo.";
        }

        int[] distancia = new int[numVertices];
        int[] anterior = new int[numVertices];
        boolean[] definitivo = new boolean[numVertices];
        java.util.Arrays.fill(distancia, Integer.MAX_VALUE);
        java.util.Arrays.fill(anterior, -1);
        distancia[inicio] = 0;

        for (int paso = 0; paso < numVertices; paso++) {
            int actual = -1;
            for (int i = 0; i < numVertices; i++) {
                if (!definitivo[i] && distancia[i] != Integer.MAX_VALUE
                        && (actual == -1 || distancia[i] < distancia[actual])) {
                    actual = i;
                }
            }
            if (actual == -1) {
                break; // Los vértices restantes son inalcanzables.
            }
            if (actual == destino) {
                break; // La distancia del destino ya es definitiva.
            }
            definitivo[actual] = true;

            for (int vecino = 0; vecino < numVertices; vecino++) {
                int peso = matrizAdyacencia[actual][vecino];
                if (peso > 0 && !definitivo[vecino]
                        && distancia[actual] <= Integer.MAX_VALUE - peso) {
                    int nuevaDistancia = distancia[actual] + peso;
                    if (nuevaDistancia < distancia[vecino]) {
                        distancia[vecino] = nuevaDistancia;
                        anterior[vecino] = actual;
                    }
                }
            }
        }

        if (distancia[destino] == Integer.MAX_VALUE) {
            return "No existe un camino entre " + verticeInicial + " y " + verticeFinal + ".";
        }

        java.util.LinkedList<String> camino = new java.util.LinkedList<>();
        for (int actual = destino; actual != -1; actual = anterior[actual]) {
            camino.addFirst(vertices[actual]);
        }
        return "Camino más corto: " + String.join(" -> ", camino)
                + " (costo total: " + distancia[destino] + ")";
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
    public String caminoMasCortoBFS(String verticeInicial, String verticeFinal) {
       int inicio = obtenerIndiceVertice(verticeInicial);
       int destino = obtenerIndiceVertice(verticeFinal);
         if (inicio == -1 || destino == -1) {
              return "Uno o ambos vértices no existen en el grafo.";
         }
         //Creamos arreglos auxiliares para almacenar la distancia y el vértice anterior en el camino más corto
         int[] distancia = new int[numVertices];
         int[] anterior = new int[numVertices];
         boolean[] visitado = new boolean[numVertices];
         java.util.Arrays.fill(distancia, Integer.MAX_VALUE);
         java.util.Arrays.fill(anterior, -1);
         distancia[inicio] = 0;
         for (int paso = 0; paso < numVertices; paso++) {
             int actual = -1;
             // Seleccionamos el vértice no visitado con la distancia más corta
             for (int i = 0; i < numVertices; i++) {
                 if (!visitado[i] && distancia[i] != Integer.MAX_VALUE
                         && (actual == -1 || distancia[i] < distancia[actual])) {
                     actual = i;
                 }
             }
             if (actual == -1) {
                 break; // Los vértices restantes son inalcanzables.
             }
             if (actual == destino) {
                 break; // La distancia del destino ya es definitiva.
             }
             visitado[actual] = true;
             for (int vecino = 0; vecino < numVertices; vecino++) {
                 int peso = matrizAdyacencia[actual][vecino];
                 if (peso > 0 && !visitado[vecino]
                         && distancia[actual] <= Integer.MAX_VALUE - peso) {
                     int nuevaDistancia = distancia[actual] + peso;
                     if (nuevaDistancia < distancia[vecino]) {
                         distancia[vecino] = nuevaDistancia;
                         anterior[vecino] = actual;
                     }
                 }
             }
         }
            if (distancia[destino] == Integer.MAX_VALUE) {
                return "No existe un camino entre " + verticeInicial + " y " + verticeFinal + ".";
            }
            java.util.ArrayList<String> camino = new java.util.ArrayList<>();
            int actual = destino; // Reconstruimos el camino más corto
            while (actual != -1) {
                camino.add(0, vertices[actual]);
                actual = anterior[actual];
            }
            return "El camino más corto de " + verticeInicial + " a " + verticeFinal + " es: " + String.join(" -> ", camino);
    }
}
