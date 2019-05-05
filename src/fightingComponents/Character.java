package fightingComponents;

import java.util.Observable;
import java.util.Observer;

public class Character {
        private int id;
        private String name;
        private int attack;
        private int health;
        private String description;
        private boolean flag ; //isVillain true or false
        private String imagePath;

        public Character(int attack,String description,int health,int id, boolean flag,String name) {
            this.id = id;
            this.name = name;
            this.attack = attack;
            this.health = health;
            this.description = description;
            this.flag = flag;
        }

    public Character(int attack, String description, int health, int id, boolean flag, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.description = description;
        this.flag = flag;
        this.imagePath = imagePath;
    }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAttack() {
            return attack;
        }

        public void setAttack(int attack) {
            this.attack = attack;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

    public String getImagePath() {
        return imagePath;
    }


    @Override
        public String toString() {
            return  " Name='" + name + '\'' +"\n"+
                    " Id=" + id +"\n"+
                    " Attack=" + attack +"\n"+
                    " Health=" + health +"\n"+
                    " Description='" + description + '\'' +"\n"+
                    " Is villain=" + flag;
        }
    }


