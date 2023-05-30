import java.io.BufferedWriter;
import java.io.IOException;

public class David_Yang_LinkedList {
	David_Yang_TreeNode listHead;
	David_Yang_TreeNode tempHead;
	
	public David_Yang_LinkedList() {
		listHead=new David_Yang_TreeNode("dummy",0,"",null,null,null);
		tempHead=listHead;
	}
	
	public void insertNewNode(David_Yang_TreeNode head, David_Yang_TreeNode newNode) {
		David_Yang_TreeNode spot=findSpot(head, newNode);
		newNode.next=spot.next;
		spot.next=newNode;
	}

	public David_Yang_TreeNode findSpot(David_Yang_TreeNode head, David_Yang_TreeNode newNode) {
		David_Yang_TreeNode spot=head;
		while(spot.next!=null&&spot.next.frequency < newNode.frequency) {
			spot=spot.next;
		}
		return spot;
	}
	
	public void printList(BufferedWriter writer) throws IOException {
		David_Yang_TreeNode curr=listHead;
		curr.printNode(writer);
		while(curr.next!=null) {
			curr=curr.next;
			curr.printNode(writer);
		}
	}
}
