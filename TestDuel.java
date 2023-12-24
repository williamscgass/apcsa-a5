import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

public class TestDuel extends Autograder<Object[]> {
    SpellCaster[] casters;

    // just to make sure random generation is correct
    @Test
    public void testRandom() {
        int[] results = { 0, 8, 9, 7, 15 };

        test("testRandom", () -> {
            Random rgen = new Random(0);
            for (int i = 0; i < 5; i++) {
                assertEquals(results[i], rgen.nextInt(20));
            }
            return "passed";
        }, "test random (please alert instructors if this fails)", "passed", 1);

    }

    @Before
    public void beforeTests() {
        casters = new SpellCaster[4];
        for (int i = 0; i < 2; i++) {
            casters[i] = new Witch("witch " + i, 100, i);
        }

        for (int i = 0; i < 2; i++) {
            casters[i + 2] = new Wizard("wizard " + i, 100, i);
        }
    }

    // test bounds of wizard range are correct
    @Test
    public void testWizardRange() {
        test("testWizardRange", () -> {
            int[] hits = new int[51];
            for (int i = 0; i < 100000; i++) {
                int wizHit = casters[2].normalCast();
                if (wizHit > 50 || wizHit < 0) {
                    fail();
                }
                hits[wizHit] += 1;
            }

            // valid at least one hit per
            for (int hit : hits) {
                assertTrue(hit > 0);
            }
            return "passed";
        }, "test wizard range", "passed", 10);
    }

    @Test
    public void testWitchRange() {
        test("testWitchRange", () -> {
            int[] hits = new int[16];
            for (int i = 0; i < 100000; i++) {
                int witchHit = casters[0].normalCast();
                if (witchHit > 30 || witchHit < 15) {
                    fail();
                }
                hits[witchHit - 15] += 1;
            }

            // valid at least one hit per
            for (int hit : hits) {
                assertTrue(hit > 0);
            }
            return "passed";
        }, "test witch range", "passed", 10);
    }

    @Test
    public void testWitchHeal() {
        int out = 95;

        test("testWitchHeal", () -> {
            // no overheals
            casters[0].health = 100;
            casters[0].takeDamage(0);
            casters[0].specialCast();
            assertEquals(100, casters[0].health);

            // correct heals
            casters[0].health = 100;
            casters[0].takeDamage(25);
            casters[0].specialCast();
            assertEquals(95, casters[0].health);
            return casters[0].health;
        }, "test witch heal", out, 10);
    }

    @Test
    public void testDuel1() {
        // only normal casts
        test("testDuel1", () -> {
            Duel duel = new Duel(casters[0], casters[1]);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            assertEquals(casters[0], duel.winner);
            return duel.winner;
        }, "only normal casts", casters[0], 10);
    }

    @Test
    public void testDuel2() {
        // only normal casts, some heals though
        test("testDuel2", () -> {
            Duel duel = new Duel(casters[0], casters[1]);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(false, false);
            assertEquals(casters[1], duel.winner);
            return duel.winner;
        }, "only normal casts, some heals though", casters[0], 10);
    }

    @Test
    public void testDuel3() {
        // only normal casts, make sure heal feals on last bout
        test("testDuel3", () -> {
            Duel duel = new Duel(casters[0], casters[1]);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(true, false);
            assertEquals(casters[1], duel.winner);
            return duel.winner;
        }, "only normal casts, make sure heal fails on last bout", casters[1], 10);
    }

    @Test
    public void testDuel4() {
        // assert health reset after duel started
        test("testDuel4", () -> {
            Duel duel = new Duel(casters[0], casters[1]);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);

            duel = new Duel(casters[0], casters[1]);
            assertEquals(casters[0].maxHealth, casters[0].health);
            assertEquals(casters[1].maxHealth, casters[1].health);
            return "passed";
        }, "assert health reset after duel started", "passed", 10);
    }

    @Test
    public void testDuel5() {
        // assert that barrier blocks damage
        test("testDuel5", () -> {
            Duel duel = new Duel(casters[2], casters[3]);
            duel.simulateTurn(true, false);

            assertEquals(casters[2].maxHealth, casters[2].health);
            return casters[2].health;
        }, "assert that barrier blocks damage", casters[2].maxHealth, 5);
    }

    @Test
    public void testDuel6() {
        // assert that barrier only blocks damage on turn casted
        test("testDuel6", () -> {
            Duel duel = new Duel(casters[2], casters[3]);
            duel.simulateTurn(true, true);
            duel.simulateTurn(false, false);

            assertTrue(casters[2].health < casters[2].maxHealth);
            return casters[2].health;
        }, "assert that barrier only blocks damage on turn casted", casters[2].maxHealth, 5);
    }

   // next tests are same as previous, except we have mirrored the casters
   @Test
    public void testDuel7() {
        // only normal casts
        test("testDuel7", () -> {
            Duel duel = new Duel(casters[0], casters[1]);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            assertEquals(casters[0], duel.winner);
            return duel.winner;
        }, "only normal casts", casters[0], 5);
    }


    @Test
    public void testDuel9() {
        // only normal casts, make sure heal feals on last bout
        test("testDuel9", () -> {
            Duel duel = new Duel(casters[0], casters[1]);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(true, false);
            duel.simulateTurn(true, false);
            assertEquals(casters[1], duel.winner);
            return duel.winner;
        }, "only normal casts, make sure heal fails on last bout", casters[1], 5);
    }

    @Test
    public void testDuel10() {
        // assert health reset after duel started
        test("testDuel10", () -> {
            Duel duel = new Duel(casters[0], casters[1]);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);
            duel.simulateTurn(false, false);

            duel = new Duel(casters[0], casters[1]);
            assertEquals(casters[0].maxHealth, casters[0].health);
            assertEquals(casters[1].maxHealth, casters[1].health);
            return "passed";
        }, "assert health reset after duel started", "passed", 5);
    }

    @Test
    public void testDuel11() {
        // assert that barrier blocks damage
        test("testDuel11", () -> {
            Duel duel = new Duel(casters[2], casters[3]);
            duel.simulateTurn(true, false);

            assertEquals(casters[2].maxHealth, casters[2].health);
            return casters[2].health;
        }, "assert that barrier blocks damage", casters[2].maxHealth, 5);
    }

    @Test
    public void testDuel12() {
        // assert that barrier only blocks damage on turn casted
        int out = 85;

        test("testDuel12", () -> {
            Duel duel = new Duel(casters[2], casters[3]);
            duel.simulateTurn(true, true);
            duel.simulateTurn(false, false);

            assertTrue(casters[2].health < casters[2].maxHealth);
            return casters[2].health;
        }, "assert that barrier only blocks damage on turn casted", out, 5);
    }
}
