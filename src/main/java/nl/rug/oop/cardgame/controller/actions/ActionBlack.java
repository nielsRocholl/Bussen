package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in the ButtonBlack, in order to let the player choose that the next card will be black.
 */
public class ActionBlack extends AbstractAction {
    Bussen bussen;

    public ActionBlack(Bussen bussen){
        super("BLACK");
        this.bussen = bussen;

    }

    @Override
    public void actionPerformed(ActionEvent e) { bussen.RedOrBlack("BLACK"); }
}
