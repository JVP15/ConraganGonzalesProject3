package cs146S19.conragangonzales.project3;

import java.awt.Color;
import java.util.*;

//TODO: Test BFS
//TODO: Finish DFS and DFSVisit
//TODO: Add printing functionality
public class Solver 
{
	private static int time = 0;
	
	public static void solveBFS(Maze unsolved) 
	{
		Cell[][] cells = unsolved.getCells();
		LinkedList<Cell>[] adjList = unsolved.getAdjList();
		Queue<Cell> queue = new LinkedList<>();

		// Initialize vertices
		// TODO: could probably move this logic into Vertex ctor
		for (int i = 0; i < adjList.length; i++) 
		{
			Cell vertex = adjList[i].getFirst();
			vertex.setDiscoveryTime(0);
			vertex.setDistance(0);
		}

		Cell start = cells[0][0];
		queue.add(start);
		while (!queue.isEmpty()) 
		{
			Cell u = queue.remove();
			// Grab all vertices in u's adj list (ie neighbors)
			ArrayList<Cell> edges = unsolved.getNeighbors(u);
			for (Cell v : edges) 
			{
				if (v.getColor() == Color.WHITE) 
				{
					v.setColor(Color.GRAY);
					v.setDistance(u.getDistance() + 1);
					v.setParent(u);
					queue.add(v);
				}
			}
			u.setColor(Color.BLACK);
		}

	} 

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
