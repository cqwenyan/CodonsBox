package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import tool.CleanBreak;
import core.WorkComplete;

public class PR2Plot implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		CleanBreak cleanBreak = new CleanBreak();
		cleanBreak.cleanLineBreak(path, fileName);
		PR2PlotFactory(fileName,path);
		cleanBreak.deleteTempFile(path);
			new WorkComplete();
	}
	public static void PR2PlotFactory(String inputfileName,String path) {
		File file = new File(path+"tempFile.fasta");
		BufferedReader reader = null;
		BufferedWriter bw3 = null;
		try {
			String tempString = null;
			FileWriter writergc3 = new FileWriter(path+"PR2_"+inputfileName, true);
			bw3 = new BufferedWriter(writergc3);
			//题目
			bw3.write("geneID\t[G3/(G3+C3)]\t[A3/(A3+U3)]\n");
			reader = new BufferedReader(new FileReader(file));
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains(">")) {
					bw3.write(tempString + "\t");
				} else {
					int a3 = 0;
					int t3 = 0;
					int g3 = 0;
					int c3 = 0;
					int length = tempString.length() / 3;
					for (int j = 0; j < length; j++) {
						String tempCodon = tempString.substring(3 * j,
								3 * j + 3);
							if ("C".equals(tempCodon.substring(2, 3))){
								c3++;
							}else if("G".equals(tempCodon.substring(2, 3))){
								g3++;
							}else if("A".equals(tempCodon.substring(2, 3))){
								a3++;
							}else if("T".equals(tempCodon.substring(2, 3))){
								t3++;
							}
					}
					//[G3/(G3+C3)]为x坐标，[A3/(A3+U3)] 为y坐标
					bw3.write(String.valueOf((float)g3/(g3+c3))+"\t"+String.valueOf((float)a3/(a3+t3))+"\n");
				}
			}
			bw3.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				bw3.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
