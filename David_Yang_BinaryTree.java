import java.io.BufferedWriter;
import java.io.IOException;

public class David_Yang_BinaryTree {
	David_Yang_TreeNode Root;
	
	public David_Yang_BinaryTree() {
		Root=new David_Yang_TreeNode("dummy",0,"",null,null,null); 
	}
	
	public boolean isLeaf(David_Yang_TreeNode node) {
		if(node.right!=null||node.left!=null) {
			return false;
		}else return true;
	}
	
	public void preOrderTraversal(David_Yang_TreeNode root, BufferedWriter writer) throws IOException {
		if(isLeaf(root)==true) {
			root.printNode(writer);
		}else {
			root.printNode(writer);
			preOrderTraversal(root.left, writer);
			preOrderTraversal(root.right,writer);
		}
	}
	
	public void inOrderTraversal(David_Yang_TreeNode root, BufferedWriter writer) throws IOException {
		if(isLeaf(root)==true) {
			root.printNode(writer);
		}else {
			inOrderTraversal(root.left, writer);
			root.printNode(writer);
			inOrderTraversal(root.right,writer);
		}
	}
	
	public void postOrderTraversal(David_Yang_TreeNode root, BufferedWriter writer) throws IOException {
		if(isLeaf(root)==true) {
			root.printNode(writer);
		}else {
			postOrderTraversal(root.left, writer);
			postOrderTraversal(root.right,writer);
			root.printNode(writer);
		}
	}
}
