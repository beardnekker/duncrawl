package game.main_structures;

import game.entities.enemies.Enemy;
import game.entities.Player;

import java.util.Random;
import java.util.Scanner;

class Fight {

    //The winner of the match
    private String victor;

    //holds the stats of the Player from after the fight
    private Player modifiedPlayer;
    private int zeroDamageCounterPlayer;

    private Enemy modifiedEnemy;
    private int zeroDamageCounterEnemy;

    Fight(Player player, Enemy enemy) {
        this.modifiedPlayer = player;
        this.modifiedEnemy = enemy;
        this.zeroDamageCounterEnemy = 0;
        this.zeroDamageCounterPlayer = 0;
    }

    Player getModifiedPlayer() {
        return this.modifiedPlayer;
    }

    String getVictor() {
        return this.victor;
    }

    void startFight(String advantage) {
        if (!advantage.equals("player") && !advantage.equals("enemy")) {
            System.out.println("The fight could not be started. Unknown advantage: " + advantage);
        } else {
            handleTurn(advantage);
        }
    }

    private void handleTurn(String advantage) {

        int turns = 0;
        turns++;

        //If both of the combatants are alive then continue the fight
        if (liveCombatants()) {
            String choice;
            System.out.println("Turns: " + turns);
            System.out.println(this.modifiedPlayer.healthString());
            System.out.println(this.modifiedEnemy.healthString());

            switch (advantage) {

                //enemy logic
                case "enemy":
                    choice = this.modifiedEnemy.aiChoice();
                    handleChoice(advantage, choice);
                    System.out.println("========");
                    handleTurn("player");
                    break;

                //Default is player
                default:
                    GameModule.incrementTurns();
                    choice = playerChoice();
                    handleChoice(advantage, choice);
                    System.out.println("========");
                    handleTurn("enemy");
                    break;
            }

            //Otherwise end the fight because one of the combatants must have died
        } else {
            endFight();
        }
    }

    private String playerChoice() {

        String input;

        while (true) {

            final Scanner tempScanner = new Scanner(System.in);
            System.out.println("What will " + this.modifiedPlayer.getName() + " do?");

            //If the player has items then give them the acceptable options
            if (this.modifiedPlayer.getHealthPotion() > 0 || this.modifiedPlayer.getDamagePotion() > 0) {
                System.out.println("(Options: [attack] [defend] [stall] [item])");
                System.out.println("Enter a, d, s, or i for convenience to the respective options.");

                input = tempScanner.nextLine().toLowerCase().trim();

                if (!input.equals("attack") && !input.equals("a") && !input.equals("defend") && !input.equals("d") &&
                        !input.equals("stall") && !input.equals("s") && !input.equals("item") && !input.equals("i")) {

                    System.out.println(input + " was not a valid choice.");

                } else {

                    switch (input) {

                        case "a":
                            input = "attack";
                            break;

                        case "d":
                            input = "defense";
                            break;

                        case "s":
                            input = "stall";
                            break;

                        case "i":
                            input = "item";
                            break;
                    }
                    return input;
                }

                //Otherwise give them the other options
            } else {
                System.out.println("(Options: [attack] [defend] [stall])");
                System.out.println("Enter a, d, or s for convenience to the respective options.");

                input = tempScanner.nextLine().toLowerCase().trim();

                if (!input.equals("attack") && !input.equals("a") && !input.equals("defend") &&
                        !input.equals("d") && !input.equals("stall") && !input.equals("s")) {
                    System.out.println(input + " was not a valid choice,");
                } else {

                    switch (input) {

                        case "a":
                            input = "attack";
                            break;

                        case "d":
                            input = "defense";
                            break;

                        case "s":
                            input = "stall";
                            break;
                    }
                    return input;
                }
            }
        }
    }

    private boolean liveCombatants() {

        //both player and entity must have health left
        return this.modifiedPlayer.getCurrentHealth() > 0 && this.modifiedEnemy.getCurrentHealth() > 0;
    }

    private void handleChoice(String advantage, String choice) {

        choice = choice.trim().toLowerCase();

        if (advantage.equals("enemy")) {

            String name = this.modifiedEnemy.getName();

            System.out.println(">" + name + " chose to " + choice + ".");

            if (choice.equals("attack")) {
                attack("enemy");
            }
            if (choice.equals("defend")) {
                this.modifiedEnemy.setDefending(true);
            }

        } else {
            String name = this.modifiedPlayer.getName();

            System.out.println(">" + name + " (you) chose to " + choice + ".");

            switch (choice) {

                case "attack":
                case "a":
                    attack("player");
                    break;

                case "defend":
                case "d":
                    this.modifiedPlayer.setDefending(true);
                    break;

                case "item":
                case "i":

                    System.out.print("Choose an item:");

                    if (this.modifiedPlayer.getHealthPotion() > 0 && this.modifiedPlayer.getDamagePotion() > 0) {
                        System.out.print(" [Health Potion] [Damage Potion] ");
                        System.out.println();
                        System.out.println("Use h and d for short cuts to pick the respective item.");

                    } else if (this.modifiedPlayer.getHealthPotion() > 0) {
                        System.out.print(" [Health Potion] ");
                        System.out.println();
                        System.out.println("Use h as a short cut.");

                    } else if (this.modifiedPlayer.getDamagePotion() > 0) {
                        System.out.print(" [Damage Potion] ");
                        System.out.println();
                        System.out.println("Use d as a short cut.");

                    } else {
                        System.out.println("HandleChoice(): This should never happen.");
                    }

                    final Scanner tempScanner = new Scanner(System.in);
                    String item = tempScanner.nextLine().trim().toLowerCase();

                    if (item.equalsIgnoreCase("HealthPotion") || item.equalsIgnoreCase("h")) {
                        this.modifiedPlayer.changeHealth(10);
                        this.modifiedPlayer.changeHealthPotionCount(-1);

                    } else if (item.equalsIgnoreCase("DamagePotion") || item.equalsIgnoreCase("d")) {
                        this.modifiedEnemy.changeHealth(-10);
                        this.modifiedPlayer.changeDamagePotionCount(-1);

                        //It wasn't a valid item, so ask again
                    } else {
                        System.out.println("That wasn't an item available.\nTry again.");

                        //The try again part
                        handleChoice(advantage, choice);
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private void attack(String turn) {

        //Set up the enemy ai and randomness for player power and armor
        Random die = new Random();

        if (turn.equals("enemy")) {

            int enemyPower = this.modifiedEnemy.getPower();
            int effectivePower = enemyPower + (die.nextInt(enemyPower) + 1);

            int playerArmor = this.modifiedPlayer.getArmor();

            if (this.modifiedPlayer.isDefending()) {
                this.modifiedPlayer.setDefending(false);
                playerArmor += (die.nextInt(playerArmor) + 1);
            }

            //actual damage
            int damage = effectivePower - playerArmor;

            /*If the enemy will del less than or equal to 0 damage to the player and the player isn't defending and
            the zero damage counter equals 0 then set the damage to 0 and increase the zero damage counter*/
            if (damage <= 0 && !this.modifiedPlayer.isDefending() && this.zeroDamageCounterEnemy == 0) {
                damage = 0;
                this.zeroDamageCounterEnemy++;

                /*If the same thing as the above except the zero damage counter is 1 then set the damage based on the
                enemy's name and reset the counter*/
            } else if (damage <= 0 && this.zeroDamageCounterEnemy == 1 && !this.modifiedPlayer.isDefending()) {

                switch (this.modifiedEnemy.getName()) {

                    case "Goblin":
                    case "Skeleton":

                        //At least 1, maybe 2
                        damage = die.nextInt(2) + 1;
                        break;

                    case "Baby Dragon":

                        //At least 2, maybe 3
                        damage = 2 + (int) (Math.random() * 2);
                        break;

                    //Dragon
                    default:

                        //At least 2, maybe 4
                        damage = 2 + (int) (Math.random() * 3);
                        break;
                }
                this.zeroDamageCounterPlayer--;
            }

            this.modifiedPlayer.changeHealth(-damage);
            System.out.println(this.modifiedPlayer.getName() + " (you) took " + damage + " damage.");

            //Otherwise it's the players turn
        } else {

            int playerPower = this.modifiedPlayer.getPower();
            int effectivePower = playerPower + (die.nextInt(playerPower) + 1);

            int enemyArmor = this.modifiedEnemy.getArmor();

            if (this.modifiedEnemy.isDefending()) {
                this.modifiedEnemy.setDefending(false);
                enemyArmor += (die.nextInt(enemyArmor) + 1);
            }

            int damage = effectivePower - enemyArmor;

            /*If the player will deal less than or equal to 0 damage to the enemy and the enemy isn't defending and
            the zero damage counter equals 0 then set the damage to 0 and increase the zero damage counter*/
            if (damage <= 0 && !this.modifiedEnemy.isDefending() && this.zeroDamageCounterPlayer == 0) {
                damage = 0;
                this.zeroDamageCounterPlayer++;

                /*If the same thing as the above except the zero damage counter is 1 then set the damage based on the
                enemy's name and probability and reset the counter*/
            } else if (damage <= 0 && this.zeroDamageCounterPlayer == 1 && !this.modifiedEnemy.isDefending()) {

                switch (this.modifiedEnemy.getName()) {

                    case "Goblin":
                    case "Skeleton":

                        //At least 1, maybe 2
                        damage = die.nextInt(2) + 1;
                        break;

                    case "Baby Dragon":
                    case "Dragon":

                        //At least 2, maybe 3 or 4
                        damage = 2 + (int) (Math.random() * 3);
                        //Between/At least bound: Min + (int)(Math.random() * ((Max - Min) + 1))
                        //(int) (Math.random() * integer) = [0, integer - 1]
                        //Math.random() = new Random().nextDouble()
                        break;

                    default:
                        System.out.println("attack(): This should never happen.");
                        break;
                }
                this.zeroDamageCounterPlayer--;
            }

            this.modifiedEnemy.changeHealth(-damage);
            System.out.println(this.modifiedEnemy.getName() + " took " + damage + " damage.");
        }
    }

    private void endFight() {

        if (this.modifiedPlayer.getCurrentHealth() > 0) {
            System.out.println("You emerged victorious!");
            this.victor = "player";

            //Give the player coins according to the losing enemy's name
            this.modifiedPlayer.changeCoinCount(this.modifiedEnemy.getCoinReward());
            System.out.println("You have been given " + this.modifiedEnemy.getCoinReward() + " coins for killing the " +
                    this.modifiedEnemy.getName());

        } else {
            this.victor = "entity";
        }
    }
}