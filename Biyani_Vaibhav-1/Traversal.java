
public class Traversal {

	public static void printLevelOrder(decodeNode root)
    {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(root, i);
    }
 


public static int height(decodeNode root)
    {
        if (root == null)
           return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);
             
            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1); 
        }
    }

public static void printGivenLevel (decodeNode root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
        	System.out.print(root.name + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }







}
