/* Class PairNode */
class PairNode
{
    int element;
    PairNode leftChild;
    PairNode nextSibling;
    PairNode prev;
 
    /* Constructor */
    public PairNode(int x)
    {
        element = x;
        leftChild = null;
        nextSibling = null;
        prev = null;
    }
}
 
/* Class PairHeap */
class PairHeap
{
    private PairNode root; 
    private PairNode [ ] treeArray = new PairNode[ 5 ];
    //public ArrayList<Node> finallist=new ArrayList<>();

    /* Constructor */
    public PairHeap( )
    {
        root = null;
      }
 
    
    public void pheapmain(int[] integers)
    {
		long start=System.currentTimeMillis();			

    	initialinsert(integers);    	
    	generateHuffman();
    	System.out.println((System.currentTimeMillis()-start));	

   // 	return finallist;
    }
    
    public void initialinsert(int[] integers){
    	int arr[] = integers;

	for (int i = 0; i < arr.length; i++) {
		if (arr[i] != 0) {
			//Node n = new Node(i, arr[i]);
			insert(arr[i]);
		}

	}
}
    
    public  void generateHuffman() {
		//Node root1;
    	while (root.leftChild!=null ) {
			int n1 = deleteMin();
			int n2 = deleteMin();
			Node n3 = new Node(-1, n1 + n2);
			Node no1=new Node(-1,n1);
			Node no2=new Node(-1,n2);
			
			n3.left = no1;
			n3.right = no2;
			insert(n1+n2);
		}
    	//root=
	//root=list.get(0);
	//root1=root;
	//print(root," ");
	//Traversal.printLevelOrder(root1);
	
	}
    
    
    
    
    /* Make heap logically empty */ 
   
    /* Function to insert data */
    public PairNode insert(int x)
    {
        PairNode newNode = new PairNode( x );
        if (root == null)
            root = newNode;
        else
            root = compareAndLink(root, newNode);
        return newNode;
    }
    /* Function compareAndLink */
    private PairNode compareAndLink(PairNode first, PairNode second)
    {
        if (second == null)
            return first;
 
        if (second.element < first.element)
        {
            /* Attach first as leftmost child of second */
            second.prev = first.prev;
            first.prev = second;
            first.nextSibling = second.leftChild;
            if (first.nextSibling != null)
                first.nextSibling.prev = first;
            second.leftChild = first;
            return second;
        }
        else
        {
            /* Attach second as leftmost child of first */
            second.prev = first;
            first.nextSibling = second.nextSibling;
            if (first.nextSibling != null)
                first.nextSibling.prev = first;
            second.nextSibling = first.leftChild;
            if (second.nextSibling != null)
                second.nextSibling.prev = second;
            first.leftChild = second;
            return first;
        }
    }
    
    
    private PairNode combineSiblings(PairNode firstSibling)
    {
        if( firstSibling.nextSibling == null )
            return firstSibling;
        /* Store the subtrees in an array */
        int numSiblings = 0;
        for ( ; firstSibling != null; numSiblings++)
        {
            treeArray = doubleIfFull( treeArray, numSiblings );
            treeArray[ numSiblings ] = firstSibling;
            /* break links */
            firstSibling.prev.nextSibling = null;  
            firstSibling = firstSibling.nextSibling;
        }
        treeArray = doubleIfFull( treeArray, numSiblings );
        treeArray[ numSiblings ] = null;
        /* Combine subtrees two at a time, going left to right */
        int i = 0;
        for ( ; i + 1 < numSiblings; i += 2)
            treeArray[ i ] = compareAndLink(treeArray[i], treeArray[i + 1]);
        int j = i - 2;
        /* j has the result of last compareAndLink */
        /* If an odd number of trees, get the last one */
        if (j == numSiblings - 3)
            treeArray[ j ] = compareAndLink( treeArray[ j ], treeArray[ j + 2 ] );
        /* Now go right to left, merging last tree with */
        /* next to last. The result becomes the new last */
        for ( ; j >= 2; j -= 2)
            treeArray[j - 2] = compareAndLink(treeArray[j-2], treeArray[j]);
        return treeArray[0];
    }
    private PairNode[] doubleIfFull(PairNode [ ] array, int index)
    {
        if (index == array.length)
        {
            PairNode [ ] oldArray = array;
            array = new PairNode[index * 2];
            for( int i = 0; i < index; i++ )
                array[i] = oldArray[i];
        }
        return array;
    }
    /* Delete min element */
    public int deleteMin( )
    {
        int x = root.element;
        if (root.leftChild == null)
            root = null;
        else
            root = combineSiblings( root.leftChild );
        return x;
    }
    
    
}

