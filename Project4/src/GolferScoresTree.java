/******************************************************************************
 *
 * This class creates a binary tree database populated by data from a file with
 * binary tree golfer nodes and allows users to modify, display, rewrite, and save data.
 *
 * @author James Ji, Samuel Acquaviva
 *
 ******************************************************************************/

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GolferScoresTree {
    public static void main(String[] args) throws FileNotFoundException {
        //Flag boolean for main program loop
        boolean isRunning = true;
        //Initializing scanner for user input
        Scanner usrInput = new Scanner(System.in);

        File data = new File("golferinfo.txt");
        TreeBag<Golfer> golferTreeBag = createDatabase(data);

        //Main program loop:
        //Displays a menu and allows user to continuously make changes to the read-in Golfer objects
        //until they are done, at which point they can quit and their changes will be saved to the text file
        while (isRunning) {
            System.out.println("\n******************** Main Menu ******************** ");
            System.out.println("1. Display all golfer’s stats ordered by last name");
            System.out.println("2. Find and display one individual golfer's stats");
            System.out.println("3. Update an individual golfer’s stats");
            System.out.println("4. Remove a golfer from the database");
            System.out.println("5. Add a new golfer to the database");
            System.out.println("6. Quit and update the database");
            System.out.println("7. Quit without updating the database");

            System.out.print("\nPlease enter your selection: ");
            int usrChoice = getValidMenuInput(usrInput, 7);
            System.out.println();

            //Switch statement given the users input
            switch (usrChoice) {
                case 1:
                    golferTreeBag.display();
                    break;
                case 2:
                    System.out.print("Please enter the last name of the golfer you want to query: ");
                    String golferName = getValidStrInput(usrInput);
                    System.out.println();
                    Golfer response = golferTreeBag.retrieve(new Golfer(golferName));

                    if (response != null)
                        System.out.println(response);
                    else
                        System.out.println("Golfer was not found");

                    break;
                case 3:
                    System.out.print("Please enter the last name of the golfer you wish to update: ");
                    golferName = getValidStrInput(usrInput);
                    System.out.println();

                    Golfer golfer = golferTreeBag.retrieve(new Golfer(golferName));
                    if (golfer == null) {
                        System.out.println("Golfer was not found");
                    } else {
                        updateGolfer(usrInput, golfer);
                    }
                    break;
                case 4:
                    System.out.print("Please enter the last name of the golfer you want to delete: ");
                    golferName = getValidStrInput(usrInput);
                    System.out.println();

                    if (golferTreeBag.remove(new Golfer(golferName)))
                        System.out.println("Delete successful\n");
                    else
                        System.out.println("Delete unsuccessful, golfer was not found\n");
                    break;
                case 5:
                    System.out.println("Please enter the following information for the new golfer...");
                    System.out.print("Last Name: ");
                    golferName = getValidStrInput(usrInput);
                    System.out.print("Number of rounds: ");
                    int golferNumOfRounds = getValidStatsInput(usrInput);
                    System.out.print("Average Score: ");
                    int golferAvgScore = getValidStatsInput(usrInput);
                    golferTreeBag.add(new Golfer(golferName, golferNumOfRounds, golferAvgScore));
                    golferTreeBag.display();
                    break;
                case 6:
                    isRunning = false;
                    updateFile(data, golferTreeBag);
                    System.out.println("File updated\nGoodbye!");
                    break;
                case 7:
                    isRunning = false;
                    System.out.println("Goodbye!");
                    break;
            }
        }

    }

    //Method to update a selected golfers stats
    public static void updateGolfer(Scanner usrInput, Golfer golfer) {
        int menuSelection;
        //Gives the user a menu to choose from, gets input and then
        //gets a value to perform an operation on the Golfer object
        System.out.println("What would you like to update?" +
                "\n1: Add a score" +
                "\n2: Update the average score" +
                "\n3: Update the number of rounds played");

        System.out.print("\nPlease enter your selection: ");
        menuSelection = getValidMenuInput(usrInput, 3);

        //Switch given user input
        switch (menuSelection) {
            case 1:
                System.out.print("\nEnter the score to add: ");
                int statNum = getValidStatsInput(usrInput);
                golfer.addScore(statNum);
                System.out.println("Score added");
                break;
            case 2:
                System.out.println("\nEnter the new average score: ");
                statNum = getValidStatsInput(usrInput);
                golfer.setAvgScore(statNum);
                System.out.println("Average score updated");
                break;
            case 3:
                System.out.println("\nEnter the new number of rounds played: ");
                statNum = getValidStatsInput(usrInput);
                golfer.setAvgScore(statNum);
                System.out.println("Number of rounds updated");
                break;
        }
    }

    //File updating method:
    //This method clears the data file and then writes the updated database to the file
    public static void updateFile(File data, TreeBag golferTreeBag) throws FileNotFoundException {
        //Clearing the text file by opening and closing with PrintWriter
        PrintWriter clearFile = new PrintWriter(data);
        clearFile.close();

        //Capturing the output from the display() method and outputting it to the file
        PrintStream stream = new PrintStream(data);
        PrintStream fileOutput = System.out;
        System.setOut(stream);
        golferTreeBag.display();
        System.setOut(fileOutput);
    }

    // integer input validation method for menus:
    // this will loop until a valid integer is entered by the user
    // the max param the set the highest number
    // example: max = 9 then the valid inputs are [1,9]
    public static int getValidMenuInput(Scanner usrInput, int max) {
        int response = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                response = usrInput.nextInt();
                if (response < 1 || response > max)
                    throw new Exception(response + " is not an valid menu option, try again: ");

                validInput = true;
                usrInput.nextLine();
            } catch (InputMismatchException e) {
                System.out.print("Input must be a integer, try again: ");
                usrInput.nextLine();
            } catch (Exception e) {
                System.out.print(e.getMessage());
                usrInput.nextLine();
            }
        }

        return response;
    }

    // integer input validation method for golfer stats:
    // this will loop until a valid integer is entered by the user
    // all golfer stats must be positive numbers
    public static int getValidStatsInput(Scanner usrInput) {
        int response = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                response = usrInput.nextInt();
                if (response < 0)
                    throw new Exception("Please enter a valid option. Round and average score cannot be less than 0");

                validInput = true;
                usrInput.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input must be a integer, try again: ");
                usrInput.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                usrInput.nextLine();
            }
        }

        return response;
    }

    // string input validation method:
    // makes sure all strings contain only letters
    public static String getValidStrInput(Scanner usrInput) {
        String response = "";
        boolean validInput = false;

        while (!validInput) {
            try {
                response = usrInput.nextLine();

                // make sure string only contains letters
                if (!response.matches("^[a-zA-Z]+$")) {
                    throw new Exception("Input must contain only letters, try again: ");
                }

                // capitalize first letter in string
                response = response.substring(0, 1).toUpperCase() + response.substring(1);

                validInput = true;
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }

        return response;
    }

    //Database creation method:
    //This reads in all the information from the text file at startup,
    //creating a new Golfer object for each golfer in the text file
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
