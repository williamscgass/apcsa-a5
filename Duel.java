public class Duel {
    public SpellCaster caster1;
    public SpellCaster caster2;
    public int turn = 1;
    public boolean isOver = false;
    public SpellCaster winner = null;

    public Duel(SpellCaster caster1, SpellCaster caster2) {
        /**
         * Your code goes here...
         */
    }


    public void simulateTurn(boolean special1, boolean special2) {
        if (this.isOver) {
            return;
        }

        /**
         * Your code goes here...
         */
    }

    public void printStats(boolean special1, boolean special2) {
        String normalString = "Casted normal!";
        String specialString = "Casted special!";

        String caster1move = normalString;
        if (special1) {
            caster1move = specialString;
        }

        String caster2move = normalString;
        if (special2) {
            caster2move = specialString;
        }

        System.out.printf("Turn %d: %s (%d) %s vs. %s (%d) %s\n",
                this.turn,
                this.caster1.name,
                this.caster1.health,
                caster1move,
                this.caster2.name,
                this.caster2.health,
                caster2move);
    }
}
