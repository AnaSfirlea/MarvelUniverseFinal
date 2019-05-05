package sample;

import fightingComponents.Character;
import fightingLogic.Fight;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class FightController {
    private Fight fight;

    public FightController(){this.fight = new Fight();}

    @FXML
    ListView<Character> fightingListView;

    @FXML
    ImageView heroImage;

    @FXML
    ImageView villainImage;

    @FXML
    ImageView planetImage;

    @FXML
    Label heroNameLabel;

    @FXML
    Label heroAttackLabel;

    @FXML
    Label heroHealthLabel;

    @FXML
    Label planetNameLabel;

    @FXML
    Label villainNameLabel;

    @FXML
    Label villainHealthLabel;

    @FXML
    Label villainAttackLabel;

    @FXML
    TextArea  attackingTextArea;

    @FXML
    TextArea heroesStateTextArea;

    @FXML
    TextArea  villainStateTextArea;

    private static int count =0;


    void setFight(Fight fight)
    {
        this.fight= fight;
    }

    private void setImagesAndLabels()
    {
        File file = new File(this.fight.getVillain().getImagePath());
        Image villainIMG = new Image(file.toURI().toString());
        villainImage.setImage(villainIMG);

        File file2 = new File(this.fight.getPlanet().getImagePath());
        Image planetIMG = new Image(file2.toURI().toString());
        planetImage.setImage(planetIMG);

        if(this.fight.getAvengers().size()==1)
        {
            File file3 = new File(this.fight.getAvengers().get(0).getImagePath());
            Image firstHeroIMG = new Image(file3.toURI().toString());
            heroImage.setImage(firstHeroIMG);
        }

        else
        {
            File file4 = new File("src/images/avengers2.png");
            Image avengersIMG = new Image(file4.toURI().toString());
            heroImage.setImage(avengersIMG);
        }

        this.planetNameLabel.setText("PLANET: "+this.fight.getPlanet().getName());
        this.villainNameLabel.setText("VILLAIN: "+this.fight.getVillain().getName());
        if(this.fight.getAvengers().size()>1)
            this.heroNameLabel.setText("AVENGERS");
        else
            this.heroNameLabel.setText("AVENGER: "+this.fight.getAvengers().get(0).getName());
    }

    private void updateStatesHero(int forceAttack)
    {
        Character currentHero = this.fight.getCurrentAvenger();
        this.heroesStateTextArea.setText(this.heroesStateTextArea.getText()+"\n"+ currentHero.getName()+
                " was attacked -> health: "+
               currentHero.getHealth()+ "\n" );

        this.attackingTextArea.setText(this.attackingTextArea.getText()+"\n"+this.fight.getVillain().getName()+
                " attacked  "+currentHero.getName()+" with force "+forceAttack+"\n");

    }
    private void updateStatesVillain(int forceAttack)
    {
        Character currentHero = this.fight.getCurrentAvenger();

        this.villainStateTextArea.setText(this.villainStateTextArea.getText()+"\n"+
                this.fight.getVillain().getName()+" was attacked ->  health: "+
                this.fight.getVillain().getHealth() +"\n");

        this.attackingTextArea.setText(this.attackingTextArea.getText()+"\n"+currentHero.getName()+
                " attacked "+this.fight.getVillain().getName()+ " wtih force "+ forceAttack+"\n");


    }

    private void test()
    {
        this.heroesStateTextArea.setText(this.heroesStateTextArea.getText()+"health :"+1+"\n");
    }

    private void fightingVillain()
    {
        Character hero = this.fight.getCurrentAvenger();

        int forceAttack = this.fight.villainAttacks(hero);
        /*Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                updateStatesHero();
                t.cancel();

            }
        };
        t.schedule(timerTask,1000);*/
        if(forceAttack!=1)
            updateStatesHero(forceAttack);
    }



    private void fightingHero()
    {
        Character hero = this.fight.getCurrentAvenger();

        int forceAttack = this.fight.heroAttacks(hero);
        if(forceAttack!=-1)
            updateStatesVillain(forceAttack);
    }

    private boolean beginFight()
    {
        this.fight.setModifiersBeforeFight();
        while(this.fight.avengersHealthIsPositive() && this.fight.getVillain().getHealth()>0)
        {
            fightingVillain();

            fightingHero();


            if(this.fight.getCurrentAvengerIdx() == this.fight.getAvengers().size()-1)
                this.fight.setCurrentAvengerIdx(0); //we start from the beginning until avengers or villain health is 0
            else
                this.fight.setCurrentAvengerIdx(this.fight.getCurrentAvengerIdx()+1);

        }
        return fight.avengersHealthIsPositive();
    }

    private void fightAndShowWinner()
    {
        boolean avengersWon = beginFight();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WINNER ");

        if(avengersWon)
        {
            if(this.fight.getAvengers().size()>1)
                alert.setHeaderText("Avengers won!");
            else
                alert.setHeaderText(this.fight.getAvengers().get(0).getName()+" won!");
        }

        else
            alert.setHeaderText(this.fight.getVillain().getName()+" won...");

        alert.showAndWait();
    }

    @FXML
    void initialize(){
        Platform.runLater(() -> {
           setImagesAndLabels();
           fightAndShowWinner();

        });
    }

}