package edu.utep.cs.cs4330.battleship;

/**
 * Created by codys on 2/11/2017.
 */

public class Ship
{

    /**
     * Size of ship
     */
    private int size;

    /**
     * Whether ship is sunk or not
     */
    private boolean isSunk;

    /**
     * Number of times the ship has been hit
     */
    private int timesHit;

    /**
     * Constructor for new ship
     * @param size Size of the ship being created
     */
    public Ship(int size)
    {
        this.size = size;
    }

    /**
     * Compares size to times hit to determine if ship is sunk
     * @return
     */
    public boolean isSunk()
    {
        return isSunk;
    }

    /**
     * Returns size of the ship
     * @return size of the ship
     */
    public int size()
    {
        return this.size;
    }

    /**
     * Returns number of times the ship has been shot
     * @return Times ship has been shot and updates if the ship is sunk or not
     */
    public int timesHit()
    {
        return this.timesHit;
    }

    /**
     * Places hit on this ship. Iterates number of times hit, and
     */
    public void hit()
    {
        timesHit++;
        if(timesHit == size)
        {
            this.isSunk = true;
        }
        this.isSunk = true;
    }

}
