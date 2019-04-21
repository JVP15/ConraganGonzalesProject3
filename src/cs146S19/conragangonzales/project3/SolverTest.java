package cs146S19.conragangonzales.project3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Solver and SolvedMaze classes.
 */
public class SolverTest 
{ 
	Maze unsolvedDFS;
	Maze unsolvedBFS;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		unsolvedDFS = new Maze(4,4);
		unsolvedBFS = new Maze(4,4);
	}

	@Test
	void testSolve()
	{
		SolvedMaze DFSsolution = Solver.solveDFS(unsolvedDFS);
		SolvedMaze BFSsolution = Solver.solveBFS(unsolvedBFS);
		
		try 
		{
			File file = new File("test.txt");
			
			PrintStream toFile = new PrintStream(file);
			PrintStream console = System.out;
			System.setOut(toFile);
			
			unsolvedDFS.printMaze();
			
			System.out.println("\nDFS");
			DFSsolution.printVisitedCells();
			DFSsolution.printPath();
			DFSsolution.printDetails();
			
			System.out.println("BFS");
			BFSsolution.printVisitedCells();
			BFSsolution.printPath();
			BFSsolution.printDetails();
			
			System.setOut(console);
		} 
		 catch (FileNotFoundException err) 
		{
			System.out.println("Destination file error: " + err.getMessage());
		} 
	}
	
	
	@Ignore	// Enable if you want to see output to console
	void testSolveDFS() 
	{
		final int n = 4;
		SolvedMaze testSolution = Solver.solveDFS(new Maze(n,n));
		testSolution.printVisitedCells();
		testSolution.printPath();
		testSolution.printDetails();
	}
	
	@Ignore	// Enable if you want to see output to console
	void testSolveBFS() 
	{
		final int n = 4;
		SolvedMaze testSolution = Solver.solveBFS(new Maze(n,n));
		testSolution.printVisitedCells();
		testSolution.printPath();
		testSolution.printDetails();
	}
	
}
