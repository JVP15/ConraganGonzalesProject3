package cs146S19.conragangonzales.project3;

import java.awt.Color;

/**
 * A single Vertex that belongs to a graph
 * Contains useful fields for Graph Searching Algorithms
 */
public class Vertex
{
	private int distance;		// Distance from the source vertex, -1 by default
	private int discoveryTime; 	// When the vertex was first discovered by a Graph Search algorithm
	private Color color;		// Color of the vertex, Color.White by default
	private Vertex parent;  	// Parent of the vertex, null by default 

	
	/** 
	 * Constructs a Vertex
	 */
	public Vertex()
	{
		distance = -1;
		discoveryTime = -1;
		color = Color.WHITE;
		parent = null; 
	}

	/**
	 * Gets the distance of the vertex from its source
	 * Returns -1 until the distance from the source has been set
	 * @return
	 */
	public int getDistance()
	{
		return distance;
	}
	
	/**
	 * Sets the distance of the vertex from its source
	 * @param distance the distance of the vertex from its source
	 */
	public void setDistance(int distance)
	{
		this.distance = distance;
	}
	
	/**
	 * Gets the discovery time of the vertex
	 * Returns -1 until the discovery time has been set
	 * @return the discovery time of the vertex
	 */
	public int getDiscoveryTime()
	{
		return discoveryTime;
	}
	
	/**
	 * Sets the discovery time of the vertex
	 * @param discoveryTime
	 */
	public void setDiscoveryTime(int discoveryTime)
	{
		this.discoveryTime = discoveryTime;
	}

	/**
	 * Gets the color of the vertex, useful for the BFS and DFS algorithms 
	 * Color.white by default
	 * @return the color of the vertex
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Sets the color of the vertex 
	 * Useful for the BFS and DFS algorithms 
	 * @param color the color to set the vertex
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	/**
	 * Gets the parent of the vertex
	 * Returns null until the parent has been set
	 * @return
	 */
	public Vertex getParent()
	{
		return parent;
	}
	
	/**
	 * Sets the parent of the vertex
	 * @param parent the parent of the vertex
	 */
	public void setParent(Vertex parent)
	{
		this.parent = parent;
	}
}
