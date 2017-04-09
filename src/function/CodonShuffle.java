package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tool.CleanBreak;
import tool.EasyRandomizeDiArray;
import tool.EasyStringToArray;
import core.MainMenu;
import core.WorkComplete;
import core.MainMenu.PulsType;

public class CodonShuffle implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		MainMenu.RefreshPlus(PulsType.CodonShuffleType);
		if (!MainMenu.getPlusfield().equals("0")) {
			CleanBreak cleanBreak = new CleanBreak();
			cleanBreak.cleanLineBreak(path, fileName);
			genIterator(path+"tempFile.fasta", path+"Shuff_"+fileName,
					60,Integer.valueOf(MainMenu.getPlusfield().trim()));
			cleanBreak.deleteTempFile(path);
			new WorkComplete();
		}
	}

	public static void genIterator(String inputFileName, String outputFileName,
			int lineLength,int randomTimes) {

		try {
			FileWriter writer = new FileWriter(outputFileName);
			BufferedWriter bw = new BufferedWriter(writer);
			String geneName = null;
			File file = new File(inputFileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));

			while ((geneName = reader.readLine()) != null) {
				if (geneName.contains(">")) {
					String gene = reader.readLine();
					int genLength = gene.length();
					int completeLineNumber = genLength / lineLength;
					int incompleteLineNumber = genLength % lineLength;
					String[] genAfterRandomArray = core(gene,randomTimes);
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < genAfterRandomArray.length; i++) {
						sb.append(genAfterRandomArray[i]);
					}
					String genAfterRandom = sb.toString();
					bw.write(geneName + "\n");
					for (int s = 0; s < completeLineNumber; s++) {
						String temp = genAfterRandom.substring(s * lineLength,
								(s + 1) * lineLength);
						bw.write(temp + "\n");
					}
					if (incompleteLineNumber != 0) {
						bw.write(genAfterRandom.substring(completeLineNumber
								* lineLength, genLength)
								+ "\n");
					}
				}
			}
			bw.flush();
			reader.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] core(String inputSequence,int randomTimes) {
		EasyStringToArray myStringToArray = new EasyStringToArray();
		String[] mygenArrays = myStringToArray.genArrays(inputSequence);
		EasyRandomizeDiArray myRandomizeDiArray = new EasyRandomizeDiArray();
		String[] afterShuffle = myRandomizeDiArray.theShuffle(mygenArrays,randomTimes);
		return afterShuffle;
	}
}
