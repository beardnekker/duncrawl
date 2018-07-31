package game.main_structures;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Leaderboard {

    //Represents all scores in the HighScores.txt
    private static ArrayList<Score> highScores;

    Leaderboard() {
        highScores = new ArrayList<>();
    }

    void logScoreOnLeaderboard(Score score) {

        /*Reads the file and then could possibly do something
        try {

        } catch (IOException e) {

            e.printStackTrace();
        }*/

        try {
            writeFile(score);
            highScores.add(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(Score score) throws IOException {

        String scoreLine = score.getPlayerName() + ": " + score.getHighScore() + "\n";

        Files.write(Paths.get("HighScore.txt"), scoreLine.getBytes(), StandardOpenOption.APPEND);
    }

    private void readFile() throws IOException {

        //Create a new stream that gets the lines in HighScore.txt
        Stream<String> scores = Files.lines(Paths.get("HighScore.txt"));

        /*For each line in the scores we are going to map each line, into a word array by splitting them,
        get the score and then add that score to a temp ArrayList that is created by using .collect to turn the
        Stream<String> into a collection of type ArrayList and set highScores to it.*/
        highScores = scores.map(line -> {
            String[] words = line.split(": ");
            return new Score(words[0], Double.parseDouble(words[1]));
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    private static void sortScores() {
        highScores.sort(Collections.reverseOrder());
    }

    ArrayList<Score> getHighScores() {

        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sortScores();
        return highScores;
    }
}