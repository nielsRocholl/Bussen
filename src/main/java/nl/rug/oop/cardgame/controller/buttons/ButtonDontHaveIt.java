package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionDontHaveIt;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option that he does not have the suit of the next card
 */
public class ButtonDontHaveIt extends JButton {

    public ButtonDontHaveIt(Bussen bussen, String tooltip){
        super(new ActionDontHaveIt(bussen));
        setButtonProperties(tooltip);

    }

    public void setButtonProperties(String tooltip){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(tooltip);
        setPreferredSize(new Dimension(180,40));
        setBackground(Color.orange);
        setForeground(Color.black);
    }
}
