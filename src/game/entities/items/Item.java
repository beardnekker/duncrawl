package game.entities.items;

import game.entities.Entity;

public class Item extends Entity {

    public static final int POTION_COST = 3;
    public static final int COINS_GIVEN_FOR_POTION = 2;

    //Changes power and armor for player
    //public static final int SWORD_WEAPON_COST = 5;
    public static final int MINIMUM_ITEM_COST = 3;

    private int cost;

    Item(String name, char appearance, int cost, int posX, int posY) {
        super(name, appearance, posX, posY);
        this.cost = cost;
    }

    /*For shop
    public int getCost(String name) {

        int cost;

        if (name.contains("Potion")){

            cost = POTION_COST;

        } else if (name.contains("")){


            //This should never happen
        } else {
            cost = 0;
        }
        return this.cost;
    }*/
}