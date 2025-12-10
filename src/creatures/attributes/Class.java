package creatures.attributes;

import java.util.List;

public class Class {
    private String className;
    private int baseHealth;
    private String primaryStat;
    private List<String> specialSkills;
//--- Constructur---)
    public Class(String className, int baseHealth, String primaryStat, List<String> specialSkills){
        this.className = className;
        this.baseHealth = baseHealth;
        this.primaryStat = primaryStat;
        this.specialSkills = specialSkills;
    }

    public void applyClassBonuses(Stats stats){
        //TODO: implement logic
    }

    //gettes
    public String getClassName(){
        return className;
    }
    public List getSpecialSkills(){
        return specialSkills;
    }
}
