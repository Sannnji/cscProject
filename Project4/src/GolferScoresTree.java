import java.io.*;
import java.util.Scanner;

public class GolferScoresTree {
    public static void main(String[] args) throws FileNotFoundException {
        boolean isRunning = true;
        Scanner usrInput = new Scanner(System.in);

        //  ******************* this needs to be changed and modified in jgrasp *******************
        File data = new File("./src/golferinfo.txt");
        TreeBag<Golfer> golferTreeBag = createDatabase(data);
        //  ***************************************************************************************

        golferTreeBag.display();

        while (isRunning) {

            System.out.println("\n\n******************** Main Menu ******************** ");
            System.out.println("1. Display all golfer’s stats ordered by last name");
            System.out.println("2. Find and display one individual golfer's stats");
            System.out.println("3. Update an individual golfer’s stats");
            System.out.println("4. Remove a golfer from the database");
            System.out.println("5. Add a new golfer to the database");
            System.out.println("6. Quit and update the database");
            System.out.println("7. Quit without updating the database");

            System.out.print("\nPlease enter your selection: ");
            int usrChoice = getValidInput(usrInput);

            switch (usrChoice) {
                case 1:
                    golferTreeBag.display();
                    break;
                case 2:
                    Golfer newGolfer = golferTreeBag.retrieve(new Golfer("Walker"));
                    System.out.print(newGolfer);
                    break;
                case 3:
                    break;
                case 4:
                    golferTreeBag.remove(new Golfer("Walker"));
                    golferTreeBag.display();
                    break;
                case 5:
                    golferTreeBag.add(new Golfer("Gilmore", 9, 90));
                    golferTreeBag.display();
                    break;
                case 6:
                    break;
                case 7:
                    isRunning = false;
                    break;
            }
        }

    }

    public static int getValidInput(Scanner usrInput) {
        int usrChoice = 0;

        try {
            usrChoice = usrInput.nextInt();
            if (usrChoice > 7 || usrChoice < 1)
                throw new Exception("Please enter a valid option.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            usrInput.next();
        }

        return usrChoice;
    }

    public static TreeBag<Golfer> createDatabase(File data) throws FileNotFoundException {
        TreeBag golferTreeBag = new TreeBag();

        Scanner GOLF_DATA_FILE = new Scanner(data);
        while (GOLF_DATA_FILE.hasNextLine()) {
            String[] golferData = GOLF_DATA_FILE.nextLine().split(" ");
            String lastName = golferData[0];
            int numOfRounds = Integer.parseInt(golferData[1]);
            double avgScore = Double.parseDouble(golferData[2]);

            Golfer golfer = new Golfer(lastName, numOfRounds, avgScore);
            golferTreeBag.add(golfer);
        }
        GOLF_DATA_FILE.close();

        return golferTreeBag;
    }
}
