import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        BTNode<Golfer> mainNode = new BTNode(5, null, null);
//        TreeBag newBag = new TreeBag(mainNode);
//
//        newBag.add(4);
//        newBag.add(6);
//        newBag.add(21);
//        newBag.add(27);
//        newBag.add(17);
//        newBag.add(3);
//        newBag.add(7);
//
//        System.out.print(newBag.remove(21));

        TreeBag golferTreeBag = new TreeBag();

        File golfDB = new File("./src/golferinfo.txt");
        Scanner GOLF_DB = new Scanner(golfDB);
        while (GOLF_DB.hasNextLine()) {
            String[] data = GOLF_DB.nextLine().split(" ");
            String lastName = data[0];
            int numOfRounds = Integer.parseInt(data[1]);
            double avgScore = Double.parseDouble(data[2]);

            Golfer golfer = new Golfer(lastName, numOfRounds, avgScore);
            golferTreeBag.add(golfer);
        }
        GOLF_DB.close();

        System.out.println(golferTreeBag.cursor);
    }
}