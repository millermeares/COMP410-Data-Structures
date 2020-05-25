package BST_A2;
// TO DO: height, contains, remove
// this theoretically could be done except height.
public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	// added unimplemented methods
	public BST() {
		size = 0;
		root = null;
	}

	// delegate everything to the root node??
	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {
		return root;
	}

//this is an interesting strategy for the size++ part of this. idk if it will work.
	@Override
	public boolean insert(String s) {
		if (empty()) {
			root = new BST_Node(s);
			size++;
			return true;
		}
		boolean x = root.insertNode(s);
		if (x) {
			size++;
			return true;
		}
		return false;
	}

// this is an interesting strategy for the size-- part of this. idk if it will work.
	@Override
	public boolean remove(String s) {
		if (s == null) {
			return false;
		}
		if (empty()) {
			return false;
		}
		if (root.data.equals(s)) { // special case = removing root
			if (size() == 1) {
				root = null;
				size--;
				return true;
			}
			if (root.left != null && root.right == null) {
				root = root.left;
				size--;
				return true;
			}
			if (root.left == null && root.right != null) {
				size--;
				root = root.right;
				return true;
			}
			//2 children 
			if (root.left != null && root.right != null) {
				String temp = root.right.findMin().data;//findMin in R subtree, replace node root being deleted with min val
				root.data = temp;
				// recursive delete the min node in the right subtree of the edited node
				root.right.removeNode(temp);
				
				return true;
			}
		}
		
		
	
		boolean x = root.removeNode(s);
		if (x) {
			size--;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String findMin() {
		if (size() == 0) {
			return null;
		}
		if (size() == 1) {
			return root.data;
		}
		return root.findMin().data;
	}

	@Override
	public String findMax() {
		if (empty()) {
			return null;
		}
		if (size() == 1) {
			return root.data;
		}

		return root.findMax().data;
	}

	@Override
	public boolean empty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(String s) { //
		if (s == null) {
			return false;
		}
		if (empty()) {
			return false;
		}
		return root.containsNode(s);
	}

	@Override
	public int size() {
		// return the amount of elements - keep counter when u use insert. a shit ton of
		// stuff relies on this. whenever u return true in insert, add one to size.
		return size;
	}

	// fuck this one in particular.
	@Override
	public int height() {
		// hmm. length of longest path. could get more complex than i'd like. need to
		// identify path to longest node.
		
		if (root != null) {
			return root.getHeight();
		} else {
			return 0;
		}
	}

}