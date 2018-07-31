package game.main_structures;

import game.data.RandomData;
import game.entities.*;
import game.entities.buildings.Building;
import game.entities.buildings.Shop;
import game.entities.enemies.*;
import game.entities.items.DamagePotion;
import game.entities.items.HealthPotion;
import game.entities.items.Item;
import game.entities.tiles.*;

import java.util.HashMap;
import java.util.Random;

class Map {

    private static final int ODD_MAP_LOWER_BOUND_WIDTH = 7;
    private static final int ODD_MAP_UPPER_BOUND_WIDTH = 15;
    private static final int ODD_MAP_LOWER_BOUND_HEIGHT = 3;
    private static final int ODD_MAP_UPPER_BOUND_HEIGHT = 7;

    private static final int EVEN_MAP_LOWER_BOUND_WIDTH = 17;
    private static final int EVEN_MAP_UPPER_BOUND_WIDTH = 30;
    private static final int EVEN_MAP_LOWER_BOUND_HEIGHT = 8;
    private static final int EVEN_MAP_UPPER_BOUND_HEIGHT = 15;

    static final int NUMBER_OF_COORDINATES = 2;

    private Entity[][] map;
    private int width;
    private int height;

    private boolean collision;
    private int[] collisionLoc = new int[NUMBER_OF_COORDINATES];
    private String collisionKind;

    private boolean looking;
    private Entity lookAt;

    //For level 1 map
    Map(char[][] charMatrix, Player player) {
        this.map = convertCharMatrixToEntityMatrix(charMatrix, player);
        this.width = this.map[0].length;
        this.height = this.map.length;
    }

    //Random map for non-level 1
    Map(int level, Player player) {
        getRandomMap(level, player);
    }

    void draw(Player player) {

        /*Actually draw the map (5 by 5 grid/matrix around the player)
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {

                //Negative and positive map bounds for all corners/direction of the map
                if (player.getPosX() + x >= 0 && player.getPosY() + y >= 0 &&
                        player.getPosX() < this.height && y + player.getPosY() < this.width) {

                    System.out.print(this.map[player.getPosX() + x][player.getPosY() + y] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        */
        for (int x = 0; x < this.height; x++) {

            for (int y = 0; y < this.width; y++) {
                System.out.print(this.map[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void removeEntityAt(int x, int y) {
        this.map[x][y] = new Ground(x, y);
    }

    void attemptMove(String direction, Player player) {

        int newX = player.getPosX();
        int newY = player.getPosY();

        switch (direction) {

            case "up":
                newX--;
                break;

            case "down":
                newX++;
                break;

            case "right":
                newY++;
                break;

            case "left":
                newY--;
                break;

            //This should never happen
            default:
                System.out.println("Direction is invalid.");
                break;
        }

        Entity entity = this.map[newX][newY];

        //There will always be a collision
        this.collision = true;
        this.collisionLoc[0] = newX;
        this.collisionLoc[1] = newY;

        if (entity instanceof Enemy) {

            this.collisionKind = "enemy";

        } else if (entity instanceof Item) {

            this.collisionKind = "map";

        } else if (entity instanceof Tile) {

            this.collisionKind = "map";

        } else if (entity instanceof Building) {

            this.collisionKind = "building";

        } else {
            System.out.println("attemptMove(): This should never happen.");
        }
    }

    void attemptGrab(String direction, Player player) {

        int newX = player.getPosX();
        int newY = player.getPosY();

        switch (direction) {

            case "up":
                newX--;
                break;

            case "down":
                newX++;
                break;

            case "right":
                newY++;
                break;

            case "left":
                newY--;
                break;

            //This should never happen
            default:
                System.out.println("Direction is invalid.");
                break;
        }

        //There will always be a collision when you grab
        this.collision = true;
        this.collisionKind = "grab";
        this.collisionLoc[0] = newX;
        this.collisionLoc[1] = newY;
    }

    void attemptLook(String direction, Player player) {

        int newX = player.getPosX();
        int newY = player.getPosY();

        switch (direction) {

            case "up":
                newX--;
                break;

            case "down":
                newX++;
                break;

            case "right":
                newY++;
                break;

            case "left":
                newY--;
                break;

            //This should never happen
            default:
                System.out.println("Direction is invalid.");
                break;
        }
        this.looking = true;
        this.lookAt = getEntityAt(newX, newY);
    }

    private void getRandomMap(int level, Player player) {

        int[] playerStartingLocationForRandomMap = new int[Map.NUMBER_OF_COORDINATES];
        int[] winLocationForRandomMap = new int[Map.NUMBER_OF_COORDINATES];
        int[] buildingLocationForRandomMap = new int[Map.NUMBER_OF_COORDINATES];

        Random random = new Random();

        //Even map
        int randomHeight;
        int randomWidth;

        if (level % 2 == 0) {

            //Get the randomHeight and randomWidth
            randomHeight = random.nextInt(EVEN_MAP_UPPER_BOUND_HEIGHT - EVEN_MAP_LOWER_BOUND_HEIGHT) +
                    EVEN_MAP_LOWER_BOUND_HEIGHT;
            randomWidth = random.nextInt(EVEN_MAP_UPPER_BOUND_WIDTH - EVEN_MAP_LOWER_BOUND_WIDTH) +
                    EVEN_MAP_LOWER_BOUND_WIDTH;

            playerStartingLocationForRandomMap[0] = random.nextInt(randomHeight - 2) + 1;
            playerStartingLocationForRandomMap[1] = random.nextInt(randomWidth - 2) + 1;
            winLocationForRandomMap[0] = random.nextInt(randomHeight - 2) + 1;
            winLocationForRandomMap[1] = random.nextInt(randomWidth - 2) + 1;
            buildingLocationForRandomMap[0] = random.nextInt(randomHeight - 2) + 1;
            buildingLocationForRandomMap[1] = random.nextInt(randomWidth - 2) + 1;

            //Odd map
        } else {

            randomHeight = random.nextInt(ODD_MAP_UPPER_BOUND_HEIGHT - ODD_MAP_LOWER_BOUND_HEIGHT) +
                    ODD_MAP_LOWER_BOUND_HEIGHT;
            randomWidth = random.nextInt(ODD_MAP_UPPER_BOUND_WIDTH - ODD_MAP_LOWER_BOUND_WIDTH) +
                    ODD_MAP_LOWER_BOUND_WIDTH;

            playerStartingLocationForRandomMap[0] = random.nextInt(randomHeight - 2) + 1;
            playerStartingLocationForRandomMap[1] = random.nextInt(randomWidth - 2) + 1;
            winLocationForRandomMap[0] = random.nextInt(randomHeight - 2) + 1;
            winLocationForRandomMap[1] = random.nextInt(randomWidth - 2) + 1;
            buildingLocationForRandomMap[0] = random.nextInt(randomHeight - 2) + 1;
            buildingLocationForRandomMap[1] = random.nextInt(randomWidth - 2) + 1;
        }

        RandomData.setPlayerStartLocation(playerStartingLocationForRandomMap);
        RandomData.setWinLocation(winLocationForRandomMap);
        RandomData.setBuildingLocation(buildingLocationForRandomMap);

        /*TODO: Conditions List
        You can only have walls on the two opposite sides.
        Player can't spawn on other entities, only ground '_'.
        Win location can't be on a wall.
        */

        //Set up map
        this.height = randomHeight;
        this.width = randomWidth;
        this.map = new Entity[randomHeight][randomWidth];

        //Draw map
        drawPerimeter(this.map);
        drawInside(this.map, playerStartingLocationForRandomMap, buildingLocationForRandomMap, winLocationForRandomMap, player);
    }

    private void drawInside(Entity[][] entityMatrix, int[] playerStartingPos, int[] buildingPos, int[] winLocation, Player player) {

        //Place the one time entities
        placePlayerAt(player, playerStartingPos[0], playerStartingPos[1]);
        setEntityAt(new Shop(buildingPos[0], buildingPos[1]));

        for (int row = 1; row < entityMatrix.length - 1; row++) {

            for (int column = 1; column < entityMatrix[0].length - 1; column++) {

                //Get the category of the entity we are going to place
                String category = getCategory();

                Entity entity;

                //Depending on the category use the specific entity weights in the sub-category, then randomly get the entity
                switch (category) {

                    case "enemy":
                        entity = getEnemy(row, column);
                        break;

                    case "item":
                        entity = getItem(row, column);
                        break;

                    case "empty":
                        entity = new Ground(row, column);
                        break;

                    case "tile":
                        entity = getOtherTile(row, column);
                        break;

                    //This should never happen
                    default:
                        entity = null;
                        System.out.println("Invalid entity type!");
                        break;
                }

                /*If the entity being placed won't be at the same location as the player or the shop and also the entity
                isn't a shop or wall placed at the win location*/
                if (!(collidingWithPlayerOrShop(row, column, buildingPos, playerStartingPos) || (entity instanceof Shop
                        && entity instanceof Wall && row == winLocation[0] && column == winLocation[1]))) {

                    setEntityAt(entity);
                }
            }
        }
    }

    private Entity[][] convertCharMatrixToEntityMatrix(char[][] map, Player player) {

        Entity[][] temp = new Entity[map.length][map[0].length];

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                temp[row][col] = getEntityFromAppearance(map[row][col], player, row, col);
            }
        }
        return temp;
    }

    private Entity getEntityFromAppearance(char appearance, Player player, int posX, int posY) {

        Entity entity;

        switch (appearance) {

            case 'g':
                entity = new Goblin(posX, posY);
                break;

            case 's':
                entity = new Skeleton(posX, posY);
                break;

            case 'D':
                entity = new Dragon(posX, posY);
                break;

            case 'd':
                entity = new BabyDragon(posX, posY);
                break;

            case '+':
                entity = new HealthPotion(posX, posY);
                break;

            case '-':
                entity = new DamagePotion(posX, posY);
                break;

            case 'W':
                entity = new Wall(posX, posY);
                break;

            case ')':
                entity = new Banana(posX, posY);
                break;

            case '^':
                entity = new Spike(posX, posY);
                break;

            case '_':
                entity = new Ground(posX, posY);
                break;

            case '$':
                entity = new Shop(posX, posY);
                break;

            case 'P':
                entity = player;
                break;

            //This should never happen
            default:
                entity = null;
                break;
        }
        return entity;
    }

    private static void drawPerimeter(Entity[][] entityMatrix) {

        for (int row = 0; row < entityMatrix.length; row++) {
            for (int col = 0; col < entityMatrix[0].length; col++) {

                if (col == 0 || col == entityMatrix[0].length - 1 || row == entityMatrix.length - 1 || row == 0) {
                    entityMatrix[row][col] = new Wall(row, col);
                }
            }
        }
    }

    private static String getCategory() {

        java.util.Map<String, Double> generalWeights = new HashMap<>(4);

        //Put the percentages for the categories
        generalWeights.put("enemy", .1);
        generalWeights.put("item", .1);
        generalWeights.put("empty", .5);
        generalWeights.put("tile", .3);

        double ratio = new Random().nextDouble();
        double total = 0;

        for (String category : generalWeights.keySet()) {
            total += generalWeights.get(category);
            if (total >= ratio) {
                return category;
            }
        }
        return null;
    }

    private static Item getItem(int posX, int posY) {

        java.util.Map<Item, Double> itemWeights = new HashMap<>(2);
        itemWeights.put(new HealthPotion(posX, posY), .5);
        itemWeights.put(new DamagePotion(posX, posY), .5);

        double ratio = new Random().nextDouble();
        double total = 0;

        for (Item item : itemWeights.keySet()) {
            total += itemWeights.get(item);
            if (total >= ratio) {
                return item;
            }
        }
        return null;
    }

    private static Tile getOtherTile(int posX, int posY) {

        java.util.Map<Tile, Double> tileWeights = new HashMap<>(3);
        tileWeights.put(new Wall(posX, posY), .6);
        tileWeights.put(new Banana(posX, posY), .2);
        tileWeights.put(new Spike(posX, posY), .2);

        double ratio = new Random().nextDouble();
        double total = 0;

        for (Tile tile : tileWeights.keySet()) {
            total += tileWeights.get(tile);
            if (total >= ratio) {
                return tile;
            }
        }
        return null;
    }

    private static Enemy getEnemy(int posX, int posY) {

        java.util.Map<Enemy, Double> enemyWeights = new HashMap<>(4);
        enemyWeights.put(new Goblin(posX, posY), .275);
        enemyWeights.put(new Skeleton(posX, posY), .325);
        enemyWeights.put(new Dragon(posX, posY), .15);
        enemyWeights.put(new BabyDragon(posX, posY), .25);

        double ratio = new Random().nextDouble();
        double total = 0;

        for (Enemy enemy : enemyWeights.keySet()) {
            total += enemyWeights.get(enemy);
            if (total >= ratio) {
                return enemy;
            }
        }
        return null;
    }

    private boolean collidingWithPlayerOrShop(int x, int y, int[] buildingPos, int[] playerStartPos) {

        boolean colliding;

        colliding = (x == playerStartPos[0] && y == playerStartPos[1])
                || (x == buildingPos[0] && y == buildingPos[1]);

        return colliding;
    }

    private void setEntityAt(Entity e) {
        this.map[e.getPosX()][e.getPosY()] = e;
    }

    Entity getEntityAt(int x, int y) {
        return this.map[x][y];
    }

    int[] getCollisionLoc() {
        return this.collisionLoc;
    }

    boolean isCollision() {
        return this.collision;
    }

    String getCollisionKind() {
        return this.collisionKind;
    }

    boolean isLooking() {
        return this.looking;
    }

    Entity getLookAt() {
        return this.lookAt;
    }

    void placePlayerAt(Player player, int x, int y) {

        //Get the player's old position
        int[] oldPosition = {player.getPosX(), player.getPosY()};

        if (player.isNotOnLastCollider()) {
            setEntityAt(player.getLastCollider(), oldPosition[0], oldPosition[1]);
        }

        //set the entity the player is going to collide with next
        player.setNextCollider(getEntityAt(x, y));

        //Actually display the player
        setEntityAt(player);

        //Change it's coordinates
        player.setPosX(x);
        player.setPosY(y);
    }

    void notLookingAnymore() {
        this.looking = false;
    }

    void notCollidingAnymore() {
        this.collision = false;
    }
}