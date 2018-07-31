package game.entities;

public class FightingEntity extends Entity {

    //statistics of fighting entities
    private int currentHealth;
    private int maxHealth;
    private int power;
    private int armor;
    private boolean isDefending;

    public FightingEntity(String name, char appearance, int health, int power, int armor, int posX, int posY) {
        super(name, appearance, posX, posY);
        this.maxHealth = health;
        this.currentHealth = this.maxHealth;
        this.power = power;
        this.armor = armor;
    }

    public boolean isDefending() {
        return this.isDefending;
    }

    public int getPower() {
        return this.power;
    }

    public int getArmor() {
        return this.armor;
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public String healthString() {
        return (getName() + ": " + this.currentHealth + "/" + this.maxHealth + "HP");
    }

    public void setDefending(boolean defending) {
        this.isDefending = defending;
    }

    public void changeHealth(int change) {

        //Change the health
        this.currentHealth += change;

        //Restrict health so if it's negative it goes to 0
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }

        //Restrict health so if it's above the max it will be set to the max
        if (this.currentHealth > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        }
    }

    /* These could be used for special abilities when the power or armor are actual changed.
    At the moment (6/25/2018) there is no need for these.
    public void setPower(int power) {
        this.power = power;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }*/
}