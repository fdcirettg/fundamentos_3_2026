package fd_3.fundamentos_3_2026.NombresArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
class nameCount {
    String name;
    int count;
    public nameCount(String name) {
        this.name = name;
        this.count = 1;
    }
    public void incrementCount() {
        this.count++;
    }
    public String getName() {
        return this.name+" (" + this.count + ")";
    }
}
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
        ArrayList<nameCount> countedNames = new ArrayList<>();
        int pos = 0; // track the position of the name 
        for (String name : names) {
            String[] splitName = name.split(" ");
            boolean found = false;
            for (nameCount nc : countedNames) {
                if (nc.name.equals(splitName[pos])) {
                    nc.incrementCount();
                    found = true;
                    break;  
                }
            }
            if (!found) {
                countedNames.add(new nameCount(splitName[pos]));
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (nameCount nc : countedNames) {
            result.add(nc.getName());
        }
        return result;
    }
}
