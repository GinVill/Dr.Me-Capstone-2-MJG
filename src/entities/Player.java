package entities;

public class Player extends CombatEntity {

    private String name;
    private int points;

    public Player(){
        super(120, 50);
    }

    public Player(String name) {
        this();
        setName(name);
        setPoints(0);
    }

    @Override
    public boolean attack(CombatEntity threat) {
        threat.deductHealth(this.getStrength());
        if(threat.getHealth() < 1){
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public boolean addPoints(int newPoints) {
        // Look up game Id to get appropriate points
        int currPoints = getPoints();
        setPoints(currPoints + newPoints);
        return true;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPoints() {
        return points;
    }

    private void setPoints(int points) {
        this.points = points;
    }

    public int getStrength() {
        return strength;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }


    @Override
    public String toString() {
        return "model.Player's name is: " + name;
    }


}