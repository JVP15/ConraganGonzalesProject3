package cs146S19.conragangonzales.project3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Solver and SolvedMaze classes.
 */
public class SolverTest 
{
	Maze unsolved;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		unsolved = new Maze(4,4);
	}

	@Test
	void testSolveDFS() 
	{
		SolvedMaze testSolution = Solver.solveDFS(unsolved);
		testSolution.printVisitedCells();
		testSolution.printPath();
	}
	
	@Test
	void testSolveBFS() 
	{
		SolvedMaze testSolution = Solver.solveBFS(unsolved);
		testSolution.printVisitedCells();
		testSolution.printPath();
	}
	
}
