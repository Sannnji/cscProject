/******************************************************************************
 * A Golfer object represents a profile for an individual golfer containing
 * their last name, the number of rounds they played, and their average score.
 *
 * @author James Ji, Samuel Acquaviva
 *
 ******************************************************************************/
public class Golfer implements Comparable<Golfer> {
    String lastName;
    int numOfRounds;
    double avgScore;

    /**
     * Accessor to get the last name of the current Golfer
     * @return
     * the last name of the current golfer
     *
     **/
    String getLastName() {
        return lastName;
    }

    /**
     * Mutator to set the last name of the current Golfer
     * @param lastName
     * the last name of the Golfer
     *
     **/
    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Accessor to get the number of rounds the Golfer has played
     * @return
     * the number of rounds the Golfer has played
     *
     **/
    int getNumOfRounds() {
        return numOfRounds;
    }

    /**
     * Mutator to set the number of rounds the Golfer has played
     * @param numOfRounds
     * the number of rounds the Golfer has played
     *
     **/
    void setNumOfRounds(int numOfRounds) {
        if (numOfRounds > -1){
            this.numOfRounds = numOfRounds;
        }
        else {
            throw new IllegalArgumentException("The number of rounds must be 0 or greater!");
        }
    }

    /**
     * Accessor to get the Golfers average score
     * @return
     * the Golfers average score
     *
     **/
    double getAvgScore() {
        return avgScore;
    }

    /**
     * Mutator to set the Golfers average score
     * @param avgScore
     * the Golfers average score
     *
     **/
    void setAvgScore(double avgScore) {
        if (avgScore > -1){
            this.avgScore = avgScore;
        }
        else {
            throw new IllegalArgumentException("The average score must be 0 or greater!");
        }
    }


    /**
     * One argument constructor for the Golfer object.
     * @param lastName
     * the Golfers last name
     *
     **/
    Golfer(String lastName) {
        this.lastName = lastName;
        numOfRounds = 0;
        avgScore = 0;
    }

    /**
     * Full argument constructor for the Golfer object.
     * @param lastName
     * the Golfers last name
     * @param numOfRounds
     * the number of rounds the Golfer has played
     * @param avgScore
     * the Golfers average score
     *
     **/
    Golfer(String lastName, int numOfRounds, double avgScore) {
        this.lastName = lastName;
        this.numOfRounds = numOfRounds;
        this.avgScore = avgScore;
    }

    /**
     * Mutator to add the score for a single round of golf
     * @param score
     * the Golfers final score after a single round of golf
     * @postcondition
     * the inputted score is added to the Golfers calculated average,
     * and the number of rounds that the Golfer has played is increased by one
     *
     **/
    void addScore(int score) {
        this.avgScore = ((numOfRounds * avgScore) + score) / numOfRounds + 1;
        numOfRounds++;
    }

    /**
     * Accessor to get necessary information about
     * the Golfer to output to the user
     * @return
     * a neat formatted string containing all the
     * necessary information about the Golfer
     *
     **/
    public String toString() {
        return lastName + " " + numOfRounds + " " + avgScore;
    }

    /**
     * This method compares two Golfers to see if they are equal
     * @return
     * returns true if they are equal,
     * otherwise it will return false
     *
     **/
    @Override
    public int compareTo(Golfer inputGolfer) {
        return lastName.compareTo(inputGolfer.getLastName());
    }
}
