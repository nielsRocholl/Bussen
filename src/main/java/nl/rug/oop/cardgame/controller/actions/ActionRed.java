package nl.rug.oop.cardgame.controller.actions;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * this action is used in ButtonRed and is used to let the player select that the next card will be red.
 */
public class ActionRed extends AbstractAction {

    private Bussen bussen;

    public ActionRed(Bussen bussen){
        super("RED");
        this.bussen = bussen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bussen.RedOrBlack("RED");
    }




}
