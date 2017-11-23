import java.io.*;
import java.util.*;
public class Fileread_write {

	public static void main(String args[]) throws IOException {
		String filename = "C:\\Uf Courses\\ADS\\project\\Project_export\\sample1\\sample_input_small.txt";
	  // String filename = "C:\\Uf Courses\\ADS\\project\\sample2\\sample2\\sample_input_large.txt";
		ArrayList<Integer> al=new ArrayList<>();
		FileReader file = new FileReader(filename);
		int[] integers = new int[1000000];
		try {
			Scanner input = new Scanner(file);
			while (input.hasNext()) {
				
				int val = input.nextInt();
				al.add(val);
				integers[val] = integers[val] + 1;
				
			}
			input.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println(al);
		//HTreeBinary htb=new HTreeBinary();
	
		//Main
		Vector<Node> result=new Vector<>();
		
		dary dr=new dary();
		result=dr.darymain(integers);
		
		/*
		MinHeap mh=new MinHeap();
		result=	mh.minheapmain(integers);
	*/		
		/*PairHeap ph =new PairHeap();
		ph.pheapmain(integers);
	
		
		*/
		
	//	System.out.println("Hell");
		
		
		//for generating .txt file code_table.txt file
		
		File file1=null;
		OutputStream os=null;
		//FileOutputStream fos=new FileOutputStream(file1);
		file1=new File("C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\code_table.txt");
		os=new FileOutputStream(file1);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
		if (!file1.exists()) {
			file1.createNewFile();
		}
	
		HashMap<Integer,String> hm=new HashMap<>();
		for(int i=0;i<result.size();i++)
		{
		  hm.put(result.get(i).name, result.get(i).huffmanValue);	
		  writer.write(result.get(i).name+" "+result.get(i).huffmanValue);
		  writer.newLine();
		  writer.flush();
		}
		
		
		
		
		///methods for generating .bin file
		
		//-------------------------------- here
		File bin=null;
		OutputStream fos=null;
		//FileOutputStream fos=new FileOutputStream(file1);
		bin=new File("C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\encoded.bin");
		fos=new FileOutputStream(bin);
		BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(fos));
		if (!bin.exists()) {
			bin.createNewFile();
		}
		
		
		String str="";
		
		for(int i=0;i<al.size();i++){
			str+=hm.get(al.get(i));
			if(str.length()%8 ==0){
			for (int j = 0; j < str.length(); j += 8) {
	        String byteString = str.substring(j, j + 8); 
	        System.out.println(byteString);
	        int parsedByte = 0xFF & Integer.parseInt(byteString, 2);
	        //System.out.println(i);
	       System.out.println(parsedByte);
	        writer1.write(parsedByte);
	        writer1.flush();
	        }
			str="";
	    }
	}
		
		System.out.println("hello");
	
		
		
		/* here u need commented
		
		//to be encoded string is appended into a string and that string would be written in the binary file
		String s="";
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<al.size();i++){
			sb.append(hm.get(al.get(i)));
			//s+=hm.get(al.get(i));
		}
		s=sb.toString();
	//System.out.println("Hello1");
		
		
		
		// the encoded string  is written into binary file
		
		String outputPath="C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\encoded.bin";
		
		try {

			BitSet bitSet = new BitSet(s.length());

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '1') {
					bitSet.set(i);
				}
			}

			OutputStream outputStream = new FileOutputStream(outputPath);
			outputStream.write(bitSet.toByteArray());
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		
//		System.out.println("Hello2");
		
		
		
		*/
		
		
		
		
		
		
//		reading from the encoded.bin
		
		//original
	/*	String decodedstr = "";
		String inputPath="C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\encoded.bin";
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
//		System.out.println("Hello after reading encoded");
	
	
	
	
*/	
		File binaryfile2;
		BufferedInputStream binaryis;
		
		binaryfile2=new File("C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\encoded.bin");
		binaryis = new BufferedInputStream(new FileInputStream(binaryfile2));
		
		int len=(int) binaryfile2.length();
		byte[] buffer=new byte[len];
        int totalBytesRead = 0;
        while(totalBytesRead < buffer.length){
            int bytesRemaining = buffer.length - totalBytesRead;
            int bytesRead = binaryis.read(buffer, totalBytesRead, bytesRemaining);
            if (bytesRead > 0){
                totalBytesRead = totalBytesRead + bytesRead;
            }
        }
        String decodedstr="";
        for(int k=0;k<buffer.length;k++)
        {
        	int x=0xFF &buffer[k];
        	decodedstr=decodedstr+Integer.toBinaryString(x);
        }
        System.out.println(decodedstr);

		
		
		
		
		
		
		
	//---------------------------------------------------------------	

	
		
		
		
	
	
	
		
	//read code table and put it into HashMap
	HashMap<String, String> map=new HashMap<>();
	
	String line = null;
	String arr[];
	String key, value;
	String codetablepath="C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\code_table.txt";
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
		System.out.println("Unable to open file '"  + "'");
	} catch (IOException ex) {
		System.out.println("Error reading file '" + "'");
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
		filefinal=new File("C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\decoded.txt");
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
	
	}
	

	}
	
