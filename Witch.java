public class Witch extends SpellCaster {
    public Witch(String name, int maxHealth, int seed) {
    }
    
    public int normalCast() {
        return this.rgen.nextInt(???) + ???;
    }
    
    public void specialCast() {
    }
}
