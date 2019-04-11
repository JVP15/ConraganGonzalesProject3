package cs146S19.conragangonzales.project3;

import java.util.ArrayList;

/**
 * Models a 1x1 cell for use in a grid. Each cell object keeps track of the
 * location and state of nieghboring cells.
 */

public class Cell
{
	public enum Direction { NORTH, SOUTH, EAST, WEST };

	// List of any neighboring cells without intact walls
	private Cell[] doorways;
	// True if every cell wall is intact, otherwise false
	private boolean wallsIntact;

	/**
	 * Cell ctor initializes cell and properties.
	 */
	public Cell()
	{
		doorways = new Cell[] {null, null, null, null};

		wallsIntact = true;		
	}

	public boolean areWallsIntact()
	{
		return wallsIntact;
	}


	public void openDoorwayToo(Cell cell, Direction d)
	{
		switch (d)
		{
			case NORTH: 
				doorways[0] = cell; wallsIntact = false; break;
			case EAST:
				doorways[1] = cell; wallsIntact = false; break;
			case WEST: 
				doorways[2] = cell; wallsIntact = false; break;
			case SOUTH: 
				doorways[3] = cell; wallsIntact = false; break;
		}
	}


	public boolean hasDoorwayTo(Direction d)
	{
		if(d == Direction.NORTH && doorways[0] != null)
			return true; 
		else if(d == Direction.EAST && doorways[1] != null)
			return true; 
		else if(d == Direction.WEST && doorways[2] != null)
			return true; 
		else if(d == Direction.SOUTH && doorways[3] != null)
			return true; 
		
		else
			return false;
	}
}
