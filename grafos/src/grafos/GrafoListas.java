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
}
    

