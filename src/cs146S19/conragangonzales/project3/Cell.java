package cs146S19.conragangonzales.project3;

import java.util.ArrayList;

/**
 * Models a 1x1 cell for use in a grid. Each cell object keeps track of the
 * location and state of neighboring cells.
 */

public class Cell extends Vertex
{
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	public static final int NUMBER_OF_DIRECTIONS = 4;
	public static final int OPPOSITE_DIRECTION_OFFSET = 2;

	// List of any neighboring cells without intact walls
	private boolean[] doorways;
	// True if every cell wall is intact, otherwise false
	private boolean wallsIntact;
	
	private int row;
	private int column;

	/**
	 * Constructor
	 */
	public Cell(int row, int column)
	{
		super();
		
		this.row = row;
		this.column = column;
		doorways = new boolean[] {false, false, false, false};
		wallsIntact = true;	
	}
	
	public int getRow()
	{
		return row;
	}

	public int getColumn()
	{
		return column;
	}

	/**
	 * Determines if a Cell has all of its wall intact
	 * In other words, there are no doorways to or from the Cell
	 * @return Whether the Cell has all of its walls intact
	 */
	public boolean areWallsIntact()
	{
		return wallsIntact;
	}
	
	public void openDoorwayTo(int direction)
	{
			
			doorways[direction % NUMBER_OF_DIRECTIONS] = true;
			wallsIntact = false;
	}


	public boolean hasDoorwayTo(int direction)
	{
		return doorways[direction % NUMBER_OF_DIRECTIONS];
	}
}
