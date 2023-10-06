import java.util.Scanner;

public class SequenceTest {
    static Scanner usrInput = new Scanner(System.in);
    static boolean isRunning = true;

    // Primarily used for viewing the results returned from the selection menu, but functions as a continue or quit prompt
    static public void proceedOrQuit() {
        boolean inputIsInvalid = true;
        int selection = 0;

        while (inputIsInvalid) {
            System.out.print("Would you like to continue(1) or quit(2): ");
            selection = usrInput.nextInt();
            if (selection == 1 || selection == 2) {
                inputIsInvalid = false;
            } else {
                System.out.println("Invalid input. Input must be either (1) or (2)");
                System.out.println();
            }
        }

        if (selection == 2) {
            System.out.println();
            System.out.println("Goodbye!");
            isRunning = false;
        }
    }

    public static void main(String[] args) {
        // create test seq with capacity of 10
        DoubleArraySeq seq = new DoubleArraySeq(0);
        seq.addAfter(7);
        seq.addAfter(5);
        seq.addAfter(7);

        while (isRunning) {
            System.out.println();
            System.out.println(
                    " 1. Print out to screen the sequence (uses toString( ) )\n" +
                            " 2. Report the capacity of sequence (uses getCapacity( ) )\n" +
                            " 3. Set the current element location (uses setCurrent(int) )\n" +
                            " 4. Add a number to the front of the sequence ( uses addFront(double) )\n" +
                            " 5. Add a number to the end of the sequence (uses size( ), setCurrent (int) and addAfter(double) )\n" +
                            " 6. Add a number before the current element (uses addBefore(double) )\n" +
                            " 7. Add a number after the current element (uses addAfter(double) )\n" +
                            " 8. Delete the first number from the sequence (uses removeFront( ) )\n" +
                            " 9. Delete a number at a location (uses setCurrent(int) and removeCurrent( ) )\n" +
                            "10. Display the value at a certain location (uses getElement(int) )\n" +
                            "11. Display the last element in the sequence(uses size( ), setCurrent(int) and getCurrent( ) )\n" +
                            "12. Quit"
            );
            System.out.println();
            System.out.print("Selection: ");
            int usrSelection = usrInput.nextInt();
            System.out.println();


            switch (usrSelection) {
                case 1:
                    System.out.println(seq);
                    System.out.println();
                    proceedOrQuit();
                    break;
                case 2:
                    System.out.println(seq.getCapacity());
                    System.out.println(seq);
                    System.out.println();
                    proceedOrQuit();
                    break;
                case 3:
                    System.out.println("Set current location to 2 (index 1)");
                    seq.setCurrent(2);
                    System.out.println(seq.getCurrent());
                    System.out.println();
                    proceedOrQuit();
                    break;
                case 4:
                    System.out.println("Add number (3) to the front of the sequence");
                    seq.addFront(3);
                    System.out.print("After:");
                    System.out.println(seq);
                    System.out.println();
                    proceedOrQuit();
                    break;
                case 5:
                    System.out.println("Add number (3) to the end of the sequence");
                    seq.setCurrent(seq.size());
                    seq.addAfter(3);
                    System.out.print("After:");
                    System.out.println(seq);

                    System.out.println();
                    proceedOrQuit();
                    break;
                case 6:
                    System.out.print("Pick a number to add before the current element: ");
                    int selection = usrInput.nextInt();
                    seq.addBefore(selection);
                    System.out.println();
                    System.out.print("After:");
                    System.out.println(seq);

                    System.out.println();
                    proceedOrQuit();
                    break;
                case 7:
                    System.out.print("Pick a number to add after the current element: ");
                    selection = usrInput.nextInt();
                    seq.addAfter(selection);
                    System.out.println();
                    System.out.print("After:");
                    System.out.println(seq);

                    System.out.println();
                    proceedOrQuit();
                    break;
                case 8:
                    System.out.print("Before:");
                    System.out.println(seq);
                    seq.removeFront();
                    System.out.print("After:");
                    System.out.println(seq);

                    System.out.println();
                    proceedOrQuit();
                    break;
                case 9:
                    System.out.print("Pick a location to delete an number (ie: the first, second, twentieth... number): ");
                    selection = usrInput.nextInt();
                    System.out.print("Before:");
                    System.out.println(seq);
                    seq.setCurrent(selection);
                    seq.removeCurrent();
                    System.out.print("After:");
                    System.out.println(seq);

                    System.out.println();
                    proceedOrQuit();
                    break;
                case 10:
                    System.out.print("Pick a location to display the value of:  ");
                    selection = usrInput.nextInt();
                    System.out.println(seq.getElement(selection));
                    System.out.println();
                    proceedOrQuit();
                    break;
                case 11:
                    seq.setCurrent(seq.size());
                    System.out.println(seq.getCurrent());
                    System.out.println();
                    proceedOrQuit();
                    break;
                case 12:
                    isRunning = false;
                    System.out.println();
                    System.out.println("Goodbye!");
                    break;
            }
        }
    }
}
