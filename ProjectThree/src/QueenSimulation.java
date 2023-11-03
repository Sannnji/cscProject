/******************************************************************************
 *
 * This class simulates the safe placement of queens on a board with a size determined by
 * the users input
 *
 * @author James Ji, Samuel Acquaviva
 *
 ******************************************************************************/

import java.util.Scanner;

public class QueenSimulation {
    // Constant that holds the starting position for the row and column (1, 1)
    static final int STARTING_POS = 1;
    // Static counter that holds the number of calculated solutions
    // Sets the row and column values to the starting position
    static int solutionCount = 0, row = STARTING_POS, column = STARTING_POS;
    static boolean allSolutionsFound = false, isConflicted = false;
    static String solutionSet = "";
    static Scanner usrInput = new Scanner(System.in);
    static LinkedStack<Queen> solutionStack = new LinkedStack<Queen>();

    // Backtracking pop method:
    // This pops a queen in the stack when the queen being placed has no
    // available spots on the row. Then sets the popped queen equal to the
    // current queen and increments the column of the popped Queen by 1.
    public static void popQueenAtColEnd(int boardSize) {
        if (solutionStack.peek().getCol() + 1 > boardSize) {
            solutionStack.pop();
        }
        row = solutionStack.peek().getRow();
        column = solutionStack.peek().getCol() + 1;
        solutionStack.pop();
    }

    // Pushes the passed in Queen object to the stack,
    // and outputs the position of the Queen to the user.
    public static void pushQueen(Queen queen) {
        solutionStack.push(queen);
        row++;
        column = STARTING_POS;
    }

    public static void main(String[] args) {
        // Intro prompt for the user
        // Gets the desired board size
        System.out.println("Welcome to the N-Queens Solver Simulation!");
        System.out.print("What size board would you like to use?: ");
        int boardSize = usrInput.nextInt();
        System.out.println();
        System.out.println("Sounds good, the simulation will now populate a " + boardSize + " x " + boardSize + " size board");
        System.out.println();

        // Main program loop
        while (!allSolutionsFound) {
            // Creates a new Queen object and prints the position of the queen
            Queen queen = new Queen(row, column);

            // Checks for conflicts and breaks from the loop if one is found.
            for (int i = 0; i < solutionStack.size(); i++) {
                if (queen.conflict((Queen) solutionStack.itemAt(i))) {
                    isConflicted = true;
                }
                if (isConflicted) break;
            }

            // Backtracks if the column is equal to the board size AND is conflicted,
            // otherwise increments the column.
            if (column == boardSize && isConflicted) {
                // If we have to backtrack to (1, Max Value) then we've discovered
                // all the possible solutions.
                if (solutionStack.peek().getRow() == 1 && solutionStack.peek().getCol() == boardSize) {
                    allSolutionsFound = true;
                } else {
                    // Otherwise, backtrack
                    popQueenAtColEnd(boardSize);
                }
            } else {
                column++;
            }

            // Push the queen if no conflicts were found
            if (!isConflicted) pushQueen(queen);

            // Reset isConflicted boolean
            isConflicted = false;

            // A solution set is found when the number of queens in the
            // solution stack is equal to the number of rows
            if (solutionStack.size() == boardSize) {
                // Increments the counter and prints solution coordinates
                // from top to bottom (according to the board)
                solutionCount++;
                solutionSet += "Solution #" + solutionCount + ": ";

                for (int i = solutionStack.size() - 1; i >= 0; i--) {
                    if (i != solutionStack.size() - 1) {
                        solutionSet += ", " + solutionStack.itemAt(i).toString();
                    } else {
                        solutionSet += solutionStack.itemAt(i).toString();
                    }
                }
                solutionSet += "\n";
                popQueenAtColEnd(boardSize);
            }

        }

        // Outputs the solution set and the total number of solutions to the user
        System.out.println(solutionSet);
        System.out.println("The total number of solutions for a " + boardSize + " x " + boardSize + " is " + solutionCount);
    }
}
