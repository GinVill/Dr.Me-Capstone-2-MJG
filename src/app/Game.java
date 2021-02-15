package app;

import entities.Pathogen;
import entities.Player;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.Colors;
import util.GameConstants;
import util.MusicPlayer;
import util.Output;

import java.util.*;

public class Game {
    private Player player;
    // Fields
    private int id = UUID.randomUUID().hashCode();
    // private Player player;
    private int difficulty;
    private Scanner sc = new Scanner(System.in);
    private final MusicPlayer mpTheme = new MusicPlayer("resources/Away - Patrick Patrikios.wav");
    private Pathogen pathogen;
    private int winningPointsReq;

    public Game() {
        super();
    }

    public Game(Player player) {
        this();
        setPlayer(player);

    }

    // Methods
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void play(int winningPointsRequired, int healthValue, ArrayList<Pathogen> pathogenList,
                     TextArea text, TextField field, Label playerStatus, Player player) {
        // Initiate primary game loop, check game ending conditions each time
        this.winningPointsReq = winningPointsRequired;
        List<Pathogen> pathogensForChosenOrgan;
               mpTheme.startMusic();
        this.player = player;
        //  player.setHealth(120);
        pathogensForChosenOrgan = questionsInCurrentOrgan(pathogenList, "mouth");
        pathogen = pathogensForChosenOrgan.get(1);
        askPathogenQuestion(pathogen, text);
        playerStatus.setText(player.toString());
        // String userAnswer = field.getText();

//        try {
//            playIntroduction(player.getName());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }

//        while (!isGameEnd(getPlayer(), winningPointsRequired)) {
//            boolean isAsking = true;
//            Output.printColor("\nwhere you want to go next? \n[brain, mouth, throat, lungs, heart, liver, colon]\n>> ", Colors.ANSI_YELLOW, false);
//            //  String chosenOrgan = sc.nextLine().strip();
//            String chosenOrgan = "mouth";
////            ReadFile.displayBodyMap(chosenOrgan);
//            Output.printColor("Now you are in the " + chosenOrgan, Colors.ANSI_YELLOW, false);
//            pathogensForChosenOrgan = questionsInCurrentOrgan(pathogenList, chosenOrgan);
//
//            int counter = pathogensForChosenOrgan.size();
//            for (Pathogen question : pathogensForChosenOrgan) {
//                if (counter > 0) {
//                    System.out.println("\n" + counter);
//                    System.out.printf("questions remain in " + question.getLocation() + ":[" + (counter - 1) + "]");
//                    System.out.printf("%70s%n%n", player.getPoints());
//
    //Pathogen currentThreat = question;
//                    askPathogenQuestion(currentThreat, text);
//                    playerStatus.setText(player.toString());
//
//                    int chances = 2;
//                    // Continue waiting until valid command/answer has been entered
//                    // Get players answer
//                    // askPathogenQuestion(currentThreat);
//                    //String userAnswer = sc.next().strip();
//                    //  userAnswer = sc.nextLine().strip().toLowerCase();
////            if (userAnswer.equalsIgnoreCase("quit")) {
////                quitGame();
////            }
//
//                    switch (userAnswer) {
//                        case "quit":
//                            quitGame();
//                            break;
//                        case "hint":
//                        case "cells":
//                        case "help":
//                            isValidUserInput(currentThreat, userAnswer, chances);
//                            break;
//                        case "exit":
//
//                        default:
//
//                            System.out.println(player.getHealth());
//                            while (chances > 0) {
//                                if (checkAnswer(currentThreat, userAnswer, chances)) {
//                                    // Correct answer, add to player points
//                                    this.getPlayer().addPoints(currentThreat.getPoints());
//                                    this.getPlayer().setHealth(120);
//                                    break;
//                                } else {
//                                    chances--;
//                                    // Wrong answer, subtract player health
//                                    currentThreat.attack(this.getPlayer());
//                                    if (!(chances == 0)) {
//                                        Output.printColor("Incorrect. Be careful your health is "
//                                                        + player.getHealth() + " enter your answer \n>> ",
//                                                Colors.ANSI_YELLOW, false);
//                                        // userAnswer = sc.nextLine().strip();
//                                    }
//                                }
//
//                            }
//                    }
//                }
////                else {
////                    System.out.println("you are exit the " + question.getLocation() + "now\n");
////                    break;
////                }
//                counter--;
//            }
//
//        }
//    }


    public boolean checkAnswer(String userAnswer, Label playerStatus, Player player, TextArea storyBox) {
//        //if chances < 1, return false
//        if (chances <= 1) {
//            return false;
//        }
//
//        // if correct answer, break out
//        if (isCorrect(pathogen, userAnswer)) {
//            return true;
//        } else if (isValidUserInput(pathogen, userAnswer, chances)) {
//            //  Output.printColor(" Input is valid", Colors.ANSI_YELLOW, true);
//
//            // Handles the command and then asks for input again.
//            Output.printColor("Please enter your answer >> ",
//                    Colors.ANSI_YELLOW, false);
//            userAnswer = sc.nextLine().strip(); // Get the user answer again
//            if (userAnswer.equalsIgnoreCase("quit")) {
//                quitGame();
//            } else {
//                checkAnswer(pathogen, userAnswer, chances);
//            }
//
//
//        } else {
//
//            chances--; // Answer is wrong, decrement chances
//            Output.printColor(chances + " chances remaining to answer the question" +
//                            " correctly",
//                    Colors.ANSI_CYAN, true);
//            Output.printColor("Please enter your answer >> ",
//                    Colors.ANSI_YELLOW, false);
//            userAnswer = sc.nextLine().strip(); //
//            if (userAnswer.equalsIgnoreCase("quit")) {
//                quitGame();
//            } else {
//                checkAnswer(pathogen, userAnswer, chances);
//            }
//        }
        List<Pathogen> pathogensForChosenOrgan;
        List<String> organslist;
        organslist = Arrays.asList("brain", "mouth", "throat", "lungs", "heart", "liver", "stomach", "colon");


        if (userAnswer.length() > 0) {
            if (isCorrect(pathogen, userAnswer)) {

                player.addPoints(pathogen.getPoints());
                playerStatus.setText(player.toString());
                //  List<Pathogen>  newPath = questionsInCurrentOrgan(pathogenList, "mouth");
                pathogensForChosenOrgan = questionsInCurrentOrgan(XMLController.readPathogenXML(), organslist.get(getRandomNumber(0, organslist.size())));
                pathogen = pathogensForChosenOrgan.get(getRandomNumber(0, pathogensForChosenOrgan.size()));
                askPathogenQuestion(pathogen, storyBox);
                System.out.println(pathogen.getLocation());
//                playerLocal.setText(pathogen.getLocation()); // location
                if (isWin(player, winningPointsReq)) {
                    storyBox.setText("WINNER");
                    PopupBox.popUp("WINNER!", "If you would like to play again select YES otherwise select NO", player);
                }

            } else {

                pathogen.attack(player);
                playerStatus.setText(player.toString());
                System.out.println("wrong");
                if (isLose(player)) {
                    storyBox.setText("Game Over!");
                    PopupBox.popUp("LOSER", "Sorry you've lost. Would you like to play again?", player);
                }

            }
        }
        return false;
    }

    private void askPathogenQuestion(Pathogen currentThreat, TextArea textarea) {
        String location = currentThreat.getLocation();
//        Output.printColor("You find yourself in the:  " + location, Colors.ANSI_BLUE, true);
//        Output.printColor("Where you find:  " + currentThreat.getDescription() + "\n", Colors.ANSI_BLUE, true);
//        Output.printColor(currentThreat.getQuestion() + "\n Type your answer >> ", Colors.ANSI_YELLOW, false);
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
        organslist = Arrays.asList("brain", "mouth", "throat", "lungs", "heart", "liver", "stomach", "colon");

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

    // Gets the user raw input and sends to handler
    private boolean isValidUserInput(Pathogen currentThreat, String userAnswer, int chances) {
        String pathogenName = currentThreat.getName();
        // True if primary command entered, false if bad command or hint/help entered
        // Handle hint, help etc.
//         userAnswer = sc.nextLine().strip();

        boolean result = Commands.handleCommand(userAnswer, pathogenName);
        //  System.out.println("Line 22: HandleCommand Result " + result);
        return result;

    }

    public void playIntroduction(String playerName) throws InterruptedException {
        // Display game introduction related information
        Output.printColor("Hello " + playerName + ". welcome to Dr Me ", Colors.ANSI_RED, true);
        //Output.printColor(playerName, Colors.ANSI_RED, true);

        // Prints a loading display sequence
        //Output.printLoading(3);

        // Start printing the games story
        Output.printColor(GameConstants.GAME_INTRODUCTION, Colors.ANSI_BLUE, true);

        //Output.printLoading(5);

        Output.printColor(GameConstants.GAME_INTRODUCTION_TWO, Colors.ANSI_BLUE, true);
        Output.printColor(">>", Colors.ANSI_BLUE, false);
        //Output.printLoading(5);
    }

    private boolean isGameEnd(Player player, int requiredPoints) {
        if (isWin(player, requiredPoints)) {
            // Player has won
            Output.printColor(player.getName() + " won the game!", Colors.ANSI_CYAN, true);
            // Show game results
            return true;

        } else if (isLose(player)) {
            // Actions to do on lose
            Output.printColor(player.getName() + " lost the game :(((", Colors.ANSI_RED, true);

            // Show game results


            return true;
        }

        return false;
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

    private boolean isCorrect(Pathogen pathogenWithQuestion, String answer) {
        String correctAnswer = pathogenWithQuestion.getCorrectAnswer().toLowerCase().trim();
        answer = answer.toLowerCase().trim();
        if (correctAnswer.contains(answer)) {
            Output.printColor(answer.toUpperCase() + " is correct", Colors.ANSI_CYAN, true);
            return true;
        } else {
            return false;
        }
    }


    private void quitGame() {
        System.out.println("\nThank you for playing our game. See you soon! ");
        System.exit(0);
    }

    private boolean exitOrgan(boolean flag) {
        boolean result = flag;
        return result;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player playerParam) {
        this.player = playerParam;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }
}
