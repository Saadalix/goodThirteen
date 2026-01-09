package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameToPlay {

    public static void playInDemo(Deck cards) {
        int numberOfAttempts = 0;
        cards.generatorOfCards();
        cards.playerHand();

        // Using try-with-resources to automatically close the scanner and fix resource leaks
        try (Scanner input = new Scanner(System.in)) {
            while (!cards.win()) {
                cards.displayTable();
                Integer[] list = cards.demo();

                System.out.println("\n Press 1 to play next step");

                if (input.hasNextInt() && input.nextInt() == 1) {
                    if (list.length < 1) {
                        System.out.println(" Out of Moves! ");
                        break;
                    } else if (list.length == 2) {
                        cards.removeSumOf13(cards.demo()[0], cards.demo()[1]);
                        numberOfAttempts++;
                    } else if (list.length == 1) {
                        cards.removeKing(cards.demo()[0]);
                        numberOfAttempts++;
                    }
                } else {
                    System.out.println("Invalid number was entered!");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("The input can't be a letter, try again!");
        }

        System.out.println(cards.win() ? "You won!!" : "Game is over...!!");
        System.out.println("Number of moves made: " + numberOfAttempts);
    }

    public static void replayGame(Deck cards) {
        System.out.println("\n<< Moves Taken >>");
        // Try-with-resources for 'item' scanner
        try (Scanner item = new Scanner(System.in)) {
            for (int i = 0; i < cards.getReplay().getCurrentSize(); i++) {
                String s = cards.getReplay().getElementByIndex(i).toString();

                System.out.println("Press < 1 > to see the moves taken! Or any other number to exit!");
                if (item.hasNextInt() && item.nextInt() == 1) {
                    System.out.println("Movement number - " + (i + 1) + " :>" + s);
                } else {
                    System.out.println("That's all in the list! ");
                    System.exit(-1);
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Input should be a number!");
        }
        System.out.println("End of the list!");
    }

    public static void playGame(Deck cards) {
        int numberOfAttempts = 0;
        cards.generatorOfCards();
        cards.playerHand();
        boolean pass = false;

        // Try-with-resources for 'sc' scanner
        try (Scanner sc = new Scanner(System.in)) {
            while (!cards.win() && !pass) {
                cards.displayTable();
                inTextDisplay();
                
                if (!sc.hasNextInt()) {
                    sc.next(); // clear invalid input
                    continue;
                }

                // Converted to modern Rule Switch (arrow syntax) to satisfy IDE hints
                switch (sc.nextInt()) {
                    case 1 -> {
                        if (cards.win()) {
                            System.out.println("You won!");
                            pass = true;
                        } else {
                            System.out.println(" -- Pick any 2 cards summing up to 13! --");
                            if (sc.hasNextInt()) {
                                int first = sc.nextInt();
                                if (sc.hasNextInt()) {
                                    cards.removeSumOf13(first, sc.nextInt());
                                    numberOfAttempts++;
                                }
                            }
                        }
                    }
                    case 2 -> {
                        if (cards.win()) {
                            System.out.println("You won!");
                            pass = true;
                        } else {
                            System.out.println("Choose King Card!");
                            if (sc.hasNextInt()) {
                                cards.removeKing(sc.nextInt());
                                numberOfAttempts++;
                            }
                        }
                    }
                    case 3 -> {
                        if (!cards.giveHint()) pass = true;
                    }
                    case 4 -> {
                        System.out.println(" Good Bye! ");
                        System.exit(-1);
                    }
                    default -> System.out.println("Wrong input! Try again!");
                }
            }

            System.out.println(cards.win() ? "You won!!" : "Number of moves made: " + numberOfAttempts);

            displayEnd();
            if (sc.hasNextInt()) {
                int endInput = sc.nextInt();
                switch (endInput) {
                    case 1 -> {
                        replayGame(cards);
                        cards.getReplay().clear();
                    }
                    case 2 -> playGame(new Deck());
                    default -> {
                        System.out.println("Other options not allowed here!\nBetter luck next time!!");
                        System.exit(-1);
                    }
                }
            }
        }
    }

    public static void inTextDisplay() {
        System.out.println("\n1. To remove 2 cards summing up to 13 rank value ");
        System.out.println("2. To remove a King ");
        System.out.println("3. To get a hint ");
        System.out.println("4. Exit ");
    }

    public static void displayStart() {
        System.out.println("\n**** Welcome to the Good Thirteen Game  **** ");
        System.out.println("\nEnter your choice and Good Luck..!!\n");
        System.out.println("1. Play a new Game");
        System.out.println("2. Play in Demo Mode");
        System.out.println("3. Exit");
    }

    public static void displayEnd() {
        System.out.println("\n-- What would you like to do next?--");
        System.out.println("1. Replay my movements");
        System.out.println("2. Play Again");
        System.out.println("3. Exit");
    }

    public static void main(String[] args) {
        Deck cards = new Deck();
        // Main scanner wrapped in try-with-resources
        try (Scanner input = new Scanner(System.in)) {
            displayStart();
            if (input.hasNextInt()) {
                int menuInput = input.nextInt();
                
                // Rule Switch for main menu
                switch (menuInput) {
                    case 1 -> playGame(cards);
                    case 2 -> playInDemo(cards);
                    case 3 -> System.exit(-1);
                    default -> {
                        System.out.println("By default game will start with option 1");
                        playGame(cards);
                    }
                }
            }
        }
    }
}