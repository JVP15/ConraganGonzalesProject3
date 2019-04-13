package cs146S19.conragangonzales.project3;

import java.awt.Color;

/**
 * A single Vertex that belongs to a graph
 * Contains useful fields for Graph Searching Algorithms
 */
public class Vertex
{
	private int distance;
	private Color color;
	private Vertex parent;
	private boolean explored;
	
	/** 
	 * Constructs a Vertex
	 */
	public Vertex()
	{
		distance = -1;
		color = Color.WHITE;
		parent = null; 
		explored = false;
	}

	public int getDistance()
	{
		return distance;
	}

	public void setDistance(int distance)
	{
		this.distance = distance;
	}
	
	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public Vertex getParent()
	{
		return parent;
	}

	public void setParent(Vertex parent)
	{
		this.parent = parent;
	}

	public boolean isExplored()
	{
		return explored;
	}

	public void setExplored(boolean explored)
	{
		this.explored = explored;
	}
}
