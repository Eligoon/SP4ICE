package creatures.attributes;

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
    // --- Modifiers to apply stats ---
    public void applyRacialBonuses(Stats stats){
        //TODO: Implement logic
    }
    public String getRaceName(){
        return raceName;
    }
    public String getSpecialAbility(){
        return specialAbility;
    }

}
