# Good Thirteen Card Game

A robust, Java-based implementation of the "Good Thirteen" solitaire game. This project demonstrates core programming concepts including custom generic data structures, resource management, and clean code practices.

## 🎮 How to Play
The goal of the game is to clear all 52 cards from the deck and your hand.

1.  **The Setup**: You start with 10 cards dealt face-up from a shuffled 52-card deck.
2.  **Removing Cards**: You can remove cards from your hand in two ways:
    * **Pairs**: Select two cards whose face values add up to **13** (e.g., a 6 and a 7, or an Ace and a Queen).
    * **Kings**: Since a King has a value of **13**, it can be removed individually.
3.  **Refilling**: Every time you remove cards, the game automatically draws new cards from the deck to refill your hand back to 10.
4.  **Winning**: You win if you successfully remove all 52 cards.
5.  **Losing**: The game is over if no more combinations of 13 can be made and the deck is empty.

**Card Values:** Ace = 1, Jack = 11, Queen = 12, King = 13.

## 🚀 Getting Started

### Prerequisites
* **Java Development Kit (JDK) 11 or higher** installed on your machine.

### Installation & Execution
1.  **Clone the repository**:
    ```bash
    git clone [https://github.com/Saadalix/goodThirteen.git](https://github.com/Saadalix/goodThirteen.git)
    cd goodThirteen
    ```
2.  **Compile the source code**:
    ```bash
    javac src/game/*.java -d out
    ```
3.  **Run the game**:
    ```bash
    java -cp out game.GameToPlay
    ```

## 🛠️ Technical Features
* **Memory Management**: Implements `try-with-resources` to ensure `Scanner` objects are closed automatically, preventing memory leaks.
* **Modern Syntax**: Uses Java's **Rule Switch (arrow syntax)** for cleaner, more readable menu logic.
* **Custom Data Structures**: Features a custom generic `Stack<T>` class for managing the deck and player hand.
* **Replay System**: Includes a move-tracking system that allows players to review their game history.
* **Demo Mode**: A built-in automated mode to showcase how the game logic identifies pairs.

## 📂 File Structure
* `GameToPlay.java`: The main controller containing the game loop and menu logic.
* `Deck.java`: Handles the deck generation, shuffling, and hand management.
* `Card.java`: A simple object representing a single playing card with immutable fields.
* `Stack.java`: A generic array-based implementation of a stack used for card storage.
