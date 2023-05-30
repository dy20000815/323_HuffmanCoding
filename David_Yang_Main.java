import java.io.*;

public class David_Yang_Main {
	public static void main (String args[]) throws IOException {
		String inFile=args[0];
		String outFile=args[1];
		
		BufferedReader reader = new BufferedReader(new FileReader(inFile));
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
		
		David_Yang_HuffmanCoding hc=new David_Yang_HuffmanCoding();
		hc.computeCharCounts(reader);
		writer.write("printing counting array: \n");
		hc.printCountAry(writer);
		writer.write("printing debug process of constructHuffmanLList method: \n");
		David_Yang_LinkedList list=hc.constructHuffmanLList(writer);
		writer.write("printing process of constructHuffmanBinTree method: \n");
		David_Yang_BinaryTree tree=hc.constructHuffmanBinTree(list, writer);
		writer.write("printList: \n");
		list.printList(writer);
		writer.write("preOrderTraversal: \n");
		tree.preOrderTraversal (tree.Root, writer);
		writer.write("inOrderTraversal: \n");
		tree.inOrderTraversal (tree.Root, writer);
		writer.write("postOrderTraversal: \n");
		tree.postOrderTraversal (tree.Root, writer);
		reader.close();
		writer.close();
		
		//PROJECT 4
		hc.constructCharCode(tree.Root,"");
		hc.userInterface(tree.Root, outFile);
	}
}
