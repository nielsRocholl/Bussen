package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.model.bussen.Bussen;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.view.textures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Draws all the stuff in the frame.
 */
public class Panel extends JPanel implements Observer {

    private static final double CARD_WIDTH = 436.0;
    private static final double CARD_HEIGHT = 600.0;
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 900;
    private Bussen bussen;
    private Image img;
    private ArrayList<Image> cardImg;
    private ArrayList<Card> cardItems;
    private  static Card current;


    public Panel(Bussen bussen){
        setBackground(Color.white);
        this.bussen = bussen;
        img();
        bussen.addObserver(this);
        setLayout(null);
        setBus();
    }

    /**
     * Used to set the first 7 cards for the bus.
     */
    public void setBus(){
        cardImg = new ArrayList<>();
        cardItems = new ArrayList<>();
        for (int i = 0; i < 7; i ++){
            cardImg.add(CardTextures.getTexture(bussen.getCardsBus().get(i)));
            cardItems.add(bussen.getCardsBus().get(i));
        }
        current = cardItems.get(0);
    }

    /**
     * Used to draw the sweet ass background.
     */
    public void img() {
        img = new ImageIcon("extras" + File.separator + "img.jpg").getImage();
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }


    /**
     * Used to paint the first 4 cards
     * @param g graphics object
     */
    private void paintDiscard(Graphics g){
        int cnt = 1;
        int posY = ((FRAME_HEIGHT / 2) - ((int) (cardHeight() * 0.7)/2));
        for (Card card: bussen.getDiscardPile()){
            int posX = ((FRAME_WIDTH/5) * cnt )- ((int) (cardWidth() *0.7)/2);
            g.drawImage(CardTextures.getTexture(card), posX, posY, (int) (cardWidth() * 0.7), (int) (cardHeight() * 0.7), this);
            g.drawRect(posX, posY,(int) (cardWidth() * 0.7), (int) (cardHeight() * 0.7));
            cnt++;
        }
    }

    /**
     * This function has a unique posX for every case, this is why I have all the if statements
     * and why I cant make a simple loop
     * Paints the pyramid in round 2
     * @param g Graphics object
     */
    public void paintPyramid(Graphics g){
        int posY;
        int cnt = 1;
        int posX = 0;
        int count = 0;
        BufferedImage cardBackTexture = CardBackTextures.getTexture(CardBack.CARD_BACK_RED);
        int a = (int) (0.3 * cardWidth());
        for (int j = 0; j < 4; j++){
            for (int i = 0; i < cnt; i++){
                posY = ((FRAME_HEIGHT/5) * cnt) - (cardHeight()/4);
                if (cnt == 1){
                    posX = (FRAME_WIDTH/2) - (cardWidth()/4);
                }else if (cnt == 2){
                    if (i == 0){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4) - a;
                    }
                    if (i == 1){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4) + a;
                    }
                }else if (cnt == 3){
                    if (i == 0){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4) - a - (cardWidth()/4);
                    }
                    if (i == 1){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4);
                    }
                    if (i == 2){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4) + a + (cardWidth()/4);
                    }
                } else if(cnt == 4){
                    if (i == 0){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4) - a - (cardWidth()/2) - (int)(0.1*cardWidth());
                    }
                    if (i == 1){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4) - a;
                    }
                    if (i == 2){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4) + a;
                    }
                    if (i == 3){
                        posX = (FRAME_WIDTH/2) - (cardWidth()/4) + a + (cardWidth()/2) + (int)(0.1*cardWidth());

                    }
                }
                if (count < bussen.getDrawCount2()){
                    g.drawImage(CardTextures.getTexture(bussen.getPyramidCards().get(count)), posX, posY,
                            cardWidth()/2, cardHeight()/2, this);
                }else {
                    g.drawImage(cardBackTexture, posX, posY, cardWidth()/2, cardHeight()/2, this);
                }
                g.drawRect(posX, posY, cardWidth()/2, cardHeight()/2);
                count++;
            }
            cnt++;
        }
    }

    /**
     * Paints your 4 cards in the top right corner and turns them over if they are used.
     * @param g Graphics object
     */
    public void paintYourCards(Graphics g){
        BufferedImage cardBackTexture = CardBackTextures.getTexture(CardBack.CARD_BACK_RED);
        int cnt = 1;
        int size = bussen.getTurnedCards().size()-1;
        for (int i = 0; i < 4; i++){
            int posX = (FRAME_WIDTH/10) * 8;
            int posY = (FRAME_HEIGHT/8) * cnt;
            cnt++;
            if (size >= 0  ){
                g.drawImage(CardTextures.getTexture(bussen.getTurnedCards().get(size)), posX, posY,
                        cardWidth()/4, cardHeight()/4, this);
                size--;
            }else {
                g.drawImage(cardBackTexture, posX, posY, cardWidth() / 4, cardHeight() / 4, this);
            }
            g.drawRect(posX, posY, cardWidth()/4, cardHeight()/4);
        }
    }


    /**
     * Paints the bus, by first painting 7 cards, then putting new cards on top of them starting at the first until the
     * 7th card, however if you  guess a card wrong it jumps back to the first card. Once you guessed 7 cards right
     * consecutively you're out of the bus.
     * @param g Graphics object
     */
    public void paintBus(Graphics g){

        int posY = ((FRAME_HEIGHT/4) *3) - cardHeight()/2/2;

        if (bussen.getDrawCount3() >= 50){
            ImageIcon iconic = new ImageIcon("extras"+ File.separator + "finished.png");
            showMessageDialog(null, null,"YOU FINISHED YOUR TRIP IN THE BUS, GAME OVER",JOptionPane.INFORMATION_MESSAGE, iconic);
            System.out.println("empty!");
            System.exit(0);
        }

        for (int i = 0; i < 7; i++){
            if (bussen.getDBusCnt() == i + 1   ) {
                cardImg.set(i,CardTextures.getTexture(bussen.getCardsBus().get(bussen.getDrawCount3())));
                if (bussen.getDBusCnt() == 6){
                    current = cardItems.get(0);
                }else {
                    current = cardItems.get(i + 1);
                }
            }
            g.drawImage(cardImg.get(i), (((FRAME_WIDTH / 8)) * (i +1)) - cardWidth()/2/2, posY, cardWidth()/2, cardHeight()/2 , this);
        }
    }

    /**
     * Paints the card pile in round 3. Just for looks.
     * @param g Graphics
     */
    public void paintCardPile(Graphics g){
        int depth;

        for (depth = 0; depth < (50 - bussen.getDrawCount3()); depth++) {
            BufferedImage cardBackTexture = CardBackTextures.getTexture(CardBack.CARD_BACK_RED);
            int posX = (FRAME_WIDTH/2) - (int) (cardWidth()/1.5/2) + depth;
            int posY =  (350) - (int)(cardHeight()/1.5/2) - (depth * 2 );
            Stroke stroke1 = new BasicStroke(0.5f);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(stroke1);
            g.drawImage(cardBackTexture, posX, posY, (int)(cardWidth()/1.5), (int)(cardHeight()/1.5), this);
            g2d.drawRect(posX, posY, (int)(cardWidth()/1.5), (int)(cardHeight()/1.5));
            g.setColor(Color.black);
        }

    }

    /**
     * Creates the drink counter.
     * @param g Graphics object.
     */
    public void drawDrinkCount(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.black);
        g.drawString("DRINK COUNTER: " + bussen.getDrinkCounter(), 25, 130);

    }

    /**
     * Draws the rect for drink counter.
     * @param g Graphics object
     */
    public void drawRect(Graphics g){
        g.drawImage(Toolkit.getDefaultToolkit().getImage("extras" + File.separator + "counter.png"),20,100,this);
    }

    /**
     * Get the scaled spacing between edges and cards
     */
    private int getSpacing() {
        return (int) ((getHeight()* 20) / CARD_HEIGHT);
    }

    /**
     * Get the scaled width of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardWidth() {
        if ((getHeight() * CARD_HEIGHT) / (getWidth() * CARD_WIDTH) <= 1.0)
            return (int) ((cardHeight() * CARD_WIDTH) / CARD_HEIGHT);
        return (getWidth() - getSpacing() * 3 - 2 * Card.values().length) / 2;
    }

    /**
     * Get the scaled height of cards. Default height is 600, default
     * width is 436, and cards are scaled depending on which dimension limits
     * their relative dimensions
     */
    public int cardHeight() {
        if ((getHeight() * CARD_HEIGHT) / (getWidth() * CARD_WIDTH) > 1.0)
            return (int) ((cardWidth() * CARD_HEIGHT) / CARD_WIDTH);
        return (getHeight() - getSpacing() * 2 - 2 * Card.values().length)/2;
    }

    public static Card getCurrent(){
        return current;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
        drawRect(g);
        drawDrinkCount(g);
        if (!bussen.getPyramid()){
            paintDiscard(g);
        }
        if (bussen.getPyramid() && bussen.getDrawCount2() <= 10){
            paintYourCards(g);
            paintPyramid(g);

        }
        if (bussen.getDrawCount2() == 11){
            paintBus(g);
            paintCardPile(g);
        }
        setLayout(null);

    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();

    }
}
