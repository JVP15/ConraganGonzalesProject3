package cs146S19.conragangonzales.project3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze
{
	private Cell[][] cells;
	private int width;
	private int height;
	
	public Maze(int w, int h)
	{
		width = w;
		height = h;
		cells = new Cell[width][height];
		
		initializeCells();
		
		createMaze();		
	}
	
	public void printMaze()
	{
		System.out.print("+  +");
		for(int i = 1; i < width; i++)
		{
			System.out.print("--+");
		}
		System.out.println();
		
		for(int i = 0; i < height; i++)
		{
			for(Cell c : cells[i])
			{
				if(c.hasDoorwayTo(c.getWest()))
					System.out.print(" " );
				else
					System.out.print("|");
				
				System.out.print("  ");
			}
			
			System.out.println("|");
			
			for(Cell c : cells[i])
			{
				System.out.print("+");
				
				if(c.hasDoorwayTo(c.getSouth()))
					System.out.print("  " );
				else
					System.out.print("--");
			}
			System.out.println("+");
		}
	}
	
	private void initializeCells()
	{
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				cells[i][j] = new Cell();
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				Cell current = cells[i][j];
				if(i - 1 >= 0)
					current.setNorth(cells[i-1][j]);
				if(i + 1 < height)
					current.setSouth(cells[i+1][j]);
				if(j - 1 >= 0)
					current.setWest(cells[i][j-1]);
				if(j + 1 < width)
					current.setEast(cells[i][j+1]);
			}
		}
	}
	
	private void createMaze()
	{
		Random rand = new Random();
		
		Stack<Cell> cellStack = new Stack<>();
		int totalCells = cells.length * cells[0].length;
		int visitedCells = 1;
		Cell current = cells[0][0];
		
		while(visitedCells < totalCells)
		{
			ArrayList<Cell> adjacentCells = new ArrayList<>();
			
			if(current.getNorth() != null && current.getNorth().areWallsIntact() == true)
				adjacentCells.add( current.getNorth());
			
			if(current.getSouth() != null && current.getSouth().areWallsIntact() == true)
				adjacentCells.add( current.getSouth());
			
			if(current.getEast() != null && current.getEast().areWallsIntact() == true)
				adjacentCells.add( current.getEast());
			
			if(current.getWest() != null && current.getWest().areWallsIntact() == true)
				adjacentCells.add( current.getWest());
			
			
			if(adjacentCells.size() > 0)
			{
				Cell randomAdjacentCell = adjacentCells.get( rand.nextInt( adjacentCells.size() ) );
				cellStack.push(current);
				current.knockDownWall(randomAdjacentCell);
				randomAdjacentCell.knockDownWall(current);
				current = randomAdjacentCell;			
				visitedCells++;
			}
			else
			{
				current = cellStack.pop();
			}
		}
		
		Cell end = new Cell();
		cells[width-1][height-1].setSouth(end);
		cells[width-1][height-1].knockDownWall(end);
	}
}
