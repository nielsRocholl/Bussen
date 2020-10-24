package nl.rug.oop.cardgame.controller.buttons;

import nl.rug.oop.cardgame.controller.actions.ActionBusRound;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import java.awt.*;

/**
 * Lets the user select the option to continue to the last round (the bus)
 */
public class ButtonBusRound extends JButton {

    public ButtonBusRound(Bussen bussen, String tooltip){
        super(new ActionBusRound(bussen));
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
