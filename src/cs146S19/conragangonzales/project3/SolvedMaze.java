package cs146S19.conragangonzales.project3;

import java.util.ArrayList;

public class SolvedMaze extends Maze
{
	private ArrayList<Cell> path;
	
	public SolvedMaze(Maze m)
	{
		super(m);
		
		path = new ArrayList<>();
	}

	
}
