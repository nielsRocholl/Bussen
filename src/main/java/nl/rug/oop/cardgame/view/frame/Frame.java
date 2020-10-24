package nl.rug.oop.cardgame.view.frame;

import nl.rug.oop.cardgame.controller.buttonMenu.ButtonMenu;
import nl.rug.oop.cardgame.model.bussen.Bussen;
import nl.rug.oop.cardgame.view.panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Draws the frame.
 */
public class Frame extends JFrame implements Observer {

    Bussen bussen;

    public Frame(Bussen bussen) {

        super("BUSSEN: SOUTHPARK EDITION");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setPreferredSize(new Dimension(1200,900));
        pack();

        this.bussen = bussen;
        setBar();

        Panel panel = new Panel(bussen);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0,1));
        add(panel);


        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void setBar(){
        setJMenuBar(new ButtonMenu(bussen));
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
