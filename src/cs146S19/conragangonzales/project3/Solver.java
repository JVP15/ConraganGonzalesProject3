package cs146S19.conragangonzales.project3;

import java.awt.Color;
import java.util.*;

//TODO: Test BFS
//TODO: Finish DFS and DFSVisit
//TODO: Add printing functionality
public class Solver 
{

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

	public static void solveDFS(Maze unsolved) 
	{
		LinkedList<Cell>[] adjList = unsolved.getAdjList();

		int time = 0; // ??? Time handling ???
		for (int i = 0; i < adjList.length; i++) {
			Cell vertex = adjList[i].getFirst();
			if (vertex.getColor() == Color.WHITE)
				DFS_Visit(unsolved, vertex);
		}
	}

	public static void DFS_Visit(Maze unsolved, Cell u) 
	{
		int time = ???; // ??? Time handling ???
		u.setColor(Color.GRAY);
		time = time + 1;
		u.setDistance(time);

		ArrayList<Cell> edges = unsolved.getNeighbors(u);
		for (Cell v : edges) 
		{
			if(v.getColor() == Color.WHITE)
				DFS_Visit(unsolved, v);
		}
		u.setColor(Color.BLACK);
		time = time + 1;
		u.setDiscoveryTime(time);
	}

}
