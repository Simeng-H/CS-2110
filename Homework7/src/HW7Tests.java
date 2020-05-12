import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
* Homework 7 <br>
* Simeng Hao, sh4aj <br>
* Source: N/A <br>
*/
/**
 * TestBST
 */
public class HW7Tests {

    BinarySearchTree<Integer> testTree;

    /**
     * test Tree: 
     * 
     *                  32   
     *               /      \               
     *            /            \            
     *          16              48          
     *        /    \          /    \        
     *      8       24      40      56      
     *    /           \       \     /       
     *   4            28      44  52        
     * 
     * 
     */
    @Before
    public void setUp() {
        TreeNode<Integer> n32 = new TreeNode<Integer>(32);
        TreeNode<Integer> n16 = new TreeNode<Integer>(16);
        TreeNode<Integer> n48 = new TreeNode<Integer>(48);
        TreeNode<Integer> n8 = new TreeNode<Integer>(8);
        TreeNode<Integer> n24 = new TreeNode<Integer>(24);
        TreeNode<Integer> n40 = new TreeNode<Integer>(40);
        TreeNode<Integer> n56 = new TreeNode<Integer>(56);
        TreeNode<Integer> n4 = new TreeNode<Integer>(4);
        TreeNode<Integer> n28 = new TreeNode<Integer>(28);
        TreeNode<Integer> n44 = new TreeNode<Integer>(44);
        TreeNode<Integer> n52 = new TreeNode<Integer>(52);
        
        n32.setLeft(n16);
        n32.setRight(n48);
        n16.setLeft(n8);
        n16.setRight(n24);
        n8.setLeft(n4);
        n24.setRight(n28);
        n48.setLeft(n40);
        n48.setRight(n56);
        n40.setRight(n44);
        n56.setLeft(n52);

        testTree = new BinarySearchTree<>(n32);
    }

    @Test
    public void testSize() {
        
        // normal case
        assertEquals(11, testTree.size());

        // empty tree
        assertEquals(0, new BinarySearchTree<>().size());
    }

    @Test
    public void testHeight() {

        // normal case
        assertEquals(4, testTree.height());

        // empty tree
        assertEquals(0, new BinarySearchTree<>().height());
    }

    @Test
    public void testFind() {

        // normal case
        assertTrue(testTree.find(44));

        // value does not exist
        assertFalse(testTree.find(9));
    }

    @Test
    public void testInsert() {

        // normal case
        assertTrue(testTree.insert(2));

        // duplicate value
        assertFalse(testTree.insert(52));
    }

    @Test
    public void testDelete() {

        // normal case
        assertTrue(testTree.delete(24));
        assertTrue(testTree.delete(4));
        assertTrue(testTree.delete(32));
        assertEquals("(8)(16)(28)(40)(44)(48)(52)(56)", testTree.inOrder());
        assertEquals("(8)(28)(16)(44)(52)(56)(48)(40)", testTree.postOrder());

        // delete a value which does not exist
        assertFalse(testTree.delete(9));
    }

    @Test
    public void testInOrder() {

        // normal case
        assertEquals("(4)(8)(16)(24)(28)(32)(40)(44)(48)(52)(56)", testTree.inOrder());

        // empty tree
        assertEquals("", new BinarySearchTree<>().inOrder());
    }

    @Test
    public void testPostOrder() {

        // normal case
        assertEquals("(4)(8)(28)(24)(16)(44)(40)(52)(56)(48)(32)", testTree.postOrder());

        // empty tree
        assertEquals("", new BinarySearchTree<>().postOrder());
    }

    @Test
    public void testToString() {

        // normal case
        assertEquals("(4)(8)(16)(24)(28)(32)(40)(44)(48)(52)(56)", testTree.toString());

        // empty tree
        assertEquals("", new BinarySearchTree<>().toString());
    }

    @Test
    public void testBuildFromList() {
        
        // normal case
        ArrayList<Integer> testArray = new ArrayList<>();
        testArray.add(20);
        testArray.add(30);
        testArray.add(5);
        testArray.add(-1);
        testArray.add(7);
        testArray.add(4);
        testArray.add(46);
        testArray.add(9);
        testTree.buildFromList(testArray);
        assertEquals("(-1)(4)(5)(7)(9)(20)(30)(46)", testTree.toString());

        // empty list
        testArray = new ArrayList<>();
        testTree.buildFromList(testArray);
        assertEquals("", testTree.toString());
    }
}