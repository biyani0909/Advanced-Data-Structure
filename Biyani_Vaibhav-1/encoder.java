
	import java.io.*;
	import java.util.ArrayList;
	import java.util.Scanner;
	import java.util.*;
	
public class encoder {
	

		public static void main(String args[]) throws IOException {
			String filename = args[0]; //"C:\\Uf Courses\\ADS\\project\\Project_export\\sample1\\sample_input_small.txt";
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
			file1=new File("code_table.txt");
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
			
			
			/*File bin=null;
			OutputStream fos=null;
			//FileOutputStream fos=new FileOutputStream(file1);
			bin=new File("C:\\Users\\Biyani\\ADS_WorkSpace\\Huffman_Coding\\src\\encoded.bin");
			fos=new FileOutputStream(bin);
			BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(fos));
			if (!bin.exists()) {
				bin.createNewFile();
			}
			*/
			
		/*	String str="";
			
			for(int i=0;i<al.size();i++){
				str+=hm.get(al.get(i));
				if(str.length()%8 ==0){
				for (int j = 0; j < str.length(); j += 8) {
		        String byteString = str.substring(j, j + 8); 
		     //   System.out.println(byteString);
		        int parsedByte = 0xFF & Integer.parseInt(byteString, 2);
		        //System.out.println(i);
		     //   System.out.println(parsedByte);
		        writer1.write(parsedByte);
		        writer1.flush();
		        }
				str="";
		    }
		}
			*/
		//	System.out.println("hello");
			
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
			
			String outputPath="encoded.bin";
			
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

			
			
			
		//	System.out.println("Hello2");
			
			
			
			
			
		}
	

}
