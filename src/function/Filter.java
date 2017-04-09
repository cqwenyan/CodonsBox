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

public class Filter implements Operation {

	public void workNew(String path,String fileName) {
		//如果需要有新的参数输入，应该在第一步弹出一个新的框输让我输入参数，框的类必须有个方法在我调用时传入名字。
		CleanBreak cleanBreak = new CleanBreak();
		cleanBreak.cleanLineBreak(path, fileName);
		TheFilter(path,fileName);
		cleanBreak.deleteTempFile(path);
		new WorkComplete();
	}
	public void TheFilter(String path, String fileName){
		File file = new File(path+"tempFile.fasta");
		File writeTo = new File(path+"Filter_"+fileName);
		String tempString = null;
		String tempStore = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(writeTo, true));
			
			while ((tempString = reader.readLine()) != null) {
				if(tempString.contains(">")){
					tempStore = tempString;
				}else{
					String cleanTempString = tempString.trim();
					int TempStringLenth = cleanTempString.length();
					if((TempStringLenth%3==0)&&((cleanTempString.substring(0, 3).toUpperCase()).equals("ATG")||
							(cleanTempString.substring(0, 3).toUpperCase()).equals("AUG"))
							&&((cleanTempString.substring(TempStringLenth-3, TempStringLenth).toUpperCase()).equals("ATG")
									||(cleanTempString.substring(TempStringLenth-3, TempStringLenth).toUpperCase()).equals("TAA")
									||(cleanTempString.substring(TempStringLenth-3, TempStringLenth).toUpperCase()).equals("TAG")
									||(cleanTempString.substring(TempStringLenth-3, TempStringLenth).toUpperCase()).equals("TGA")
									||(cleanTempString.substring(TempStringLenth-3, TempStringLenth).toUpperCase()).equals("UAA")
									||(cleanTempString.substring(TempStringLenth-3, TempStringLenth).toUpperCase()).equals("UAG")
									||(cleanTempString.substring(TempStringLenth-3, TempStringLenth).toUpperCase()).equals("UGA"))){
						if(null!=cleanTempString&&""!=cleanTempString){
							bw.write(tempStore+"\n");
							for(int i =0;i<(TempStringLenth/60)-1;i++){
								bw.write(cleanTempString.substring(60*i, 60*(i+1))+"\n");
							}
							bw.write(cleanTempString.substring((TempStringLenth/60)*60,TempStringLenth)+"\n");
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
