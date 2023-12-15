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
        File data = new File("src\\names.txt");
        Scanner file = new Scanner(data);

        // Declare instance variables
        Integer key;
        String name;
        int count = 0;
        double linearCollisionSum = 0, doubleCollisionSum = 0, chainCollisionSum = 0;

        // Initiate table
        Table<Integer, String> table = new Table<Integer, String>(241);
        TableDoubleHash<Integer, String> doubleTable = new TableDoubleHash<Integer, String>(241);
        TableChainHash<Integer, String> chainTable = new TableChainHash<Integer, String>(241);

        // Run collision test
        System.out.println();
        System.out.println("Attempt    linear   double   chain");
        while (file.hasNextLine()) {
            count++;
            name = file.next();
            key = file.nextInt();

            // Add data to table
            table.put(key, name);
            doubleTable.put(key, name);
            chainTable.put(key, name);

            // Format ints into strings with the proper spacing
            String countStr = formattedNumString(count);
            String tableCollisionStr = formattedNumString(table.getCollisionCount());
            String doubleTableCollisionStr = formattedNumString(doubleTable.getCollisionCount());
            String chainTableCollisionStr = formattedNumString(chainTable.getCollisionCount());

            // Print collision report on each input into table
            System.out.print(countStr);
            System.out.print(tableCollisionStr);
            System.out.print(doubleTableCollisionStr);
            System.out.println(chainTableCollisionStr);

            // Add collision count
            linearCollisionSum += table.getCollisionCount();
            doubleCollisionSum += doubleTable.getCollisionCount();
            chainCollisionSum += chainTable.getCollisionCount();
        }

        // Print result
        // ...CollisionSum/count returns average since count will be 200 (the total number of elements)
        System.out.println();
        System.out.println("Average for Linear: " + linearCollisionSum/count);
        System.out.println("Average for Double: " + doubleCollisionSum/count);
        System.out.println("Average for Chain: " + chainCollisionSum/count);
    }

    // Formats integers [0-999] and returns them as a string with proper spacing for collision report printing
    // Takes a parameter called num of type int that is to be formatted
    private static String formattedNumString(int num) {
        String numString = "";

        if (num >= 0 && num < 10) {
            numString = "  " + num + "      ";
        } else if (num < 100) {
            numString = " " + num + "      ";
        } else {
            numString = num + "      ";
        }

        return numString;
    }
}

