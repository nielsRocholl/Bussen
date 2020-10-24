package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in the betweebutton, in order to  let the player choose the option that a card
 * is between the two previous cards.
 */
public class ActionBetween extends AbstractAction {

    Bussen bussen;

    public ActionBetween(Bussen bussen){
        super("BETWEEN");
        this.bussen = bussen;

    }

    @Override
    public void actionPerformed(ActionEvent e) { bussen.BetweenOrOutside("BETWEEN"); }
}
