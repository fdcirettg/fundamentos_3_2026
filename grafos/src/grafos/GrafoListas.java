package grafos;
import java.util.ArrayList;

public class GrafoListas {
    ArrayList<Nodos> nodos;
    public GrafoListas(String texto) {
        creaListaVertices(texto);
       agregaAristas(texto);
    }
    private void creaListaVertices(String texto) {
        String[] lineas = texto.split("\\R");
        nodos = new ArrayList<>();
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
}
    private void agregarVertice(String nombre) {
        for (Nodos nodo : nodos) {
            if (nodo.getNombre().equals(nombre)) {
                return; // El vértice ya existe
            }
        }
        nodos.add(new Nodos(nombre));
    }
    public void display() {
        for (Nodos nodo : nodos) {
            nodo.display();
        }
    }
    private void agregaAristas(String texto) {
        String[] lineas = texto.split("\\R");
        for (String linea : lineas) {
            String arista = linea.trim();
            if (arista.startsWith("{")) {
                arista = arista.substring(1).trim();
            }
            int etiqueta = arista.indexOf('[');
            int peso = 0;
            if (etiqueta >= 0) {
                String pesoStr = arista.substring(etiqueta + 7, arista.indexOf(']', etiqueta));
                pesoStr = pesoStr.replace("\"", "").trim();
                try {
                    peso = Integer.parseInt(pesoStr.trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir el peso a entero: " + pesoStr);
                }
                arista = arista.substring(0, etiqueta);
            }
            String[] extremos = arista.split("--", 2);
            if (extremos.length == 2) {
                Nodos nodo1 = new Nodos(extremos[0].trim());
                Nodos nodo2 = new Nodos(extremos[1].trim());
                if (nodo1 != null && nodo2 != null) {
                    agregarAdyacente(nodo1, nodo2, peso);
                    agregarAdyacente(nodo2, nodo1, peso); // Para grafos no dirigidos
                }
            }
        }
    }
    private void agregarAdyacente(Nodos nodoOrigen, Nodos nodoDestino, int peso) {
        for (Nodos n : nodos) {
            if (n.getNombre().equals(nodoOrigen.getNombre())) {
                n.agregarAdyacente(nodoDestino, peso);
                return;
            }
        }
    }

    private Nodos obtenerNodoPorNombre(String nombre) {
        for (Nodos nodo : nodos) {
            if (nodo.getNombre().equals(nombre)) {
                return nodo;
            }
        }
        return null; // Retorna null si no se encuentra el nodo
    } 
    public void DFS(String verticeInicial, String VerticeFinal){
        int numVertices = nodos.size();
        boolean[] visitado = new boolean[numVertices];
        Nodos nodoInicial = obtenerNodoPorNombre(verticeInicial);
        Nodos nodoFinal = obtenerNodoPorNombre(VerticeFinal);

        if (nodoInicial == null || nodoFinal == null) {
            System.out.println("Uno o ambos vértices no existen en el grafo.");
            return;
        }
        System.out.println("Recorrido DFS desde " + verticeInicial + " hasta " + VerticeFinal + ":");
        java.util.Stack<Nodos> stack = new java.util.Stack<>();
        stack.push(nodoInicial);
        while (!stack.isEmpty()) {
            Nodos nodoActual = stack.pop();
            if (!nodoActual.isVisitado()) {
                nodoActual.setVisitado(true);
                System.out.print(nodoActual.getNombre() + " ");
                if (nodoActual.getNombre().equals(nodoFinal.getNombre())) {
                    System.out.println("\nSe ha alcanzado el vértice final.");
                    return; 
                }
            }
            // Agregar vecinos no visitados al stack
            for (Nodos vecino : nodoActual.getAdyacentes()) {
                if (!vecino.isVisitado()) {
                    stack.push(vecino);
                }
            }
        }
    }

}
    

