import controller.GameController;

public class Main {
    public static void main(String[] args) {
        // Create the game controller
        GameController gc = new GameController();

        // Asks if you want to load or make a new game if new game -->
        // Initialize the game (welcome message, story, player, starting location)
        // If you chose to load t will load the previous game from the database
        gc.handleGameStart();

        // Start the main game loop
        gc.startGameLoop();
    }
}
