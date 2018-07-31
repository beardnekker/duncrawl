package game.data;

public abstract class RandomData {

    private static int[] winLocation;
    private static int[] playerStartLocation;
    private static int[] buildingLocation;

    public static int[] getWinLocation() {
        return winLocation;
    }

    public static void setWinLocation(int[] winLocation) {
        RandomData.winLocation = winLocation;
    }

    public static int[] getPlayerStartLocation() {
        return playerStartLocation;
    }

    public static void setPlayerStartLocation(int[] playerStartLocation) {
        RandomData.playerStartLocation = playerStartLocation;
    }

    public static int[] getBuildingLocation() {
        return buildingLocation;
    }

    public static void setBuildingLocation(int[] buildingLocation) {
        RandomData.buildingLocation = buildingLocation;
    }

    /*/*Singleton method (of is from the last two letters of instanceof)
    Used when keeping things non-static but you only want to create one instance of the class, so you would use this
    method to get access to the class
    public static LevelData of(int level) {

    if (level == 1) {

            if (level1Data == null) {
                level1Data = new Level1Data();
            }
            return level1Data;

        } else {

            if (randomData == null) {
                randomData = new RandomData();
            }
            return randomData;
        }
    }
    */
}