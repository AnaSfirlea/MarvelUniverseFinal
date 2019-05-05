package dataReader;
import fightingComponents.HeroModifiers;
import fightingComponents.Planet;
import fightingComponents.Character;
import fightingComponents.VillainModifiers;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    private List<Character> characters = new ArrayList<Character>();
    private List<Planet>  planets = new ArrayList<Planet>();

    public DataReader() {
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void readCharacters()
    {
        String charactersFile = "C:\\Users\\Anamaria-Dell\\IdeaProjects\\MarvelUniverseFX\\src\\characters.xml";
        File file = new File(charactersFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc= documentBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList charactNodeList = doc.getElementsByTagName("character");
            for (int i = 0; i < charactNodeList.getLength(); i++) {


                int attack = Integer.parseInt(doc.getElementsByTagName("attack").item(i).getTextContent());
                String descr = doc.getElementsByTagName("description").item(i).getTextContent();
                int health = Integer.parseInt(doc.getElementsByTagName("health").item(i).getTextContent());
                int id = Integer.parseInt(doc.getElementsByTagName("id").item(i).getTextContent());
                boolean isVillain = Boolean.parseBoolean(doc.getElementsByTagName("isVillain").item(i).getTextContent());
                String name = doc.getElementsByTagName("name").item(i).getTextContent();
                String imagePath = doc.getElementsByTagName("image").item(i).getTextContent();
                characters.add(new Character(attack,descr,health,id,isVillain,name,imagePath));

            }

        }
        catch(Exception ex)
        {
            System.out.println("Exception at reading characters //thrown from dataReader");
        }

    }

    public void readPlanets()
    {
        String planetsFile = "C:\\Users\\Anamaria-Dell\\IdeaProjects\\MarvelUniverseFX\\src\\planets.xml";
        File file = new File(planetsFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc= documentBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList planetsNodeList = doc.getElementsByTagName("planet");
            for (int i = 0; i < planetsNodeList.getLength(); i++) {

                String descr = doc.getElementsByTagName("description").item(i).getTextContent();
                int id = Integer.parseInt(doc.getElementsByTagName("id").item(i).getTextContent());
                String name = doc.getElementsByTagName("name").item(i).getTextContent();
                int heroAttackModifier = Integer.parseInt(doc.getElementsByTagName("heroAttackModifier").item(i).getTextContent());
                int heroHealthModifier = Integer.parseInt(doc.getElementsByTagName("heroHealthModifier").item(i).getTextContent());
                int villainAttackModifier = Integer.parseInt(doc.getElementsByTagName("villainAttackModifier").item(i).getTextContent());
                int villainHealthModifier = Integer.parseInt(doc.getElementsByTagName("villainHealthModifier").item(i).getTextContent());
                String imagePath = doc.getElementsByTagName("image").item(i).getTextContent();

                HeroModifiers heroModifiers = new HeroModifiers(heroAttackModifier,heroHealthModifier);
                VillainModifiers villainModifiers = new VillainModifiers(villainAttackModifier,villainHealthModifier);
                Planet newPlanet = new Planet(descr,id,name,heroModifiers, villainModifiers,imagePath);
                planets.add(newPlanet);

            }

        }
        catch(Exception ex)
        {
            System.out.println("Exception at reading characters //thrown from dataReader");
        }

    }

    public List<Character> getHeroes(){
        List<Character> heroes = new ArrayList<Character>();
        for(Character hero : characters)
            if(!hero.isFlag())
                heroes.add(hero);

        return heroes;
    }

    public List<Character> getVillains(){
        List<Character> villains = new ArrayList<Character>();
        for(Character villain : characters)
            if(villain.isFlag())
                villains.add(villain);

        return villains;
    }


}
