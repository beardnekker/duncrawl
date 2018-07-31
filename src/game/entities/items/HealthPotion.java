package game.entities.items;

public class HealthPotion extends Item {

    private static final char HEALTH_POTION_SYMBOL = '+';

    public HealthPotion(int posX, int posY) {
        super("Health Potion", HEALTH_POTION_SYMBOL, Item.POTION_COST, posX, posY);
    }
}
