package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is uses in the ButtonBusRound, and its used to enter the last round of the game
 * which is the bus
 */
public class ActionBusRound extends AbstractAction {
    Bussen bussen;

    public ActionBusRound(Bussen bussen){
        super("CONTINUE");
        this.bussen = bussen;

    }

    @Override
    public void actionPerformed(ActionEvent e) { bussen.bus(); }

}
