/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebalinkedlist;

/**
 *
 * @author Federico Cirett Galán
 * @date   Aug 17, 2026
 */
class Student {
    String name;
    int score;
    Student next;
    public Student(String name, int score) {
        this.name = name;
        this.score= score;
        this.next = null;
    }
}
class LnkdLst {
    Student head;
    public LnkdLst() // Constructor
    {
        this.head = null;
    }
    //Agrega un nuevo estudiante al final de la lista
    public void add(String name, int score) {
        Student newStudent = new Student(name,score);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student current = head;
        while (current.next !=null){
            current = current.next;
        }
        current.next = newStudent;
    }
    public void print() {
        Student current = this.head;
        while (current !=null) {
            System.out.println(current.name +
                       " - " + current.score);
            current = current.next;
        }
    }
}
public class PruebaLinkedList {
    public static void main(String[] args) {
        LnkdLst lista = new LnkdLst();
        lista.add("Linus",90);
        lista.add("Ada",95);
        lista.add("Paul",78);
        lista.print();
    }
    
}
