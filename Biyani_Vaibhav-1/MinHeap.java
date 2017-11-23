import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MinHeap {

    private ArrayList<Node> list;
    public ArrayList<Node> finallist=new ArrayList<>();

    
    public ArrayList<Node> minheapmain(int[] integers) throws IOException
    {
		long start=System.currentTimeMillis();			

		initialinsert(integers);

			generateHuffman();
		
	System.out.println((System.currentTimeMillis()-start));	
	return finallist;
    }
    public void initialinsert(int[] integers)
    {
    	int arr[] = integers;

    	for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				Node n = new Node(i, arr[i]);
				insert(n);
			}
		}
    }
    
    public  void generateHuffman() throws IOException {
		Node root,root1;
    	while (list.size() != 1) {
			Node n1 = extractMin();
			Node n2 = extractMin();
			Node n3 = new Node(-1, n1.frequency + n2.frequency);
			n3.left = n1;
			n3.right = n2;
			insert(n3);
		}

    //	root=list.get(0);
	//root1=root;

	//print(root," ");

	//Traversal.printLevelOrder(root1);
	
	}
    
    

	public void print(Node root,String val ) throws IOException
{
	if(root==null)
		return;
	if(root.left==null && root.right==null)
	{
		root.huffmanValue=val;
		finallist.add(root);
		//System.out.println(root.name +" "+root.huffmanValue);
	}
	print(root.left,val+0);
	print(root.right,val+1);
}

    
	public MinHeap() {

        this.list = new ArrayList<Node>();
    }

    public MinHeap(ArrayList<Node> items) {

        this.list = items;
        buildHeap();
    }

    public void insert(Node item) {

        list.add(item);
        int i = list.size() - 1;
        int parent = parent(i);

        while (parent != i && list.get(i).frequency < list.get(parent).frequency) {

            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    public void buildHeap() {

        for (int i = list.size() / 2; i >= 0; i--) {
            minHeapify(i);
        }
    }

    public Node extractMin() {

         if (list.size() == 1) {

           Node min = list.remove(0);
            return min;
        }

        // remove the last item ,and set it as new root
        Node min = list.get(0);
        Node lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);

        // bubble-down until heap property is maintained
        minHeapify(0);

        // return min key
        return min;
    }



    private void minHeapify(int i) {

        int left = left(i);
        int right = right(i);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= list.size() - 1 && list.get(left).frequency < list.get(i).frequency) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= list.size() - 1 && list.get(right).frequency < list.get(smallest).frequency) {
            smallest = right;
        }

        // if the smallest key is not the current key then bubble-down it.
        if (smallest != i) {

            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public Node getMin() {

        return list.get(0);
    }


    private int right(int i) {

        return 2 * i + 2;
    }

    private int left(int i) {

        return 2 * i + 1;
    }

    private int parent(int i) {

        if (i % 2 == 1) {
            return i / 2;
        }

        return (i - 1) / 2;
    }

    private void swap(int i, int parent) {

        Node temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }

}