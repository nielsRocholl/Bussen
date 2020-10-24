package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionBlack;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option that the next card is black
 */
public class ButtonBlack extends JButton {

    public ButtonBlack(Bussen bussen, String tooltip){
        super(new ActionBlack(bussen));
        setButtonProperties(tooltip);

    }

    public void setButtonProperties(String tooltip){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(tooltip);
        setPreferredSize(new Dimension(160,40));
        setBackground(Color.black);
        setForeground(Color.white);
    }
}
