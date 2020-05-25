package MinBinHeap_A3;

public class MinBinHeap_Playground {
  public static void main(String[] args){   
    //Add more tests as methods and call them here!!
//	  insert(<"a", 4>)
//	  insert(<"b", 1>)
//	  insert(<"c", 2>)
//	  delMin()
//	  insert(<"d", 0>)
//	  insert(<"e", 3>)
//	  insert(<"f", 7>)
//	  delMin()
//	  insert(<"g", 0>)
//	  Test()
//
//	  Sequence
//
//	  0 2 7 4 3 
	  
	  MinBinHeap x = new MinBinHeap();
	  x.insert(new EntryPair("a", 4));
	  x.insert(new EntryPair("b", 1));
	  x.insert(new EntryPair("c", 2));
	  x.delMin();
	  x.insert(new EntryPair("d", 0));
	  x.insert(new EntryPair("e", 3));
	  x.insert(new EntryPair("f", 7));
	  x.delMin();
	  x.insert(new EntryPair("g", 0));
	  printHeap(x.getHeap(), x.size());
    
   

  }
  
  public static void TestBuild(){ 
    // constructs a new minbinheap, constructs an array of EntryPair, 
    // passes it into build function. Then print collection and heap.
    MinBinHeap mbh= new MinBinHeap();
    EntryPair[] collection= new EntryPair[8];
    collection[0]=new EntryPair("i",3);
    collection[1]=new EntryPair("b",5);
    collection[2]=new EntryPair("c",1);
    collection[3]=new EntryPair("d",0);
    collection[4]=new EntryPair("e",46);
    collection[5]=new EntryPair("f",5);
    collection[6]=new EntryPair("g",6);
    collection[7]=new EntryPair("h",17);
    mbh.build(collection);
    printHeapCollection(collection);
    printHeap(mbh.getHeap(), mbh.size());
  }
  
  public static void printHeapCollection(EntryPair[] e) { 
    //this will print the entirety of an array of entry pairs you will pass 
    //to your build function.
    System.out.println("Printing Collection to pass in to build function:");
    for(int i=0;i < e.length;i++){
      System.out.print("("+e[i].value+","+e[i].priority+")\t");
    }
    System.out.print("\n");
  }
  
  public static void printHeap(EntryPair[] e,int len) { 
    //pass in mbh.getHeap(),mbh.size()... this method skips over unused 0th index....
    System.out.println("Printing Heap");
    for(int i=1;i < len+1;i++){
      System.out.print("("+e[i].value+","+e[i].priority+")\t");
    }
    System.out.print("\n");
  }
}