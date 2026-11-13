package grafos;
import java.util.ArrayList;
public class Nodos {
    String nombre;
    ArrayList<Nodos> adyacentes;
    int peso;
    boolean visitado;
    public Nodos(String nombre) {
        this.nombre = nombre;
        this.peso = 0;
        this.visitado = false;
        this.adyacentes = new ArrayList<>();
    }
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    public boolean isVisitado() {
        return visitado;
    }
    public void agregarAdyacente(Nodos nodo, int peso) {
        adyacentes.add(nodo);
        nodo.peso = peso;
    }
    public void display() {
        System.out.print(nombre);
        System.out.print("| Adyacentes: ");
        for (Nodos nodo : adyacentes) {
            System.out.print(nodo.nombre + ": " + nodo.peso + " ");
        }
        System.out.println();
    }
    
    public String getNombre() {
        return nombre;
    }
    public ArrayList<Nodos> getAdyacentes() {
        return adyacentes;
    }
    public int getPeso() {
        return peso;
    }
}
