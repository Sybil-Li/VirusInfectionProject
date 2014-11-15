/**
 * File: Node.java
 * 
 * A node class for nodes generated from data triples to be used to construct a directed graph
 *
 * @author Sybil Li
 * @date 10/09/14
 *
 * @see LinkedList
 */


import java.util.*;

public class Node {

	private int com_index;
	private int t;
	private boolean discover = false;

	private LinkedList<Node> adj_nodes = new LinkedList();

	/* constructor */
	public Node(int i, int tk)
	{
		com_index = i;
		t = tk;
	}

	/** Returns the computer index */
	public int getComNum() { return com_index; }

	/** Returns the time value */
	public int getTime() { return t; }

	/** Returns discovery status */
	public boolean discovered() { return discover; }

	/** Set node as an discovered node */
	public void setDiscovered() { discover = true; }

	/** 
	 * Add a new node to the list of adjacent nodes 
	 * @param the node to be added
	 * @return the node being added 
	 */
	public void addAdjNode (Node v) { adj_nodes.addLast(v); }

	/** Returns an iterable collection of the nodes 
	 *  that is pointed to by an edge from the current node */
	public LinkedList<Node> adjNodesSet() { return adj_nodes; }

}