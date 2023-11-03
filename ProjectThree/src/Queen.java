/******************************************************************************
 *
 * A Queen object represents queen on a chess board. The object possesses
 * coordinates to indicate the position of the queen on the board.
 *
 * @author James Ji, Samuel Acquaviva
 *
 ******************************************************************************/
public class Queen {
    private int row;
    private int column;

    /**
     * Accessor method to get the row that the queen is in.
     * @return
     * The integer value of the row that the queen is positioned in.
     **/
    public int getRow() {
        return row;
    }

    /**
     * Accessor method to get the column that the queen is in.
     * @return
     * The integer value of the column that the queen is positioned in.
     **/
    public int getCol() {
        return column;
    }

    /**
     * Constructor for the Queen object.
     * @param row
     * The row that the Queen object will be placed in.
     * @param column
     * The column that the Queen object will be place in.
     * @precondition
     * The row and column values don't exceed the size of the
     * current board configuration.
     **/
     Queen (int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Checks to see if the passed in Queen object could take the
     * referenced Queen object based on the positions of both objects.
     * @param - queen
     * A Queen object used to compare with the Queen that the method is called on.
     * @return
     * Returns True if there is a conflict between both Queen objects, and returns
     * false if there isn't a conflict.
     **/
    public boolean conflict(Queen queen) {
        boolean isConflicted = false;
        double slope = (queen.row - row) != 0 ? (double)(queen.column - column) / (queen.row - row) : 0.0;

        if (queen.row == row || queen.column == column) {
            System.out.print("X Y conflict");
            isConflicted = true;
        }
        // if the slope between two points is 1 or -1 that means it is directly diagonal in some direction
        if (slope == 1 || slope == -1) {
            System.out.print("Diagonal conflict");
            isConflicted = true;
        }

        return isConflicted;
    }

    /**
     * Accessor method that returns the row and column values of
     * the Queen object that the method is called on.
     * @return
     * Returns a presentable string containing the row and column
     * values of the referenced Queen object, to be printed to the user.
     **/
    public String toString() {
        return "(row = " + row + ", column = " + column + ")";
    }
}
