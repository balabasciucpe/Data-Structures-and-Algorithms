package arrays;

import java.util.Arrays;

public class ScoreBoard {

    private static final int CAPACITY = 10;
    private int numOfEntriesInArray = 0; //at the moment;
    private GameEntry[] gameEntries;

    public ScoreBoard(int capacity)
    {
        this.gameEntries = new GameEntry[capacity];
    }

    public ScoreBoard()
    {
        this(CAPACITY);
    }

    public void addGame(GameEntry game)
    {
        int newScore = game.getScore();

        if(numOfEntriesInArray < gameEntries.length || newScore > gameEntries[numOfEntriesInArray-1].getScore()) {
            //if is empty or not at capacity, just increase capacity -->
            if (numOfEntriesInArray < gameEntries.length)
                numOfEntriesInArray++;

            int j = numOfEntriesInArray - 1;
            //if we full, check for scores
            while (j > 0 && gameEntries[j - 1].getScore() < newScore)
            {
                gameEntries[j] = gameEntries[j-1]; //move score to right;
                j--; // next score check
            }
            //--> and place obj at position j
            gameEntries[j] = game;
        }
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException
    {
        if(index < 0 || index > this.numOfEntriesInArray)
            throw new IndexOutOfBoundsException();
    }

    public GameEntry remove(int index) throws IndexOutOfBoundsException
    {
        checkIndex(index);

        GameEntry gameEntry = gameEntries[index];

        //arrays are from 0 to n-1 that s why
        for(int i = index; i < numOfEntriesInArray -1; i++)
        {
            gameEntries[i] = gameEntries[i+1]; //move from right to left
            gameEntries[numOfEntriesInArray-1] = null; // last position is null
            numOfEntriesInArray--; //decrement
        }

        return gameEntry;
    }

    public static void insertionSort(char[] data)
    {
        int dataLength = data.length;
        //we start at index 1 assuming that first element is sorted
        for(int i = 1; i < dataLength; i++)
        {
            char currentChar = data[i];
            int j = i; //for inner loop
            //here we test all the elements after i with the i itself, that's why is j, taking many values while iterates
            //non decresing order test
            while(j > 0 && currentChar < data[j-1])
            {
                data[j] = data[j-1];
                j--;
            }
            data[j] = currentChar;
        }
    }

    public static void insertionSortInt(int[] data)
    {
        int dataLength = data.length;
        for(int i = 1; i < dataLength; i++)
        {
            int currentNumber = data[i];
            int j = i;

            while(j > 0 && currentNumber < data[j-1])
            {
                data[j] = data[j-1];
                j--;
            }
            data[j] = currentNumber;
        }
    }

    @Override
    public String toString() {
        return "ScoreBoard{" +
                "numOfEntriesInArray=" + numOfEntriesInArray +
                ", gameEntries=" + Arrays.toString(gameEntries) +
                '}';
    }
}
