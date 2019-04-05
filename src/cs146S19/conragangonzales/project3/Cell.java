package cs146S19.conragangonzales.project3;

import java.util.ArrayList;


public class Cell
{	
	private Cell north;
	private Cell east;
	private Cell south;
	private Cell west;
	private ArrayList<Cell> doorways;
	private boolean wallsIntact;
	
	public Cell()
	{
		north = null;
		south = null;
		east = null;
		west = null;
		wallsIntact = true;
		doorways = new ArrayList<>();
	}
	
	public Cell getNorth()
	{
		return north;
	}
	
	public Cell getSouth()
	{
		return south;
	}
	
	public Cell getEast()
	{
		return east;
	}
	
	public Cell getWest()
	{
		return west;
	}
	
	
	public void setNorth(Cell other)
	{
		north = other;
	}
	
	public void setSouth(Cell other)
	{
		south = other;
	}
	
	public void setEast(Cell other)
	{
		east = other;
	}
	
	public void setWest(Cell other)
	{
		west = other;
	}
	
	public boolean areWallsIntact()
	{
		return wallsIntact;
	}
	
	public void knockDownWall(Cell cell)
	{
		doorways.add(cell);
		wallsIntact = false;
	}
	
	public boolean hasDoorwayTo(Cell cell)
	{
		boolean doorway = false;
		
		for(Cell c : doorways)
		{
			if(c == cell)
				doorway = true;
		}
		return doorway;
	}
}
