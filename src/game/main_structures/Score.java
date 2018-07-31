package game.main_structures;

class Score implements Comparable<Score> {

    private String playerName;
    private double highScore;

    Score(String playerName, double highScore) {
        this.playerName = playerName;
        this.highScore = highScore;
    }

    String getPlayerName() {
        return this.playerName;
    }

    double getHighScore() {
        return this.highScore;
    }

    @Override
    public String toString() {
        return this.playerName + ": " + this.highScore;
    }

    @Override
    public int compareTo(Score score) {
        //Using the Double class' overrided compareTo
        return Double.compare(this.highScore, score.getHighScore());
    }
}
