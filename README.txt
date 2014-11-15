/******************************************************************************
/* Compile and run the program
/* $ javac *.java
/* $ java checkInfection < data.txt(file name that contains the )	
/******************************************************************************

/******************************************************************************
/* Data Structure
/*
/* Node Class
/* Each node has three attributes
/* 		- computer it is associated with	
/*		- the time when the communication occurs
/*		- a LinkedList of adjacent nodes (nodes connected by a directed edge)
/* 
/* Storing Triples
/* An array of LinkedList is created to store the nodes derived from triples
/*		- Each LinkedList corresponeds to the set of nodes (Ci, t?)
/*		- Each LinkedList can be accessed by indexing, so accessing runs in 
/*		  constant time
/*		- Every time a new node (Ci, tj) is added to any LinkedList of Nodes, 
/*		  a directed edge is added from (Ci, ti) to (Ci, tj), where (Ci, ti) 
/*		  is the second last node
/*		- Every time a triple (Ci, Cj, t) is read, the program checks that if 
/*		  the two derivative nodes (Cj, t) and (Ci, t) already exist by 
/*		  checking the time of last node of the corresponding LinkedList 
/*		  stored in the array. This is done in constant time.
/*		- For each triple read, two directed edges will be added,
/*		  one from (Ci, t) to (Cj, t), the other from (Cj, t) to (Ci, t)
/*
/* Queue-Based BFS
/*		- The starting node is first inserted into a LinkedList of nodes
/*		  used as a queue, i.e. insertion is done by invoking addLast(),
/*		  and removal is done by invoking removeFirst().
/*		- For every node connected to the starting node, it is inserted into
/*		  the queue and mark as visited. This is done in constant time.
/*		- The next ndoe being checked is provided by invoking removeLast().
/*		  This also runs in constant time.
/*
/* Keeping track of infection time
/* 		- an array of infection time for each computer is maintained
/*		- updated when a node is visited in BFS
/*		- accessing infection time with constant time
/******************************************************************************

/******************************************************************************
/* Algorithm Overview
/* 
/* After triples are stored in the array of LinkedList of nodes, the starting /* nodes is found by iterating through the LinkedList of all nodes that share
/* the computer number.
/* Then BFS is performed starting from the given node.
/* Every time a node is visited, it is set as discovered, and the infection 
/* time of the corresponeding computer is updated if necessary. 
/* After the BFS, the array keeping track of infection time will be fully 
/* updated since all nodes that are connected to the starting node will be 
/* visited.
/* To check the infection status of a computer, we just need to check the 
/* corresponding infection time stored in the array, which can be done in 
/* constant time.
/******************************************************************************