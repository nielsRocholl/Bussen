package nl.rug.oop.cardgame.model.deck;

import nl.rug.oop.cardgame.util.Emptiable;
import nl.rug.oop.cardgame.util.Sized;
import nl.rug.oop.cardgame.model.card.Card;

import java.util.*;

/**
 * An arbitrary deck of cards. Cards in a deck are usually closed.
 */
abstract public class AbstractDeck implements Emptiable, Sized {

    private static int seed = 42;

    /**
     * To allow slight variation in the way games play out, the seed used
     * is changed every time, but it is seeded to allow reproducible results
     */
    private static int nextSeed() {
        return seed++;
    }

    /**
     * For the purpose of digital shuffling the list-interface, or rather
     * the Collections function for swapping that requires the list-interface
     * is essential, as the algorithm used for it is also used in the
     * shuffle-method.
     */
    protected List<Card> cards;
    private Random random;

    /**
     * Create a new deck with no cards
     */
    public AbstractDeck() {
        cards = new ArrayList<>();
        random = new Random(nextSeed());
        addCards();
    }

    /**
     * reset the deck and put in the cards it has by default.
     */
    public void reset() {
        empty();
        addCards();
    }

    /**
     * Adds the cards this deck has by default
     */
    abstract protected void addCards();

    /**
     * Place a card back on the deck at the top of the deck
     *
     * @param card The card to be placed on top
     */
    public void addOnTop(Card card) {
        cards.add(card);
    }

    /**
     * Place a card at the back of the deck. Due to the backing data structure
     * this is slower than putting the card on top.
     *
     * @param card The card to be placed back
     */
    public void addOnBottom(Card card) {
        cards.add(0, card);
    }

    /**
     * Puts a card anywhere in the deck
     *
     * @param card The card to be placed back
     */
    public void addAnywhere(Card card) {
        cards.add(random.nextInt(cards.size()), card);
    }

    /**
     * Add all cards in the discard pile back into the deck. The deck is
     * shuffled afterwards to eliminate the possibility of cheating
     *
     * @param pile Pile of Cards
     */
    public void addAll(Collection<Card> pile) {
        cards.addAll(pile);
    }

    /**
     * Shuffle the deck using a Knuth/Fisher-Yates shuffle. Every card is
     * swapped with an arbitrary card from the cards after it.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Check the number of cards in this deck
     */
    @Override
    public int size() {
        return cards.size();
    }

    /**
     * Check if there are any cards left in this deck.
     */
    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Make this deck empty (e.g. make isEmpty() return true)
     */
    @Override
    public void empty() {
        cards.clear();
    }

    /**
     * Draw a card from the deck. This method will return null if the
     * deck is empty,
     */
    public Card draw() {
        if (!isEmpty())
            return cards.remove(cards.size() - 1);
        return null;
    }


}
