package fightingComponents;

public class HeroModifiers extends Modifiers {

    public HeroModifiers(int attackModifier,int healthModifier) {
        super(attackModifier,healthModifier);

    }

    @Override
    public String toString() {
        return "attackModifier=" + super.getAttackModifier() +","+
                " healthModifier=" + super.getHealthModifier();
    }
}

