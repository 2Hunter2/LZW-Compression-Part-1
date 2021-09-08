import java.util.*;
import java.io.*;

public class LZWCompression {
	ArrayList<String> arr = new ArrayList<String>();
	
	public LZWCompression ()
	{
	for (int k = 0; k < 255; k ++)
	{
		arr.add ((char)k + "");
		
	}
	}
	
}