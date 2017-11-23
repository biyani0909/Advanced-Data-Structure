import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class decoded {
	
	HashMap<String, String> map;
	decodeNode root=new decodeNode(-1);
	decodeNode root1=root;
	public decoded(	HashMap<String, String> m)
	{
		map=m;
	}

	public decodeNode constructtree() {
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String value1 = entry.getValue();
			String name=entry.getKey();
			//System.out.println(value1);
			buildtree(value1,name);
			root=root1;
			
		}
		
		return root1;
		//Traversal.printLevelOrder(root1);

	
		
	}

	private void buildtree(String value1,String name) {
		for(int i=0;i<value1.length();i++)
		{
			if(value1.charAt(i)=='1')
			{
				if(root.right==null)
				{
				decodeNode newnode=new decodeNode(-1);
				root.right=newnode;
				root=newnode;
				}

				else
				{
					root=root.right;
				}
			}
			else if(value1.charAt(i)=='0' )
			{
				if(root.left==null)
				{
				decodeNode newnode=new decodeNode(-1);
				root.left=newnode;
				root=newnode;	
				}
				else
				{
					root=root.left;
				}
			}

		}
		root.name=Integer.parseInt(name);

	}

	public ArrayList<Integer> decodevalues(decodeNode resultnoderoot, String decodedstr, HashMap<String, String> map2) 
	{
		ArrayList<Integer> al=new ArrayList<>();
		decodeNode dn=resultnoderoot;
		for(int i=0;i<decodedstr.length();)
		{
			while(dn.left!=null && dn.right!=null)
			{
				if(decodedstr.charAt(i)=='0')
				{
					dn=dn.left;
				}
				else
				{
					dn=dn.right;
				}
				i++;
			}
			al.add(dn.name);
			dn=resultnoderoot;
		}
		return al;
			//		System.out.println(al);
	}	
	
	
	
}
