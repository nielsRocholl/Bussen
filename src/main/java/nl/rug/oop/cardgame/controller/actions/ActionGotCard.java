package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used to let the player select the option that he has this card in his pile, this action is used
 * in the second round of the game (pyramid)
 */
public class ActionGotCard extends AbstractAction {
    private Bussen bussen;
    public ActionGotCard(Bussen bussen){
        super("GOT IT");
        this.bussen = bussen;
    }

    @Override
    public void actionPerformed(ActionEvent e) { bussen.gotCard(); }
}
