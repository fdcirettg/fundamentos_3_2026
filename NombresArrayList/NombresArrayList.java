package fd_3.fundamentos_3_2026.NombresArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class NombresArrayList {
    public static void main(String[] args) {
        Path filePath = Paths.get("/Users/federicocirettgalan/Documents/proyectos_vscode/fundamentos_computacion3/fd_3/fundamentos_3_2026/NombresArrayList/listado.txt");
        ArrayList<String> namesList = readNamesFromFile(filePath.toString());
        printNames(namesList);
        ArrayList<String> countedNames = countNames(namesList);
        printNames(countedNames);
    }
    public static ArrayList<String>readNamesFromFile(String filePath) {
        ArrayList<String> names = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(Paths.get(filePath));
            for (String line : (Iterable<String>) lines::iterator) {
                names.add(line);
            }
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }
    public static void printNames(ArrayList<String> names) {
        for (String name : names) {
            System.out.println(name);
        }
    }
    public static ArrayList<String> countNames(ArrayList<String> names) {
        
    }
}
