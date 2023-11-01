public class Queen {
    private int row;
    private int column;

    public int getRow() {
        return row;
    }
    public int getCol() {
        return column;
    }

    Queen (int row, int column) {
        this.row = row;
        this.column = column;
    }

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

    public String toString() {
        return "(row = " + row + ", column = " + column + ")";
    }
}
