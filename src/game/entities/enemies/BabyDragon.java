package game.entities.enemies;

public class BabyDragon extends Enemy {

    private static final char BABY_DRAGON_SYMBOL = 'd';

    static final int ATTACK_CHANCE = 71;
    static final int DEFEND_CHANCE = 16;
    static final int STALL_CHANCE = 13;

    static final int COINS_GIVEN = 4;

    private static final int HEALTH = 40;
    private static final int POWER = 4;
    private static final int ARMOR = 5;

    public BabyDragon(int posX, int posY) {
        super("Baby Dragon", BABY_DRAGON_SYMBOL, HEALTH, POWER, ARMOR, posX, posY);
    }
}