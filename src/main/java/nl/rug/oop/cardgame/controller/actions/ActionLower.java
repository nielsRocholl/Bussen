package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in buttonLower and is used to let the player select the option that the next card will be lower
 * than the previous one.
 */
public class ActionLower extends AbstractAction {

    private Bussen bussen;
    public ActionLower(Bussen bussen){
        super("LOWER");
        this.bussen = bussen;


    }
    @Override
    public void actionPerformed(ActionEvent e) { bussen.HigherOrLower("LOWER");

    }
}
