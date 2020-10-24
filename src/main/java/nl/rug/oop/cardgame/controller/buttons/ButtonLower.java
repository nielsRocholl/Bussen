package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionLower;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option the next card will be lower
 */
public class ButtonLower extends JButton {

    public ButtonLower(Bussen bussen, String tooltip){
        super(new ActionLower(bussen));
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


