import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class David_Yang_HuffmanCoding {
	String charCode[]=new String[256];
	int charCountAry[]=new int[256];
	
	public void computeCharCounts(BufferedReader reader) throws IOException {
		String line;
		while((line=reader.readLine())!=null) {
			for(int i=0; i<line.length(); i++) {
				int asc=(int)line.charAt(i);
				if(asc<256) {
					charCountAry[asc]++;
				}
			}charCountAry[10]++;//added: put newline '\n' code into array
		}
	}
	
	public void printCountAry(BufferedWriter writer) throws IOException {
		for(int i=0; i<256; i++) {
			if(charCountAry[i]!=0) {
				writer.write("("+i+", "+((char)i)+", "+charCountAry[i]+")\n");
			}
		}
	}
	
	public David_Yang_LinkedList constructHuffmanLList(BufferedWriter writer) throws IOException {
		David_Yang_LinkedList huffList=new David_Yang_LinkedList();
		for(int i=0; i<256; i++) {
			if(charCountAry[i]>0) {
				David_Yang_TreeNode newNode=new David_Yang_TreeNode(String.valueOf((char)i), charCountAry[i], "", null, null, null);
				huffList.insertNewNode(huffList.listHead, newNode);
				huffList.printList(writer);
			}
		}return huffList;
	}
	
	public David_Yang_BinaryTree constructHuffmanBinTree (David_Yang_LinkedList list, BufferedWriter writer) throws IOException {
		David_Yang_BinaryTree tree=new David_Yang_BinaryTree();
		while(list.tempHead.next.next!=null) {
			StringBuilder sb=new StringBuilder();
			sb.append("("+list.tempHead.next.chStr+", "+list.tempHead.next.next.chStr+")");
			String s=sb.toString();
			int f=list.tempHead.next.frequency+list.tempHead.next.next.frequency;
			David_Yang_TreeNode newNode=new David_Yang_TreeNode(s,f,"",list.tempHead.next,list.tempHead.next.next,null);
			list.insertNewNode(list.tempHead, newNode);
			list.tempHead=list.tempHead.next.next;
			list.printList(writer);
		}tree.Root=list.tempHead.next;
		return tree;
	}
	
	//project 4
	
	
	public void constructCharCode(David_Yang_TreeNode t, String Code ) {
		if(t.left==null&&t.right==null) {
			t.code=Code;
			int index=(int)t.chStr.charAt(0);
			charCode[index]=Code;
		}else {
			constructCharCode(t.left, Code+"0");
			constructCharCode(t.right, Code+"1");
		}
	}
	
	public void userInterface(David_Yang_TreeNode root, String outFile) throws IOException {
		String nameOrg, nameCompress, nameDeCompress;
		char yesNo=' ';
		while(true) {
			System.out.println("Please enter 'Y' or 'N': ");
			Scanner sc=new Scanner(System.in);
			yesNo=sc.next().charAt(0);
			if(yesNo!='Y'&&yesNo!='N') {
				System.out.println("Please enter 'Y' for YES, 'N' for NO!");
			}else {
				if(yesNo=='N') {
					sc.close();
					System.exit(0);
				}else {
					System.out.println("Please enter name of file: ");
					sc=new Scanner(System.in);
					nameOrg=sc.next();
					nameCompress=nameOrg+"_Compressed.txt";
					nameDeCompress=nameOrg+"_Decompressed.txt";
					nameOrg+=".txt";
					BufferedReader reader=new BufferedReader(new FileReader(nameOrg));
					File compressed=new File(nameCompress);
					File decompressed=new File(nameDeCompress);
					if(compressed.createNewFile()) {
						System.out.println("Compressed file createing success, compressing in progress..");
					}else {
						System.out.println("Compressed file creating fail");
						continue;
					}
					if(decompressed.createNewFile()) {
						System.out.println("DeCompressed file creating success, decompressing in progress..");
					}else {
						System.out.println("DeCompressed file creating fail");
						continue;
					}
					BufferedWriter writerC=new BufferedWriter(new FileWriter(nameCompress));
					BufferedWriter writerDC=new BufferedWriter(new FileWriter(nameDeCompress));
					Encode(reader, writerC, outFile);
					writerC.close();
					BufferedReader readerC=new BufferedReader(new FileReader(nameCompress));
					Decode(readerC, writerDC,root);
					reader.close();
					writerDC.close();
					readerC.close();
				}
			}
		}
	}

	private void Decode(BufferedReader readerC, BufferedWriter writerDC, David_Yang_TreeNode root) throws IOException {
		David_Yang_TreeNode Spot=root;
		String line;
		while((line=readerC.readLine())!=null) {
			for(int i=0; i<line.length();i++) {
				char oneBit=line.charAt(i);
				if(oneBit=='0') {
					if(Spot.left!=null) {
						Spot=Spot.left;
					}else {
						writerDC.write(Spot.chStr);
						Spot=root;
						Spot=Spot.left;
					}
				}else if(oneBit=='1') {
					if(Spot.right!=null) {
						Spot=Spot.right;
					}else {
						writerDC.write(Spot.chStr);
						Spot=root;
						Spot=Spot.right;
					}
				}else {
					System.out.println("Error! The compress file contains invalid character!");
					System.exit(0);
				}
			}
		}if(Spot.left!=null&&Spot.right!=null&&Spot.chStr!="dummy") {
			System.out.println("Error: The compress file is corrupted!");
		}else {
			writerDC.write(Spot.chStr);
			System.out.println("Decompression finished.");
		}
	}

	private void Encode(BufferedReader reader, BufferedWriter writerC, String outFile) throws IOException {
		BufferedWriter debug=new BufferedWriter(new FileWriter(outFile));
		String line;
		while((line=reader.readLine())!=null) {
			for(int i=0; i<line.length(); i++) {
				char charIn=line.charAt(i);
				int index=(int)charIn;
				if(index<256) {//there are some weird characters in test files, this line is trying to ignore them
					String code=charCode[index];
					writerC.write(code);
					debug.write((char)index+"\t"+index+"\t"+code+"\n");//for debug
				}else {
					index=32;
					String code=charCode[index];
					writerC.write(code);
				}//if there is a weird character, change it to whitespace..
			}writerC.write(charCode[10]);//added: newline '\n' code
		}debug.close();
		System.out.println("Compression finished.");
	}
	
	
	
	
}
