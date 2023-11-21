//Authors: James Ji, Samuel Acquaviva
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GolferScoresTree {
    public static void main(String[] args) throws FileNotFoundException {
        //Flag boolean for main program loop
        boolean isRunning = true;
        //Initializing scanner for user input
        Scanner usrInput = new Scanner(System.in);

        //  ******************* this needs to be changed and modified in jgrasp *******************
        File data = new File("./src/golferinfo.txt");
        TreeBag<Golfer> golferTreeBag = createDatabase(data);
        //  ***************************************************************************************

        golferTreeBag.display();

        //Main program loop:
        //Displays a menu and allows user to continuously make changes to the read-in Golfer objects
        //until they are done, at which point they can quit and their changes will be saved to the text file
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
            int usrChoice = getValidInput(usrInput, 1, 7);
            usrInput.nextLine();

            //Switch statement given the users input
            switch (usrChoice) {
                case 1:
                    golferTreeBag.display();
                    break;
                case 2:
                    Golfer newGolfer = golferTreeBag.retrieve(new Golfer("Walker"));
                    System.out.print(newGolfer);
                    break;
                case 3:
                    String string_input;
                    System.out.print("Please enter the last name of the golfer you wish to update" +
                            "\n>");
                    string_input = usrInput.nextLine();

                    Golfer golfer = golferTreeBag.retrieve(new Golfer(string_input));
                    if(golfer == null){
                        System.out.println("ERROR: Golfer not found");
                        return;
                    }else {
                        updateGolfer(usrInput, golfer);
                    }
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
    public static void updateGolfer(Scanner usrInput, Golfer golfer){
        String string_input;
        int int_input;
        //Gives the user a menu to choose from, gets input and then
        //gets a value to perform an operation on the Golfer object
        System.out.println("What would you like to do?" +
                "\n1: Add a score" +
                "\n2: Update the average score" +
                "\n3: Update the number of rounds played");

        int_input = getValidInput(usrInput, 1, 3);

        //Switch given user input
        switch(int_input){
            case 1:
                System.out.println("Enter the score to add");
                int_input = getValidInput(usrInput, 0, 256);
                golfer.addScore(int_input);
                System.out.println("Score added");
                break;
            case 2:
                System.out.println("Enter the new average score");
                int_input = getValidInput(usrInput, 0, 256);
                golfer.setAvgScore(int_input);
                System.out.println("Average score updated");
                break;
            case 3:
                System.out.println("Enter the new number of rounds played");
                int_input = getValidInput(usrInput, 0, 256);
                golfer.setAvgScore(int_input);
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

    //Input validation method:
    //This will loop until a valid menu option is selected
    public static int getValidInput(Scanner usrInput, int min, int max) {
        int input = 0;
        boolean flag = true;

        while(flag){
            System.out.print("\n>");
            try{
                input = usrInput.nextInt();

                if(input >=  min && input <= max){
                    flag = false;
                }
                else{
                    throw new Exception("Invalid input value: Please select a valid option (" + min + "-" + max + ")");
                }
            }
            catch(InputMismatchException e){
                System.out.print("Invalid input type: Please enter a valid integer (" + min + "-" + max + ")");
                usrInput.nextLine();
            }
            catch(Exception e){
                System.out.print(e.getMessage());
            }
        }
        return input;
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
