package app;

import GUI.controllers.MenuSceneController;
import entities.Pathogen;
import entities.Player;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.MusicPlayer;
import util.PopupBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    private Player player;
    private TextArea storyBox;
    private Pathogen currentPathogen;
    List<Pathogen> pathogensForChosenOrgan = new ArrayList<>();


    private int winningPointsReq;

    public Game() {
        super();
    }

    public Game(Player player) {
        this();
        setPlayer(player);

    }

    // Methods
    public void play(int winningPointsRequired, int healthValue, ArrayList<Pathogen> pathogenList, String currentOrgan,
                     TextArea text, TextField field, Label playerStatus, Player player, MusicPlayer mpTheme) {
        // Initiate primary game loop, check game ending conditions each time
        this.winningPointsReq = winningPointsRequired;
        this.storyBox = text;


        mpTheme.startMusic();

        this.player = player;
        // return questions specific to the organ that player pick from the menu scene.
        System.out.println(currentOrgan);
        this.pathogensForChosenOrgan = questionsInCurrentOrgan(pathogenList, currentOrgan);
        currentPathogen = pathogensForChosenOrgan.get(0);
        askPathogenQuestion(currentPathogen, text);
        playerStatus.setText(player.toString());

    }



    public boolean checkAnswer(String userAnswer, Label playerStatus, Player player, TextArea storyBox, TextArea feedbackTextArea) {

        try {
            if (userAnswer.length() > 0) {
                if (isCorrect(currentPathogen, userAnswer, feedbackTextArea)) {
                    int idx = pathogensForChosenOrgan.indexOf(currentPathogen);
                    player.addPoints(currentPathogen.getPoints());
                    playerStatus.setText(player.toString());

                    if (idx != pathogensForChosenOrgan.size()) {
                        askPathogenQuestion(pathogensForChosenOrgan.get(idx + 1), storyBox);
                        currentPathogen = pathogensForChosenOrgan.get(idx + 1);
                        idx++;
                    }

                    if (isWin(player, winningPointsReq)) {
                        storyBox.setText("WINNER");
                        PopupBox.popUp("WINNER!", "If you would like to play again select YES otherwise select NO", player);
                    }
                    if (idx == pathogensForChosenOrgan.size()) {
                        PopupBox.makeASelection("HALT", "Look to the smartest man in the Universe for more questions");

                    }
                } else {

                    currentPathogen.attack(player);
                    playerStatus.setText(player.toString());
                    //System.out.println("wrong");
                    feedbackTextArea.setText("Wrong, please try again");
                    if (isLose(player)) {
                        storyBox.setText("Game Over!");
                        PopupBox.popUp("LOSER", "Sorry you've lost. Would you like to play again?", player);
                    }

                }
            }
        } catch (IndexOutOfBoundsException e) {
            PopupBox.makeASelection("HALT", "Look to the smartest man in the Universe for more questions");
        }
        return false;
    }

    private void askPathogenQuestion(Pathogen currentThreat, TextArea textarea) {
        String location = currentThreat.getLocation();
        textarea.clear();
        textarea.setText(currentThreat.getDescription());
        textarea.appendText("\n" + currentThreat.getQuestion());
    }

    // public method used to test private method of questionsInCurrentOrgan.
    public int questionsInOrgan(ArrayList<Pathogen> pathogenList, String organ) {
        if (questionsInCurrentOrgan(pathogenList, organ) != null) {
            return questionsInCurrentOrgan(pathogenList, organ).size();
        }
        return 1;
    }

    private List<Pathogen> questionsInCurrentOrgan(ArrayList<Pathogen> pathogenList, String organ) {
        List<Pathogen> currentOrganList = new ArrayList<>();
        List<String> organslist;
        organslist = Arrays.asList("brain", "mouth", "throat", "lungs", "heart", "liver", "colon");

        if (organslist.contains(organ)) { // if the organs is valid organ.
            // iterate through PathogenList
            for (Pathogen pathogen : pathogenList) {
                if (pathogen.getLocation().equals(organ)) {
                    currentOrganList.add(pathogen);
                }
            }
        }
        return currentOrganList;
    }


    private boolean isWin(Player player, int requiredPoints) {
        if (player.getPoints() >= requiredPoints) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLose(Player player) {
        if (player.getHealth() < 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCorrect(Pathogen pathogenWithQuestion, String answer, TextArea feedbackTextArea) {
        String correctAnswer = pathogenWithQuestion.getCorrectAnswer().toLowerCase().trim();
        answer = answer.toLowerCase().trim();
        if (correctAnswer.contains(answer)) {
            //Output.printColor(answer.toUpperCase() + " is correct", Colors.ANSI_CYAN, true);
            feedbackTextArea.setText(answer.toUpperCase() + " is correct");
            return true;
        } else {
            return false;
        }
    }

    public void organChange(String organ) {
        MenuSceneController.setCurrentOrgan(organ);
        this.pathogensForChosenOrgan = questionsInCurrentOrgan(XMLController.readPathogenXML()
                , MenuSceneController.getCurrentOrgan());
        this.currentPathogen = pathogensForChosenOrgan.get(0);
        askPathogenQuestion(pathogensForChosenOrgan.get(0), this.storyBox);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player playerParam) {
        this.player = playerParam;
    }

}
