package game.entities.enemies;

import game.entities.FightingEntity;

import java.util.Random;

public class Enemy extends FightingEntity {

    private int attackChance;
    private int defendChance;
    private int stallChance;

    Enemy(String name, char appearance, int health, int power, int armor, int posX, int posY) {
        super(name, appearance, health, power, armor, posX, posY);

        //Set the specific combat chances based on the name of the enemy
        switch (name) {

            case "Goblin":
                this.attackChance = Goblin.ATTACK_CHANCE;
                this.defendChance = Goblin.DEFEND_CHANCE;
                this.stallChance = Goblin.STALL_CHANCE;
                break;

            case "Skeleton":
                this.attackChance = Skeleton.ATTACK_CHANCE;
                this.defendChance = Skeleton.DEFEND_CHANCE;
                this.stallChance = Skeleton.STALL_CHANCE;
                break;

            case "Baby Dragon":
                this.attackChance = BabyDragon.ATTACK_CHANCE;
                this.defendChance = BabyDragon.DEFEND_CHANCE;
                this.stallChance = BabyDragon.STALL_CHANCE;
                break;

            default:
                this.attackChance = Dragon.ATTACK_CHANCE;
                this.defendChance = Dragon.DEFEND_CHANCE;
                this.stallChance = Dragon.STALL_CHANCE;
                break;
        }
    }

    public String aiChoice() {

        final Random ai = new Random();

        //Get the pseudo random roll
        int roll = ai.nextInt(100) + 1;

        if (roll <= this.attackChance) {
            return "attack";

        } else if (roll <= (this.attackChance + this.defendChance)) {
            return "defend";

        } else if (roll <= (this.attackChance + this.defendChance + this.stallChance)) {
            return "stall";

        } else {
            return "aiChoice(): This should never happen.";
        }
    }

    public int getCoinReward() {

        int coin;

        switch (getName()) {

            case "Goblin":
                coin = Goblin.COINS_GIVEN;
                break;

            case "Skeleton":
                coin = Skeleton.COINS_GIVEN;
                break;

            case "Baby Dragon":
                coin = BabyDragon.COINS_GIVEN;
                break;

            default:
                coin = Dragon.COINS_GIVEN;
                break;
        }
        return coin;
    }
}