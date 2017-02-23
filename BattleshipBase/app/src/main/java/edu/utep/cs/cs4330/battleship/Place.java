package edu.utep.cs.cs4330.battleship;

/**
 * Created by codys on 2/11/2017.
 */

public class Place
{

    /**
     * X position of the place
     */
    private int x;

    /**
     * Y position of the place
     */
    private int y;

    /**
     * Ship on this place. Initialized null if no ship is present on this place
     */
    private Ship ship;

    /**
     * Indicates whether place has been hit
     */
    private boolean isHit;

    /**
     * Place constructor
     * @param x X index of place
     * @param y Y index of place
     */
    public Place(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.isHit = false;
        this.ship = null;
    }

    /**
     * Place constructor
     * @param x X index of place
     * @param y Y index of place
     * @param ship Ship to be placed in this place
     */
    public Place(int x, int y, Ship ship)
    {
        this.x = x;
        this.y = y;
        this.isHit = false;
        this.ship = ship;
    }

    /**
     * Determines if place is hit
     * @return True if place is hit, false otherwise
     */
    public boolean isHit()
    {
        return this.isHit;
    }

    /**
     * Returns x index of place
     * @return X index of place
     */
    public int xIndex()
    {
        return this.x;
    }

    /**
     * Returns y index of place
     * @return Y index of place
     */
    public int yIndex()
    {
        return this.y;
    }

    /**
     * Determine if place has a ship on it
     * @return True if there is a ship, false otherwise
     */
    public boolean hasShip()
    {
        if(this.ship == null)
        {
            return false;
        }
        return true;
    }

    /**
     * Hits Place, hits ship id place has a ship
     * @return True if place was hit, false if the place has already been hit
     */
    public boolean hit()
    {
        if(this.isHit)
        {
            return false;
        }
        else
        {
            this.isHit = true;
            if (this.ship != null)
            {
                this.ship.hit();
            }
        }
        return true;
    }

    /**
     * Adds ship to this place
     * @param ship Ship at this place
     */
    public void setShip(Ship ship)
    {
        this.ship = ship;
    }

}
