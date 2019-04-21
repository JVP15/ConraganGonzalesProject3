package cs146S19.conragangonzales.project3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Maze class.
 */
class MazeTest 
{
	List<Integer> testSizes;

	@BeforeEach
	void setUp() throws Exception 
	{
		testSizes = Arrays.asList(4, 5, 6, 7, 8, 9, 10);
	}

	@Test
	void testMaze() 
	{
		for (int n : testSizes) 
		{
			System.out.println("Size: " + n + "x" + n);
			new Maze(n, n).printMaze();
			System.out.print("\n");
		}
	}
	
	@Ignore
	void testNeighbors() 
	{
		Maze testMaze = new Maze(4,4);
		testMaze.printMaze();
		System.out.println("\n");
		
		ArrayList<Cell> cells = testMaze.getCellsAsList(); 
		for(int i=0; i<cells.size(); i++) 
		{
			System.out.println("Cell " + i + " has neighbors:");
			ArrayList<Cell> neighbors = testMaze.getNeighbors(cells.get(i));
			for(Cell neighbor: neighbors)
				System.out.println("\t(" + neighbor.getRow() + ',' + neighbor.getColumn() + ')');
		}
	}
}
