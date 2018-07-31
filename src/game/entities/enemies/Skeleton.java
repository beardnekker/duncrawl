package game.entities.enemies;

public class Skeleton extends Enemy {

    private static final char SKELETON_SYMBOL = 's';

    static final int ATTACK_CHANCE = 69;
    static final int DEFEND_CHANCE = 20;
    static final int STALL_CHANCE = 11;

    static final int COINS_GIVEN = 3;

    private static final int HEALTH = 30;
    private static final int POWER = 4;
    private static final int ARMOR = 4;

    public Skeleton(int posX, int posY) {
        super("Skeleton", SKELETON_SYMBOL, HEALTH, POWER, ARMOR, posX, posY);
    }
}