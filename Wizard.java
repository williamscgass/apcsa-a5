public class Wizard extends SpellCaster {
    public Wizard(String name, int maxHealth, int seed) {
    }

    public int takeDamage(int damage) {
        return 0;
    }
    
    public int normalCast() {
        return this.rgen.nextInt(???) + ???;
    }
    
    public void specialCast() {
    }
}
