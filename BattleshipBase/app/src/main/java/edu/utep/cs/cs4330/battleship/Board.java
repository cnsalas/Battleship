package edu.utep.cs.cs4330.battleship;

import android.util.Log;

/**
 * A game board consisting of <code>size</code> * <code>size</code> places
 * where battleships can be placed. A place of the board is denoted
 * by a pair of 0-based indices (x, y), where x is a column index
 * and y is a row index. A place of the board can be shot at, resulting
 * in either a hit or miss.
 */
public class Board {

    /**
     * Size of this board. This board has
     * <code>size*size </code> places.
     */
    private final int size;

    /**
     * List of ships to initialize
     */
    private final int[] shipSizes = new int[]{5, 4, 3, 3, 2};

    /**
     * List of ships present on the board
     */
    private Ship[] ships;

    /**
     * Places present on the board arranged in a 2D array
     */
    private Place[][] places;

    /**
     * Number of shots plaed on the board
     */
    private int numberOfShots = 0;

    /** Create a new board of the given size. */
    public Board(int size)
    {
        this.size = size;
        places = new Place[size][size];
        initPlaces();
        initShips();
    }

    /** Return the size of this board. */
    public int size() {
        return size;
    }

    /**
     * Initializes places in the board
     */
    private void initPlaces()
    {
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                places[i][j] = new Place(i, j);
            }
        }
    }

    /**
     * Initializes ship locations on the board
     */
    private void initShips()
    {
        ships = new Ship[shipSizes.length];
        for(int i = 0; i < shipSizes.length; i++)
        {
            ships[i] = new Ship(shipSizes[i]);
            placeShip(ships[i]);
        }
    }

    /**
     * Places ship on the board. Randomly picks an index on the board, randomally calculates
     * an orientation and checks place validity before associating ships to the places
     * @param ship Ship to be placed on the board
     */
    public void placeShip(Ship ship)
    {
        boolean shipPlaced = false;
        boolean spotValid = true;
        int orientation;
        int x = -1;
        int y = -1;
        while(!shipPlaced)
        {
            orientation = (int) (Math.random() * 2);
            x = (int) (Math.random() * (size - ship.size()));
            y = (int) (Math.random() * (size - ship.size()));
            spotValid = true;
            for(int i = 0; i < ship.size() && spotValid; i++)
            {
                if(orientation == 0)
                {
                    if(places[x + i][y].hasShip())
                    {
                        spotValid = false;
                    }
                }
                else if(orientation == 1)
                {
                    if(places[x][y + i].hasShip())
                    {
                        spotValid = false;
                    }
                }
            }
            if(spotValid)
            {
                for(int i = 0; i < ship.size(); i++)
                {
                    if (orientation == 0)
                    {
                        places[x + i][y].setShip(ship);
                    }
                    else if (orientation == 1)
                    {
                        places[x][y + i].setShip(ship);
                    }
                }
                shipPlaced = true;
            }
        }
    }

    private void logPlaces()
    {
        for(int i = 0; i < size; i++)
        {
            Log.d("MyActivity", row(i));
        }
    }
    private String row(int i)
    {
        String print = "";
        for(int j = 0; j < size; j++)
        {
            if(places[i][j].isHit())
            {
                print += " [x] ";
            }
            else
                print += " [o] ";
        }
        return print;
    }

    /**
     * Places a shot at a place. Iterates number of shots
     * @param x X index of place to hit
     * @param y Y index of place to hit
     */
    public void hit(int x, int y)
    {
        if(places[x][y].hit())
        {
            numberOfShots++;
        }
        //Log.d("MainActivity", "Spot Hit: " + x + " " + y);
        //logPlaces();
    }

    /**
     * Returns place at given X/Y Indexs
     * @param x X index of place
     * @param y Y index of place
     * @return Place at given indexs
     */
    public Place at(int x, int y)
    {
        return places[x][y];
    }

    /**
     * Return all places on the board
     * @return 2D array of places on the board
     */
    public Place[][] places()
    {
        return this.places;
    }

    /**
     * Checks if all ships on the board have been sunk
     * @return True if all ships are sunk, False otherwise
     */
    public boolean isGameOver()
    {
        for(int i = 0; i < ships.length; i++)
        {
            if(!ships[i].isSunk())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns number of shots made on the board
     * @return Number of shots
     */
    public int getNumberOfShots()
    {
        return this.numberOfShots;
    }



    // Suggestions:
    // 1. Consider using the Observer design pattern so that a client,
    //    say a BoardView, can observe changes on a board, e.g.,
    //    hitting a place, sinking a ship, and game over.
    //
    // 2. Introduce methods including the following:
    //    public boolean placeShip(Ship ship, int x, int y, boolean dir)
    //    public void hit(Place place)
    //    public Place at(int x, int y)
    //    public Place[] places()
    //    public int numOfShots()
    //    public boolean isGameOver()
    //    ...
}
