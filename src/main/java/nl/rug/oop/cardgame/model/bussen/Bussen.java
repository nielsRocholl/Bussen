package nl.rug.oop.cardgame.model.bussen;

import nl.rug.oop.cardgame.model.deck.DiscardPile;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.deck.CompleteDeck;
import nl.rug.oop.cardgame.view.panel.Panel;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This can be seen as the main game class, it has all the methods to check the logic of the game.
 */
public class Bussen extends Observable {

    private Card card;
    private CompleteDeck deck;
    private DiscardPile discardPile;
    private int drawCount = 0;
    private int drawCount2 = 0;
    private int drinkCounter = 0;
    private int busCnt = 0;
    private int drawCount3 = 6;
    private boolean pyramid = false;
    private ArrayList<Card> cards;
    private ArrayList<Card> pyramidCards;
    private ArrayList<Card> turnedCards;
    private ArrayList<Card> cardsBus;


    public Bussen(){
        discardPile = new DiscardPile();
        deck = new CompleteDeck();
        CompleteDeck deckBus = new CompleteDeck();
        deck.shuffle();
        deckBus.shuffle();
        cards = new ArrayList<>();
        turnedCards = new ArrayList<>();
        cardsBus = new ArrayList<>();
        makeList(deckBus);
    }

    /**
     * Creates list, since I prever a list over a stack (busdeck).
     * @param deckBus completeDeck
     */
    public void makeList(CompleteDeck deckBus){
        while (!deckBus.isEmpty()){
            cardsBus.add(deckBus.draw());
        }

    }

    /**
     * Used by the actionBlack andActionRed handles the logic of the selected option.
     * @param answer answer from user
     */
    public void RedOrBlack(String answer){
        update();
        if(answer.equals(card.getColour().toString())){
            popUpGive();
        }else {
            popUpDrink();
            drinkCounter++;
        }

    }

    /**
     * Used by actionHigher and actionLower, handles the logic of the selected option.
     * @param answer from user
     */
    public void HigherOrLower(String answer){
        update();
        if (drawCount2 >= 11){
            if (busCnt == 7){
                showMessageDialog(null, "You left the bus, the game is over!");
                System.exit(0);
            }
            if (cardsBus.get(drawCount3+1).getValue() > Panel.getCurrent().getValue() && answer.equals("HIGHER")
            ||cardsBus.get(drawCount3+1).getValue() < Panel.getCurrent().getValue() && answer.equals("LOWER")){
                popUpGive();
            }else {
                busCnt = 0;
                popUpDrink();
            }
            busCnt++;
            drawCount3++;
            notifyObservers();
            setChanged();
            update();
        }else {
            if(answer.equals("HIGHER") && card.getValue() > cards.get(0).getValue()
                    || answer.equals("LOWER") && card.getValue() < cards.get(0).getValue()){
                popUpGive();
            }else {
                popUpDrink();
                drinkCounter++;
            }
        }

    }

    /**
     * Used by actionBetween and actionOutside, handles the logic of the selected option i.e. checks if the
     * player actually predicted the right answer..
     * @param answer from user
     */
    public void BetweenOrOutside(String answer){
        update();
        int a = cards.get(0).getValue();
        int b = cards.get(1).getValue();
        if (answer.equals("BETWEEN") && (card.getValue() > Math.min(a,b)  && card.getValue() < Math.max(a, b))) {
            popUpDrink();
        }else if ((answer.equals("OUTSIDE") && card.getValue() < Math.min(a,b))
                || (answer.equals("OUTSIDE") && card.getValue() > Math.max(a,b))) {
            popUpGive();
        }else {
            popUpDrink();
            drinkCounter++;
        }
    }

    /**
     * Used by actionHaveIt and actionDontHaveIt, handles the logic of the selected option i.e. checks
     * if the player actually predicted the right answer.
     * @param answer from user
     */
    public void WhichSuit(String answer){
        update();
        if ((answer.equals("YES") && suitInPile()) || (answer.equals("NO") && !suitInPile())){
            popUpGive();
        }else {
            popUpDrink();
            drinkCounter++;
        }
        showMessageDialog(null,"MEMORISE YOUR CARDS!!");
    }

    /**
     * Used by actionGotCard, handles the logic of the selected option i.e. checks if player actually got the card.
     */
    public void gotCard(){
        boolean a = false;
        int i = cards.size() -1;
        for ( ; i >= 0 ; i--){
            if( drawCount2-1 < 0){
                return;
            }
            if (pyramidCards.get(drawCount2-1).getValue() == cards.get(i).getValue()) {
                a = true;
                turnedCards.add(cards.get(i));
                cards.remove(cards.get(i));
                break;
            }
        }
        if (a){
            popUpGive();
        }else {
            popUpDrink();
            drinkCounter++;
        }
        notifyObservers();
        setChanged();
    }

    /**
     * Used by actionGotCard, handles the logic of the selected option and checks if suit is in players cards.
     */
    public boolean suitInPile(){
        int len = cards.size() - 2;
        for (;len >= 0; len--){
            if (card.getSuit() == cards.get(len).getSuit()){
                return true;
            }
        }
        return false;
    }

    /**
     * Used to enter the pyramid round.
     */
    public void pyramid(){
        drawCount++;
        setChanged();
        notifyObservers();
        pyramid = true;
        pyramidCards = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            pyramidCards.add(deck.draw());
        }
    }

    /**
     * used to enter the bus round.
     */
    public void bus(){
        drawCount2++;
        setChanged();
        notifyObservers();
    }

    /**
     * Used by actionTurn card to turn a card in the second round.
     */
    public void turnCard(){
        drawCount2++;
        drawCount++;
        setChanged();
        notifyObservers();
    }

    /**
     * used to update the model, which needs to be updated at every action.
     */
    public void update(){
        card = deck.draw();
        cards.add(card);
        discardPile.put(card);
        drawCount++;
        setChanged();
        notifyObservers();
    }

    /**
     * Wanted to put these in view however it works better in this class since the timing of the pop up message is
     * too hard to generalize and put in view, meaning that the pop up message will show up to late or to early
     * depending on the action if it's in view. And also I preferred a pop up over for instance a text box in the frame
     * because after vigorous testing I noticed that the player tends to proceed to quickly when he/she
     * actually need to take a shot/drink if its displayed in a text box.
     */
    public void popUpDrink(){
        ImageIcon iconic = new ImageIcon("extras"+ File.separator + "drink.png");
        //new Mp3Player("drink.wav");
        showMessageDialog(null, null,"YOU HAVE TO DRINK",JOptionPane.INFORMATION_MESSAGE, iconic);
    }

    public void popUpGive(){
        ImageIcon iconic = new ImageIcon("extras"+ File.separator + "giveDrink.png");
        showMessageDialog(null, null, "YOU NEED TO GIVE A DRINK AWAY",JOptionPane.INFORMATION_MESSAGE, iconic);
    }

    public ArrayList<Card> getPyramidCards(){ return pyramidCards; }

    public ArrayList<Card> getCards() { return cards; }

    public ArrayList<Card> getTurnedCards() { return turnedCards; }

    public ArrayList<Card> getCardsBus() { return cardsBus; }

    public DiscardPile getDiscardPile(){ return discardPile; }

    public boolean getPyramid(){ return pyramid; }

    public int getDrinkCounter(){ return drinkCounter; }

    public int getDBusCnt(){ return busCnt; }

    public int getDrawCount(){ return drawCount; }

    public int getDrawCount2(){ return drawCount2; }

    public int getDrawCount3() { return drawCount3; }

}
