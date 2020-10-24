package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action is used in ButtonTurn card, to let the player turn a card in the pyramid round.
 */
public class ActionTurnCard extends AbstractAction {


    Bussen bussen;

    public ActionTurnCard(Bussen bussen){
        super("TURN CARD");
        this.bussen = bussen;

    }

    @Override
    public void actionPerformed(ActionEvent e) { bussen.turnCard();  }
}
