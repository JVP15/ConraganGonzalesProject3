package cs146S19.conragangonzales.project3;

/**
 * 
 * Models a single cell in a maze
 * Keeps track of the direction of its open doorways
 *
 */

public class Cell extends Vertex
{
	// Indicates the cardinal direction relative to the Cell
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
		
	public static final int NUMBER_OF_DIRECTIONS = 4; // The number of directions that the Cell keeps track of
	public static final int OPPOSITE_DIRECTION_OFFSET = 2; // The difference in value between opposite directions, i.e., SOUTH = NORTH + 2

	private boolean[] doorways; // Each index holds a boolean indicating whether the cell has a doorway in the direction given by the index
								// i.e., doorways[0] indicates whether a doorway going north exists
	
	private boolean wallsIntact; // True if every cell wall is intact, otherwise false
	
	private int row;			// The row of the Cell in a Maze
	private int column;			// The column of the Cell in a maze

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
	 * i.e., there are no doorways to or from the Cell
	 * @return Whether the Cell has all of its walls intact
	 */
	public boolean areWallsIntact()
	{
		return wallsIntact;
	}
	
	/**
	 * Opens a doorway in a direction determined by the Cell constants
	 * @param direction an integer representation of the cardinal direction
	 */
	public void openDoorwayTo(int direction)
	{
			doorways[direction % NUMBER_OF_DIRECTIONS] = true;
			wallsIntact = false;
	}

	/**
	 * Determines if the Cell has a doorway in the given direction
	 * @param direction an integer representation of the cardinal direction
	 * @return whether the Cell has a doorway in the given direction
	 */
	public boolean hasDoorwayTo(int direction)
	{
		return doorways[direction % NUMBER_OF_DIRECTIONS];
	}
}
