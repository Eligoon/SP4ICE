package controller;

import util.DataSaving;

public class GameController {

   private DataSaving db = new DataSaving();
   private String url = "jdbc:sqlite:identifier.sqlite";


    // db.connect cannot exist without being in a method, just put it in here for now.
    // Needs to be present in everything containing data, so new game, load game, save game etc.
    public void newGame() {
        db.connect(url);
    }

}
