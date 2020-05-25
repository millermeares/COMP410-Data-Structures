package DiGraph_A5;

public class DiGraphPlayground {
	 public static void main (String[] args) {
		  
	      // thorough testing is your responsibility
	      //
	      // you may wish to create methods like 
	      //    -- print
	      //    -- sort
	      //    -- random fill
	      //    -- etc.
	      // in order to convince yourself your code is producing
	      // the correct behavior
		 
	      DiGraph d = new DiGraph();
//	      d.addNode(1, "p");
//	      d.addNode(2, "a");
//	      d.addNode(3, "g");
//	      d.addNode(4, "e");
//	      d.addEdge(10, "p", "a", 10, "p to a");
//	      d.addEdge(6, "p", "g", 4, "p to f");
//	      d.addEdge(7, "p", "e", 8, "p to e");
//	      d.addEdge(8, "a", "p", 9, "a to p");
//	      d.addEdge(9, "a", "g", 12, "a to g");
//	      d.addEdge(0, "a", "e", 100, "a to e");
//	      d.addEdge(1, "g", "p", 2, "g to p");
//	      d.addEdge(2, "g", "a", 15, "g to a");
//	      d.addEdge(3, "g", "e", 1, "g to e");
//	      d.addEdge(4, "e", "p", 6, "e to p");
//	      d.addEdge(5, "e", "a", 3, "e to a");
//	      d.addNode(0, "a");
//	      d.addNode(1, "b");
//	      d.addNode(2, "c");
//	      d.addEdge(0, "a", "b", 3, "c to a");
//	      d.addEdge(1, "a", "c", 5, "c to d");
//	      d.addEdge(2, "b", "c", 4, "d to b");
	      
	      d.addNode(1,"f");
	      d.addNode(3,"s");
	      d.addNode(7,"t");
	      d.addEdge(0,"f","s");
	      d.addEdge(1, "f", "s");


//	      ShortestPathInfo[] x = d.shortestPath("p");
//	      for (int i = 0; i < x.length; i++) {
//	    	  System.out.println(x[i]);
//	      }
	      
	    }
	  
	    public static void exTest(){
	      DiGraph d = new DiGraph();
	      d.addNode(1, "f");
	      d.addNode(3, "s");
	      d.addNode(7, "t");
	      d.addNode(0, "fo");
	      d.addNode(4, "fi");
	      d.addNode(6, "si");
	      d.addEdge(0, "f", "s", 0, null);
	      d.addEdge(1, "f", "si", 0, null);
	      d.addEdge(2, "s", "t", 0, null);
	      d.addEdge(3, "fo", "fi", 0, null);
	      d.addEdge(4, "fi", "si", 0, null);
	      System.out.println("numEdges: "+d.numEdges());
	      System.out.println("numNodes: "+d.numNodes());
	    }
}
