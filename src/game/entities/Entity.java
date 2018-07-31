package game.entities;

public class Entity {

    private String name;
    private char appearance;
    private int posX;
    private int posY;

    public Entity(String name, char appearance, int posX, int posY) {
        this.name = name;
        this.appearance = appearance;
        this.posX = posX;
        this.posY = posY;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "" + this.appearance;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}