package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.model.bussen.Bussen;
import nl.rug.oop.cardgame.view.frame.Frame;

/**
 * Runs the game. Although technically a controller this class can be found
 * more easily if it's not in that package.
 */
public class Main {
    public static void main(String[] args) {
        Bussen bussen = new Bussen();
        new Frame(bussen);
    }
}