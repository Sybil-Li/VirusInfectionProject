/**
 * File: checkInfection.java
 * 
 * An implementation of a breadth-first approach to solve the virus infection problem
 *
 * @author Sybil Li
 * @date 10/09/14
 *
 * @see Node
 * @see LinkedList
 * @see Scanner
 */

import java.util.*;
import java.io.*;

public class checkInfection {

	public static void main (String args[]) throws IOException
	{
		
		Scanner scanner = new Scanner(System.in);

		/* read from input */
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		/* this array of LinkedList stores nodes from the triples according to the com_index */
		LinkedList<Node>[] comList = (LinkedList<Node>[])new LinkedList[n];
		/* initializing comList */
		for (int i = 0; i < n; i++)
			comList[i] = new LinkedList<Node>();

		/* array that maintains the infection time of each computer */
		int[] infectiontime = new int[n];
		for (int i = 0; i < n; i++)
			infectiontime[i] = -1;


		/* variables to store triples */	
		int cp; 		// index of the first computer
		int cq;			// index of the second computer
		int tk; 		// time when communication occurred

		Node v1, v2; 	// for use when adding edges

		/* read triples */
		for (int i = 0; i < m ; i++)
		{
			cp = scanner.nextInt();
			cq = scanner.nextInt();
			tk = scanner.nextInt();

			/*check if (cp, tk) already exists*/
			if (comList[cp - 1].size() != 0 )
			{
				if (comList[cp - 1].getLast().getTime() == tk)
					v1 = comList[cp - 1].getLast(); 			// if exists, set v1 to be the node
				else
				{
					v1 = new Node(cp, tk); 						// if not, create a new node
					comList[cp - 1].getLast().addAdjNode(v1); 	// add a directed edge from the last node
					comList[cp - 1].addLast(v1); 				// add the node to the end
				}
			}
			else
			{
				v1 = new Node(cp, tk);
				comList[cp - 1].addLast(v1); 					// add the node to the end
			}


			/*check if (cq, tk) already exists*/
			if (comList[cq - 1].size() != 0 )
			{
				if (comList[cq - 1].getLast().getTime() == tk)
					v2 = comList[cq - 1].getLast(); 			// if exists, set v2 to be the node
				else
				{
					v2 = new Node(cq, tk); 						// if not, create a new node
					comList[cq - 1].getLast().addAdjNode(v2); 	// add a directed edge from the last node
					comList[cq - 1].addLast(v2); 				// add the node to the end
				}
			}
			else
			{
				v2 = new Node(cq, tk);
				comList[cq - 1].addLast(v2); 					// add the node to the end
			}

			/* add edge from v1 to v2 and from v2 to v1*/
			v1.addAdjNode(v2);
			v2.addAdjNode(v1);								
		}

		/* Read starting node */
		int sc = scanner.nextInt(); 		//from input
		int tinfection = scanner.nextInt(); //from input
		Node s = new Node(0,0), v, w; 		//for use in BFS

		/* preparing to do BFS */
		/* Structure to store nodes to be explored */
		LinkedList<Node> queue = new LinkedList<Node>();

		/*Find the starting node */
		int found = 0;

		if (comList[sc - 1].size() == 0)
		{
			System.out.println("This computer did not communicate with any " +
				"other computer in the network. No computer will be infected.");
			System.exit(0);
		}

		/* find the node with the smallest t > tinfection */
		ListIterator<Node> c = comList[sc - 1].listIterator(0);
		while (c.hasNext())
		{
			s = c.next();
			if (s.getTime() >= tinfection)
			{
				found = 1;
				break;
			}
		}

		if (found == 0)
		{
			System.out.println("This computer did not communicate with any " +
				"other computer after it is infected. No computer will be infected.");
			System.exit(0);
		}

		/* update infection time of starting computer */
		infectiontime[sc - 1] = tinfection;


		/* queue based BFS */
		/* add starting node to queue */
		queue.addLast(s);
		/* while queue != empty */
		while (queue.size() != 0)
		{	
			/* get the first element from the queue */
			v = queue.removeFirst();
			ListIterator<Node> adjNodes = v.adjNodesSet().listIterator();
			/* for every edge going out from node v */
			while (adjNodes.hasNext())
			{
				w = adjNodes.next();
				if (!w.discovered())
				{
					w.setDiscovered();
					/* insert w into Li+1*/
					queue.addLast(w);
					/* update infection time */
					if ((infectiontime[w.getComNum() - 1] == -1) 
						|| (infectiontime[w.getComNum() - 1] > w.getTime()))
						infectiontime[w.getComNum() - 1] = w.getTime();
				}
			}	
		} // end of BFS

		/* Read query */
		int query_c = scanner.nextInt(); // from input
		int query_t = scanner.nextInt(); // from input

		/* check infection time by accessing the array infectionTime */
		if ((infectiontime[query_c - 1] != -1) 
			&& (infectiontime[query_c - 1] <= query_t))
			System.out.println("Yes, computer " + query_c 
				+ " would have been infected by time " + query_t + ".");
		else
			System.out.println("No, computer " + query_c 
				+ " would not have been infected by time " + query_t + ".");
	}
}