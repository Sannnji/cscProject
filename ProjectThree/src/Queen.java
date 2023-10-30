public class Queen {
    private int x;
    private int y;

    Queen (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean conflict(Queen queen) {
        boolean isConflicted = false;
        if (queen.x == x) {
            isConflicted = true;
        } else if (queen.y == y) {
            isConflicted = true;
        }

        return isConflicted;
    }

    public String toString() {
        return "Coordinate: (" + x + ", " + y + ")";
    }
}
