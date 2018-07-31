package game.entities.enemies;

public class Dragon extends Enemy {

    private static final char DRAGON_SYMBOL = 'D';

    static final int ATTACK_CHANCE = 75;
    static final int DEFEND_CHANCE = 15;
    static final int STALL_CHANCE = 10;

    static final int COINS_GIVEN = 6;

    private static final int HEALTH = 50;
    private static final int POWER = 5;
    private static final int ARMOR = 5;

    public Dragon(int posX, int posY) {
        super("Dragon", DRAGON_SYMBOL, HEALTH, POWER, ARMOR, posX, posY);
    }
}