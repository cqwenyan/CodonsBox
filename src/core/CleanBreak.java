package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CleanBreak {
	public static void cleanLineBreak(String path, String inputFileName) {
		File file = new File(path + inputFileName);
		BufferedReader reader = null;
		FileWriter rwriter = null;
		BufferedWriter bw = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			rwriter = new FileWriter(path + "tempFile.fasta", true);
			bw = new BufferedWriter(rwriter);
			String tempString = null;
			tempString = reader.readLine();
			bw.write(tempString + "\n");
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains(">")) {
					bw.write("\n" + tempString + "\n");
				} else {
					bw.write(tempString);
				}
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteTempFile(String inputFileName, String path){
		File file = new File(path + inputFileName);
		if(file.exists()){
			file.delete();
		}
	}
}
