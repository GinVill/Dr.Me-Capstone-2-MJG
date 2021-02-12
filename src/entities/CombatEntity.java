package entities;

public abstract class CombatEntity {

    public int health;
    public int strength;

    // Ctor
    public CombatEntity(int health, int strength) {
        setHealth(health);
        setStrength(strength);
    }

    // Methods
    public abstract boolean attack(CombatEntity threat);

    public void deductHealth(int amount) {
        int remainingHealth = getHealth() - amount;
        if( remainingHealth < 1){
            setHealth(0);
        } else {
            setHealth(remainingHealth);
        }
    }

    // Accessors
    public int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }
}
