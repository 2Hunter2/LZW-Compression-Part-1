import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Tester
{
	public static void main(String[] args) throws Exception
	{
		LZWCompression compressor = new LZWCompression ();

		
//		BufferedReader reader = new BufferedReader(new FileReader("lzw-file1.txt"));
//		
//		String str = null;
//		
//		while (reader.ready())
//		{
//			str = str + reader.read();
//		}
//		
//		compressor.fileCompression (str);
//		compressor.compress(str);
		
		System.out.println(compressor.decompress());
	}
}
