package DiGraph_A5;

import java.util.HashMap;
import java.util.Comparator;
public class Node implements Comparable<Node>{
	private long idNum;
	private String label;
	private HashMap<Long, Edge> outgoing;
	private HashMap<Long, Edge> incoming;
	private HashMap<String, Long> inc_id;
	private HashMap<String, Long> out_id;
	private HashMap<String, Edge> out;
	private HashMap<String, Edge> inc;
	private int dist; // or dist
	private boolean visited;
	
	public Node(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		this.outgoing = new HashMap<Long, Edge>();
		this.incoming = new HashMap<Long, Edge>();
		this.inc_id = new HashMap<String, Long>();
		this.out_id = new HashMap<String, Long>();
		this.out = new HashMap<String, Edge>();
		this.inc = new HashMap<String, Edge>();
		this.dist = Integer.MAX_VALUE;
		this.visited = false;
	}
	
	public int getDist() {
		return dist;
	}
	
	public void setDist(int dist) {
		this.dist = dist;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean getVisited() {
		return visited;
	}
	public long getIdNum() {
		return idNum;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void addIncomingEdge(Edge e) {
		inc_id.put(e.getsLabel(), e.getIdNum());
		incoming.put(e.getIdNum(), e);
		inc.put(e.getsLabel(), e);
	}
	
	public void addOutgoingEdge(Edge e) {
		outgoing.put(e.getIdNum(), e);
		out_id.put(e.getdLabel(), e.getIdNum());
		out.put(e.getdLabel(), e);
	}
	public HashMap<Long, Edge> getOutgoing() { // <key><edge that it is going from this node to another node>
		return outgoing;
	}
	
	public HashMap<Long, Edge> getIncoming() { // <key><edge that is going to this node from another node>
		return incoming;
	}
	public HashMap<String, Long> getInc_id() {
		return inc_id;
	}
	public HashMap<String, Long> getOut_id() {
		return out_id;
	}
	public HashMap<String, Edge> getOut() {
		return out;
	}
	public HashMap<String, Edge> getInc() {
		return inc;
	}

	@Override
	public int compareTo(Node o) {
		if (getDist() > o.getDist()) {
			return 1;
		} else if (o.getDist() > getDist()) {
			return -1;
		} else {
			return 0;
		}
		
	}

	
	
		
	
	
}
