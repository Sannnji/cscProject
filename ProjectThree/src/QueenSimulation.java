import java.util.Scanner;

public class QueenSimulation {
    // constant for the starting position for row and column
    static final int STARTING_POS = 1;
    static int solutionCount = 0, row = STARTING_POS, column = STARTING_POS;
    static boolean allSolutionsFound = false, isConflicted = false;
    static String solutionSet = "";
    static Scanner usrInput = new Scanner(System.in);
    static LinkedStack<Queen> solutionStack = new LinkedStack<Queen>();

    // backtrack pop method
    // pops a queen in the stack when the queen being placed has no available spots on the row
    // and set the popped queen to current queen with + 1 to column
    // if the queen being popped is
    public static void popQueenAtColEnd(int boardSize) {
        if (solutionStack.peek().getCol() + 1 > boardSize) {
            solutionStack.pop();
        }
        row = solutionStack.peek().getRow();
        column = solutionStack.peek().getCol() + 1;
        solutionStack.pop();
    }

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

        while (!allSolutionsFound) {
            Queen queen = new Queen(row, column);
            System.out.println("Location of the Queen being placed: " + queen.toString());

            // check for conflicts, break from loop if one is found
            for (int i = 0; i < solutionStack.size(); i++) {
                if (queen.conflict((Queen) solutionStack.itemAt(i))) {
                    isConflicted = true;
                    System.out.println(" with " + solutionStack.itemAt(i).toString());
                    System.out.println();
                }
                if (isConflicted) break;
            }

            // backtrack
            // if column is equal to board size and is conflicted
            // otherwise increment column
            if (column == boardSize && isConflicted) {
                // this is the exiting loop
                // if we have to backtrack to (1,5) then that means we've discovered all the possible solution
                if (solutionStack.peek().getRow() == 1 && solutionStack.peek().getCol() == boardSize) {
                    allSolutionsFound = true;
                } else {
                    // backtrack
                    popQueenAtColEnd(boardSize);
                }
            } else {
                column++;
            }

            // push queen if no conflicts were found
            if (!isConflicted) pushQueen(queen);

            // reset isConflicted
            isConflicted = false;
            // a solution set is found when the number of queens in the solution stack is equal to the number of rows
            if (solutionStack.size() == boardSize) {
                solutionCount++;
                // print solution coordinates from top to bottom (according to board)
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

        System.out.println(solutionSet);
        System.out.println("The total number of solutions for a " + boardSize + " x " + boardSize + " is " + solutionCount);
    }
}
