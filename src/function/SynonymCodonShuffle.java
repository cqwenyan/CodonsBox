package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tool.RandomizeDiArray;
import tool.StringToArray;
import core.MainMenu;
import core.WorkComplete;
import core.MainMenu.PulsType;

public class SynonymCodonShuffle implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		// TODO Auto-generated method stub
		MainMenu.RefreshPlus(PulsType.SynonymCodonShuffleType);

		int lineLength = cleanLineBreak(path, fileName);
		if (!MainMenu.getPlusfield().equals("0")) {
			int shuffleTimes = Integer.valueOf(MainMenu.getPlusfield());
			genIterator(path + "tempFile.fasta", path + fileName
					+ "_random.fasta", lineLength,shuffleTimes);

			File f = new File(path + "tempFile.fasta"); // 输入要删除的文件位置
			if (f.exists())
				f.delete();
			new WorkComplete();

		}
	}

	public static void genIterator(String inputFileName, String outputFileName,
			int lineLength,int shuffleTimes) {

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
					String genAfterRandom = core(gene,shuffleTimes);
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

					// bw.write(genAfterRandom + "\n");
				}
			}
			bw.flush();
			reader.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String core(String inputSequence,int shuffleTimes) {
		StringToArray myStringToArray = new StringToArray();
		RandomizeDiArray myRandomizeDiArray = new RandomizeDiArray();
		// String inputSequence = "CGTTTATTATTACGCAGA";
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

	public static int cleanLineBreak(String path, String inputFileName) {
		File file = new File(path + inputFileName);
		int sensor = 0;
		int lineLength = 0;
		try {
			FileWriter rwriter = new FileWriter(path + "tempFile.fasta");
			BufferedWriter bw = new BufferedWriter(rwriter);
			String tempStringUpper = null;
			BufferedReader reader = new BufferedReader(new FileReader(file));

			// find the first line contain ">".
			tempStringUpper = reader.readLine();
			while (tempStringUpper.contains(">") != true) {
				tempStringUpper = reader.readLine();
			}
			bw.write(tempStringUpper + "\n");
			// end

			while ((tempStringUpper = reader.readLine()) != null) {
				if (sensor == 0) {
					lineLength = tempStringUpper.length();
					sensor++;
				}
				if (tempStringUpper.contains(">")) {
					bw.write("\n" + tempStringUpper + "\n");
				} else {
					if (tempStringUpper.length() >= 65534) {
						System.out
								.println("warning,some gene is too long in your FASTA file,and we lost some base of them");
					}
					bw.write(tempStringUpper);
				}
			}
			bw.flush();
			reader.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineLength;
	}
}
