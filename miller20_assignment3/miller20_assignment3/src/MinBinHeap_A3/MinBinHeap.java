package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
    
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

@Override
public void insert(EntryPair entry) {
	if (entry.priority < 0) {
		throw new IllegalArgumentException("invalid entry priority");
	}
	size++;
	int hole = size(); 
	array[hole] = entry; 
	for (; array[hole/2].priority > array[hole].priority; hole /= 2) {
		EntryPair temp = array[hole/2];
		array[hole/2] = array[hole];
		array[hole] = temp;
	}
	
	
}

@Override
public void delMin() {
	if (size == 0) {
		throw new RuntimeException("can't remove the minimum from an empty binary heap");
	}
	if (size == 1) {
		size--;
		array[1] = null;
		return;
	}
	size--;
	array[1] = array[size+1]; // put last node into top.
	array[size+1] = null; // delete last node
	int child;
	// percolate down (array[1])
	EntryPair temp = array[1];
	int hole = 1;
	for (; hole *2 <= size(); hole = child) {
		
		child = hole *2;
		if (child != size() && array[child + 1].priority < array[child].priority) { // move to right node if right node is smaller than left node.
			child++;
		}
		if (array[child].priority < temp.priority) {
			array[hole] = array[child];
		} 
		else {
			break;
		}
	}
	array[hole] = temp; 
}

@Override
public EntryPair getMin() {
	if (size() > 0) {
		return array[1]; // first element in array is unused. 2nd element should be the smallest. 
	}
	return null;
}

@Override
public int size() {
	return size; // manipulate size in the other parts. initialize in build, decrease in del, increase in insert
}

@Override
public void build(EntryPair[] entries) { // should be done - just need to do the percolate. 
	size = entries.length; // initialize size.
	int i = 1;
	for (EntryPair item: entries) { // put items from entries into array. in theory, could also insert each individually to avoid the second half of this method.
		array[i] = item;
		i++;
	}
	for (i = size / 2; i > 0; i--) { // this line might be incorrect.
		percolateDown(i); // ordering the heap.
	}	
}

// the percolate methods are the key to this entire thing.
public void percolateDown(int hole) { 
	EntryPair temp = array[hole];
	int child; 
	
	for ( ; hole * 2 <= size; hole = child) { // taken straight from the book.
		child = hole * 2; // will be initialized on the first loop.
		if ( child != size && array[child+1].priority < array[child].priority) {
			child++;
		}
		if (array[child].priority < temp.priority) {
			array[hole] = array[child];
		}
		else {
			break;
		}
	}
	array[hole] = temp;
}



}