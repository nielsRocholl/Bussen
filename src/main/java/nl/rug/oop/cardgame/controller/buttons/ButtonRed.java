package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionRed;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option that the next card will be red
 */
public class ButtonRed extends JButton {

    public ButtonRed(Bussen bussen, String tooltip){
        super(new ActionRed(bussen));
        setButtonProperties(tooltip);
    }

    public void setButtonProperties(String tooltip){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setToolTipText(tooltip);
        setPreferredSize(new Dimension(160,40));
        setBackground(Color.red);
        setForeground(Color.white);
        }
}
