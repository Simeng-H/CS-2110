
/**
 * Binary Tree Node
 * 
 * Tree node that has two children: left and right
 * 
 * @author sh4aj
 * @param <Comparable> The type of data this tree node stores
 */
public class TreeNode<T extends Comparable<T>> {
	
	/**
	 * Reference pointer to the left subtree
	 */
	private TreeNode<T> left;
	
	/**
	 * Reference pointer to the right subtree
	 */
	private TreeNode<T> right;
	
	/**
	 * Data stored at this node
	 */
	private T data;
	
	/**
	 * Default Constructor
	 * 
	 * Creates a binary tree node with null data and null children
	 */
	public TreeNode(){
		this(null,null,null);
	}
	
	/**
	 * Data-only Constructor
	 * 
	 * Creates a binary tree node with the given data and null children
	 * 
	 * @param theData The data to store at this node
	 */
	public TreeNode(T theData){
		this(theData,null,null);
	}
	
	
	/**
	 * Full Constructor
	 * 
	 * Creates a binary tree node with the given data and child reference pointers
	 * 
	 * @param theData The data to store at this node
	 * @param leftChild A reference pointer to the left subtree
	 * @param rightChild A reference pointer to the right subtree
	 */
	public TreeNode(T theData, TreeNode<T> leftChild, TreeNode<T> rightChild){
		data = theData;
		left = leftChild;
		right = rightChild;
	}


	/**
	 * Left Child/Subtree getter
	 * 
	 * @return A reference pointer to the root of the left subtree
	 */
	public TreeNode<T> getLeft() {
		return left;
	}

	/**
	 * Left Child/Subtree Setter
	 * 
	 * @param left A reference pointer to the new left subtree's root node
	 */
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

    /**
     * Right Child/Subtree getter
     * 
     * @return A reference pointer to the root of the right subtree
     */
	public TreeNode<T> getRight() {
		return right;
	}

    /**
     * Right Child/Subtree Setter
     * 
     * @param left A reference pointer to the new right subtree's root node
     */
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	/**
	 * Get the data at this node
	 * 
	 * @return The data stored at this node
	 */
	public T getData() {
		return data;
	}

	/**
	 * Set the data at this node
	 * 
	 * @param data The data to be stored at this node
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * returns a string representation of the tree
	 */
	@Override
	public String toString() {
	    return this.inOrder();
	}

	/**
	 * Finds the size of the subtree with this node as root, practically equal to the sum of the sizes of this node and its subtrees (if any) 
	 * @return {@code 1 + size(left) + size (right)}
	 */
	public int size(){
		int result = 1;
		if(left != null){
			result+=left.size();
		}
		if(right!=null){
			result+=right.size();
		}
		return result;
	}

	/**
	 * finds the height of the tree with this node as the root
	 * @return 1 + the bigger of the heights of the two subtrees;
	 */
	public int height() {
		int leftHeight = 0;
		int rightHeight = 0;
		int maxHeight = 0;
		if (left != null) {
			leftHeight = left.height();
		}
		if (right != null) {
			rightHeight = right.height();
		}
		maxHeight = leftHeight > rightHeight ? leftHeight : rightHeight;
		return 1 + maxHeight;
	}

	/**
	 * look for value in the tree recursively. Checks using compareTo() method
	 * @param val a value to look for
	 * @return true if found in the tree, false otherwise.
	 */
	public boolean find(T val) {
		return(findNode(val) != null);
	}

	/**
	 * looks for a node containing the target value in the tree.
	 * @param val the target value
	 * @return the node containing val, or null if unfound.
	 */
	private TreeNode<T> findNode(T val) {
		if (val.compareTo(this.data) == 0) {	// Compares with this node
			return this;
		}
		TreeNode<T> next = null;		// find in respective subtree
		if (val.compareTo(this.data) < 0) {
			next = left;
		} else {
			next = right;
		}
		if(next == null){
			return null;
		} else {
			return next.findNode(val);
		}
	}

	/**
	 * recursively inserts val into the tree. If child node is null, creates a new node with val and makes it a child; calls insert(val) on the child node otherwise. Duplicates are not permitted.
	 * @param val the value to insert
	 * @return true if val is successfully inserted, false otherwise.
	 */
	public boolean insert(T val) {
		if(val == null){
			return false;
		}

		if(val.compareTo(this.data) == 0){
			return false;
		}
		
		if(val.compareTo(this.data)<0){
			if(left == null){
				left = new TreeNode<T>(val);
			} else {
				return left.insert(val);
			}
		} else if(val.compareTo(this.data)>0) {
			if(right == null){
				right = new TreeNode<T>(val);
			} else {
				return right.insert(val);
			}
		}
		return true;
	}

	/**
	 * deletes a value from the tree.
	 * @param val the value to delete
	 * @return true if successfully deleted. false if value == null or value is unfound in the tree.
	 */
	public boolean delete(T val){
		TreeNode<T> toDelete = findNode(val);
		if(toDelete == null){
			return false;
		}
		return deleteNode(toDelete);
	}

	/**
	 * Pre-condition: toDelete is a node in the tree, if toDelete is the root, toDelete must be full (has both left and right children)
	 * @param toDelete a node in the tree
	 * @return true if the node is successfully deleted, false otherwise.
	 */
	public boolean deleteNode(TreeNode<T> toDelete) {
		if(this == toDelete){
			if(this.isFullNode()){
				return deleteFullNode(toDelete);
			}
			return false;
		}
		if (toDelete.isLeafNode()) {
			return deleteLeafNode(toDelete);
		} else if (toDelete.isFullNode()) {
			return deleteFullNode(toDelete);
		} else {
			return deleteHalfNode(toDelete);
		}

	}

	/**
	 * deletes a leaf node in the tree
	 * Pre-condition: toDelete is a node in the tree
	 * @param toDelete
	 * @return true if successfully deleted, false otherwise
	 */
	private boolean deleteLeafNode(TreeNode<T> toDelete) {
		TreeNode<T> parent = getParent(toDelete);
		if (parent.left == toDelete) {
			parent.left = null;
		} else {
			parent.right = null;
		}
		return true;
	}

	/**
	 * deletes a half node (a node with only one child node) in the tree. 
	 * Pre-condition: toDelete is a node in the tree
	 * @param toDelete
	 * @return true if successfully deleted, false otherwise
	 */
	private boolean deleteHalfNode(TreeNode<T> toDelete) {
		TreeNode<T> parent = getParent(toDelete);

		// finds child node of toDelete
		TreeNode<T> child = toDelete.left != null ? toDelete.left : toDelete.right;

		if (parent.left == toDelete) {
			parent.left = child;
		} else {
			parent.right = child;
		}
		return true;
	}

	/**
	 * deletes a full node (a node with both left and right child nodes) in the tree
	 * Pre-condition: toDelete is a node in the tree
	 * @param toDelete
	 * @return true if successfully deleted, false otherwise
	 */
	private boolean deleteFullNode(TreeNode<T> toDelete) {
		TreeNode<T> rightMinNode = toDelete.right.findMinNode();
		toDelete.data = rightMinNode.data;
		return deleteNode(rightMinNode);
	}

	/**
	 * finds the node containing the minimum value in the tree and returns it.
	 * @return the node with the minimum value in the tree. 
	 */
	public TreeNode<T> findMinNode() {
		TreeNode<T> minNode = this;
		while(minNode.left!=null){
			minNode = minNode.left;
		}
		return minNode;
	}

	/**
	 * Precondition: input is a node in the tree
	 * 
	 * @param input
	 * @return the parent node of input if it is in the tree. <br>
	 *         returns null if input is the root (i.e. input == this)
	 */
	private TreeNode<T> getParent(TreeNode<T> input) {
		if (this == input) {
			return null;
		}
		if (this.left == input || this.right == input) {
			return this;
		}
		if (input.getData().compareTo(this.data) < 0) {
			return left.getParent(input);
		} else {
			return right.getParent(input);
		}
	}

	/**
	 * @return a String representation of the tree generated by in-order traversal
	 */
	public String inOrder(){
		StringBuilder sbInOrder = new StringBuilder();
		buildInOrder(sbInOrder);
		return sbInOrder.toString();
	}

	/**
	 * @return a String representation of the tree generated by post-order traversal
	 */
	public String postOrder(){
		StringBuilder sbPostOrder = new StringBuilder();
		buildPostOrder(sbPostOrder);
		return sbPostOrder.toString();
	}


	/**
	 * builds a String representation of the tree by recursive in-order traversal with a provided StringBuilder to avoid unnecessary computational cost of direct concatenation.
	 * @param sbOutput a StringBuilder which stores the result of the traversal.
	 */
	private void buildPostOrder(StringBuilder sbOutput) {
		if(left!=null){
			left.buildPostOrder(sbOutput);
		}
		if(right!=null){
			right.buildPostOrder(sbOutput);
		}
		sbOutput.append("(");
		sbOutput.append(this.getData());
		sbOutput.append(")");
	}

	/**
	 * * builds a String representation of the tree by recursive post-order traversal with a provided StringBuilder to avoid unnecessary computational cost of direct concatenation.
	 * @param sbOutput a StringBuilder which stores the result of the traversal.
	 */
	private void buildInOrder(StringBuilder sbOutput) {
		if(left!=null){
			left.buildInOrder(sbOutput);
		}
		sbOutput.append("(");
		sbOutput.append(this.getData());
		sbOutput.append(")");
		if(right!=null){
			right.buildInOrder(sbOutput);
		}
	}

	public boolean isLeafNode() {
		return (this.left == null && this.right == null);
	}

	public boolean isFullNode() {
		return (this.left != null && this.right != null);
	}

	/**
	 * Main method
	 * 
	 * For main method testing, etc
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {

	}

}
