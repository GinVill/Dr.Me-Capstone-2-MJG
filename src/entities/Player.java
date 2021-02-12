package entities;

public class Player extends CombatEntity {
    // Fields
    private String name;
    private int points;
    //private int health;

    // Ctor
    public Player(){
        super(120, 50);
    }

    public Player(String name) {
        this();
        setName(name);
        setPoints(0);
    }

    // Methods
    @Override
    public boolean attack(CombatEntity threat) {
        threat.deductHealth(this.getStrength());
        if(threat.getHealth() < 1){
            return true;
        } else {
            return false;
        }
    }

    // Accessors
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
        return "Health: " + getHealth() + "\nCurrent points: " + getPoints();
    }


}