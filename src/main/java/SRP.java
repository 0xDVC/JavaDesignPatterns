/*
 * SRP - Single Responsibility Principle
 * A class should have only one reason to change, meaning that a class should have
 * only one job or responsibility.
 * Essentially, maintain a separation of concerns
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void resetCount() {
        Journal.count = 0;
    }


    public void addEntry(String text) {
        entries.add(
                String.format("%d: %s", ++count, text)
        );
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    public void save(String filename) throws FileNotFoundException{
        try(PrintStream out = new PrintStream(filename)) {
            out.println(this);
        }
    }
}

class Persistence {

    public void saveToFile (Journal journal,
                            String filename,
                            boolean overwrite) throws FileNotFoundException {

       if(overwrite || new File(filename).exists()) {
           try(PrintStream out = new PrintStream(filename)) {
               out.println(journal.toString());
           }
       }
    }

    public Journal load(String filename) throws FileNotFoundException {
        Journal journal = new Journal();

        try(Scanner scanner = new Scanner(new File(filename))) {
            journal.resetCount();
            while(scanner.hasNextLine()) {
                String text = scanner.nextLine();
                int index = text.indexOf(":");
                text = text.substring(index + 2);
                journal.addEntry(text);
            }
        }

        return journal;
    }
}
class SRP {
    public static void main(String[] args) throws FileNotFoundException {
        Journal j = new Journal();
        j.addEntry("I ate porridge this morning");
        j.addEntry("I am planning to eat Spaghetti later");
        j.addEntry("Maybe this whole thing is such a great idea");
        j.removeEntry(2);
//        j.save("journal.txt");

        Journal j2 = new Persistence().load("journal.txt");
//        System.out.println(j2);

//        j.addEntry("Testing the persistence here");
//        j.save("journal.txt");
//        j2 = new Persistence().load("journal.txt");
//        System.out.println(j2);

        Journal j3 = new Journal();
        Persistence p = new Persistence();
        p.saveToFile(j, "journal.txt", true);

        System.out.println(j);
    }
}



