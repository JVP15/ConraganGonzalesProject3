package cs146S19.conragangonzales.project3;

import java.util.*;

/**
 * Models a maze as a graph using an adjacency list.
 */
public class MazeAsGraph {
	private Maze maze;
	private LinkedList<Cell> adjListArray[];

	public MazeAsGraph(Maze maze) {
		this.maze = maze;
		int n = maze.getSize();

		// Create a new list for each vertex (cell) to store edges (doorways)
		adjListArray = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			adjListArray[i] = new LinkedList<>();
		}

		
		Cell[][] cells = maze.getCells();
		int index = 0;	// Index to insert edges into adjList
		// For all vertices (cells) in the maze
		for (int i = 0; i < maze.getWidth(); i++) {
			for (int j = 0; j < maze.getHeight(); j++) {
				Cell currentCell = cells[i][j];
				int currentRow = currentCell.getRow();
				int currentCol = currentCell.getColumn();
				
				// Store the edges (doorways) of each vertex in adjList
				ArrayList<Integer> edgeDirections = currentCell.getDoorways();
				for (Integer direction: edgeDirections)
					switch(direction)
					{
					case Cell.NORTH:
						adjListArray[index].add(new Cell(++currentRow, currentCol));
						break;
					case Cell.SOUTH:
						adjListArray[index].add(new Cell(--currentRow, currentCol));
						break;
					case Cell.EAST:
						adjListArray[index].add(new Cell(currentRow, ++currentCol));
						break;
					case Cell.WEST:
						adjListArray[index].add(new Cell(currentRow, --currentCol));
						break;
					}
				// Update index
				index++;
			}
		}
	}
	
	// Print function for testing 
	public void printAdjListSize() {
		for (int i=0; i<adjListArray.length; i++) {
			LinkedList<Cell> edgeList = adjListArray[i];
			System.out.println("Cell Vertex " + i + ": " + edgeList.size());
			for(int j=0; j<edgeList.size(); j++) {
				Cell edge = edgeList.get(j);
				System.out.println("\tEdge " + j + " location: (" + edge.getRow() + ',' + edge.getColumn() + ')'); }
		}
			
	}
	
	public LinkedList<Cell>[] getAdjList() {
		return adjListArray;
	}
	
	// Quick tester function
	public static void main(String[] args) {
		Maze testMaze = new Maze(4, 4);
		testMaze.printMaze();
		
		MazeAsGraph testGraph = new MazeAsGraph(testMaze);
		System.out.println("Adj list for 4x4 maze");
		System.out.println("-----------------");
		testGraph.printAdjListSize();
	}
}
