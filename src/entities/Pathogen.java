package entities;

import java.util.ArrayList;
import java.util.Objects;

public class Pathogen extends CombatEntity {

    private String name;
    private String description;
    private String hint;
    private String location;
    private String question;
    private String correctAnswer;
    private int points;
    private static ArrayList<Pathogen> pathogenList;

    public Pathogen(){
        super(20, 30);
    }


    public Pathogen(String name, String description,
                    String hint, String location,
                    String question, String correctAnswer,
                    int points){
        this();
        setName(name);
        setDescription(description);
        setHint(hint);
        setLocation(location);
        setQuestion(question);
        setCorrectAnswer(correctAnswer);
        setPoints(points);
    }

    @Override
    public boolean attack(CombatEntity threat) {
        threat.deductHealth(this.getStrength());
        if(threat.getHealth() < 1){
            // The threat is dead
            return true;
        } else {
            // The threat is still alive
            return false;
        }
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public static ArrayList<Pathogen> getDiseaseList() {
        return pathogenList;
    }

    public void setDiseaseList(ArrayList<Pathogen> pathogenList) {
        this.pathogenList = pathogenList;
    }

    @Override
    public boolean equals(Object comparedDisease) {
        if (this == comparedDisease) return true;
        if (comparedDisease == null || getClass() != comparedDisease.getClass()) return false;
        Pathogen pathogen = (Pathogen) comparedDisease;
        return Objects.equals(getName(), pathogen.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Disease: " +
                "name=  " + name + "  " +
                ", description='" + description + "  " +
                ", hint= '" + hint + "  " +
                ", location= '" + location + "  " +
                ", question= '" + question + "  " +
                ", correctAnswer=  '" + correctAnswer +"  "+
                ", points= " + points;
    }



}
