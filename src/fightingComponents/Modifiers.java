package fightingComponents;

public abstract class Modifiers {
    private int attackModifier;
    private int healthModifier;

    public Modifiers(int attackModifier, int healthModifier) {
        this.attackModifier = attackModifier;
        this.healthModifier = healthModifier;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public int getHealthModifier() {
        return healthModifier;
    }

    public void setHealthModifier(int healthModifier) {
        this.healthModifier = healthModifier;
    }
}

