public class MakeShiftDuel {
    public static void main(String[] args) {
        Wizard wiz1 = new Wizard("Harry", 100, 1);
        // Wizard wiz2 = new Wizard("Voldemort", 100, 2);
        Witch witch1 = new Witch("Hermione", 100, 3);
        // Witch witch2 = new Witch("Bellatrix", 100, 4);

        Duel duel = new Duel(wiz1, witch1);
        duel.simulateTurn(true, true);
        duel.simulateTurn(false, false);
    }
}
