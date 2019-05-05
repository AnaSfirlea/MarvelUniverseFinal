package fightingComponents;

public class VillainModifiers extends Modifiers {

    public VillainModifiers(int attackModifier,int healthModifier) {
        super(attackModifier,healthModifier);
    }

    @Override
    public String toString() {
        return "attackModifier=" + super.getAttackModifier() +","+
                " healthModifier=" + super.getHealthModifier();
    }
}

