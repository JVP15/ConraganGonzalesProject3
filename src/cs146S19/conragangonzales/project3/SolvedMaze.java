package cs146S19.conragangonzales.project3;

import java.io.*;
import java.util.ArrayList;

/**
 * Models and prints a solved maze with a solution path and the solution algorithm's
 * sequence of discovery.
 */
public class SolvedMaze extends Maze
{
	private ArrayList<Cell> path;	// Solution path for a maze
	
	/**
	 * Constructs a solved maze.
	 */
	public SolvedMaze(Maze m)
	{
		super(m);
		
		path = new ArrayList<>();
	}

	public void setPath(ArrayList<Cell> newPath)
	{
		path = newPath;
	}
	
	/**
	 * Prints the sequence of cell discovery for a solved maze's solution.
	 */
	public void printVisitedCells()
	{
		System.out.print("+ +");
		for (int i = 1; i < this.getWidth(); i++)
		{
			System.out.print("-+");
		}
		System.out.println();

		for (int i = 0; i < this.getHeight(); i++)
		{
			
			for (int j = 0; j < this.getWidth(); j++)
			{
				Cell c = this.getCellAt(i, j);
				// if cells are connected, no wall is printed in between them
				if (c.hasDoorwayTo(Cell.WEST))
					System.out.print(" ");
				else
					System.out.print("|");			
				
				if(c.getDiscoveryTime() != -1)
					System.out.print(c.getDiscoveryTime() % 10);
				else
					System.out.print(" ");
			}
			
			System.out.println("|");
			
			for (int j = 0; j < this.getWidth(); j++)
			{
				Cell c = this.getCellAt(i, j);
				System.out.print("+");

				if (c.hasDoorwayTo(Cell.SOUTH) || c == this.getCellAt(getHeight()-1, getWidth()-1))
					System.out.print(" ");
				else
					System.out.print("-");
			}
			System.out.println("+");
		}
	}
	
	/**
	 * Prints the solution path for a solved maze.
	 */
	public void printPath()
	{
		System.out.print("+ +");
		for (int i = 1; i < this.getWidth(); i++)
		{
			System.out.print("-+");
		}
		System.out.println();

		for (int i = 0; i < this.getHeight(); i++)
		{
			
			for (int j = 0; j < this.getWidth(); j++)
			{
				Cell c = this.getCellAt(i, j);
				// if cells are connected, no wall is printed in between them
				if (c.hasDoorwayTo(Cell.WEST))
					System.out.print(" ");
				else
					System.out.print("|");			
				
				if(path.contains(c))
					System.out.print("#");
				else
					System.out.print(" ");
			}
			
			System.out.println("|");
			
			for (int j = 0; j < this.getWidth(); j++)
			{
				Cell c = this.getCellAt(i, j);
				System.out.print("+");

				if (c.hasDoorwayTo(Cell.SOUTH) || c == this.getCellAt(getHeight()-1, getWidth()-1))
					System.out.print(" ");
				else
					System.out.print("-");
			}
			System.out.println("+");
		}
	}
	
	/**
	 * Prints some extra statistics of a solved maze's solution.
	 */
	public void printDetails() 
	{
		// Print cells in path
		System.out.print("Path:");
		for(Cell c: path)
			System.out.print(" (" + c.getRow() + ',' + c.getColumn() + ')');
		
		// Print path length
		System.out.println("\nLength of path: " + path.size());
		
		// Print visited cell amount
		int lastCell = path.size() - 1;
		int visitedCells = path.get(lastCell).getDiscoveryTime() + 1;
		System.out.println("Visited cells: " + visitedCells + '\n');
	}
	
	/**
	 * Gets the solution path of cells as a string.
	 */
	public String getPath() 
	{
		StringBuilder pathBuilder = new StringBuilder();
		for(Cell c: path)
			pathBuilder.append(" (" + c.getRow() + ',' + c.getColumn() + ')');
		
		return pathBuilder.toString();
	}
	
	/**
	 * Gets the length of solution path.
	 */
	public int getPathLength()
	{
		return path.size();
	}
	
	/**
	 * Gets the number of visited cells.
	 */
	public int getVisitedCells() 
	{
		int lastCell = path.size() - 1;
		int visitedCells = path.get(lastCell).getDiscoveryTime() + 1;
		return visitedCells;
	}
}
