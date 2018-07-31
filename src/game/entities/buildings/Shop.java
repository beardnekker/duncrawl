package game.entities.buildings;

public class Shop extends Building {

    private static final char SHOP_SYMBOL = '$';
    private static final int HEALTH_POTIONS_AVAILABLE = 4;
    private static final int DAMAGE_POTIONS_AVAILABLE = 4;

    public Shop(int posX, int posY) {
        super("Shop", SHOP_SYMBOL, HEALTH_POTIONS_AVAILABLE, DAMAGE_POTIONS_AVAILABLE, posX,  posY);
    }
}
