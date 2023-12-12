/******************************************************************************
 * <CODE>HashTesting</CODE> is the main testing file to determine the average
 * number of conflicts per element placed into the table per table type.
 *
 * @author James Ji, Samuel Acquaviva
 *
 ******************************************************************************/

import java.io.*;
import java.util.Scanner;

public class HashTesting {
    public static void main(String[] args) throws FileNotFoundException {
        // Load file
        File data = new File("./src/names.txt");
        Scanner file = new Scanner(data);

        // Declare instance variables
        Integer key;
        String name;
        int count = 0;
        double linearAve = 0, doubleAve = 0, chainAve = 0;

        // Initiate table
        Table<Integer, String> table = new Table<Integer, String>(241);
        TableDoubleHash<Integer, String> doubleTable = new TableDoubleHash<Integer, String>(241);
        TableChainHash<Integer, String> chainTable = new TableChainHash<Integer, String>(241);

        // Run collision test
        System.out.println("Attempt    linear    double    chain");
        while (file.hasNextLine()) {
            count++;
            name = file.next();
            key = file.nextInt();

            table.put(key, name);
            doubleTable.put(key, name);
            chainTable.put(key, name);

            System.out.print(count + "             ");
            System.out.print(table.getCollisionCount() + "            ");
            System.out.print(doubleTable.getCollisionCount() + "            ");
            System.out.println(chainTable.getCollisionCount() + "            ");

            linearAve += table.getCollisionCount();
            doubleAve += doubleTable.getCollisionCount();
            chainAve += chainTable.getCollisionCount();
        }

        // Print result
        System.out.println();
        System.out.println("Average for Linear: " + linearAve/200);
        System.out.println("Average for Double: " + doubleAve/200);
        System.out.println("Average for Chain: " + chainAve/200);
    }
}