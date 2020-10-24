package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in buttonHigher, and lets the layer select the option that the next card will be higher
 * than the previous one.
 */
public class ActionHigher extends AbstractAction {

    private Bussen bussen;
    public ActionHigher(Bussen bussen){
        super("HIGHER");
        this.bussen = bussen;


    }
    @Override
    public void actionPerformed(ActionEvent e) { bussen.HigherOrLower("HIGHER");

    }
}
