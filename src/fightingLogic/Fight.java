package fightingLogic;
import fightingComponents.*;
import fightingComponents.Character;

import java.util.*;

public class Fight {
    private Planet planet;
    private Character villain;
    private List<Character> avengers; //if the user does no choose a team of avengers the list will consist of one element
    private int currentAvengerIdx;

    public Fight()
    {
        this.avengers = new ArrayList<Character>();
    }
    public Fight(Planet planet, Character villain, List<Character> avengers) {
        this.planet = planet;
        this.avengers = avengers;
        this.villain = villain;
        currentAvengerIdx = 0;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Character getVillain() {
        return villain;
    }

    public void setVillain(Character villain) {
        this.villain = villain;
    }

    public List<Character> getAvengers() {
        return avengers;
    }

    public void setAvengers(List<Character> avengers) {
        this.avengers = avengers;
    }

    public int addAvenger(Character hero){
        try{
            for (Character avenger:avengers)
                if(hero.getId() == avenger.getId())
                    throw(new Exception("Avenger already chosen"));

            this.avengers.add(hero);
            return 1;
        }

        catch(Exception ex){
            System.out.println("Something went wrong" + ex.getMessage());
            return 0;
        }
    }

    public void setModifiersBeforeFight(){
        for (Character hero : avengers) {
            hero.setAttack(hero.getAttack() + planet.getHeroModifiers().getAttackModifier());
            hero.setHealth(hero.getHealth() + planet.getHeroModifiers().getHealthModifier());
        }
        villain.setAttack(villain.getAttack() + planet.getVillainModifiers().getAttackModifier());
        villain.setHealth(villain.getHealth() + planet.getVillainModifiers().getHealthModifier());
    }

    public int villainAttacks(Character hero){

        if(villain.getHealth()<=0 || hero.getHealth()<=0)
            return -1;

        Random r = new Random();
        int low = 60;
        int high = 100;

        int villainPercentage = r.nextInt(high - low) + low;
        int villainAttack = (villain.getAttack() * villainPercentage) / 100;

        hero.setHealth(hero.getHealth()-villainAttack);

        return villainAttack;

    }

    public int heroAttacks(Character hero){
        if(hero.getHealth()<=0 || villain.getHealth()<=0)
            return -1;

        Random r = new Random();
        int low = 60;
        int high = 100;

        int heroPercentage = r.nextInt(high - low) + low;
        int heroAttack = (hero.getAttack() * heroPercentage) / 100;

        villain.setHealth(villain.getHealth()-heroAttack);

        return heroAttack;
    }
    public boolean avengersHealthIsPositive(){
        for (Character avenger : avengers)
            if (avenger.getHealth() > 0)
                return true;
        return false;
    }

    public Character getCurrentAvenger(){
        return avengers.get(currentAvengerIdx);
    }

    public int getCurrentAvengerIdx(){return currentAvengerIdx;}

    public void setCurrentAvengerIdx(int idx){this.currentAvengerIdx=idx;}

}
