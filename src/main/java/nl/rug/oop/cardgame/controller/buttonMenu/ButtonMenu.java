package nl.rug.oop.cardgame.controller.buttonMenu;

import nl.rug.oop.cardgame.controller.buttons.*;
import nl.rug.oop.cardgame.model.bussen.Bussen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

/**
 * This  class sets the Jmenubar and adds/removes the buttons.
 */
public class ButtonMenu extends JMenuBar implements Observer {

    private Bussen bussen;
    private ButtonRed red;
    private ButtonBlack black;
    private ButtonHigher higher;
    private ButtonLower lower;
    private ButtonBetween between;
    private ButtonOutside outside;
    private ButtonHaveIt haveIt;
    private ButtonDontHaveIt dontHaveIt;
    private ButtonPyramidRound nextRound;
    private ButtonTurnCard turnCard;
    private ButtonGotCard gotCard;
    private ButtonBusRound busRound;
    private Color bgColor=Color.black;

    public ButtonMenu(Bussen bussen){
        setButtonMenuConstraints();
        setButtons(bussen);


    }

    /**
     * Every round we need new buttons, this method handles that by checking the draws.
     * @param bussen is the game.
     */
    public void setButtons(Bussen bussen){
        this.bussen = bussen;
        bussen.addObserver(this);

        if (bussen.getDrawCount() == 0) {
            red     = new ButtonRed(bussen, "Do you predict the card to be red?");
            black   = new ButtonBlack(bussen, "Do you predict the card to be black?");
            add(red);
            add(black);
        } else if (bussen.getDrawCount() == 1){
            remove(red);
            remove(black);
            higher  = new ButtonHigher(bussen, "Do you predict the card to be higher then the previous one?");
            lower   = new ButtonLower(bussen, "Do you predict the card to be lower than the previous one?");
            add(higher);
            add(lower);
        } else if (bussen.getDrawCount() == 2) {
            remove(higher);
            remove(lower);
            between = new ButtonBetween(bussen, "Do you predict the card to be between the previous two?");
            outside = new ButtonOutside(bussen, "Do you predict the card to be outside the previous two?");
            add(between);
            add(outside);
        } else if (bussen.getDrawCount() == 3) {
            remove(between);
            remove(outside);
            haveIt      = new ButtonHaveIt(bussen, "You already have the suit in your pile");
            dontHaveIt  = new ButtonDontHaveIt(bussen, "You dont have the suit in your pile");
            add(haveIt);
            add(dontHaveIt);
        } else if (bussen.getDrawCount() == 4){
            remove(haveIt);
            remove(dontHaveIt);
            nextRound   = new ButtonPyramidRound(bussen,"click to continue to the pyramid round");
            add(nextRound);
        } else if (bussen.getDrawCount() == 5){
            remove(nextRound);
            turnCard    = new ButtonTurnCard(bussen, "Click to turn the card");
            gotCard     = new ButtonGotCard(bussen,"You have this card in your pile");
            add(turnCard);
            add(gotCard);
        }
        else if (bussen.getDrawCount2() == 10){
            remove(turnCard);
            remove(gotCard);
            busRound = new ButtonBusRound(bussen,"Continue to bus");
            add(busRound);

        } else if(bussen.getDrawCount2() == 11){
            remove(busRound);
            add(higher);
            add(lower);
        }

    }

    public void setButtonMenuConstraints(){
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(bgColor);
        g2d.fill3DRect(0, 0, getWidth() - 1, getHeight() - 1,true);
        g.drawImage(Toolkit.getDefaultToolkit().getImage("extras" + File.separator + "menubar.png"),0,0,this);


    }

    @Override
    public void update(Observable o, Object arg) {
        setButtons(bussen);
        doLayout();
        repaint();
        revalidate();
    }
}
