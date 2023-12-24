# AP CSA Assignment 4 - Witches, Wizards, and Polyjuice Potions  

Here are the instructions for Assignment 4.  
Fair warning... this assignment is HARD! It's in your best interest to get started early. You have a couple weeks to work on it, so don't wait until the last minute.  
This assignment gives you practice with polymophism (formally referred to as polyjuice potion), inheritance, static methods, and boolean logic.  
There are no designated extra credit questions for this assignment, but, because it's so massive, the maximum score is 106/100.

Assignment 4 is due this Friday at Midnight, and will be used as the base for your project.

### IMPORTANT: TESTING

As with previous assignments, run either `make test` or `make windows-test` to test your code. Choose the appropriate command based on your operation system.

## Some Background

**Important: for this assignment, make all methods and variables public. This is not best practice, but allows you to get by without setting up getter methods.**

Welcome to the wizarding world! You are a student at Hogwarts, a school of witchcraft and wizardry. You are currently taking a class called "Defense Against the Dark Arts" (DADA), taught by Hogwarts Fellow Professor Mahuta.

Your professor is a big propenent of teaching through... well, experience. So, you and your classmates are going to be practicing your spells by dueling each other.

Your task is to create four classes: SpellCaster, Witch, Wizard, and Duel. Witch and Wizard will both be subclasses of the superclass SpellCaster. The Duel class is used to simulate duels between two SpellCasters. 

## Spellcasters

SpellCasters are the superclass of both Witches and Wizards. You must implement the following SpellCaster instance variables:
- `name` (String): the name of the SpellCaster
- `maxHealth` (int): the maximum health of the SpellCaster
- `health` (int): the current health of the SpellCaster
- `battlesWon` (int): the number of battles the SpellCaster has won

Each SpellCaster has a normal cast, implemented with the `normalCast()` method. They also have a special cast, implemented with the `specialCast()` method. Witches and Wizards have different implementations of these methods, so they are abstract in the SpellCaster class -- meaning they have no implementation. I encourange you to think about why it's important that we have these methods in the superclass, even though they have no implementation.

SpellCasters have the following methods:
- `SpellCaster(String name, int maxHealth, int seed)`: constructor that takes in a name and health. battlesWon should have an initial value of 0. You may ingore the seed part, but if you are interested, look up why random seeding is important.
- `int normalCast()`: No implementation...
- `void specialCast()`: No implementation...
- `int takeDamage(int damage)`: decreases the SpellCaster's health by the amount of damage taken, then returns the SpellCaster's health.
- `void increaseBattlesWon()`: increases the SpellCaster's battlesWon by 1.
- `boolean isDefeated()`: returns true if the SpellCaster's health is less than or equal to 0, false otherwise.

## Witches

Witches are SpellCasters that have a normal cast that deals a random amount of damage in the range `[15, 30]`.

I recommend your implementation of this method look something like this... I am only being picky here because the random numbers need to be reproducible for testing purposes. If you do not follow this, I can't guarantee your tests will pass.
```java
public int normalCast() {
    return this.rgen.nextInt(???) + ???;
}
```

Their special cast heals them for `20` health.

To implement this, you must override the `normalCast()` and `specialCast()` methods. `normalCast()` should return a random number in the range `[15, 30]`. To generate a random number, use the `nextInt(int bound)` method on the `this.rgen` instance variable.
`specialCast()` should increase the Witch's health by 20, but not above the Witch's maxHealth.

## Wizards 

Wizards are SpellCasters that have a normal cast that deals a random amount of damage in the range `[0, 50]`.  
Their special cast gives them a barrier that negates all damage on the turn it is cast.

To implement this, you must override the `normalCast()` and `specialCast()` methods. `normalCast()` should return a random number in the range `[0, 50]`. To generate a random number, use the `nextInt(int bound)` method on the `this.rgen` instance variable.

I will leave the implementation of `specialCast()` up to you.

## Duels

Duels are simulated battles between two SpellCasters. The Duel class has the following instance variables:
- `SpellCaster caster1`: the first SpellCaster in the duel
- `SpellCaster caster2`: the second SpellCaster in the duel
- `int turn`: the current turn number of the duel
- `boolean isOver`: true if the duel is over, false otherwise
- `SpellCaster winner`: the winner of the duel

The Duel class has the following methods:
- `Duel(SpellCaster caster1, SpellCaster caster2)`: constructor that takes in two SpellCasters and initializes the instance variables. The turn should be initialized to 1, and isOver should be initialized to false.
- `void simulateTurn(bool special1, bool special2)`: simulates a turn of the duel. By default, a SpellCaster will use their normal cast. If special1 is true, caster1 will use their special cast. If special2 is true, caster2 will use their special cast.
    - each turn should use the following control flow (order matters):
        - If either caster is a wizard and is casting special, they should negate all damage on this turn.
        - If either caster decides to cast normal, they should deal damage to the other caster. The amount of damage dealt is the return value of the caster's normalCast() method.
        - If either caster is dead, the duel is over. Modify the appropriate instance vraiables to reflect this. If both casters are dead, the winner should be the one with more health -- if they have the same health, caster1 should be the winner (so unfair, I know...).
        - If either caster is a witch and is casting special, they should heal themselves.
        - if the duel is not over, the turn number is increased by 1

Above is what makes this assignment **VERY DIFFICULT!**: how the cast priorities work. I will leave the implementation of this up to you. I will, however, provide an idea:
- One approach is to use the instanceof operator to check if a SpellCaster is a Witch or Wizard. For example, `caster1 instanceof Witch` will return true if caster1 is a Witch, and false otherwise. This can be used to create a control flow that checks if a SpellCaster is a Witch or Wizard, and then casts their special at the appropriate time.

## A Final Remark

Most tests, if you look at the test file, are ran through duels. This means I am giving you a fair amount of freedom in how you implement your SpellCasters (specifically, how you implement the special cast). However, you must follow the specifications above. If you do not, you will lose points. If you are unsure if your implementation is valid, ask me! I am more than happy to help you out.



