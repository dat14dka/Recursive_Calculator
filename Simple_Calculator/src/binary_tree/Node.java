package binary_tree;

/* 
 * The node class for the BinaryTree structure
 */
public class Node {
    private String value;
    private Node left; //left child node
    private Node right; //right child node
 
    // create a node with value and no children
    Node(String value) {
        this.value = value;
        right = null;
        left = null;
    }

    // create a node with value and left child "left" and right child "right"
    Node(String value, Node left, Node right) {
        this.value = value;
        this.right = right;
        this.left = left;
    }
    
    // set left and right child of node
    public void setLeftAndRightNode(Node left,Node right) {
    	 this.left = left;
    	 this.right = right;
    }
    
    // get left child node if there is one, otherwise return null
    public Node getLeftNode() {
    	return left;
    }
    
    // get right child node if there is one, otherwise return null
    public Node getRightNode() {
    	return right;
    }
    
    // get value of node
    public String getValue() {
    	return value;
    }
}