package creatures.attributes;

import java.util.List;

public class CharacterClass {
    //the name of the class: Warrior, Mage, Ranger
    private String characterClassName;

    //Stat modifiers added by choosing this class
    private int healthModifier;
    private int strengthModifier;
    private int dexterityModifier;
    private int intelligenceModifier;

    //Special skills a CharacterClass has.
    private List<String> specialSkills;

//--- Constructur--- defines how much this class modifies the base stats
    public CharacterClass(String characterClassName, int healthModifier,
                          int strengthModifier,
                          int dexterityModifier,
                          int intelligenceModifier,
                          List<String> specialSkills
    ){
        this.characterClassName = characterClassName;
        this.healthModifier = healthModifier;
        this.strengthModifier = strengthModifier;
        this.dexterityModifier = dexterityModifier;
        this.intelligenceModifier = intelligenceModifier;
        this.specialSkills = specialSkills;
    }

    //--- Classes ---
    public static CharacterClass createWarrior(){
        return new CharacterClass("Warrior", 2, 3, 0, 0, List.of("Heavy Strike", "Shield Bash"));
    }
    public static CharacterClass createMage(){
        return new CharacterClass("Mage",0 ,0,1,4, List.of("Fireball", "Arcane Shield"));
    }
    public static CharacterClass createRanger(){
        return new CharacterClass("Ranger", 1,0,4,0, List.of("Precision Shot", "Piercing arrow"));
    }

    public static List<CharacterClass> getAllCharacterClasses() {
        return List.of(createWarrior(), createMage(), createRanger());
    }
    /*
    //Example of use: CharacterClass chosenClass = CharacterClass.createWarrior();
    //Stats stats = new Stats(x, x, x, x);
    //chosenClass.applyClassBonuses(stats);
*/

    // --- Modifiers --- Apply the class stat bonuses to the given class
    public void applyCharacterClassBonuses(Stats stats){
        stats.setCurrentHealth(stats.getCurrentHealth() + healthModifier);
        stats.setStrength(stats.getStrength() + strengthModifier);
        stats.setDexterity(stats.getDexterity() + dexterityModifier);
        stats.setIntelligence(stats.getIntelligence() + intelligenceModifier);
    }


    // --- Getters ---
    public String getCharacterClassName(){
        return characterClassName;
    }
    public List<String> getSpecialSkills() {
        return specialSkills;
    }
}
