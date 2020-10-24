package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionGotCard;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option that he has the card in his pile
 */
public class ButtonGotCard extends JButton {

    public ButtonGotCard(Bussen bussen, String tooltip){
        super(new ActionGotCard(bussen));
        setButtonProperties(tooltip);

    }

    public void setButtonProperties(String tooltip){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(tooltip);
        setPreferredSize(new Dimension(160,40));
        setBackground(Color.orange);
        setForeground(Color.black);
    }
}
