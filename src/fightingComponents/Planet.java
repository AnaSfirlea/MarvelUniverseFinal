package fightingComponents;
public class Planet {
        private int id;
        private String name;
        private String description;
        private HeroModifiers heroModifiers;
        private VillainModifiers villainModifiers;
        private String imagePath;


    public Planet(String description, int id, String name, HeroModifiers heroModifiers, VillainModifiers villainModifiers) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.heroModifiers = heroModifiers;
            this.villainModifiers = villainModifiers;
        }

    public Planet(String description, int id, String name, HeroModifiers heroModifiers, VillainModifiers villainModifiers,String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.heroModifiers = heroModifiers;
        this.villainModifiers = villainModifiers;
        this.imagePath = imagePath;
    }

        public HeroModifiers getHeroModifiers() {
            return heroModifiers;
        }

        public void setHeroModifiers(HeroModifiers heroModifiers) {
            this.heroModifiers = heroModifiers;
        }

        public VillainModifiers getVillainModifiers() {
            return villainModifiers;
        }

        public void setVillainModifiers(VillainModifiers villainModifiers) {
            this.villainModifiers = villainModifiers;
        }

        @Override
        public String toString() {
            return  " Name='" + name + '\'' + "\n"+
                    " Id=" + id + "\n"+
                    " Description='" + description + '\'' +"\n"+
                    " Hero Modifiers : " + heroModifiers +"\n"+
                    " Villain Modifiers : " + villainModifiers;
        }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }
}
