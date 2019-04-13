package cs146S19.conragangonzales.project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Models and prints a maze as a specified nxn grid of cells. Solutions are
 * randomly generated pathways with exactly one entrance and exit.
 */
public class Maze
{
	private Cell[][] cells;
	private int width;
	private int height;

	/**
	 * Maze ctor creates a maze with specified width and height.
	 * 
	 * @param w width of the maze
	 * @param h height of the maze
	 */
	public Maze(int w, int h)
	{
		width = w;
		height = h;
		cells = new Cell[width][height];
		// Create grid of cells
		initializeCells();
		// Construct the maze
		createMaze();
	}

	/**
	 * Prints an ASCII formatted representation of a maze. '+' represents cell
	 * intersections and corners '|' and '-' represents cell walls
	 */
	public void printMaze()
	{
		System.out.print("+  +");
		for (int i = 1; i < width; i++)
		{
			System.out.print("--+");
		}
		System.out.println();

		for (int i = 0; i < height; i++)
		{
			
			for (Cell c : cells[i])
			{
				// if cells are connected, no wall is printed in between them
				if (c.hasDoorwayTo(Cell.WEST))
					System.out.print(" ");
				else
					System.out.print("|");			
				
				System.out.print("  ");
			}
			
			System.out.println("|");
			
			for (Cell c : cells[i])
			{
				System.out.print("+");

				if (c.hasDoorwayTo(Cell.SOUTH))
					System.out.print("  ");
				else
					System.out.print("--");
			}
			System.out.println("+");
		}
	}

	/**
	 * Constructs the grid of cells specified in the Maze constructor.
	 */
	private void initializeCells()
	{
		// Construct the grid
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				cells[i][j] = new Cell(i, j);
	}

	/**
	 * The Maze generation algorithm constructs the maze using DFS on the cell grid.
	 */
	private void createMaze() 
	{
		Random rand = new Random(0);

		Stack<Cell> cellStack = new Stack<>();
		int totalCells = cells.length * cells[0].length;
		int visitedCells = 1;
		Cell current = cells[0][0];

		while (visitedCells < totalCells) 
		{
			ArrayList<Integer> possibleDirections = this.getPotentialDoorways(current.getRow(), current.getColumn());
			
			if (possibleDirections.size() > 0)
			{
				int randIndex = rand.nextInt(possibleDirections.size());
				
				int randDirection = possibleDirections.get(randIndex);
				Cell randCell = null;
				
				switch(randDirection)
				{
					case Cell.NORTH: 
						randCell = cells[current.getRow()-1][current.getColumn()]; break;
					case Cell.EAST: 
						randCell = cells[current.getRow()][current.getColumn()+1]; break; 
					case Cell.SOUTH: 
						randCell = cells[current.getRow()+1][current.getColumn()]; break; 
					case Cell.WEST: 
						randCell = cells[current.getRow()][current.getColumn()-1]; break;
				}
				
				cellStack.push(current);
				current.openDoorwayTo(randDirection);
				randCell.openDoorwayTo(randDirection + Cell.OPPOSITE_DIRECTION_OFFSET); 
				current = randCell;
				visitedCells++;
			} 
			else 
			{
				current = cellStack.pop();
			}
		}
		
		// Create the maze exit
		cells[width - 1][height - 1].openDoorwayTo(Cell.SOUTH);
	}

	public ArrayList<Integer> getPotentialDoorways(int row, int col)
	{
		ArrayList<Integer> doorways = new ArrayList<>();
		
		if (row - 1 > 0 && cells[row - 1][col].areWallsIntact() == true)
			doorways.add(Cell.NORTH);

		if (col + 1 < width && cells[row][col + 1].areWallsIntact() == true)
			doorways.add(Cell.EAST);

		if (col - 1 > 0 && cells[row][col - 1].areWallsIntact() == true)
			doorways.add(Cell.WEST);

		if (row + 1 < height && cells[row + 1][col].areWallsIntact() == true)
			doorways.add(Cell.SOUTH);

		return doorways;
	}

	// Size = total # of cells in a maze
	public int getSize()
	{
		return width * height;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public Cell[][] getCells()
	{
		return cells;
	}
}