package game;

import java.util.Random;

public final class Stack<T> {
    private T[] deck;
    private int numOfEntries;
    // Renamed to follow ALL_CAPS convention
    private static final int STANDARD_PACK = 52;
    private boolean initialised;
    private static final int MAX_CAPACITY = 8000;

    public T getElementByIndex(int index) {
        T result = null;
        if (!isEmpty() && index >= 0 && index < numOfEntries) {
            result = deck[index];
        }
        return result;
    }

    public void shuffleCards() {
        Random mix = new Random();

        for (int i = deck.length - 1; i > 0; i--) {
            int j = mix.nextInt(i + 1);
            T tmp = deck[j];
            deck[j] = deck[i];
            deck[i] = tmp;
        }
    }

    private boolean isArrayFull() {
        return (deck.length == numOfEntries);
    }

    private void checkInitialisation() {
        if (!initialised)
            throw new SecurityException("CardStack was not initialised properly!");
    }

    public T removeElementAt(int index) {
        T result = null;
        if (!isEmpty() && index >= 0 && index < numOfEntries) {
            result = deck[index];
            deck[index] = deck[numOfEntries - 1];
            deck[numOfEntries - 1] = null;
            numOfEntries--;
        }
        return result;
    }

    public Stack() {
        this(STANDARD_PACK);
    }

    // Suppressing unchecked warning for the generic array cast
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        if (capacity <= MAX_CAPACITY) {
            // Java cannot verify this cast at runtime, so we suppress the warning
            T[] tempStack = (T[]) new Object[capacity];
            deck = tempStack;
            numOfEntries = 0;
            initialised = true;
        } else throw new IllegalStateException("Attempt to create a bag where the capacity is too high!");
    }

    public int getCurrentSize() {
        return numOfEntries;
    }

    public boolean isEmpty() {
        return (numOfEntries == 0);
    }

    public boolean addNewEntry(T newEntry) {
        checkInitialisation();
        if (isArrayFull()) return false;
        else {
            deck[numOfEntries++] = newEntry;
            return true;
        }
    }

    public T remove() {
        checkInitialisation();
        return removeElementAt(numOfEntries - 1);
    }

    public boolean remove(T anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numOfEntries)
            if (deck[index].equals(anEntry)) found = true;
            else index++;
        if (found) removeElementAt(index);
        return found;
    }

    public void clear() {
        while (!isEmpty())
            remove();
    }

    public int getFrequencyOf(T anEntry) {
        int count = 0;
        for (int i = 0; i < numOfEntries - 1; i++)
            if (deck[i].equals(anEntry))
                count++;
        return count;
    }

    public boolean contains(T anEntry) {
        boolean found = false;
        int index = 0;
        while (!found && index < numOfEntries) {
            if (deck[index++].equals(anEntry))
                found = true;
        }
        return found;
    }

    // Suppressing unchecked warning for toArray cast
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] resultArray = (T[]) new Object[numOfEntries];
        System.arraycopy(deck, 0, resultArray, 0, numOfEntries);
        return resultArray;
    }
}