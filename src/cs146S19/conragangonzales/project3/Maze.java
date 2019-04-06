package cs146S19.conragangonzales.project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Models and prints a maze as a specified nxn grid of cells. Solutions are
 * randomly generated pathways with exactly one entrance and exit.
 */
public class Maze {
	private Cell[][] cells;
	private int width;
	private int height;

	/**
	 * Maze ctor creates a maze with specified width and height.
	 * 
	 * @param w width of the maze
	 * @param h height of the maze
	 */
	public Maze(int w, int h) {
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
	public void printMaze() {
		System.out.print("+  +");
		for (int i = 1; i < width; i++) {
			System.out.print("--+");
		}
		System.out.println();

		for (int i = 0; i < height; i++) {
			for (Cell c : cells[i]) {
				// if cells are connected, no wall is printed inbetween them
				if (c.hasDoorwayTo(c.getWest()))
					System.out.print(" ");
				else
					System.out.print("|");

				System.out.print("  ");
			}

			System.out.println("|");

			for (Cell c : cells[i]) {
				System.out.print("+");

				if (c.hasDoorwayTo(c.getSouth()))
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
	private void initializeCells() {
		// Construct the grid
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				cells[i][j] = new Cell();
		// Set the correct neighbors of each cell in the grid
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Cell current = cells[i][j];
				if (i - 1 >= 0)
					current.setNorth(cells[i - 1][j]);
				if (i + 1 < height)
					current.setSouth(cells[i + 1][j]);
				if (j - 1 >= 0)
					current.setWest(cells[i][j - 1]);
				if (j + 1 < width)
					current.setEast(cells[i][j + 1]);
			}
		}
	}

	/**
	 * The Maze generation algorithm constructs the maze using DFS on the cell grid.
	 */
	private void createMaze() {
		Random rand = new Random();

		Stack<Cell> cellStack = new Stack<>();
		int totalCells = cells.length * cells[0].length;
		int visitedCells = 1;
		Cell current = cells[0][0];

		while (visitedCells < totalCells) {
			ArrayList<Cell> adjacentCells = new ArrayList<>();
			// Find all nieghbors of the current cell with intact walls
			if (current.getNorth() != null && current.getNorth().areWallsIntact() == true)
				adjacentCells.add(current.getNorth());

			if (current.getSouth() != null && current.getSouth().areWallsIntact() == true)
				adjacentCells.add(current.getSouth());

			if (current.getEast() != null && current.getEast().areWallsIntact() == true)
				adjacentCells.add(current.getEast());

			if (current.getWest() != null && current.getWest().areWallsIntact() == true)
				adjacentCells.add(current.getWest());

			// If an intact cell nieghbor is found
			if (adjacentCells.size() > 0) {
				// Choose a neighboring cell at random, knock down its wall, and repeat for the
				// modified cell
				Cell randomAdjacentCell = adjacentCells.get(rand.nextInt(adjacentCells.size()));
				cellStack.push(current);
				current.knockDownWall(randomAdjacentCell);
				randomAdjacentCell.knockDownWall(current);
				current = randomAdjacentCell;
				visitedCells++;
			} else {
				current = cellStack.pop();
			}
		}
		// Create the maze exit
		Cell end = new Cell();
		cells[width - 1][height - 1].setSouth(end);
		cells[width - 1][height - 1].knockDownWall(end);
	}
}
