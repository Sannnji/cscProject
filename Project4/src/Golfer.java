public class Golfer implements Comparable<Golfer> {
    String lastName;
    int numOfRounds;
    double avgScore;

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    int getNumOfRounds() {
        return numOfRounds;
    }

    void setNumOfRounds(int numOfRounds) {
        this.numOfRounds = numOfRounds;
    }

    double getAvgScore() {
        return avgScore;
    }

    void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    Golfer(String lastName) {
        this.lastName = lastName;
        numOfRounds = 0;
        avgScore = 0;
    }

    Golfer(String lastName, int numOfRounds, double avgScore) {
        this.lastName = lastName;
        this.numOfRounds = numOfRounds;
        this.avgScore = avgScore;
    }

    void addScore(int score) {
        this.avgScore = ((numOfRounds * avgScore) + score) / numOfRounds + 1;
        numOfRounds++;
    }

    public String toString() {
        return lastName + " " + numOfRounds + " " + avgScore;
    }

    @Override
    public int compareTo(Golfer o) {
        return 0;
    }
}
