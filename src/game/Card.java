
package game;

public class Card {
    private final String cardSuit;
    private final String cardRank;
    private final int faceValue;

    public Card(String rank, String suit, int value) {
        //Constructor to call rank, suit and face value
        cardSuit = suit;
        cardRank = rank;
        faceValue = value;
    }
    public String getCardSuit() {
        //Returns the suit of the card.
        return cardSuit;
    }
    public String getCardRank() {
        //Returns the rank of the card.
        return cardRank;
    }
    public int getFaceValue() {
        //Returns the face value of the card.
        return faceValue;
    }
    
    @Override
    public String toString() {
        //Return the output value.
        String script = cardRank + cardSuit;
        return script;
    }
}
