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
        if (numOfRounds > -1){
            this.numOfRounds = numOfRounds;
        }
        else {
            throw new IllegalArgumentException("The number of rounds must be 0 or greater!");
        }
    }

    double getAvgScore() {
        return avgScore;
    }

    void setAvgScore(double avgScore) {
        if (avgScore > -1){
            this.avgScore = avgScore;
        }
        else {
            throw new IllegalArgumentException("The average score must be 0 or greater!");
        }
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
    public int compareTo(Golfer inputGolfer) {
        return lastName.compareTo(inputGolfer.getLastName());
    }
}
