package cs146S19.conragangonzales.project3;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests the Solver and SolvedMaze classes.
 */
public class SolverTest 
{ 
	static ArrayList<String>  pathSolutions;
	static ArrayList<Integer> lengthSolutions;
	static ArrayList<Integer> visitedSolutionsDFS;
	static ArrayList<Integer> visitedSolutionsBFS;
	
	static final int[] testSizes = {4, 8, 10, 20};

	@org.junit.jupiter.api.BeforeAll
	static void setupTests()
	{	
		/*
		 * Setup tests for random/extra mazes.
		 */
		PrintStream console = System.out;
		pathSolutions = new ArrayList<>();
		lengthSolutions = new ArrayList<>();
		visitedSolutionsDFS = new ArrayList<>();
		visitedSolutionsBFS = new ArrayList<>();
		
		File file = new File("testRandomMazes.txt");

		try 
		{
			PrintStream toFile = new PrintStream(file);
			System.setOut(toFile);
		} 
		catch (FileNotFoundException err) 
		{
			System.out.println("Destination file error: " + err.getMessage());
		}
		
		System.out.println("This file shows test solutions of randomly generated (seeded) mazes.\n");
		// Solve all extra mazes, store results for future tests
		for (int size: testSizes) 
		{
			Maze unsolvedDFS = new Maze(size, size);
			Maze unsolvedBFS = new Maze(size, size);
		
			SolvedMaze DFSsolution = Solver.solveDFS(unsolvedDFS);
			SolvedMaze BFSsolution = Solver.solveBFS(unsolvedBFS);
			
			pathSolutions.add(DFSsolution.getPath());
			lengthSolutions.add(DFSsolution.getPathLength());
			visitedSolutionsDFS.add(DFSsolution.getVisitedCells());
			visitedSolutionsBFS.add(BFSsolution.getVisitedCells());
	
				System.out.println(size + "x" + size);
				unsolvedDFS.printMaze();
			
				System.out.println("\nDFS");
				DFSsolution.printVisitedCells();
				DFSsolution.printPath();
				DFSsolution.printDetails();
			
				System.out.println("BFS");
				BFSsolution.printVisitedCells();
				BFSsolution.printPath();
				BFSsolution.printDetails();
		}
		
		/*
		 * Setup tests for provided mazes.
		 */
		ArrayList<String> providedTestMazes = new ArrayList<>();
		providedTestMazes.add("maze4.txt");
		providedTestMazes.add("maze6.txt");
		providedTestMazes.add("maze8.txt");
		providedTestMazes.add("maze10.txt");
		providedTestMazes.add("maze20.txt");
		
		file = new File("testProvidedMazes.txt");

		try 
		{
			PrintStream toFile = new PrintStream(file);
			System.setOut(toFile);
		} 
		catch (FileNotFoundException err) 
		{
			System.out.println("Destination file error: " + err.getMessage());
		}
		
		System.out.println("This file shows test solutions of mazes provided by from instructor.\n");
		// Solve all provided mazes, store results for future tests
		for(String maze : providedTestMazes)
		{
					
			SolvedMaze DFSsolution = null;
			SolvedMaze BFSsolution = null;
					
			try
			{
				Maze unsolvedDFS = new Maze(maze);
				Maze unsolvedBFS = new Maze(maze);
				DFSsolution = Solver.solveDFS(unsolvedDFS);
				BFSsolution = Solver.solveBFS(unsolvedBFS);
			}
			catch(FileNotFoundException err) 
			{
				System.out.println("Input test file error:  " + err.getMessage());
			}
			
			pathSolutions.add(DFSsolution.getPath());
			lengthSolutions.add(DFSsolution.getPathLength());
			visitedSolutionsDFS.add(DFSsolution.getVisitedCells());
			visitedSolutionsBFS.add(BFSsolution.getVisitedCells());
			
			System.out.println(DFSsolution.getHeight() + "x" + DFSsolution.getWidth());
			DFSsolution.printMaze();
		
			System.out.println("\nDFS");
			DFSsolution.printVisitedCells();
			DFSsolution.printPath();
			DFSsolution.printDetails();
		
			System.out.println("BFS");
			BFSsolution.printVisitedCells();
			BFSsolution.printPath();
			BFSsolution.printDetails();
		}
		
		System.setOut(console);
		
	}
	
	@Test
	void testSolve_ProvidedMazes()
	{
		// Initialize test variables
		ArrayList<String> providedTestMazes = new ArrayList<>();
		providedTestMazes.add("maze4.txt");
		providedTestMazes.add("maze6.txt");
		providedTestMazes.add("maze8.txt");
		providedTestMazes.add("maze10.txt");
		providedTestMazes.add("maze20.txt");
		
		// Test all provided mazes
		for(String maze : providedTestMazes)
		{
			
			SolvedMaze DFSsolution = null;
			SolvedMaze BFSsolution = null;
			
			try
			{
				Maze unsolvedDFS = new Maze(maze);
				Maze unsolvedBFS = new Maze(maze);
				DFSsolution = Solver.solveDFS(unsolvedDFS);
				BFSsolution = Solver.solveBFS(unsolvedBFS);
			}
			catch(FileNotFoundException err) 
			{
				System.out.println("Input test file error:  " + err.getMessage());
			}
			
			int index = providedTestMazes.indexOf(maze) + 4; // indexed location of provided maze's solutions
			// Test DFS expected solution vs actual 
			assertEquals(pathSolutions.get(index), DFSsolution.getPath());
			assertEquals((int) lengthSolutions.get(index), DFSsolution.getPathLength());
			assertEquals((int) visitedSolutionsDFS.get(index), DFSsolution.getVisitedCells());	
			// Test BFS expected solution vs actual
			assertEquals(pathSolutions.get(index), BFSsolution.getPath());
			assertEquals((int) lengthSolutions.get(index), BFSsolution.getPathLength());
			assertEquals((int) visitedSolutionsBFS.get(index), BFSsolution.getVisitedCells());	
		}
	}
	
	//
	// The following tests are for the random mazes generated during setup.
	//
	
	@Test
	void testSolveDFS_Size4() 
	{
		final int n = 4;
		SolvedMaze testDFS = Solver.solveDFS(new Maze(n,n));
		assertEquals(pathSolutions.get(0), testDFS.getPath());
		assertEquals((int) lengthSolutions.get(0), testDFS.getPathLength());
		assertEquals((int) visitedSolutionsDFS.get(0), testDFS.getVisitedCells());		
	}
	
	@Test
	void testSolveBFS_Size4() 
	{
		final int n = 4;
		SolvedMaze testBFS = Solver.solveBFS(new Maze(n,n));
		assertEquals(pathSolutions.get(0), testBFS.getPath());
		assertEquals((int) lengthSolutions.get(0), testBFS.getPathLength());
		assertEquals((int) visitedSolutionsBFS.get(0), testBFS.getVisitedCells());		
	}
	
	@Test
	void testSolveDFS_Size8() 
	{
		final int n = 8;
		SolvedMaze testDFS = Solver.solveDFS(new Maze(n,n));
		assertEquals(pathSolutions.get(1), testDFS.getPath());
		assertEquals((int) lengthSolutions.get(1), testDFS.getPathLength());
		assertEquals((int) visitedSolutionsDFS.get(1), testDFS.getVisitedCells());		
	}
	
	@Test
	void testSolveBFS_Size8() 
	{
		final int n = 8;
		SolvedMaze testBFS = Solver.solveBFS(new Maze(n,n));
		assertEquals(pathSolutions.get(1), testBFS.getPath());
		assertEquals((int) lengthSolutions.get(1), testBFS.getPathLength());
		assertEquals((int) visitedSolutionsBFS.get(1), testBFS.getVisitedCells());		
	}
	
	@Test
	void testSolveDFS_Size10() 
	{
		final int n = 10;
		SolvedMaze testDFS = Solver.solveDFS(new Maze(n,n));
		assertEquals(pathSolutions.get(2), testDFS.getPath());
		assertEquals((int) lengthSolutions.get(2), testDFS.getPathLength());
		assertEquals((int) visitedSolutionsDFS.get(2), testDFS.getVisitedCells());		
	}
	
	@Test
	void testSolveBFS_Size10() 
	{
		final int n = 10;
		SolvedMaze testBFS = Solver.solveBFS(new Maze(n,n));
		assertEquals(pathSolutions.get(2), testBFS.getPath());
		assertEquals((int) lengthSolutions.get(2), testBFS.getPathLength());
		assertEquals((int) visitedSolutionsBFS.get(2), testBFS.getVisitedCells());		
	}
	
	@Test
	void testSolveDFS_Size20() 
	{
		final int n = 20;
		SolvedMaze testDFS = Solver.solveDFS(new Maze(n,n));
		assertEquals(pathSolutions.get(3), testDFS.getPath());
		assertEquals((int) lengthSolutions.get(3), testDFS.getPathLength());
		assertEquals((int) visitedSolutionsDFS.get(3), testDFS.getVisitedCells());		
	}
	
	@Test
	void testSolveBFS_Size20() 
	{
		final int n = 20;
		SolvedMaze testBFS = Solver.solveBFS(new Maze(n,n));
		assertEquals(pathSolutions.get(3), testBFS.getPath());
		assertEquals((int) lengthSolutions.get(3), testBFS.getPathLength());
		assertEquals((int) visitedSolutionsBFS.get(3), testBFS.getVisitedCells());		
	}
}
