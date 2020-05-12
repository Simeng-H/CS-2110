import java.util.ArrayList;

/**
 * Binary Search Tree Class
 * 
 * The head class for a binary search tree implementation.
 * 
 * @author sh4aj
 * @param <Comparable> Type of data to store in the binary tree
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * A reference pointer to the root of the tree
     */
    private TreeNode<T> root;

    /**
     * Default constructor
     * 
     * Creates a binary tree object with null root note (empty tree)
     */
    public BinarySearchTree() {
        this(null);
    }

    /**
     * Constructor
     * 
     * Creates a binary tree object with the given node as root
     * 
     * @param newRoot The root of the tree
     */
    public BinarySearchTree(TreeNode<T> newRoot) {
        this.root = newRoot;
    }

    /**
     * Get the root of the tree
     * 
     * @return The root of the tree
     */
    public TreeNode<T> getRoot() {
        return root;
    }

    /**
     * Set the root of the tree
     * 
     * @param root The new root of this tree
     */
    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * Insert an element
     * 
     * Inserts val into the tree where it should appear, returning true on success
     * and false otherwise
     * 
     * @param val The value to insert
     * @return True on success, false otherwise
     */
    public boolean insert(T val) {
        if (val == null) {
            return false;
        }
        if (root == null) {
            root = new TreeNode<T>(val);
            return true;
        } else {
            return root.insert(val);
        }
    }

    /**
     * Delete an element from the tree
     * 
     * Deletes val from the tree if it appears, returning true on success and false
     * otherwise
     * 
     * @param val The value to delete
     * @return True on success, false otherwise
     */
    public boolean delete(T val) {
        if (root == null) {
            return false;
        }
        boolean result = root.delete(val);
        if (result == false) {
            if (val.compareTo(root.getData()) == 0) {
                result = deleteRoot();
            }
        }
        return result;
    }

    /**
     * deletes the root of the tree, by reassigning reference if root is leaf or
     * half node, or by reassigning value if root is full node;
     * 
     * @return true if deleted successfully, false otherwise
     */
    private boolean deleteRoot() {
        if (root.isLeafNode()) {
            root = null;
            return true;
        } else if (root.isFullNode()) {
            return root.deleteNode(root);
        } else {
            root = root.getLeft() != null ? root.getLeft() : root.getRight();
            return true;
        }
    }

    /**
     * Build from a list
     * 
     * Build the tree from the given list, overwriting any tree data previously
     * stored in this tree. Should read from beginning to end of the list and
     * repeatedly call insert() to build the tree.
     * 
     * @param list The list from which to build the tree
     * @return True if successfully built, false otherwise
     */
    public boolean buildFromList(ArrayList<T> list) {
        this.root = null;
        boolean result = false;
        for (T val : list) {
            result = this.insert(val);
            if (result = false) {
                root = null;
                break;
            }
        }
        return result;
    }

    /**
     * Generates a String representation of the tree. All elements are enclosed in parenthesis and arranged in ascending order
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if(root != null){
            return root.inOrder();
        }
        return "";
    }

    /**
     * 
     * @return the total number of nodes in this tree, including the root
     */
    public int size() {
        if (root != null) {
            return root.size();
        }
        return 0;
    }

    /**
     * Finds the height of the tree. The node is at height 1 and it's child is at height 2 etc. 
     * @return the height of the tree
     */
    public int height() {
        if (root != null) {
            return root.height();
        }
        return 0;
    }

    /**
     * Find if an element exists
     * 
     * Checks to see if the value val appears in the tree (recursively). Returns
     * true if it appears and false otherwise.
     * 
     * @param val The value to find
     * @return True if the tree contains the value, false otherwise
     */
    public boolean find(T val) {
        if (root != null) {
            return root.find(val);
        }
        return false;
    }

    /**
     * @return a String representation of the tree by in-order traversal
     */
    public String inOrder() {
        if (root != null) {
            return root.inOrder();
        }
        return "";
    }
    
    /**
     * 
     * @return a String representation of the tree by post-order traversal
     */
    public String postOrder(){
        if (root != null) {
            return root.postOrder();
        }
        return "";
    }

    /**
     * Main method
     * 
     * For testing, etc
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        
    }
}
