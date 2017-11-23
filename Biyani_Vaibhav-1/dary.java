import java.util.Vector;;

public class dary {

    private Vector<Node> list;
    public Vector<Node> finallist=new Vector<>();
    
    public Vector<Node> darymain(int[] integers)
    {
		//long start1=System.currentTimeMillis();			

		initialinsert(integers);
		//System.out.println("Hellofromdarymain before genera");
		generateHuffman();
		//System.out.println("Hellofromdarymain before genera after");
		
		//System.out.println("\ntime");		
		//System.out.println((System.currentTimeMillis()-start1));	
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
    public  void generateHuffman() {
		Node root;
    	while (list.size() != 1) {
			Node n1 = extractMin();
			Node n2 = extractMin();
			Node n3 = new Node(-1, n1.frequency + n2.frequency);
			n3.left = n1;
			n3.right = n2;
			insert(n3);
		}
    	root=list.get(0);
	//root1=root;
	print(root,"");
	//Traversal.printLevelOrder(root1);
	
	}
    
    

	public void print(Node root,String val )
{
	if(root==null)
		return ;
	if(root.left==null && root.right==null)
	{
		root.huffmanValue=val;
	//	System.out.println(root.name +" "+root.huffmanValue);
		finallist.add(root);
		
	}
	
	print(root.left,val+0);
	print(root.right,val+1);
}

    public dary() {

        this.list = new Vector<Node>();
       
    }

    
    public dary(Vector<Node> items) {

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

        for (int i = list.size() / 4; i >= 0; i--) {
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

    	int x=4*i;
        int child1 = x+1;
        int child2 = x+2;
        int child3 = x+3;
        int child4 = x+4;
        
        int min = -1;

        // find the smallest key between current node and its children.
/*        if (child <= list.size() - 1 && list.get(left).frequency < list.get(i).frequency) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= list.size() - 1 && list.get(right).frequency < list.get(smallest).frequency) {
            smallest = right;
        }
        
*/
        int size=list.size()-1;
        if (child1 > size)
			min= -1;
        else if (child2 > size)
			min= child1;
		else if (child3 > size)
			min= (list.get(child1).frequency <= list.get(child2).frequency) ? child1 : child2;
		else if (child4 > size)
			min= ((list.get(child1).frequency <= list.get(child2).frequency) && (list.get(child1).frequency <= list.get(child3).frequency)) ? child1 : (list.get(child2).frequency <= list.get(child3).frequency)? child2 : child3;
		
		else if ((list.get(child1).frequency <= list.get(child2).frequency) && (list.get(child1).frequency <= list.get(child3).frequency) && (list.get(child1).frequency <= list.get(child4).frequency))  { // a >= b,c,d,e
		    min=child1;
		} 
		else if ((list.get(child2).frequency <= list.get(child3).frequency) && (list.get(child2).frequency <= list.get(child4).frequency) /*&& (list.get(child2).frequency <= list.get(child1).frequency)*/) {      // b >= c,d,e
		   min=child2;
		}
		else if ((list.get(child3).frequency <= list.get(child4).frequency) /*&& (list.get(child3).frequency <= list.get(child1).frequency) &&(list.get(child3).frequency <= list.get(child2).frequency)*/ ) {                  // c >= d,e
		    min=child3;
		}
		else 
		{
			min=child4;
		}

        
        if(min!=-1)
        {
        	if(list.get(min).frequency<list.get(i).frequency)
        	{
        		swap(i, min);
        		minHeapify(min);
        
        	}
        }
     
        }
        
      
    
    
    public Node getMin() {

        return list.get(0);
    }


/*    private int right(int i) {

        return 2 * i + 2;
    }

    private int left(int i) {

        return 2 * i + 1;
    }*/

    private int parent(int i) {


        return ((i-1) / 4);
    }

    private void swap(int i, int parent) {

        Node temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }

}