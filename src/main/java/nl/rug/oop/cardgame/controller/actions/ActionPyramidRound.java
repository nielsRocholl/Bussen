package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in ButtonPyramidRound and is used to enter the second round i.e. pyramid round.
 */
public class ActionPyramidRound extends AbstractAction {


    Bussen bussen;

    public ActionPyramidRound(Bussen bussen){
        super("CONTINUE");
        this.bussen = bussen;

    }

    @Override
    public void actionPerformed(ActionEvent e) { bussen.pyramid();}
}
