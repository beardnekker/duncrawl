package game.entities.tiles;

public class Wall extends Tile {

    private static final char WALL_SYMBOL = 'W';

    public Wall(int posX, int posY) {
        super("Wall", WALL_SYMBOL, posX, posY);
    }
}
