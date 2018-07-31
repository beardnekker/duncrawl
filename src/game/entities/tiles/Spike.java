package game.entities.tiles;

public class Spike extends Tile{

    private static final char SPIKE_SYMBOL = '^';

    public Spike(int posX, int posY) {
        super("Spike", SPIKE_SYMBOL, posX, posY);
    }
}
