
package game;

public class Deck {
    //This class will create 52 cards
    private final Stack<Card> deck;
    private final Stack<Card> cardsInHand;
    private final Stack<Object> replay;
    public int numberOfCards = 0;
    final String[] rankArray = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    final Integer[] cardValue = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    final String[] suitArray = {" of Hearts ", " of Diamonds ", " of Spades ", " of Clubs "};

    public Deck() {
        //This constructor will instantiate 3 arrays of Stack data type.
        deck = new Stack<>(52);
        cardsInHand = new Stack<>(10);
        replay = new Stack<>();
    }
    public void generatorOfCards() {
        for (String suite : suitArray) {
            for (int i = 0; i < rankArray.length; i++) {
                deck.addNewEntry(new Card(rankArray[i], suite, cardValue[i]));
                numberOfCards++;
            }
        }
        deck.shuffleCards();
    }
    public void playerHand() {
        for (int i = 0; i < 10; i++) {
            cardsInHand.addNewEntry(deck.remove());
        }
    }

    public void displayTable() {
         //This method will print the sample table to guide the user about the input
        System.out.println("\nThe shuffled list of 10 cards in hand, pick the cards (separated by '--'), using index positions from 1 to 10.\n");
        StringBuilder str = new StringBuilder("-- ");
        for (int i = 0; i < cardsInHand.getCurrentSize(); i++) {
            str.append(cardsInHand.getElementByIndex(i).getCardRank()).append(cardsInHand.getElementByIndex(i).getCardSuit()).append("-- ");
            if (i == 4) str.append("\n-- ");
            else if (i == 9) str.append("\n");
        }
        System.out.println(str);
        System.out.println("where Ace = 1, Jack = 11, Queen = 12, King = 13 ");
        System.out.println("\nThe following options will allow you to remove 52 cards from the playing area. ");
    }

    public void removeSumOf13 (int x, int y) {
        //to avoid crashing the program, try and catch is used
        try {
            //This method will remove 2 cards from the player hand if in sum will make 13
            int xValue = cardsInHand.getElementByIndex(x - 1).getFaceValue();
            int yValue = cardsInHand.getElementByIndex(y - 1).getFaceValue();
            int sum = xValue + yValue;

            Card first = cardsInHand.getElementByIndex(x - 1);
            Card second = cardsInHand.getElementByIndex(y - 1);

            if (sum == 13) {
                cardsInHand.remove(first);
                String str1 = first.toString() + "has removed!";
                replay.addNewEntry(str1);
                System.out.println(str1);
                cardsInHand.remove(second);
                String str2 = second.toString() + "has removed!";
                replay.addNewEntry(str2);
                System.out.println(str2);
                fillUpPlayersHand();
            } else {
                System.out.println("Numbers you entered don't make 13 in sum! ");
            }
        } catch (NullPointerException e) {
            System.out.println("\nWrong number entered! Please insert a valid number from 1 to 10..!!");
        }
    }

    public void removeKing(int x) {
        try {
            //This method will remove 1 card from the player hand if in value is 13,
            int xValue = cardsInHand.getElementByIndex(x - 1).getFaceValue();
            Card first = cardsInHand.getElementByIndex(x - 1);

            if (xValue == 13) {
                cardsInHand.remove(first);
                String str1 = first.toString() + "has removed!";
                replay.addNewEntry(str1);
                System.out.println(str1);
                fillUpPlayersHand();
            } else {
                System.out.println("The number entered is not a King ");
            }
        } catch (NullPointerException e) {
            System.out.println("\nWrong number entered! Please insert a valid number from 1 to 10..!!");
        }
    }

    public void fillUpPlayersHand() {
        //Since we know that we have 10 cards faceUp we will subtract from 10 the current size of player hand
        //and we will fill up the amount of cards that the user is missing.
        int preSize = 10 - cardsInHand.getCurrentSize();
        for (int i = 0; i < preSize; i++) {
            if (deck.getCurrentSize() != 0) {
                Card x = deck.remove();
                cardsInHand.addNewEntry(x);
                String str = x.toString() + " < + > Added to the player hand!";
                //Storing the new entry that was added to the replay array.
                replay.addNewEntry(str);
                System.out.println(str);

            } else {
                break;
            }

        }
        String str1 = "Cards in deck: ( " + deck.getCurrentSize() + " )\n";
        replay.addNewEntry(str1);
        System.out.println(str1);
    }

    public boolean giveHint() {
       //it will guide the user about the possible combination that can make in sum 13
        System.out.println("___________________________________");
        System.out.println("|The list of possible combinations!|");
        System.out.println("|__________________________________|");
        if (checkKing()) {
            for (int i = 0; i < cardsInHand.getCurrentSize(); i++) {
                if (cardsInHand.getElementByIndex(i).getFaceValue()==13) {
                    System.out.println("Card" + cardsInHand.getElementByIndex(i).toString() + " at index " + (i+ 1));
                    System.out.println();
                    return true;
                }
            }

        } else if (check13()) {

            for (int i = 0; i < cardsInHand.getCurrentSize(); i++) {
                for (int j = 0; j < cardsInHand.getCurrentSize(); j++) {
                    int sum = cardsInHand.getElementByIndex(i).getFaceValue() + cardsInHand.getElementByIndex(j).getFaceValue();
                    if (sum == 13) {
                        System.out.println("Card" + cardsInHand.getElementByIndex(i).toString() + " at index " + (i + 1));
                        System.out.println("Card" + cardsInHand.getElementByIndex(j).toString() + " at index " + (j + 1));
                        System.out.println("Can be removed!!");
                        return true;
                    }
                }
            }

        } else {
            System.out.println("No available hints!!!Game Lost!");
            return false;
        }

        System.out.println("####################################");
        return false;
    }

    public boolean checkKing() {
        //This method simply returns true if king card detected in our hand.
        boolean response=false;
        for (int i = 0; i < cardsInHand.getCurrentSize(); i++) {
            if  (cardsInHand.getElementByIndex(i).getFaceValue()==13)
                response=true;        }
        return (response);
    }

    public boolean check13() {
        /*
        Using 2 for loops to check for any possible combinations that will make 13 in sum
        @Returns true if found, otherwise false.
         */
        for (int i = 0; i < cardsInHand.getCurrentSize(); i++) {
            for (int j = 0; j < cardsInHand.getCurrentSize(); j++) {
                int sum = cardsInHand.getElementByIndex(i).getFaceValue() + cardsInHand.getElementByIndex(j).getFaceValue();
                if (sum == 13) return true;
            }
        }
        return false;
    }

    public Integer[] demo() {
        int size = 0;
        Integer[] ourIndexes = new Integer[size];

        if (checkKing()) {

            for (int i = 0; i < cardsInHand.getCurrentSize(); i++) {
                if (cardsInHand.getElementByIndex(i).getFaceValue()==13) {
                    ourIndexes = new Integer[]{i + 1};
                    size += 1;
                }
            }
        } else if (check13()) {

            for (int i = 0; i < cardsInHand.getCurrentSize(); i++) {
                for (int j = 0; j < cardsInHand.getCurrentSize(); j++) {
                    int sum = cardsInHand.getElementByIndex(i).getFaceValue() + cardsInHand.getElementByIndex(j).getFaceValue();
                    if (sum == 13) {
                        ourIndexes = new Integer[]{i + 1, j + 1};
                        size += 2;
                    }
                }
            }
        }
        //returning the list of indexes that will help us to remove the elements from the playerhand automatically.
        return ourIndexes;
    }

    public boolean win() {
        //This method return true if the deck size is 0 AND if there are no cards in the playerhand.
        return deck.getCurrentSize() == 0 && cardsInHand.getCurrentSize() == 0;

    }
    public Stack<Object> getReplay() {
        return replay;
    }

}
