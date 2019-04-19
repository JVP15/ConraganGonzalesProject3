package cs146S19.conragangonzales.project3;

import java.awt.Color;
import java.util.*;

//TODO: Test BFS
//TODO: Finish DFS and DFSVisit
//TODO: Add printing functionality
public class Solver 
{
	/*
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

	} */

	public static SolvedMaze solveDFS(Maze unsolved) 
	{
		SolvedMaze maze = new SolvedMaze(unsolved.clone());
		
		Cell first = maze.getCellAt(0, 0);
		first.setDistance(0);
		first.setDiscoveryTime(0);
		
		Cell last = maze.getCellAt(maze.getHeight(), maze.getWidth());
		
		int currentDiscovery = 0;
		Cell current = first;
		
		Stack<Cell> cellStack = new Stack<>();
		cellStack.add(current);
		
		while(cellStack.peek() != last)
		{
			current = cellStack.pop();
			
			ArrayList<Cell> adjacent = maze.getNeighbors(current);
			
			for(int i = 0; i < adjacent.size(); i++)
			{
				Cell c = adjacent.get(i);
				
				if(c.getColor() == Color.WHITE)
				{
					currentDiscovery++;
					
					c.setColor(Color.GRAY);
					c.setDistance(current.getDistance() + 1);
					c.setDiscoveryTime(currentDiscovery);
					c.setParent(current);
					cellStack.push(c);
				}
				
				current.setColor(Color.BLACK);
			}
		}
		
		return null;
	}
}
