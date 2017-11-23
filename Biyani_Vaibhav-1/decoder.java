
	import java.io.*;
import java.util.*;
	
public class decoder {

 public static void main(String args[]) throws IOException{
	


		String decodedstr = "";
		String inputPath=args[0];
		try {
			int length = (int) (new File(inputPath).length());
			byte buffer[] = new byte[length];
			InputStream inputstream = new FileInputStream(inputPath);

			inputstream.read(buffer);
			BitSet set = BitSet.valueOf(buffer);

			StringBuffer sbuff = new StringBuffer();

			for (int i = 0; i <= set.length(); i++) {
				if (set.get(i)) {
					sbuff.append("1");
				} else {
					sbuff.append("0");
				}
			}
			
			decodedstr = sbuff.toString();
		//	return decodedstr;
			inputstream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(decodedstr);
		//System.out.println("Hello after reading encoded");
	
	

	 
	 //read code table and put it into HashMap
		HashMap<String, String> map=new HashMap<>();
		
		String line = null;
		String arr[];
		String key, value;
		String codetablepath=args[1];//"C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\code_table.txt";
		try {
			FileReader fileReader = new FileReader(codetablepath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				arr = line.split(" ");
				key = arr[0];
				value = arr[1];
				map.put(key, value);
			}

			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file " );
		} catch (IOException ex) {
			System.out.println("Error reading file " );
		}
		
		

		//System.out.println("Hello after reading HAshmap");

		
		/*for (Map.Entry<String, String> entry : map.entrySet()) {
		    String key1 = entry.getKey().toString();
		    String value1 = entry.getValue();
		  //  System.out.println("key, " + key1 + " value " + value1);
		}
	*/
		
		//decode functions
		
		ArrayList<Integer> finalinputvalues=new ArrayList<>();
		decoded dm=new decoded(map);
		decodeNode resultnoderoot=dm.constructtree();
		finalinputvalues=dm.decodevalues(resultnoderoot,decodedstr,map);	
			
			
			//output decoder in decode.txt
		
			File filefinal=null;
			OutputStream finalos=null;
			//FileOutputStream fos=new FileOutputStream(file1);
			filefinal=new File("decoded.txt");
			finalos=new FileOutputStream(filefinal);
			BufferedWriter writerfinal = new BufferedWriter(new OutputStreamWriter(finalos));
			if (!filefinal.exists()) {
				filefinal.createNewFile();
			}
			for(int i=0;i<finalinputvalues.size();i++)
			{
			//  hm.put(result.get(i).name, result.get(i).huffmanValue);	
			  writerfinal.write(finalinputvalues.get(i).toString());
			  writerfinal.newLine();
			  writerfinal.flush();
			}
			writerfinal.close();
		

//	 System.out.println("done");
	 
	 
}
}
