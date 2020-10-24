package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in buttonHaveIt and lets the player select the option that he already possesses this suit in
 * his pile.
 */
public class ActionHaveIt extends AbstractAction {

    private Bussen bussen;
    public ActionHaveIt(Bussen bussen){
        super("I have the suit");
        this.bussen = bussen;


    }
    @Override
    public void actionPerformed(ActionEvent e) { bussen.WhichSuit("YES");

    }
}
