package app;

import entities.Pathogen;
import entities.Player;
import util.Colors;
import util.GameConstants;
import util.Output;

import java.util.*;

public class Game {
    // Fields
    private int id = UUID.randomUUID().hashCode();
    private static Player player;
    private int difficulty;
    private Scanner sc = new Scanner(System.in);

    // Ctors
    public Game() {
        super();
    }

    public Game(Player player, int difficulty) {
        this();
        setPlayer(player);
        setDifficulty(difficulty);
    }

    // Methods
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void play(int winningPointsRequired, int healthValue, ArrayList<Pathogen> pathogenList) {
            String userInput = "";
            String playerPickedOrgan = "";
            Iterator<Pathogen> itr = null;
            // Initiate primary game loop, check game ending conditions each time
            while (!isGameEnd(this.getPlayer(), winningPointsRequired)) {
                // display intro



                while(flag){

                }

            }
    }



    private List<Pathogen> pathogensInOrgan(ArrayList<Pathogen> pathogenList, String organ) {
        List<Pathogen> currentOrganList = new ArrayList<>();
        List<String> organslist;
        organslist = Arrays.asList("brain","mouth","throat","lungs","heart","liver","stomach","small intestine","colon");

        if (organslist.contains(organ)) { // if the organs is valid organ.
            // iterate through PathogenList
            for (Pathogen pathogen : pathogenList) {
                if (pathogen.getLocation().equals(organ)){
                    currentOrganList.add(pathogen);
                }
            }
        }
        return currentOrganList;
    }

    private boolean checkAnswer(Pathogen pathogen, String userAnswer, int chances) {
        if (isValidUserInput(pathogen, userAnswer, chances)) {
            if (userAnswer.equalsIgnoreCase("quit")) {
                quitGame();
            } else if (userAnswer.equalsIgnoreCase("exit")) {
                exitOrgan(false);
            } else {
                checkAnswer(pathogen, userAnswer, chances);
                //if chances < 1, return false
                if (chances <= 1) {
                    System.out.println("You are running out of chance"); // TODO:delete after testing
                    return false;
                }
                // if correct answer, break out
                if (isCorrect(pathogen, userAnswer)) {
                    System.out.println("You are Right"); // TODO:delete after testing
                    return true;
                } else {

                    chances--; // Answer is wrong, decrement chances
                    Output.printColor(chances + " chances remaining to answer the question" +
                                    " correctly",
                            Colors.ANSI_CYAN, true);
                    Output.printColor("Please enter your answer >> ",
                            Colors.ANSI_YELLOW, false);
                    userAnswer = sc.nextLine().strip();
                    checkAnswer(pathogen, userAnswer, chances);
                    }
                  }
        }
        return false;
    }
    private void askPathogenQuestion(Pathogen currentThreat) {
        String location = currentThreat.getLocation();
        Output.printColor("You find yourself in the:  " + location, Colors.ANSI_BLUE, true);
        Output.printColor("Where you find:  " + currentThreat.getDescription() + "\n", Colors.ANSI_BLUE, true);
        Output.printColor(currentThreat.getQuestion() + "\n Type your answer >> ", Colors.ANSI_YELLOW, false);
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
        Output.printColor("Hello "+ playerName +". welcome to Dr Me ", Colors.ANSI_RED, true);
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

    private boolean exitOrgan(boolean flag){
        boolean result = flag;
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player playerParam) {
        player = playerParam;
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
