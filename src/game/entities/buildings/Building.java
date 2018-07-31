package game.entities.buildings;

import game.entities.Entity;

public class Building extends Entity {

    private int healthPotionsAvailable;
    private int damagePotionsAvailable;

    Building(String name, char appearance, int healthPotionsAvailable, int damagePotionsAvailable, int posX, int posY) {
        super(name, appearance, posX, posY);
        this.healthPotionsAvailable = healthPotionsAvailable;
        this.damagePotionsAvailable = damagePotionsAvailable;
    }

    public int getHealthPotionsAvailable() {
        return this.healthPotionsAvailable;
    }

    public int getDamagePotionsAvailable() {
        return this.damagePotionsAvailable;
    }

    public void changeHealthPotionsAvailable(int healthPotionsAvailable) {
        this.healthPotionsAvailable += healthPotionsAvailable;

        if (this.healthPotionsAvailable <= 0) {
            this.healthPotionsAvailable = 0;
        }
    }

    public void changeDamagePotionsAvailable(int damagePotionsAvailable) {
        this.damagePotionsAvailable += damagePotionsAvailable;

        if (this.damagePotionsAvailable <= 0) {
            this.damagePotionsAvailable = 0;
        }
    }
}