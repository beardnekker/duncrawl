package game.entities;

import game.data.Level1Data;

public class Player extends FightingEntity {

    private static final char PLAYER_SYMBOL = 'P';
    private static final int MAX_HEALTH = 65;
    private static final int POWER = 5;
    private static final int ARMOR = 4;

    private Entity nextCollider;

    //Player specific statistics
    private int healthPotion;
    private int damagePotion;
    private int coins;

    public Player(String name, Entity nextCollider, int posX, int posY) {
        super(name, PLAYER_SYMBOL, MAX_HEALTH, ARMOR, POWER, posX, posY);
        this.healthPotion = 0;
        this.damagePotion = 0;
        this.coins = Level1Data.STARTING_COINS;
        this.nextCollider = nextCollider;
    }

    public int getHealthPotion() {
        return this.healthPotion;
    }

    public int getDamagePotion() {
        return this.damagePotion;
    }

    public int getCoins() {
        return this.coins;
    }

    public Entity getLastCollider() {
        return this.nextCollider;
    }

    public boolean isNotOnLastCollider() {

        //TODO 7/30/2018: Breaks here
        return getPosX() != this.nextCollider.getPosX() && getPosY() != this.nextCollider.getPosY();
    }

    public void changeHealthPotionCount(int numberOfHealthPotions) {
        this.healthPotion += numberOfHealthPotions;
    }

    public void changeDamagePotionCount(int numberOfDamagePotions) {
        this.damagePotion += numberOfDamagePotions;
    }

    public void changeCoinCount(int numberOfCoins) {
        this.coins += numberOfCoins;

        if (this.coins < 0) {
            this.coins = 0;
        }
    }

    public void setNextCollider(Entity lastCollidedWith) {
        this.nextCollider = lastCollidedWith;
    }
}