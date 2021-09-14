
import java.util.*;
import java.io.*;
import java.nio.file.Files;

public class LZWCompression{
	HashMap<String,Integer> hMap = new HashMap<String,Integer>();
	ArrayList<Byte> theArrList = new ArrayList<Byte>();
	
	public LZWCompression()
	{
		for(int k = 0 ; k < 255; k++)
		{
			hMap.put("" + (char)(k),k);
		}
	}
	
	public void fileCompression (String str) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(str));
		File f = new File(str + "good");
		
		String str2 = "";
		try
		{
			String str3 = reader.readLine();
			
			while(str3 != null){
				str2 = str2 + str3;
				str3 = reader.readLine();
			}
			
			try(FileOutputStream fOutput = new FileOutputStream(f))
			{
				fOutput.write (this.compress(str2));
			}
			reader.close();
			
			System.out.println("Correct!");
			
		}
		catch(FileNotFoundException exception)
		{
			exception.printStackTrace();
		}
		catch(IOException exception2)
		{
			exception2.printStackTrace();
		}
	}
	
	public byte[] compress (String str)
	{
		ArrayList<Byte> arrList = new ArrayList<Byte>();
		String s1 = "";
		
		for(int k = 0; str != null && k < str.length(); k++)
		{
			char ch = str.charAt(k);
			
			if(hMap.containsKey(s1+ch))
			{
				s1 = s1 + ch;
			}
			
			else
			{
				arrList.add((new Integer(hMap.get(s1)).byteValue()));
				
				arrList.add((new Integer(hMap.get(s1)/256)).byteValue());
				
				hMap.put(s1 + ch, hMap.size() + 1);
				s1= "" + ch;
			}
		}
		
		if(s1 != "")
		{
			arrList.add((new Integer(hMap.get(s1)).byteValue()));
			
			arrList.add((new Integer(hMap.get(s1)/256)).byteValue());
		
		}
		
		byte[] arr = new byte[arrList.size()];
		
		for(int k = 0; k < arrList.size(); k++)
		{
			arr[k] = arrList.get(k);
		}
		
		theArrList = arrList;
		return(arr);
	}
	
	public String decompress () throws Exception
	{
		ArrayList<Integer> compressed = new ArrayList<Integer>();
		
		for (int i = 0; i < theArrList.size(); i++)
		{
			compressed.add(theArrList.get(i).intValue());
		}
		
		Map<Integer, String> dictionary = new HashMap<Integer, String>();
		
		int dictionarySize = 256;
		
		for (int i = 0; i < dictionarySize; i++)
		{
			Character theChar = (char)i;
			String theString = "" + theChar;
			dictionary.put(i, theString);
		}
		
		int firstCompressedInt = compressed.get(0);
		Character firstCompressedChar = (char)firstCompressedInt;
		
		compressed.remove(0);

		String currentLetters = "" + firstCompressedChar;
		
		String output = currentLetters;
		
		for (int i = 0; i < compressed.size(); i++)
		{
			int letters = compressed.get(i);
					
			String dictionaryEntry;

			if (dictionary.containsKey(letters))
			{
				dictionaryEntry = dictionary.get(letters);
			}
			else
			{
				dictionaryEntry = currentLetters + currentLetters.charAt(0);
			}
			
			output = output + dictionaryEntry;
			
			dictionary.put(dictionarySize++, currentLetters + dictionaryEntry.charAt(0));
			
			currentLetters = dictionaryEntry;
		}
		
		return (output);
	}
}