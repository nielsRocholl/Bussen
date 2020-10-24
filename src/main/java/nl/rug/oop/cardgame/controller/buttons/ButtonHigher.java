package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionHigher;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option that the next card will be higher
 */
public class ButtonHigher extends JButton {

    public ButtonHigher(Bussen bussen, String tooltip){
        super(new ActionHigher(bussen));
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
