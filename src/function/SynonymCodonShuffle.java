package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tool.CleanBreak;
import tool.RandomizeDiArray;
import tool.StringToArray;
import core.MainMenu;
import core.WorkComplete;
import core.MainMenu.PulsType;

public class SynonymCodonShuffle implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		MainMenu.RefreshPlus(PulsType.SynonymCodonShuffleType);

		CleanBreak cleanBreak = new CleanBreak();
		cleanBreak.cleanLineBreak(path, fileName);
		int lineLength = cleanBreak.GetLineLength();
		if (!MainMenu.getPlusfield().equals("0")) {
			int shuffleTimes = Integer.valueOf(MainMenu.getPlusfield());
			genIterator(path + "tempFile.fasta", path + "SynShuff_" + fileName,
					lineLength, shuffleTimes);
			cleanBreak.deleteTempFile(path);
			new WorkComplete();
		}
	}

	public static void genIterator(String inputFileName, String outputFileName,
			int lineLength, int shuffleTimes) {

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
					String genAfterRandom = core(gene, shuffleTimes);
					bw.write(geneName + "\n");

					for (int s = 0; s < completeLineNumber; s++) {
						bw.write(genAfterRandom.substring(s * lineLength,
								(s + 1) * lineLength) + "\n");
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

	public static String core(String inputSequence, int shuffleTimes) {
		StringToArray myStringToArray = new StringToArray();
		RandomizeDiArray myRandomizeDiArray = new RandomizeDiArray();
		// 一条基因生成一个密码子数组
		String[] mygenArrays = myStringToArray.genArrays(inputSequence);
		// 将该密码子数组放入到固定顺序的二维数组
		int[][] mytwoDiArrays = myStringToArray.twoDiArrays(mygenArrays);
		// twoDiArray[1-18][]分别打乱顺序
		int[][] afterShuffle = myRandomizeDiArray.theShuffle(mytwoDiArrays,
				shuffleTimes);
		String resultSequence = myRandomizeDiArray.theShuffleOutput(
				afterShuffle, mygenArrays);
		return resultSequence;
	}
}
