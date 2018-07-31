package game.entities.tiles;

public class Banana extends Tile {

    private static final char BANANA_SYMBOL = '(';

    public Banana(int posX, int posY) {
        super("Banana", BANANA_SYMBOL, posX, posY);
    }
}
