package game.entities.enemies;

public class Goblin extends Enemy {

    private static final char GOBLIN_SYMBOL = 'g';

    static final int ATTACK_CHANCE = 65;
    static final int DEFEND_CHANCE = 23;
    static final int STALL_CHANCE = 12;

    static final int COINS_GIVEN = 2;

    private static final int HEALTH = 25;
    private static final int POWER = 3;
    private static final int ARMOR = 4;

    public Goblin(int posX, int posY) {
        super("Goblin", GOBLIN_SYMBOL, HEALTH, POWER, ARMOR, posX, posY);
    }
}