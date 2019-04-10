package cs146S19.conragangonzales.project3;

import java.util.ArrayList;

/**
 * Models a 1x1 cell for use in a grid. Each cell object keeps track of the
 * location and state of nieghboring cells.
 */

public class Cell {
	// Neighboring cell positions
	private Cell north;
	private Cell east;
	private Cell south;
	private Cell west;
	// List of any neighboring cells without intact walls
	private ArrayList<Cell> doorways;
	// True if every cell wall is intact, otherwise false
	private boolean wallsIntact;

	/**
	 * Cell ctor initializes cell and properties.
	 */
	public Cell() {
		north = null;
		south = null;
		east = null;
		west = null;
		wallsIntact = true;
		doorways = new ArrayList<>();
	}

	public Cell getNorth() {
		return north;
	}

	public Cell getSouth() {
		return south;
	}

	public Cell getEast() {
		return east;
	}

	public Cell getWest() {
		return west;
	}

	public void setNorth(Cell other) {
		north = other;
	}

	public void setSouth(Cell other) {
		south = other;
	}

	public void setEast(Cell other) {
		east = other;
	}

	public void setWest(Cell other) {
		west = other;
	}

	public boolean areWallsIntact() {
		return wallsIntact;
	}

	/**
	 * Deletes the wall of a specified neighboring cell and adds it to doorways.
	 * 
	 * @param cell neighboring cell to connect to current cell
	 */
	public void knockDownWall(Cell cell) {
		doorways.add(cell);
		wallsIntact = false;
	}

	/**
	 * Checks if a cell is connected to a neighboring cell.
	 * 
	 * @param cell neighboring cell
	 * @return true if both cell objects share a pathway false if neither cell
	 *         shares a path
	 */
	public boolean hasDoorwayTo(Cell cell) {
		boolean doorway = false;

		for (Cell c : doorways) {
			if (c == cell)
				doorway = true;
		}
		return doorway;
	}
	
	public ArrayList<Cell> getDoorways() {
		return doorways;
	}
}
