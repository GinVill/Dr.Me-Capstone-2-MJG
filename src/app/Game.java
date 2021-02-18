package app;

import GUI.controllers.MenuSceneController;
import entities.Pathogen;
import entities.Player;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.MusicPlayer;
import util.PopupBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class in charge of the game logic.
 * It includes methods:
 * play()
 * checkAnswer()
 * askPathogenQuestion()
 * questionsInOrgan()
 * questionsInCurrentOrgan()
 * isWin()
 * isDead()
 * isCorrect()
 * organChange()
 * getPlayer()
 * setPlayer()
 */

public class Game {
    //Fields
    private Player player;
    private TextArea storyBox;
    private Pathogen currentPathogen;
    List<Pathogen> pathogensForChosenOrgan = new ArrayList<>();
    private ImageView body;
    private int winningPointsReq;
    //Ctor
    public Game() {
        super();
    }
    public Game(Player player) {
        this();
        setPlayer(player);
    }

    /**
     *  <P>This method stars the game flow.</P>
     * @param winningPointsRequired the points that player needs to reach to win the game.
     * @param healthValue initial the healthValue of player.
     * @param pathogenList a List of all pathogen questions from Pathogens.xml.{@link GUI.controllers.GameSceneController#initialize()}
     * @param currentOrgan current organ name returned from {@link GUI.controllers.MenuSceneController#getCurrentOrgan()}
     * @param text a TextArea with fx:id storybox on gameScene.fxml {@link GUI.controllers.GameSceneController#storyBox}
     * @param text a Label with fx:id labelplayer on gameScene.fxml {@link GUI.controllers.GameSceneController#labelPlayer}
     * @param player a static Player instance {@link GUI.controllers.GameSceneController#player}
     * @param mpTheme a instance of MusicPlayer {@link GUI.controllers.GameSceneController#mpTheme}
     * @param bodyMap a imageView @see GUI.controllers.GameSceneController#bodyMap {@link GUI.controllers.GameSceneController#bodyMap}
     */
    public void play(int winningPointsRequired, int healthValue, ArrayList<Pathogen> pathogenList, String currentOrgan,
                     TextArea text, Label playerStatus, Player player, MusicPlayer mpTheme, ImageView bodyMap) {
        // Initiate primary game loop, check game ending conditions each time
        this.winningPointsReq = winningPointsRequired;
        this.storyBox = text;
        this.body = bodyMap;
        body.setImage(new Image(getClass().getResource("/GUI/views/" + currentOrgan + ".png").toExternalForm()));
        mpTheme.startMusic();
        this.player = player;
        // return a List of questions specific to the organ base on player choice from the menu scene.
        this.pathogensForChosenOrgan = questionsInCurrentOrgan(pathogenList, currentOrgan);
        currentPathogen = pathogensForChosenOrgan.get(0); // get the question at index 0.
        askPathogenQuestion(currentPathogen, text); // ask and display the question at index 0.
        playerStatus.setText(player.toString()); // initialize player status on game scene.
    }

    /**
     * <p>
     *   trigger by mouse click event when player answer by click one of the three button on game scene.
     *   validate player answer with current question and update game scene controls accordingly.
     * </p>
     * @param userAnswer player answers e.g. 'A' ,'B'. 'C'.
     * @param playerStatus a Label with fx:id labelplayer on gameScene.fxml {@link GUI.controllers.GameSceneController#labelPlayer}
     * @param player a static Player instance {@link GUI.controllers.GameSceneController#player}
     * @param storyBox a TextArea with fx:id storybox on gameScene.fxml {@link GUI.controllers.GameSceneController#storyBox}
     * @param feedbackTextArea a TextArea with fx:id storybox on gameScene.fxml {@link GUI.controllers.GameSceneController#feedbackTextArea}
     * @return boolean false
     * */
    public boolean checkAnswer(String userAnswer, Label playerStatus, Player player, TextArea storyBox, TextArea feedbackTextArea) {

        try {
            if (userAnswer.length() > 0) {
                if (isCorrect(currentPathogen, userAnswer, feedbackTextArea)) { // if player answer correctly, execute following code.

                    int idx = pathogensForChosenOrgan.indexOf(currentPathogen); // get id of current question being asked.
                    player.addPoints(currentPathogen.getPoints()); // increase player point
                    playerStatus.setText(player.toString());


                    if (idx != pathogensForChosenOrgan.size() ) { // if not the last question in current organ.
                        askPathogenQuestion(pathogensForChosenOrgan.get(idx + 1), storyBox); // ask the next question in line.
                        currentPathogen = pathogensForChosenOrgan.get(idx + 1); // update currentPathogen
                        idx++;
                    }

                    if (isWin(player, winningPointsReq)) { // if player's point reach the define goal<winningPointsReq>
                        storyBox.setText("WINNER");
                        PopupBox.popUp("WINNER!", "If you would like to play again select YES otherwise select NO",player);
                    }
                    if (idx == pathogensForChosenOrgan.size()) { // if no more question in current organ
                        PopupBox.makeASelection("HALT", "Look to the smartest man in the Universe for more questions"); // prompt player for more options.

                    }
                } else { // else if player answer wrong, execute following code.

                    currentPathogen.attack(player);
                    playerStatus.setText(player.toString());
                    feedbackTextArea.setText("Wrong, please try again"); // give player feedback
                    if (isLose(player)) { // if player's points equal to zero.
                        storyBox.setText("Game Over!");
                       PopupBox.popUp("LOSER", "Sorry you've lost. Would you like to play again?", player); // prompt player for more options.
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            feedbackTextArea.setText("Good job! you just got all the question right!.");
            PopupBox.makeASelection("HALT", "Look to the smartest man in the Universe for more questions");
            feedbackTextArea.clear();
        }
        return false;
    }

    /**
     * <p>
     *  output question in TextArea on game scene.
     * </p>
     * @param currentThreat a question with current organ.
     * @param textarea a TextArea with fx:id storybox on gameScene.fxml {@link GUI.controllers.GameSceneController#storyBox}
     * */
    private void askPathogenQuestion(Pathogen currentThreat, TextArea textarea) {
        textarea.clear();
        textarea.setText(currentThreat.getDescription() + "\n");
        textarea.appendText("\n" + currentThreat.getQuestion());
    }

    /**
     * <p>
     *     this method is for testing a private method called questionsInCurrentOrgan {@link app.Game#questionsInCurrentOrgan}
     * </p>
     * @param pathogenList a list of pathogen questions from Pathogens.xml.
     * @param organ a string representation of the chosen organ.
     * @return 1
     * */
    public int questionsInOrgan(ArrayList<Pathogen> pathogenList, String organ) {
        if (questionsInCurrentOrgan(pathogenList, organ) != null) {
            return questionsInCurrentOrgan(pathogenList, organ).size();
        }
        return 1;
    }

    /**
     * <p>
     *     given a list of pathogen questions and specific organ name,
     *     return a List of pathogen questions specific to the organ.
     * </p>
     * @param pathogenList a list of pathogen questions from Pathogens.xml.
     * @param organ a string representation of the chosen organ.
     **/
    private List<Pathogen> questionsInCurrentOrgan(ArrayList<Pathogen> pathogenList, String organ) {
        Set<Pathogen> currentOrganSet = new HashSet<>(); // use Set to prevent duplicates.
        // iterate through PathogenList
            for (Pathogen pathogen : pathogenList) {
                if (pathogen.getLocation().equals(organ)) {
                    currentOrganSet.add(pathogen);
                }
            }
        return new ArrayList<>(currentOrganSet);
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
            feedbackTextArea.setText(answer.toUpperCase() + " is correct");
            return true;
        } else {
            return false;
        }
    }

    public void organChange(String organ) {
        MenuSceneController.setCurrentOrgan(organ);
        body.setImage(new Image(getClass().getResource("/GUI/views/" + organ + ".png").toExternalForm())); // set the image on game scene to correlate to organ that specified.
        pathogensForChosenOrgan = questionsInCurrentOrgan(XMLController.readPathogenXML()
                , organ);
        currentPathogen = pathogensForChosenOrgan.get(0);
        askPathogenQuestion(pathogensForChosenOrgan.get(0), this.storyBox);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player playerParam) {
        this.player = playerParam;
    }

}
