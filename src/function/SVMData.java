package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tool.CleanBreak;
import tool.SVMOnlyCodonMaker;
import core.MainMenu;
import core.WorkComplete;
import core.MainMenu.PulsType;

public class SVMData implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		MainMenu.RefreshPlus(PulsType.SVMLibType);
		
		
		if(!MainMenu.getPlusfield().equals("0")){
			CleanBreak cleanBreak = new CleanBreak();
			cleanBreak.cleanLineBreak(path, fileName);
			genIterator(fileName,path,Integer.valueOf(MainMenu.getPlusfield()));
			cleanBreak.deleteTempFile(path);
			new WorkComplete();
		}

	}
	
	private static void genIterator(String inputFileName, String path,
			int classNumber) {
		SVMOnlyCodonMaker myStringToArray = SVMOnlyCodonMaker.getStringToArray();
		try {
			FileWriter writer = new FileWriter(path+inputFileName.substring(0, inputFileName.indexOf("."))+"_SVM.svm");
			BufferedWriter bw = new BufferedWriter(writer);

			String geneName = null;

			File file = new File(path+"tempFile.fasta");
			BufferedReader reader = new BufferedReader(new FileReader(file));

			while ((geneName = reader.readLine()) != null) {
				if (geneName.contains(">")) {
					String gene = reader.readLine();
					myStringToArray.initArrays();
					String[] mygenArrays = myStringToArray.genArrays(gene);
					
					bw.write(myStringToArray.resultArrays(mygenArrays,classNumber) + "\n");
				}
			}
			bw.flush();
			reader.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
