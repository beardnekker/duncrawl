package game.main_structures;

import game.entities.Player;

import java.util.Scanner;

abstract class EventHandler {

    private static final String[] COMMAND_LIST = {
            "move", "go", "grab", "look", "help", "stats", "quit"
    };

    private static String previousInput;

    static void updateTurn(final Map map, Player player) {
        printWhatWillYouDo();

        //Reads in the input words
        String[] inputStrings = readInput();

        String command = inputStrings[0];

        //User is asking for the help page
        if (command.equalsIgnoreCase("help")) {
            printHelp();

        } else if (command.equalsIgnoreCase("quit")) {
            System.out.println("Bye!");
            GameModule.turnGameOff();

        } else if (command.equalsIgnoreCase("stats")) {
            System.out.println("Health Potions: " + player.getHealthPotion());
            System.out.println("Damage Potions: " + player.getDamagePotion());
            System.out.println("Coins: " + player.getCoins());
            System.out.println();
            System.out.println("Your current health is: " + player.getCurrentHealth() + "/" + player.getMaxHealth());

        } else if (command.equalsIgnoreCase("HackStats")) {
            player.changeHealth(15);
            player.changeDamagePotionCount(2);
            player.changeHealthPotionCount(2);
            player.changeCoinCount(4);
            System.out.println("You have successfully hacked your item count.");
            System.out.println("You have successfully hacked your health.");

        } else if (validateInput(inputStrings)) {
            System.out.println("Not enough arguments given.");

            //Check if the first word is a valid command
        } else if (isValidCommand(command)) {

            switch (command) {

                case "move":
                case "go":
                    if (inputStrings[1].equals("up") ||
                            inputStrings[1].equals("down") ||
                            inputStrings[1].equals("left") ||
                            inputStrings[1].equals("right")) {
                        map.attemptMove(inputStrings[1], player);
                    }
                    break;

                case "grab":
                    if (inputStrings[1].equals("up") ||
                            inputStrings[1].equals("down") ||
                            inputStrings[1].equals("left") ||
                            inputStrings[1].equals("right")) {
                        map.attemptGrab(inputStrings[1], player);
                    }
                    break;

                case "look":
                    if (inputStrings[1].equals("up") ||
                            inputStrings[1].equals("down") ||
                            inputStrings[1].equals("left") ||
                            inputStrings[1].equals("right")) {
                        map.attemptLook(inputStrings[1], player);
                    }
                    break;

                //This should never happen
                default:
                    System.out.println("Don't know how to handle " + command + ".");
                    break;
            }

        } else {
            System.out.println("Invalid Command Entered");
        }
    }

    static void printHelp() {
        System.out.println(" _    _        _");
        System.out.println("| |  | |      | |");
        System.out.println("| |__| |  ___ | | _ __");
        System.out.println("|  __  | / _ \\| || '_ \\       ");
        System.out.println("| |  | ||  __/| || |_) |_  _  _");
        System.out.println("|_|  |_| \\___||_|| .__/(_)(_)(_)");
        System.out.println("                 | |");
        System.out.println("                 |_|");
        System.out.println();

        //Print the commands with commas in between except on the last one
        System.out.print("This is a list of acceptable commands: ");
        for (int i = 0; i < COMMAND_LIST.length; i++) {
            if (i == COMMAND_LIST.length - 1) {
                System.out.print(COMMAND_LIST[i]);
            } else {
                System.out.print(COMMAND_LIST[i] + ", ");
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("The goal of the game is to get through as many levels as you can and to get as many coins as possible.");
        System.out.println("You can get coins by killing enemies and moving through levels.");
        System.out.println("The game ends when you die and your score is based on the level you got to and the coins you had when you died.");
        System.out.println("The \'P\' character is you (the player).");
        System.out.println("The move and go commands are the same. Example: move up or go down.");
        System.out.println("The look command followed by a direction (up, down, right, left) gives you information about" +
                " the space at that direction in relation to the player position.");
        System.out.println("The grab command works the same way, but instead of getting information you can pick up " +
                "items (potions and coins).\nNote: Grabbing tiles (walls, spikes, bananas) or enemies (goblins, skeletons," +
                " baby dragons, and dragons) doesn't work, so don't do it.");
        System.out.println("Well you can, but you won't like the computers response.");
        System.out.println("The stats command tells you how much of each item you have and your health.");
        System.out.println("The quit command exits you out of the game, doesn't work if you're in a fight though.");
        System.out.println("Finally, the \'p\' command executes the last command you typed.\nP is for previous.");
        System.out.println("In some areas of the game it won't work because you won't need it.");
        System.out.println("You can also spam stuff in the keyboard, but just warning you, the computer will say that you're dumb.");
    }

    private static String[] readInput() {

        final Scanner input = new Scanner(System.in);

        //User input
        String inputString = input.nextLine();

        //If the user's input is P then their actual command will be their previous command
        if (inputString.equalsIgnoreCase("P")) {

            inputString = previousInput;

            //Otherwise the player didn't want to repeat their previous input, so update the previous input
        } else {
            previousInput = inputString;
        }

        //Display it to the player
        System.out.println("Input: " + inputString);

        //Split the line by spaces
        return inputString.split(" ");
    }

    private static boolean isValidCommand(String command) {

        //Iterate through the command list
        for (String aValidCommand : COMMAND_LIST) {

            //if the command is found, return true
            if (aValidCommand.equals(command)) {
                return true;
            }
        }
        //no matching command found in the list
        return false;
    }

    private static boolean validateInput(String[] inputStrings) {
        return inputStrings.length == 1 && (inputStrings[0].equalsIgnoreCase("go")
                || inputStrings[0].equalsIgnoreCase("move")
                || inputStrings[0].equalsIgnoreCase("grab")
                || inputStrings[0].equalsIgnoreCase("look")
                || inputStrings[0].equalsIgnoreCase("up")
                || inputStrings[0].equalsIgnoreCase("down")
                || inputStrings[0].equalsIgnoreCase("right")
                || inputStrings[0].equalsIgnoreCase("left"));
    }

    private static void printWhatWillYouDo() {
        System.out.println(" _    _  _             _               _  _  _                             _        ___ ");
        System.out.println("| |  | || |           | |             (_)| || |                           | |      |__ \\");
        System.out.println("| |  | || |__    __ _ | |_  __      __ _ | || |  _   _   ___   _   _    __| |  ___    ) |");
        System.out.println("| |/\\| || '_ \\  / _` || __| \\ \\ /\\ / /| || || | | | | | / _ \\ | | | |  / _` | / _ \\  / / ");
        System.out.println("\\  /\\  /| | | || (_| || |_   \\ V  V / | || || | | |_| || (_) || |_| | | (_| || (_) ||_|");
        System.out.println(" \\/  \\/ |_| |_| \\__,_| \\__|   \\_/\\_/  |_||_||_|  \\__, | \\___/  \\__,_|  \\__,_| \\___/ (_) ");
        System.out.println("                                                 __/ |");
        System.out.println("                                                |___/");

    }
}