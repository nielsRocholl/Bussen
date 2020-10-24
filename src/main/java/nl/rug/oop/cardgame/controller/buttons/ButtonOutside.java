package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionOutside;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option that the nxt card will be outside of the previous two
 */
public class ButtonOutside extends JButton {

    public ButtonOutside(Bussen bussen, String tooltip){
        super(new ActionOutside(bussen));
        setButtonProperties(tooltip);

    }

    public void setButtonProperties(String tooltip){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(tooltip);
        setSize(new Dimension(400,400));
        setPreferredSize(new Dimension(160,40));
        setBackground(Color.orange);
        setForeground(Color.black);
    }
}
