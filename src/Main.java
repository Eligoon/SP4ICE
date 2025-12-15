import controller.GameController;

public class Main {
    public static void main(String[] args) {
        // Create the game controller
        GameController gc = new GameController();

        // Initialize the game (welcome message, story, player, starting location)
        gc.initializeGame();

        // Start the main game loop
        gc.startGameLoop();
    }
}
