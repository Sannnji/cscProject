import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File data = new File("./src/names.txt");
        Scanner file = new Scanner(data);

        Integer key;
        String name;
        int count = 0;
        double linearAve = 0, doubleAve = 0, chainAve = 0;

        Table<Integer, String> table = new Table<Integer, String>(241);
//        TableDoubleHash<Integer, String> doubleTable = new TableDoubleHash<Integer, String>(241);
//        TableChainHash<Integer, String> chainTable = new TableChainHash<Integer, String>(241);

        System.out.println("Attempt    linear    double    chain");
        while (file.hasNextLine()) {
            count++;
            name = file.next();
            key = file.nextInt();
            table.put(key, name);

            System.out.print(count + "            ");
            System.out.println(table.getCollisionCount() + "            ");

            linearAve += table.getCollisionCount();
        }

        System.out.println();
        System.out.println("Average for Linear: " + linearAve/200);
    }
}