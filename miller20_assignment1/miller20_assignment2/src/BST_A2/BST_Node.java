package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	int height;
	BST_Node(String data) {
		this.data = data;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	// --- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false
	// or some appropriate "fake" value
	//
	// you make them work properly
	// add the meat of correct implementation logic to them

	// you MAY change the signatures if you wish...
	// make the take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations
// should be good.
	public boolean containsNode(String s) {
		if (s == this.data) {
			return true; // found it
		} else if (s.compareTo(this.data) < 0) {
			if (this.left == null) {
				return false;
			}
			return this.left.containsNode(s);
		} else {
			if (this.right == null) {
				return false;
			}
			return this.right.containsNode(s);
		}
	}

	// should have already handled the empty insert. should be done.
	public boolean insertNode(String s) {
		if (this.data.contentEquals(s)) {
			// data already exists in tree.
			return false;
		} else if (this.data.compareTo(s) < 0) {
			if (this.right == null) {
				this.right = new BST_Node(s);
				return true;
			} else {
				return this.right.insertNode(s);
			}

		} else {
			if (this.left == null) {
				this.left = new BST_Node(s);
				return true;
			} else {
				return this.left.insertNode(s);
			}
		}

	}

	public boolean removeNode(String s) {
		// handle the node that you need to remove if it's one of the children.
		
		if (this.left != null) {
			if (this.left.data.equals(s)) {
				// this is a way to access parent
				if (this.left.left == null && this.left.right == null) {
					this.left = null; // unhooks the 0 children case.
					return true;
				}
				if (this.left.left == null && this.left.right != null) {
					BST_Node temp = this.left.right;
					this.left = temp; // unhook?
					// one child that's on the right
					return true;
				}
				if (this.left.left != null && this.left.right == null) {
					// one child that's on the left
					BST_Node temp = this.left.left;
					this.left = temp;
					return true;
				}
				if (this.left.left != null && this.right.right != null) {
					// two children.
					String temp = this.left.right.findMin().data;
					this.left.data = temp;
					// recursive delete the min node in the right subtree of the edited node
					this.left.right.removeNode(temp);
					return true;
				}
			}
		}
		if(this.right != null) {
			if (this.right.data.equals(s)) {
				if (this.right.left == null && this.right.right == null) {
					this.right = null; // unhooks the 0 children case.
					return true;
				}
				if (this.right.left == null && this.right.right != null) {
					BST_Node temp = this.right.right;
					this.right = temp; // unhook?
					// one child that's on the right
					return true;
				}
				if (this.right.left != null && this.right.right == null) {
					// one child that's on the left
					BST_Node temp = this.right.left;
					this.right = temp;
					return true;
				}
				if (this.right.left != null && this.right.right != null) {
					// two children.
					String temp = this.right.right.findMin().data;
					this.right.data = temp;
					this.right.right.removeNode(temp);
					return true;
				}
			}
		}
		// navigate closer to the node
		if (s.compareTo(this.data) < 0) {
			if (this.left != null) {
				return this.left.removeNode(s);
			} else {
				return false;
			}
		} else {
			if (this.right != null) {
				return this.right.removeNode(s);
			} else {
				return false;
			}
		}

		// 3 cases - no children, one child, two children
	}

// should be good.
	public BST_Node findMin() {
		if (this.left == null) {
			return this;
		} else {
			return this.left.findMin();
		}

	}

// should be good.
	public BST_Node findMax() {
		if (this.right == null) {
			return this;
		} else {
			return this.right.findMax();
		}
	}

	public int getHeight() {
		int leftHeight = 0; int rightHeight = 0;
		if (this.left != null) {
			leftHeight = this.left.getHeight();
		}
		if (this.right != null) {
			rightHeight = this.right.getHeight();
		}
		return Math.max(leftHeight, rightHeight);
	}


	@Override
	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}
}
