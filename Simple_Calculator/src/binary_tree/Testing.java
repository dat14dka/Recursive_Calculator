package binary_tree;

public class Testing {
	public static void main(String[] args) {
		
		//System.out.println("test 1:");
		BinaryTree test1 = new BinaryTree("+", "1");
		//test1.printTree();
		//System.out.println("");
		
		System.out.println("test 2:");
		test1.addNode("3", "-");
		test1.printTree();	
	}
}
