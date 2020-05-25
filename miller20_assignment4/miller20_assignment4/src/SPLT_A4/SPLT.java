package SPLT_A4;

public class SPLT implements SPLT_Interface {
	public BST_Node root;
	int size;
	  
	public SPLT(){
		size = 0;
		root = null;
	}
	  
	@Override
	//used for testing, please leave as is
	public BST_Node getRoot(){
//		if (root == null) {
//			throw new NullPointerException("root is null.");
//		}
		return root;
	}

	@Override 
	public boolean insert(String s) { // potential problem - not checking for duplicates. - this never returns false. is that a problem? i don't know.
		 
		if (empty()) { // special case - empty tree - works!
			root = new BST_Node(s);
			root.par = null;
			size += 1;
			return true;
		}
		if (!root.contNoSpl(s)) {
			size += 1;
		}
		root = root.insertNode(s); // sometimes, null is being returned by this.
		return true;
	}

	@Override
	public boolean remove(String s) {  //the part in the interface should be done.
		// call contains, unhook the root (the thing that was contained), leaving with 2 substrees (what if contains is false? - 
		// think the nearest gets splayed and that's the end of it)
		// findMax on L (which makes max of L the root). Then set old right subtree of the OG to be the right subtree of the newly splayed L subtree.
		
		// special case for removing the root.
		
		if (!contains(s)) { // calling contains here should cause a splay. Is this the right place to call this? Yes.
			return false;
		} else {
			if (size == 1) { // special case - removing only node in tree.
				root = null;
				size -= 1;
				return true;
			} else {
				// root.data.equals(s) true always here because of the splay and contains check. For some reason, "c" is disconnected from b.
				//System.out.println(root.right.data);
				BST_Node L = root.left;
				BST_Node R = root.right;
				root.left = null;
				root.right = null;
				
				// disconnect root and save trees.
				if (L == null) { // special case L null why 
					R.par = null; 
					root = R;
					size -= 1;

					return true;
				}
				if (R == null) { // special case R null
					L = L.findMax();
					L.par = null;
					root = L;
					size -= 1;
					return true;
				}
				L.par = null;
				R.par = null;
				L = L.findMax(); // this splay is not working.
				L.right = R;
				R.par = L;
				L.par = null;
				root = L;
				size -= 1;

				return true;
			}
		}
	}

	@Override
	public String findMin() { // min node should end up as root after splay. 
		 
		if (empty()) {
			return null;
		} else {
			root = root.findMin();
			return root.data;
		}
	}

	@Override
	public String findMax() { // this is an access method, so the max node should end up as the root.
		 
		if (empty()) {
			return null;
		} else {
			root = root.findMax();
			return root.data;
		}
	}

	@Override
	public boolean empty() { // nothing
		 
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) { // as of now, this isn't testing anything (which is it's job - just splaying. 
		if (empty()) {
			return false;
		}
		
		root = root.containsNode(s);
		if (root.data == s) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public int size() { // nothing
		 
		return size;
	}

	@Override
	public int height() { // nothing
		 
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}
}
