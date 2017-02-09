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
		// TODO Auto-generated method stub
		MainMenu.RefreshPlus(PulsType.CodonShuffleType);
		if (!MainMenu.getPlusfield().equals("0")) {
			CleanBreak cleanBreak = new CleanBreak();
			cleanBreak.cleanLineBreak(path, fileName);
			genIterator(path+"tempFile.fasta", (path+fileName).substring(0, (path+fileName).indexOf(".")) + "_easyRandom.fasta",
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

	public static String[] core(String inputSequence,int randomTimes) {
		EasyStringToArray myStringToArray = new EasyStringToArray();
		// String inputSequence = "CGTTTATTATTACGCAGA";
		String[] mygenArrays = myStringToArray.genArrays(inputSequence);
		EasyRandomizeDiArray myRandomizeDiArray = new EasyRandomizeDiArray();

		String[] afterShuffle = myRandomizeDiArray.theShuffle(mygenArrays,randomTimes);

		return afterShuffle;
	}

	// clean all the line break in the FASTA ,and if length is longer than
	// 65534,print out error.
//	public static int cleanLineBreak(String inputFileName) {
//		File file = new File(inputFileName);
//		int sensor = 0;
//		int lineLength = 0;
//		try {
//			FileWriter rwriter = new FileWriter("tempFile.fasta");
//			BufferedWriter bw = new BufferedWriter(rwriter);
//			String tempStringUpper = null;
//			BufferedReader reader = new BufferedReader(new FileReader(file));
//
//			// find the first line contain ">".
//			tempStringUpper = reader.readLine();
//			while (tempStringUpper.contains(">") != true) {
//				tempStringUpper = reader.readLine();
//			}
//			bw.write(tempStringUpper + "\n");
//			// end
//
//			while ((tempStringUpper = reader.readLine()) != null) {
//				if (sensor == 0) {
//					lineLength = tempStringUpper.length();
//					sensor++;
//				}
//				if (tempStringUpper.contains(">")) {
//					bw.write("\n" + tempStringUpper + "\n");
//				} else {
//					if (tempStringUpper.length() >= 65534) {
//						System.out
//								.println("warning,some gene is too long in your FASTA file,and we lost some base of them");
//					}
//					bw.write(tempStringUpper);
//				}
//			}
//			bw.flush();
//			reader.close();
//			bw.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return lineLength;
//	}
}
