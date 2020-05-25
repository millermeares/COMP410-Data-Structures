package DiGraph_A5;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	public HashMap<Long, Node> listNodes;
	public HashMap<Long, Edge> listEdges;
	public HashMap<String, Long> nodesID;
	public PriorityQueue<ShortestPathInfo> pq;
	public String[] values;
	public int[] dist;
	public HashSet<String> settled;
  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  listNodes = new HashMap<Long, Node>();
	  listEdges = new HashMap<Long, Edge>();
	  nodesID = new HashMap<String, Long>();
	  
      
  }

@Override
public boolean addNode(long idNum, String label) {
	// make sure the id number passed in is 0 or greater
	// make label + name unique as well
	// true if addition successful, false if already in graph
	if (idNum < 0) {
		return false;
	}
	if (listNodes.containsKey(idNum)) {
		return false;
	}
	if (nodesID.containsKey(label)) {
		return false;
	}
	
	Node n = new Node(idNum, label);
	listNodes.put(idNum, n);
	nodesID.put(label, idNum);
	return true;
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	// serial number an edge so that each has a unique integer id ( > 0). will specify an edge by naming source and destination nodes. 
	// label is optional, use null as default. might have overlapping edges (M to N AND N to M). Could also be M to M.
	// true if edge added, false if edge not added.

	if (idNum < 0) {
		return false;
	}
	
	if (!nodesID.containsKey(sLabel)) { // if start doesn't exist
		return false;
	}
	if (!nodesID.containsKey(dLabel)) { // if destination doesn't exist
		return false;
	}
	long skey = nodesID.get(sLabel);
	long dkey = nodesID.get(dLabel);
	Node s = listNodes.get(skey);
	Node d = listNodes.get(dkey);
	
	Edge e = new Edge(idNum, sLabel, dLabel, weight, eLabel);
	if(s.getOutgoing().containsKey(e.getIdNum())) { // if edge already exists - need to check for bad 'id'
		return false;
	}
	if (s.getOut().containsKey(dLabel) && d.getInc().containsKey(sLabel)) {
		return false;
	}
//	if (d.getIncoming().containsKey(e.getIdNum())) { // if edge already exists - i think this is redundant.
//		return false;
//	}

	s.addOutgoingEdge(e);
	d.addIncomingEdge(e);
	listEdges.put(idNum, e);
	return true;
}

public boolean addEdge(long idNum, String sLabel, String dLabel) {
	return addEdge(idNum, sLabel, dLabel, 0, null);
}


@Override
public boolean delNode(String label) {
	if (!nodesID.containsKey(label)) {
		return false;
	}
	long id = nodesID.get(label);
	Node n = listNodes.get(id);
	
	 for (Edge entry : n.getOutgoing().values())  { // iterate over the outgoing nodes and delete all edges. 
		 delEdge(n.getLabel(), entry.getdLabel());
	 }
	 
	 for (Edge entry : n.getIncoming().values()) { // iterate over all incoming nodes and delete all edges.
		 delEdge(entry.getsLabel(), n.getLabel());
	 }
	nodesID.remove(label);
	listNodes.remove(id);
	// id node to remove by label (since node names are unique). Find node and remove it from structure. 
	// removing a node will also require removing the edges into and out of that node.
	// return true if successfully removed, false if node was not in graph.
	return true;
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	// id edge to remove by passing in labels for the source and destination nodes (since only one edge per location + direction). 
	// Don't need to remove nodes.
	// return true if edge is found and removed, return false otherwise
	if (!nodesID.containsKey(sLabel) || !nodesID.containsKey(dLabel)) { // if the nodes don't exist in the graph
		return false;
	}
	long skey = nodesID.get(sLabel);
	long dkey = nodesID.get(dLabel);
	Node s = listNodes.get(skey);
	Node d = listNodes.get(dkey);
	
	if (s.getOut_id().containsKey(dLabel) && d.getInc_id().containsKey(sLabel)) {
		long ekey = s.getOut_id().get(dLabel);
		// edge exists - remove connections from all 2 hashmaps in both nodes and also the listEdges hashmap in digraph
		s.getOutgoing().remove(ekey);
		s.getOut_id().remove(dLabel);
		s.getOut().remove(dLabel);
		d.getInc().remove(sLabel);
		d.getInc_id().remove(sLabel);
		d.getIncoming().remove(ekey);
		listEdges.remove(ekey);
		return true;
	}
	return false;
}

@Override
public long numNodes() {
	// either a live counter or a .size method
	return listNodes.size();
}

@Override
public long numEdges() {
	// either a live counter or a .size method
	return listEdges.size();
}

@Override
public ShortestPathInfo[] shortestPath(String label) { 
	int size = (int) listNodes.size();
	PriorityQueue<Node> pq = new PriorityQueue<Node>(size);
	Node src = listNodes.get(nodesID.get(label));
	src.setDist(0);
	pq.add(src);
	while(pq.size() != 0) {
		Node n = pq.poll();
		int dist = n.getDist();
		if (n.getVisited() == false) {
			n.setVisited(true);
			// access outgoing to 
			Edge[] edges = n.getOutgoing().values().toArray(new Edge[n.getOutgoing().size()]); 
			for (int i = 0; i < edges.length; i++) {
				String d = edges[i].getdLabel();
				Node a = listNodes.get(nodesID.get(d));
				if (a.getVisited() == false) {
					
					if (a.getDist() > dist + edges[i].getWeight()) {
						a.setDist((int) (dist + edges[i].getWeight())); // typecast to int.
					}
					pq.add(a);
					// i don't think i care about "tracing the path itself using "from" fields"
				
				}
			}
		// for some reason, the last thing isn't being added.
		}
	}
	Long[] nodes_keys = listNodes.keySet().toArray(new Long[size]);
	ShortestPathInfo[] answer = new ShortestPathInfo[size];
	for (int i = 0; i < size; i++) {
		Node x = listNodes.get(nodes_keys[i]);
		if (x.getVisited() == false) {
			answer[i] = new ShortestPathInfo(x.getLabel(), -1);
		} else {
			answer[i] = new ShortestPathInfo(x.getLabel(), x.getDist());
		}
		
	}
	// need to create answer - array of ShortestPathInfo
	return answer;
}

//Put start s node in table with dist of 0
//Put (0,s) on priority queue PQ - can be a PQ of Nodes, EntryPair, or ShortestPathInfo. Whatever I put in the PQ has to be comparable (pref Nodes).
// add "weight" field to Node class initialized to Max. also add boolean "visited" field in each node initialized to false
//Loop: until PQ is empty:
//n=PQ.getMin().node; d=PQ.getMin().dist;  PQ.delMin()
//Is n known? Back to Loop and get another from PQ; 
//Mark n as known  
//For each unknown node a adjacent to n 
//if a.dist>d+edge.weight then 
//      update a.dist in table to be d+edge.weight
//   and add a to PQ with priority d+edge.weight
//Trace the path itself using “from” fields

// table is array of shortestPathInfo.. huh. maybe create that with nodes that contain dist and str info after loop runs.
}