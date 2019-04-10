package cs146S19.conragangonzales.project3;


/**
 * A vertex is a cell with some extra information that is modeled by a graph.
 */
public class Vertex {
	private Cell data;
	private String color;
	private int distance;
	private Vertex parent;
	
	public Vertex(Cell cell) {
		data = cell;
		color = null;
		parent = null;
		distance = 0;
		
	}
	
	public void setData(Cell cell) {
		this.data = cell;
	}
}
