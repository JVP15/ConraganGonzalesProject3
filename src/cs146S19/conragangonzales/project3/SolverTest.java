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
	static void testSolve()
	{	
		pathSolutions = new ArrayList<>();
		lengthSolutions = new ArrayList<>();
		visitedSolutionsDFS = new ArrayList<>();
		visitedSolutionsBFS = new ArrayList<>();
		
		File file = new File("test.txt");
		PrintStream console = System.out;
		try 
		{
			PrintStream toFile = new PrintStream(file);
			System.setOut(toFile);
		} 
		catch (FileNotFoundException err) 
		{
			System.out.println("Destination file error: " + err.getMessage());
		}
		
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
		
		System.setOut(console);
		
	}
	
	
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
