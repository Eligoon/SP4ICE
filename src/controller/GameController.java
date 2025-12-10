    package controller;
    import world.Location;

    import util.DataSaving;
    import world.Story;

    public class GameController {
        private Location currentLocation;
        private Story emeraldTear;

        public GameController(){
            emeraldTear = new Story();
        }

        public void initializeGame(){
            // Displays the welcome message
            emeraldTear.displayWelcomeMessage();

            // Loads story, with locations and connects all locations together
            emeraldTear.loadStory();

            // Set starting location to be current location
            currentLocation = emeraldTear.getLocation("The Clearing");


        }




       private DataSaving db = new DataSaving();
       private String url = "jdbc:sqlite:identifier.sqlite";


        // db.connect cannot exist without being in a method, just put it in here for now.
        // Needs to be present in everything containing data, so new game, load game, save game etc.
        public void newGame() {
            db.connect(url);
        }

    }
