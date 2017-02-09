package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tool.CleanBreak;
import core.WorkComplete;

public class NeutralityPlot implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		CleanBreak cleanBreak = new CleanBreak();
		cleanBreak.cleanLineBreak(path, fileName);
		NeutralityFactory("tempFile.fasta",path);
		cleanBreak.deleteTempFile(path);
		new WorkComplete();

	}
	public static void NeutralityFactory(String inputfileName,String path) {
		File file = new File(path+inputfileName);
		BufferedReader reader = null;

		BufferedWriter bw3 = null;
		try {
			String tempString = null;
			
			FileWriter writergc3 = new FileWriter(path+"PR2bias.fasta", true);
			bw3 = new BufferedWriter(writergc3);
			//题目
			bw3.write("geneID\tGC12\tGC3\n");
			reader = new BufferedReader(new FileReader(file));
			while ((tempString = reader.readLine()) != null) {
			
				if (tempString.contains(">")) {
					
					bw3.write(tempString + "\t");
				} else {
					int gc3 = 0;
					int gc12 = 0;
					int oneTwoPostiNumber = 0;
					int threePostiNumber = 0;
					int length = tempString.length() / 3;
					
					for (int j = 0; j < length; j++) {
						String tempCodon = tempString.substring(3 * j,
								3 * j + 3).toUpperCase();
						if ((!"ATG".equals(tempCodon))&&(!"TGG".equals(tempCodon))&&
								(!"AUG".equals(tempCodon))&&(!"UGG".equals(tempCodon))){
							oneTwoPostiNumber++;
							String oneTemp = tempCodon.substring(0, 1);
							String twoTemp = tempCodon.substring(1, 2);
							if ("C".equals(oneTemp)){
								gc12++;
							}else if("G".equals(oneTemp)){
								gc12++;
							}else if("C".equals(twoTemp)){
								gc12++;
							}else if("G".equals(twoTemp)){
								gc12++;
							}
							if((!"TAA".equals(tempCodon))&&(!"UAA".equals(tempCodon))&&
									(!"TAG".equals(tempCodon))&&(!"UAG".equals(tempCodon))&&
									(!"TGA".equals(tempCodon))&&(!"UGA".equals(tempCodon))&&
									(!"ATT".equals(tempCodon))&&(!"AUU".equals(tempCodon))&&
									(!"ATC".equals(tempCodon))&&(!"AUC".equals(tempCodon))&&
									(!"ATA".equals(tempCodon))&&(!"AUA".equals(tempCodon))){
								threePostiNumber++;
								String threeTemp = tempCodon.substring(2, 3);
								if ("C".equals(threeTemp)){
									gc3++;
								}else if("G".equals(threeTemp)){
									gc3++;
								}
							}
						}
					}
					bw3.write(String.valueOf(((float)gc12)/(oneTwoPostiNumber*2))+"\t"+String.valueOf(((float)gc3)/threePostiNumber)+"\n");
				}
			}

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
