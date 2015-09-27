package bartana;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class Comparator {
	
	public static String tempPath = "";

	public static Double compareBlackAndWhitePixelDifference(String file1, String file2) throws IOException, InterruptedException{
		
		Double ret = Double.MAX_VALUE;
		
		String bWfile1 = convertToBlackAndWhite(file1);
		String bWfile2 = convertToBlackAndWhite(file2);
		
		String[] command = {"compare", "-metric", "AE", "-dissimilarity-threshold", "1", bWfile1, bWfile2, "/dev/null"};
		///opt/imagemagick-6.9/bin/compare -metric phash 2.png 5.png null

		Process process = Runtime.getRuntime().exec(command);
		
		InputStream stdout = process.getErrorStream();
		BufferedReader reader = new BufferedReader (new InputStreamReader(stdout));
		
		String line;
		while ((line = reader.readLine ()) != null) {
			ret = new Double(line);
		}
		
		
		process.waitFor();
		
		Files.delete(new File(bWfile1).toPath());
		Files.delete(new File(bWfile2).toPath());
		
		return ret;
	}
	
	
	public static String convertToBlackAndWhite(String file) throws IOException, InterruptedException{
		
		Integer i = (int) (Math.random() * Integer.MAX_VALUE);
		
		String[] command = {"convert", file, "-threshold", "50%", tempPath + i.toString()};
		
		Process process = Runtime.getRuntime().exec(command);
		
		process.waitFor();
		
		return tempPath + i;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println(compareBlackAndWhitePixelDifference("images/1.png", "images/2.png")); 
		
	}
}
