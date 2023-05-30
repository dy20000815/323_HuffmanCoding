import java.io.BufferedWriter;
import java.io.IOException;

public class David_Yang_TreeNode {
	String chStr;
	int frequency;
	String code;
	David_Yang_TreeNode left;
	David_Yang_TreeNode right;
	David_Yang_TreeNode next;
	
	public David_Yang_TreeNode(String s, int f, String c, David_Yang_TreeNode l, David_Yang_TreeNode r, David_Yang_TreeNode n ) {
		chStr=s;
		frequency=f;
		code=c;
		left=l;
		right=r;
		next=n;
	}
	
	public void printNode(BufferedWriter writer) throws IOException {
		if(left==null&&right==null) {
			writer.write("("+chStr+", "+frequency+", "+code+", null, null)\n");
		}else if(left!=null&&right==null){
			writer.write("("+chStr+", "+frequency+", "+code+", "+left.chStr+", null)\n");
		}else if(left==null&&right!=null) {
			writer.write("("+chStr+", "+frequency+", "+code+", null, "+right.chStr+")\n");
		}else writer.write("("+chStr+", "+frequency+", "+code+", "+left.chStr+", "+right.chStr+")\n");
	}
}
