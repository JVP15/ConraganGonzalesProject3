package cs146S19.conragangonzales.project3;

import java.awt.Color;
import java.util.*;

/**
 * Contains various methods to solve a maze.
 */
public class Solver 
{
	private static int time = 0;
	
	/**
	 * Searches a maze breadth-first for the exit. Records the disovery time of each cell 
	 * and the solution path from entrance to exit.
	 * @param unsolved an unsolved maze
	 * @return		   a solved maze
	 */
	public static SolvedMaze solveBFS(Maze unsolved) 
	{
		SolvedMaze maze = new SolvedMaze(unsolved.clone());

		Cell first = maze.getCellAt(0, 0);
		first.setDistance(0);
		first.setDiscoveryTime(0);
		
		Cell last = maze.getCellAt(maze.getHeight() - 1, maze.getWidth() - 1);
		
		LinkedList<Cell> queue = new LinkedList<>();
		queue.add(first);
		
		// Search for the end of the maze, breadth-first
		while (!queue.isEmpty()) 
		{
			Cell current = queue.remove();
			ArrayList<Cell> adjacent = maze.getNeighbors(current);
			
			// If we find the end of the maze, we're done
			if(adjacent.contains(last))
			{
				last.setDiscoveryTime(++time);
				last.setParent(current);
				current.setColor(Color.BLACK);
				break;
			}
			
			// Otherwise, continue searching
			for (Cell c : adjacent) 
			{
				if (c.getColor() == Color.WHITE) 
				{
					c.setDiscoveryTime(++time);
					c.setParent(current);
					queue.add(c);
				}
			}
			current.setColor(Color.BLACK);
		}
		
		// Set solution path
		Cell pathFinder = last;
		ArrayList<Cell> path = new ArrayList<>();
		while (pathFinder != first) 
		{
			path.add(0, pathFinder);
			pathFinder = (Cell) pathFinder.getParent();
		}
		path.add(0, first);
		maze.setPath(path);
		
		time = 0;
		
		return maze;

	} 

	/**
	 * Searches a maze depth-first for the exit. Records the disovery time of each cell 
	 * and the solution path from entrance to exit.
	 * @param unsolved an unsolved maze
	 * @return		   a solved maze
	 */
	public static SolvedMaze solveDFS(Maze unsolved) 
	{
		SolvedMaze maze = new SolvedMaze(unsolved.clone());
		
		Cell first = maze.getCellAt(0, 0);
		first.setDistance(0);
		first.setDiscoveryTime(0);
		
		Cell last = maze.getCellAt(maze.getHeight() - 1, maze.getWidth() - 1);
		
		
		
		DFS_Visit(maze, first, null);		
		
		Cell current = last;
		
		ArrayList<Cell> path = new ArrayList<>();
		while(current != first)
		{
			path.add(0, current);
			current = (Cell) current.getParent();
		}
		
		path.add(0, first);
		maze.setPath(path);
		
		time = 0;
		
		return maze;
	}
	
	private static boolean DFS_Visit(SolvedMaze maze, Cell c, Cell parent)
	{
		time++;
		
		if(c != maze.getCellAt(maze.getHeight() - 1, maze.getWidth() - 1))
		{
			c.setColor(Color.BLACK);
			c.setDiscoveryTime(time);
			
			if(parent != null)
			{
				c.setParent(parent);
				c.setDistance(parent.getDistance() + 1);
			}
				
			ArrayList<Cell> adjacent = maze.getNeighbors(c);
			
			for(int i = 0; i < adjacent.size(); i++)
			{
				Cell next = adjacent.get(i);
				if(next.getColor() == Color.WHITE)
				{
					boolean mazeSolved = Solver.DFS_Visit(maze, next, c);
					
					if(mazeSolved)
						return true;
				}
					
			}
		}
		
		else
		{
			c.setParent(parent);
			c.setDiscoveryTime(time);
			c.setDistance(parent.getDistance() + 1);
			
			return true;
		}		
		
		
		return false;
	}
}
