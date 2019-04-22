package cs146S19.conragangonzales.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Models and prints a maze as a specified nxn grid of cells. Solutions are
 * randomly generated pathways with exactly one entrance and exit.
 */
public class Maze implements Cloneable
{
	private Cell[][] cells;		// 2D grid of cells forming the maze
	private int width;			// Width of the maze
	private int height;			// Height of the maze
	
	private static final int SEED = 0; // Seed for random maze generation and testing

	/**
	 * Constructs a maze with specified width and height.
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
	 * Constructs a maze given another maze.
	 * @param other target maze to clone
	 */
	public Maze(Maze other)
	{
		this.width = other.width;
		this.height = other.height;
			
		this.cells = other.cells;

	}
	
	/**
	 * Constructs a maze from a provided file.
	 * @param filename	name of file to import maze from
	 * @throws FileNotFoundException
	 */
	public Maze(String filename) throws FileNotFoundException
	{
		importMaze(filename);
	}

	/**
	 * Prints an ASCII formatted representation of a maze. '+' represents cell
	 * intersections and corners '|' and '-' represents cell walls
	 */
	public void printMaze()
	{
		System.out.print("+ +");
		for (int i = 1; i < width; i++)
		{
			System.out.print("-+");
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
				
				System.out.print(" ");
			}
			
			System.out.println("|");
			
			for (Cell c : cells[i])
			{
				System.out.print("+");

				if (c.hasDoorwayTo(Cell.SOUTH) || c == cells[width-1][height-1])
					System.out.print(" ");
				else
					System.out.print("-");
			}
			System.out.println("+");
		}
	}	
	
	/**
	 * Retreives a list of neighboring cells in the maze.
	 * @param cell	specified cell to of neighbors to find
	 * @return		list of neighbors
	 */
	public ArrayList<Cell> getNeighbors(Cell cell)
	{
		ArrayList<Cell> neighbors = new ArrayList<>();
		
		ArrayList<Integer> doorways = cell.getDoorways();
		for(int doorway : doorways) 
		{
			int row = cell.getRow();
			int col = cell.getColumn();
			// Add cells with doorways as neighbors, prioritizing South and East to optimize searching
			switch(doorway) 
			{
			case Cell.NORTH:
				neighbors.add(cells[row - 1][col]);
				break;
			case Cell.EAST:
				neighbors.add(0, cells[row][col + 1]);
				break;
			case Cell.SOUTH:
				neighbors.add(0, cells[row + 1][col]);
				break;
			case Cell.WEST:
				neighbors.add(cells[row][col - 1]);
				break;
			}
		}
		return neighbors;
	}
	
	public Cell[][] getCells()
	{
		return cells;
	}
	
	public ArrayList<Cell> getCellsAsList() 
	{
		ArrayList<Cell> cellList = new ArrayList<>();
		
		for (int i = 0; i < getWidth(); i++) 
			for (int j = 0; j < getHeight(); j++) 
				cellList.add(cells[i][j]);
				
		return cellList;	
	}
	
	public Cell getCellAt(int row, int col)
	{
		return cells[row][col];
	}
	
	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
	
	public int getSize()
	{
		return width * height;
	}

	@Override
	public Maze clone()
	{
		Maze other = new Maze(this);
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
				other.cells[i][j] = this.cells[i][j].clone();
		}
		
		return other;
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
		Random rand = new Random(SEED);

		Stack<Cell> cellStack = new Stack<>();
		int totalCells = width * height; 
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
				
				cellStack.add(current);
				current.openDoorwayTo(randDirection);
				randCell.openDoorwayTo(randDirection + Cell.OPPOSITE_DIRECTION_OFFSET); 
				current = randCell;
				current.setDiscoveryTime(visitedCells);
				visitedCells++;
			} 
			else 
			{
				current = cellStack.pop();
			}
		}
	}
	
	/**
	 * Determines all potential doorways of a specified cell in the maze.
	 * @param row	row of specified cell
	 * @param col	column of specified cell
	 * @return		list of cardinal directions with potential doorways
	 */
	private ArrayList<Integer> getPotentialDoorways(int row, int col)
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
	
	/**
	 * Reads and creates an ASCII maze from a specified text file.
	 * @param filename	text file to read maze from
	 * @throws FileNotFoundException
	 */
	private void importMaze(String filename) throws FileNotFoundException 
	{
		File file = new File(filename);
		Scanner in = new Scanner(file);
		
		// Gets the width and height of the Maze, and quits if both aren't specified on the first line
		if(in.hasNextInt())
		{
			height = in.nextInt();
			if(in.hasNextInt())
				width = in.nextInt();
			else
			{
				System.out.println("No width specified in " + filename );
				System.exit(0);
			}
		}
		else
		{
			System.out.println("No width or height specified in " + filename );
			System.exit(0);
		}
		
		cells = new Cell[height][width];
		
		initializeCells();
		
		in.nextLine(); // Leave the line containing sizes
		in.nextLine(); // Skip the first line of the Maze
		
		in.useDelimiter("");
		for(int i = 0; i < 2 * height - 1; i++)
		{
			in.next(); // Skip the first character in each row
			
			for(int j = 0; j < 2 * width - 1; j++)
			{
				String toTest = in.next();
				if( i % 2 == 0) // True if the scanner is on a row with | in it
				{
					if(j % 2 == 1) // True if scanner is on column that is either '|' or ' '
					{
						if(!toTest.equals("|"))
						{
							cells[i / 2][j / 2].openDoorwayTo(Cell.EAST);
							cells[i / 2][j / 2 + 1].openDoorwayTo(Cell.WEST); 
						}
					}
				}
		
				else // True if the scanner is on a row with +-+ in it
				{
					if(j % 2 == 0) // True if scanner is on a column that is either '-' or ' '
					{
						if(!toTest.equals("-"))
						{
							cells[i / 2][j / 2].openDoorwayTo(Cell.SOUTH);
							cells[i / 2 + 1][j / 2].openDoorwayTo(Cell.NORTH); 
						}
					}
				}
			}
			
			in.nextLine();
		}
		
		in.close();
	}
}
