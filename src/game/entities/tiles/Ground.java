package game.entities.tiles;

public class Ground extends Tile {

    private static final char GROUND_SYMBOL = '_';

    public Ground(int posX, int posY) {
        super("Ground", GROUND_SYMBOL, posX, posY);
    }
}
