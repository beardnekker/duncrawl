package game.entities.items;

public class DamagePotion extends Item{

    private static final char DAMAGE_POTION_SYMBOL = '-';

    public DamagePotion(int posX, int posY) {
        super("Damage Potion", DAMAGE_POTION_SYMBOL, Item.POTION_COST, posX, posY);
    }
}