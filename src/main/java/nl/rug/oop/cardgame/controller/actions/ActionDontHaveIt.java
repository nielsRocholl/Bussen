package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in ButtonDontHaveIt, and it lets the player choose the option that he does not have this card
 * (or more precicely this cards suit) in his dack of three cards.
 */
public class ActionDontHaveIt extends AbstractAction {
    private Bussen bussen;
    public ActionDontHaveIt(Bussen bussen){
        super("I dont have the suit");
        this.bussen = bussen;


    }
    @Override
    public void actionPerformed(ActionEvent e) { bussen.WhichSuit("NO");

    }
}
