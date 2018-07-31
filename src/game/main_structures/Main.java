package game.main_structures;

abstract class Main {

    private static MainMenu mainMenu = new MainMenu();

    public static void main(String[] args) {

        //Start the mainMenu
        mainMenu.startScreen();
    }

    static MainMenu getMainMenu() {
        return mainMenu;
    }
}