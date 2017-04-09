package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tool.CleanBreak;
import core.WorkComplete;

public class Longer300 implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		CleanBreak cleanBreak = new CleanBreak();
		cleanBreak.cleanLineBreak(path, fileName);
		Get300Name(path,fileName);
		cleanBreak.deleteTempFile(path);
		new WorkComplete();
	}

	public void Get300Name(String path, String fileName){
		File file = new File(path+"tempFile.fasta");
		File writeTo = new File(path+"300_"+fileName);
		String tempString = null;
		String tempStore = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(writeTo, true));
			
			while ((tempString = reader.readLine()) != null) {
				if(tempString.contains(">")){
					tempStore = tempString;
				}else{
					if(tempString.trim().length()>=300){
						if(null!=tempString&&""!=tempString){
							bw.write(tempStore+"\n");
							for(int i =0;i<(tempString.trim().length()/60)-1;i++){
								bw.write(tempString.substring(60*i, 60*(i+1))+"\n");
							}
							bw.write(tempString.substring((tempString.length()/60)*60,tempString.length())+"\n");
						}
					}
				}
			}
			reader.close();
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
}
