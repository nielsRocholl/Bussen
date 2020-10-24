package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionHaveIt;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option that he has the suit of the next card
 */
public class ButtonHaveIt extends JButton {

    public ButtonHaveIt(Bussen bussen, String tooltip){
        super(new ActionHaveIt(bussen));
        setButtonProperties(tooltip);

    }

    public void setButtonProperties(String tooltip){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(tooltip);
        setSize(new Dimension(400,400));
        setPreferredSize(new Dimension(180,40));
        setBackground(Color.orange);
        setForeground(Color.black);
    }
}
