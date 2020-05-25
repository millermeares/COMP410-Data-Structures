package SPLT_A4;


public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
    
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }
	
	
	public String getData(){
		return data;
	}
	public BST_Node getLeft(){
		return left;
	}
	public BST_Node getRight(){
		return right;
	}
	
	/*
	 * In: String to test if contains
	 * Out: the node that is now the root (either the node that matches the string or the node that is closest if the string didn't exist.
	 * 
	 */
	public BST_Node containsNode(String s){ //life easier if i change return to type to BST_Node
		if(data.equals(s)) {
			BST_Node x = this;
			splay(x); // if true, should be legit
			return x;
		}
		if(data.compareTo(s)>0){//s lexiconically less than data
			if(left==null) {
				BST_Node x = this;
				splay(x); // if false, splays last node right before.
				return x;
			}
			return left.containsNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null) {
				BST_Node x = this;
				splay(x); // if false, splays last node right before.
				return x;
			}
			return right.containsNode(s);
		}
		return null; //shouldn't hit
	}
	public BST_Node insertNode(String s){ // seems good.
		if(data.compareTo(s)>0){
			if(left==null){
				left=new BST_Node(s);
				left.par = this;
				BST_Node x = left;
				splay(x); 
				return x;
			}
			return left.insertNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null){
				right=new BST_Node(s);
				right.par = this;
				BST_Node x = right;
				splay(x); 
				return x;
			}
			return right.insertNode(s);
		}
		if (data.compareTo(s) == 0) {
			//duplicate szn
			splay(this);
			return this;
		}
		System.out.println("this shouldn't happen.");
		return null; // this should never happen.
	}
	public boolean removeNode(String s){  // i don't think this matters at all.
		if(data==null)return false;
		if(data.equals(s)){
			if(left!=null){
				data=left.findMax().data;
				left.removeNode(data);
				if(left.data==null)left=null;
			}
			else if(right!=null){
				data=right.findMin().data;
				right.removeNode(data);
				if(right.data==null)right=null;
			}
			else data=null;
			return true;
		}
		else if(data.compareTo(s)>0){
			if(left==null)return false;
			if(!left.removeNode(s))return false;
			if(left.data==null)left=null;
			return true;
		}
		else if(data.compareTo(s)<0){
			if(right==null)return false;
			if(!right.removeNode(s))return false;
			if(right.data==null)right=null;
			return true;
		}
		return false;
	}
	public BST_Node findMin(){ // should be good
		if(left!=null) {
			return left.findMin();
		}
		splay(this);
		return this;
	}
	public BST_Node findMax(){ // should be good
		if(right!=null) {
			return right.findMax();
		} else {
//			System.out.println("we're here: " + this);
			// it gets dc'ed at the beginning of the block
			BST_Node x = this;
			splay(x); 
			System.out.println("find max: " + x);
			return x;
		}
		
	}
	public int getHeight(){ // do nothing
		int l=0;
		int r=0;
		if(left!=null)l+=left.getHeight()+1;
		if(right!=null)r+=right.getHeight()+1;
		return Integer.max(l, r);
	}
	public String toString(){ // do nothing
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}
	private BST_Node splay (BST_Node toSplay) { 
		System.out.println(toSplay);
		BST_Node p = toSplay.par;
		if (p == null) {
			return toSplay;
		}
		BST_Node g = p.par;
		
		if (g == null) { // nodes are 2nd to top level - i think this should be fine for this part.
			if (p.left == toSplay) { // turnRight
				p.par = toSplay;
				p.left = toSplay.right;
				if (toSplay.right != null) {
					toSplay.right.par = p;
				}
				toSplay.right = p;
				toSplay.par = null;
				return toSplay;
			}
			if (p.right == toSplay) { // turnLeft
				p.par = toSplay;
				p.right = toSplay.left;
				if (toSplay.left != null) {
					toSplay.left.par = p;
				}
				toSplay.left = p; // why isn't this being a thing
				toSplay.par = null;
				return toSplay;
			}
		}
		if (p.left == toSplay && g.left == p) {
			boolean left = false;
			if (g.par != null) {
				if (g.par.left == g) {
					left = true;
				} 
			}
			p.par = toSplay;
			p.left = toSplay.right;
			if (toSplay.right != null) {
				toSplay.right.par = p.left;
			}
			toSplay.right = p;
			g.left = p.right;
			p.right = g;
			toSplay.par = g.par;
			// now need to set g.par.right or g.par.left
			if (g.par != null) {
				if (left) {
					g.par.left = toSplay;
				} else {
					g.par.right = toSplay;
				}
			}
			if (g.left != null) {
				g.left.par = g;
			}
			g.par = p;
			if (toSplay.par != null) {
				return(splay(toSplay)); // recursive call if toSplay is not the root.
			} else {
				return toSplay;
			}
		}
		if (p.right == toSplay && g.right == p) {
			boolean left = false;
			if (g.par != null) {
				if (g.par.left == g) {
					left = true;
				} 
			}
			p.par = toSplay;
			p.right = toSplay.left;
			if (toSplay.left != null) {
				toSplay.left.par = p.right;
			}
			toSplay.left = p;
			g.right = p.left;
			p.left = g;
			toSplay.par = g.par;
			// now need to set g.par.right or g.par.left
			if (g.par != null) {
				if (left) {
					g.par.left = toSplay;
				} else {
					g.par.right = toSplay;
				}
			}
			if (g.right != null) {
				g.right.par = g;
			}
			g.par = p;
			if (toSplay.par != null) {
				return(splay(toSplay));
			} else {
				return toSplay;
			}
		}
		
		if (p.right == toSplay && g.left == p) {
			boolean left = false;
			if (g.par != null) {
				if (g.par.left == g) {
					left = true;
				}
			}
			p.par = toSplay;
			p.right = toSplay.left;
			if (toSplay.left != null) {
				toSplay.left.par = p;
			}
			toSplay.left = p;
			g.left = toSplay.right;
			if (toSplay.right != null) {
				toSplay.right.par = g;
			}
			// need to change grandparent parent thing if it exists.
			if (g.par != null) {
				if (left) {
					g.par.left = toSplay;
				} else {
					g.par.right = toSplay;
				}
			}
			toSplay.par = g.par;
			g.par = toSplay;
			toSplay.right = g;
			if (toSplay.par != null) {
				return(splay(toSplay));
			} else {
				return toSplay;
			}
			//return toSplay;
		}
		
		if (p.left == toSplay && g.right == p) {
			boolean left = false;
			if (g.par != null) {
				if (g.par.left == g) {
					left = true;
				}
			}
			
			
			p.par = toSplay;
			p.left = toSplay.right;
			if (toSplay.right != null) {
				toSplay.right.par = p;
			}
			
			toSplay.right = p;
			g.right = toSplay.left;
			if (toSplay.left != null) {
				toSplay.left.par = g;
			}
			
			if (g.par != null) {
				if (left) {
					g.par.left = toSplay;
				} else {
					g.par.right = toSplay;
				}
			}
			toSplay.par = g.par;
			g.par = toSplay;
			toSplay.left = g;
			if (toSplay.par != null) {
				return(splay(toSplay));
			} else {
				return toSplay;
			}
		}
		
		
		
		return null;
	}
	
	// a contains method that does not splay.
	public boolean contNoSpl(String s){ //life easier if i change return to type to BST_Node
		if(data.equals(s)) {
			return true;
		}
		if(data.compareTo(s)>0){//s lexiconically less than data
			if(left==null) {
				return false;
			}
			return left.contNoSpl(s);
		}
		if(data.compareTo(s)<0){
			if(right==null) {
				return false;
			}
			return right.contNoSpl(s);
		}
		return false; //shouldn't hit
	}
}
