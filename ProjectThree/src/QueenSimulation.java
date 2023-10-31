import java.util.Scanner;

public class QueenSimulation {
    // constant for the starting position for row and column
    static final int STARTING_POS = 1;
    static int solutions = 0, row = STARTING_POS, column = STARTING_POS;
    static boolean solutionFound = false, isConflicted = false;
    static String solutionSet = "";
    static Scanner usrInput = new Scanner(System.in);
    static LinkedStack<Queen> solutionStack = new LinkedStack<Queen>();

    public static void pushQueen(Queen queen) {
        solutionStack.push(queen);
        System.out.println("Queen placed at: " + queen.toString());
        System.out.println();
        row++;
        column = STARTING_POS;
    }

    public static void main(String[] args) {
        // intro prompt
        System.out.println("Welcome to the N-Queens Solver Simulation!");
        System.out.print("What size board would you like to use?: ");
        int boardSize = usrInput.nextInt();
        System.out.println();
        System.out.println("Sounds good, the simulation will now begin populating a " + boardSize + " x " + boardSize + " size board");
        System.out.println();



        while (!solutionFound) {
//            System.out.println("(" + row + ", " + column + ")");
            int stackSize = solutionStack.size();
            Queen queen = new Queen(row, column);
            System.out.println("Location of the Queen being placed: " + queen.toString());

            // push queen onto stack if it's empty
            if (stackSize == 0) {
                pushQueen(queen);
            } else {
                for (int i = 0; i < stackSize; i++) {
                    if ( queen.conflict((Queen) solutionStack.itemAt(i)) ) {
                        isConflicted = true;
                        System.out.println("Conflict with " + solutionStack.itemAt(i).toString());
                        System.out.println();

                        // **********************************ERROR HERE****************************************
                        if (column == boardSize && isConflicted) {
                            row = solutionStack.peek().getRow();
//                            column = solutionStack.peek().getCol() + 1 > boardSize ? 1 : solutionStack.peek().getCol() + 1 ;
                            if (solutionStack.peek().getCol() + 1 > boardSize) {
                                solutionStack.pop();
                            } else {
                                column = solutionStack.peek().getCol() + 1;
                            }
                            System.out.println(solutionStack.size());

                            System.out.println(solutionStack.size());
                        } else {
                            column++;
                        }

//                        System.out.println("Coordinate: (" + row + ", " + column + ")");
                    }
                    if (isConflicted) {
                        break;
                    }
                }

                // push queen if no conflicts were found
                if (!isConflicted) {
                    pushQueen(queen);
                }


                // reset isConflicted
                isConflicted = false;
                // the number of solutions is dictated by the number rows and since our board is a square
                // we can use boardSize for the checking value
                if (solutionStack.size() == boardSize) {
                    // print solution coordinates from top to bottom (according to board)
                    for (int i = solutionStack.size() - 1; i >= 0; i--) {
                        if (solutionSet.length() != 0 ) {
                            solutionSet += ", " + solutionStack.itemAt(i).toString();
                        } else {
                            solutionSet += solutionStack.itemAt(i).toString();
                        }
                    }
                    System.out.println(solutionSet);
                    System.out.println();

                    System.out.print("Would you like to continue and find another solution set? (yes = 1, no = 0): ");
                    int findAnotherSolution = usrInput.nextInt();

                    if (findAnotherSolution == 1) {
                        row = solutionStack.peek().getRow();
                        column = solutionStack.peek().getCol() + 1;
                        solutionStack.pop();
                        solutionSet = "";
                    } else {
                        solutionFound = true;
                    }
                }
            }
        }
    }
}
