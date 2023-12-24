import java.util.Random;

public class SpellCaster {
    public String name;
    public int maxHealth;
    public int health;
    public int battlesWon;
    protected Random rgen;

    public SpellCaster(String name, int maxHealth, int seed) {
        this.rgen = new Random(seed);

        /*
         * Your code goes here...
         */
    }
    
    /*
     * These two get overridden...
     */
    public int normalCast() {
        return 0;
    }
    public void specialCast() {
        return;
    }

    /*
     * Your code goes here....
     */

    public int takeDamage(int damage) {
        return 0;
    }

    public void increaseBattlesWon() {
    }

    public boolean isDefeated() {
        return false;
    }
}