package util;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {

    private static Scanner sc = new Scanner(System.in);

    public ArrayList<String> promptChoice( ArrayList<String> options, int limit, String msg){
        displayMsg(msg);
        displayList(options, "");
        ArrayList<String> choices = new ArrayList<>(); // An Array to Store the players choice

        while(choices.size() < limit){  //checking if the player needs to choose again!
            int choice = promptNumeric(msg);
            choices.add(options.get(choice-1));
        }
        return choices;
    }


    public void displayList(ArrayList<String>list, String msg){
        for (int i =0; i< list.size(); i++){
            System.out.println(i + 1 +". "+list.get(i));
        }
    }


    public void displayMsg(String msg){ //The simple Display Message
        System.out.println(msg);
    }

    public int promptNumeric(String msg){
        displayMsg(msg);                        //Give the Player a question
        String input = sc.nextLine();           // Give the player a place to place their answer!
        int numInput = Integer.parseInt(input); //Convert the answer to a Number!

        return numInput;
    }

    public String promptText(String msg){
        displayMsg(msg);
        String input = sc.nextLine();  //same as above Give question and make a place to answer! though not converting to a number but just Text.

        return input;
    }

    public boolean promptBinary(String msg){
        displayMsg(msg);
        String input = sc.nextLine();
                if(input.equalsIgnoreCase("Y")){
                    return true;
                }
                else if(input.equalsIgnoreCase("N")) {
        return false;
        }else{return promptBinary(msg);
                }

    }
}
