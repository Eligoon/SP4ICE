import controller.GameController;
import controller.Choices.Choice;
import creatures.Player;
import world.Location;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create the game controller
        GameController game = new GameController();

        // 2. Initialize the game (loads story, locations, player, etc.)
        game.initializeGame();

        // 3. Get reference to player (optional)
        Player player = game.getPlayer();

        // 4. Start main game loop
        while (!player.getIsDead()) {
            // Show location description
            Location currentLocation = game.getCurrentLocation();
            currentLocation.displayDescription();

            // Get available choices and prompt player
            List<Choice> choices = game.getLocationChoices();
            Choice selectedChoice = game.getUi().promptChoiceOb(choices, "What do you want to do?");

            // Execute the player's choice
            selectedChoice.execute(game);

             game.saveGameState();
        }

        System.out.println("Game over!");
    }
}
