package creatures.attributes;

import java.util.List;

public class Race {
    /*' Race '
class Race {
    - raceName : String
    - healthModifier : int
    - strengthModifier : int
    - dexterityModifier : int
    - intelligenceModifier : int
    - specialAbility : String
    + applyRacialBonuses(stats : Stats) : void
    + getRaceName() : String
    + getSpecialAbility() : String
}*/
    private String raceName;
    private int healthModifier;
    private int strengthModifier;
    private int dexterityModifier;
    private int intelligenceModifier;
    private String specialAbility;

    // --- Constructor ---
    public Race (String raceName, int healthModifier, int strengthModifier, int dexterityModifier, int intelligenceModifier, String specialAbility){
        this.raceName = raceName;
        this.healthModifier = healthModifier;
        this.strengthModifier = strengthModifier;
        this.dexterityModifier = dexterityModifier;
        this.intelligenceModifier = intelligenceModifier;
        this.specialAbility = specialAbility;
    }

    public static Race createHuman(){
        return new Race(
                "Human", 1, 1, 1, 1, "Adaptability:Balanced growth across all stats!"
        );
    }

    public static Race createDwarf(){
        return new Race("Dwarf", 2, 1,0,1, "Endurance: Natually hardy mountain folk");
    }

    public static Race createElf(){
        return new Race(
                "Elf",0,1,1,2,"Arcane Heritage: Born with natual magic affinity"
        );
    }

    public static Race createOrc(){
        return new Race(
                "Orc", 1, 2,1,0, "Battle fury: powerfull warriors with fierce Hamsters"
        );
    }

    //Exsample of use: Race chosenRace = Race.createElf();
    //Stats stats = new Stats(10, 5, 4, 7);
    //chosenRace.applyRacialBonuses(stats);


    // --- Modifiers to apply stats ---
    public void applyRacialBonuses(Stats stats){
        stats.setCurrentHealth(stats.getCurrentHealth() + healthModifier);
        stats.setStrength(stats.getStrength() + strengthModifier);
        stats.setDexterity(stats.getDexterity() + dexterityModifier);
        stats.setIntelligence(stats.getIntelligence() + intelligenceModifier);
    }

    public static List<Race> getAllRaces(){
        return List.of(createHuman(), createDwarf(), createElf(), createOrc());
    }
    /* Example of use in Main:
    List<Race> races = Race.getAllRaces();

ArrayList<String> raceNames = new ArrayList<>();
for (Race r : races) {
    raceNames.add(r.getRaceName());
}

int choice = promptNumeric("Choose your race!:") - 1;
Race chosenRace = races.get(choice);


     */

    public String getRaceName(){
        return raceName;
    }
    public String getSpecialAbility(){
        return specialAbility;
    }

}
