package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in buttonOutside and is used to let the player select the option that the next value of the next
 * card will be outside teh previous two.
 */
public class ActionOutside extends AbstractAction {

    Bussen bussen;

    public ActionOutside(Bussen bussen){
        super("OUTSIDE");
        this.bussen = bussen;

    }

    @Override
    public void actionPerformed(ActionEvent e) { bussen.BetweenOrOutside("OUTSIDE"); }
}
