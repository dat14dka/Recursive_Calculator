package binary_tree;
/* 
 * A tree structure where each nodes has two or no children at all times. Nodes are
 * added at the root(see add function), and nodes are never removed.
 *  
*/
public class BinaryTree {
    private Node root; //root node
    
    public BinaryTree() {

    }
    
    // creating a new Tree, with operation as root and firstOperand as right node, 
    // 0 as left
    public BinaryTree(String operation, String firstOperand) {
    	this.root = new Node(operation);
    	root.setLeftAndRightNode(new Node("0"), new Node(firstOperand));
    }
    
    // print the Tree
    public void printTree() {
    	printSubTree(root); //calls a recursive function printing all the subtrees
    }
    
    //
    private void printSubTree(Node root) {
    	System.out.println("root_node is: " + root.getValue());
      	if(root.getLeftNode() != null) {
        	System.out.println("left_child is: " + root.getLeftNode().getValue());
    	}
    	if(root.getRightNode() != null)  {
        	System.out.println("right_child is: " + root.getRightNode().getValue());
    	}
    	System.out.println("");

    	// if there is a left node, print it and its children
      	if(root.getLeftNode() != null) {
        	printSubTree(root.getLeftNode());
    	}// if there is a right node, print it and its children
    	if(root.getRightNode() != null)  {
        	printSubTree(root.getRightNode());
    	}
    }
    
    // adding a node like always entails an operand and and operation(ex: + and 2)
    // 1. operation becomes new root 
    // 2. operand becomes right root
    // 3. old root becomes left root
    public void addNode(String operand, String operation) {
    	Node oldroot = root;    // save old root
    	root = new Node(operation, oldroot, new Node(operand)); //perform 1.,2. and 3.
    }
    
    // return current root
    public Node getRoot() {
    	return root;
    }
    
}
