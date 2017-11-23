import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HTreeBinary {
	static Vector<Node> vec = new Vector<>();
	static Vector<Integer> vechuff = new Vector<>();

	static Node root;
	static Node root1;
		
		
		public static void Htreemain(int[] integers) 
		{
		int arr[] = integers;

		long start=System.currentTimeMillis();			
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				Node n = new Node(i, arr[i]);
				insert(n);
			}

		}
		generateHuffman();
		
	System.out.println((System.currentTimeMillis()-start));	
		}
	

	public static void generateHuffman() {
		while (vec.size() != 1) {
			Node n1 = remove();
			Node n2 = remove();
			Node n3 = new Node(-1, n1.frequency + n2.frequency);
			n3.left = n1;
			n3.right = n2;
			insert(n3);
		}
	root=vec.get(0);
	root1=root;
	//(root," ");
	//Traversal.printLevelOrder(root1);
	
	}
		public static void print(Node root,String val )
	{
		if(root==null)
			return;
		if(root.left==null && root.right==null)
		{
			root.huffmanValue=val;
			System.out.println(root.name +" "+root.huffmanValue);
		}
		
		print(root.left,val+0);
		print(root.right,val+1);
	}
	
	
	public static void insert(Node n) {
		vec.add(n);
		bubbleUp(vec.size() - 1);
	}

	public static Node remove() {
/*
		if (vec.size() == 0)
			throw new NoSuchElementException();
*/
/*		if (!isHeap()) {
			System.err.println("Heap property broken!");
		}
*/
		Node result = vec.get(0);
		vec.set(0, vec.get(vec.size() - 1));
		vec.remove(vec.size() - 1);
		bubbleDown(0);
/*		if (!isHeap()) {
			System.err.println("Heap property broken!");
		}
*/		return result;
	}

	private static void bubbleDown(int n) {
		int minChildIndex = minChildIndex(n);
		while (minChildIndex != -1 && vec.get(minChildIndex).frequency < vec.get(n).frequency) {
			swap(minChildIndex, n);
			n = minChildIndex;
			minChildIndex = minChildIndex(n);
		}
	}

	private static int minChildIndex(int n) {
		if (left(n) > vec.size() - 1)
			return -1;
		if (right(n) > vec.size() - 1)
			return left(n);
		return vec.get(left(n)).frequency <= vec.get(right(n)).frequency ? left(n) : right(n);
	}


	private static void bubbleUp(int n) {
		int parIndex = par(n);
		while (n > 0 && vec.get(parIndex).frequency > vec.get(n).frequency) {
			swap(parIndex, n);
			n = parIndex;
			parIndex = par(n);
		}

	}

	private static void swap(int parIndex, int n) {
		Node tmp = vec.get(parIndex);
		vec.set(parIndex, vec.get(n));
		vec.set(n, tmp);
	}

	private static int par(int i) {

        if (i % 2 == 1) {
            return i / 2;
        }

        return (i - 1) / 2;
	}

	private static int left(int n) {
		return n * 2 + 1;
	}

	private static int right(int n) {
		return n * 2 + 2;
	}

	public static int size() {
		return vec.size();
	}

}