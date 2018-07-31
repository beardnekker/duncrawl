package game.main_structures;

import java.util.*;

class MainMenu {

    private Leaderboard leaderboard;
    private boolean done;

    MainMenu() {
        this.leaderboard = new Leaderboard();
        done = false;
    }

    void startScreen() {
        System.out.println("Welcome to Dungeon Crawler!");
        System.out.println();
        System.out.println("Please enter a choice from the below options.");
        System.out.println("(Options: [Play Game] [How To Play] [High Scores] [Leave])");
        playerChoice();
    }

    private void redirection() {
        System.out.println();
        System.out.println("Please select a choice from the below options.");
        System.out.println("(Options: [Play Game] [How To Play] [High Scores] [Leave])");
        playerChoice();
    }

    private void playerChoice() {

        final Scanner input = new Scanner(System.in);
        String choice = input.nextLine();

        while (!this.done) {

            if (choice.equalsIgnoreCase("Play Game")) {

                GameModule gameModule = new GameModule();
                gameModule.startGame();

            } else if (choice.equalsIgnoreCase("How to Play")) {

                EventHandler.printHelp();

            } else if (choice.equalsIgnoreCase("High Scores")) {

                System.out.println();

                ArrayList<Score> highScores = this.leaderboard.getHighScores();

                //If highScores isn't empty then print out the scores
                if (!highScores.isEmpty()) {

                    for (Score score : highScores) {
                        System.out.println(score);
                    }

                    //Otherwise the game hasn't been played yet, so tell the user
                } else {
                    System.out.println("There are no high scores available because the game hasn't been played yet.");
                }

            } else if (choice.equalsIgnoreCase("Leave")) {

                this.done = true;
                System.out.println("Bye!");

            } else {
                System.out.println("Sorry that wasn't an option given.\nPlease enter your choice again.");
            }

            if (!this.done) {
                redirection();
            } else {
                input.close();
            }
        }
    }

    Leaderboard getLeaderboard() {
        return this.leaderboard;
    }
}