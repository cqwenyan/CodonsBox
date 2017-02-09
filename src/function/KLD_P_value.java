package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;



import tool.KLDRandoms;
import core.MainMenu;
import core.WorkComplete;
import core.MainMenu.PulsType;

public class KLD_P_value implements Operation {

	final public static String[] Code_table = { "TTT", "TTC", "TTA", "TTG",
		"CTT", "CTC", "CTA", "CTG", "TCT", "TCC", "TCA", "TCG", "AGT",
		"AGC", "TAT", "TAC", "TGT", "TGC", "CCT", "CCC", "CCA", "CCG",
		"CAT", "CAC", "CAA", "CAG", "CGT", "CGC", "CGA", "CGG", "AGA",
		"AGG", "ATT", "ATC", "ATA", "ACT", "ACC", "ACA", "ACG", "AAT",
		"AAC", "AAA", "AAG", "GAA", "GAG", "GTT", "GTC", "GTA", "GTG",
		"GCT", "GCC", "GCA", "GCG", "GAT", "GAC", "GGT", "GGC", "GGA",
		"GGG" };
	final public static String[] AA_table = { "Phe", "Ser", "Tyr", "Cys",
			"Leu", "Pro", "His", "Gin", "Arg", "Ile", "Thr", "Asn", "Lys",
			"Val", "Ala", "Asp", "Glu", "Gly" };

	public static Map<String, String> synAminoAcidBox() {
		HashMap<String, String> synAminoAcidTable = new HashMap<String, String>();
		// 除去起始ATG，除去终止TAA,TAG,TGA，除去单个TGG,Trp
		synAminoAcidTable.put("TTT", "Phe");
		synAminoAcidTable.put("TTC", "Phe");
		synAminoAcidTable.put("TCT", "Ser");
		synAminoAcidTable.put("TCC", "Ser");
		synAminoAcidTable.put("TCA", "Ser");
		synAminoAcidTable.put("TCG", "Ser");
		synAminoAcidTable.put("TAT", "Tyr");
		synAminoAcidTable.put("TAC", "Tyr");
		synAminoAcidTable.put("TGT", "Cys");
		synAminoAcidTable.put("TGC", "Cys");
		synAminoAcidTable.put("TTA", "Leu");
		synAminoAcidTable.put("TTG", "Leu");
		synAminoAcidTable.put("CTT", "Leu");
		synAminoAcidTable.put("CTC", "Leu");
		synAminoAcidTable.put("CTA", "Leu");
		synAminoAcidTable.put("CTG", "Leu");
		synAminoAcidTable.put("CCT", "Pro");
		synAminoAcidTable.put("CCC", "Pro");
		synAminoAcidTable.put("CCA", "Pro");
		synAminoAcidTable.put("CCG", "Pro");
		synAminoAcidTable.put("CAT", "His");
		synAminoAcidTable.put("CAC", "His");
		synAminoAcidTable.put("CAA", "Gin");
		synAminoAcidTable.put("CAG", "Gin");
		synAminoAcidTable.put("CGT", "Arg");
		synAminoAcidTable.put("CGC", "Arg");
		synAminoAcidTable.put("CGA", "Arg");
		synAminoAcidTable.put("CGG", "Arg");
		synAminoAcidTable.put("ATT", "Ile");
		synAminoAcidTable.put("ATC", "Ile");
		synAminoAcidTable.put("ATA", "Ile");
		synAminoAcidTable.put("ACT", "Thr");
		synAminoAcidTable.put("ACC", "Thr");
		synAminoAcidTable.put("ACA", "Thr");
		synAminoAcidTable.put("ACG", "Thr");
		synAminoAcidTable.put("AAT", "Asn");
		synAminoAcidTable.put("AAC", "Asn");
		synAminoAcidTable.put("AAA", "Lys");
		synAminoAcidTable.put("AAG", "Lys");
		synAminoAcidTable.put("AGT", "Ser");
		synAminoAcidTable.put("AGC", "Ser");
		synAminoAcidTable.put("AGA", "Arg");
		synAminoAcidTable.put("AGG", "Arg");
		synAminoAcidTable.put("GTT", "Val");
		synAminoAcidTable.put("GTC", "Val");
		synAminoAcidTable.put("GTA", "Val");
		synAminoAcidTable.put("GTG", "Val");
		synAminoAcidTable.put("GCT", "Ala");
		synAminoAcidTable.put("GCC", "Ala");
		synAminoAcidTable.put("GCA", "Ala");
		synAminoAcidTable.put("GCG", "Ala");
		synAminoAcidTable.put("GAT", "Asp");
		synAminoAcidTable.put("GAC", "Asp");
		synAminoAcidTable.put("GAA", "Glu");
		synAminoAcidTable.put("GAG", "Glu");
		synAminoAcidTable.put("GGT", "Gly");
		synAminoAcidTable.put("GGC", "Gly");
		synAminoAcidTable.put("GGA", "Gly");
		synAminoAcidTable.put("GGG", "Gly");
		return synAminoAcidTable;
	}

	
	@Override
	public void workNew(String path, String fileName) {
		MainMenu.RefreshPlus(PulsType.KLD_PvalueTpye);
		if (!MainMenu.getPlusfield().equals("0")) {
			String [] kldtemp =MainMenu.getPlusfield().trim().split("=");
			noSpaceTempFile(fileName, path);
			int binSize = Integer.valueOf(kldtemp[0]);
			int binNumber = Integer.valueOf(kldtemp[1]);
			KLD_Builder(binSize, binNumber, path, "tempNoSpace.fasta", true);
			KLD_Pvalue(binSize, binNumber, path, fileName, Integer.valueOf(kldtemp[2]));

			new WorkComplete();
		}
	}

	// coreKLDPartA和coreKLDPartB生成的数据，调用KLD的公式。isOutputFile为真输出原始KLD.fasta，为假只返回结果数组不生成文件
		private static BigDecimal[][] KLD_Builder(int binSize, int binNumber,
				String path, String inputFileName, boolean isOutputFile) {
			double[] totalCodonF = coreKLDPartB(synAminoAcidBox(), path);
			double[][] codonF = coreKLDPartA(binSize, binNumber, synAminoAcidBox(),
					path, inputFileName);
			BigDecimal[][] result = new BigDecimal[Code_table.length][binNumber];

			if (isOutputFile) {
				FileWriter writer;
				try {
					writer = new FileWriter(path + "KLD.fasta");
					BufferedWriter bw = new BufferedWriter(writer);
					for (int j = 0; j < Code_table.length; j++) {
						for (int i = 0; i < binNumber; i++) {
							// System.out.println("codonF[j][i]"+codonF[j][i]);
							// System.out.println("totalCodonF[j]"+totalCodonF[j]);
							double partAB = Math.pow(codonF[j][i] / totalCodonF[j],
									1000 * codonF[j][i]);
							// System.out.println("partAB"+partAB);
							double x = Math.log(partAB);
							// System.out.println("x"+x);
							result[j][i] = new BigDecimal(Double.toString(x))
									.setScale(3, BigDecimal.ROUND_HALF_UP);
							bw.write(result[j][i].toString() + "\t");
						}
						bw.write("\n");
					}
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				for (int j = 0; j < Code_table.length; j++) {
					for (int i = 0; i < binNumber; i++) {
						// System.out.println("codonF[j][i]"+codonF[j][i]);
						// System.out.println("totalCodonF[j]"+totalCodonF[j]);
						double partAB = Math.pow(codonF[j][i] / totalCodonF[j],
								1000 * codonF[j][i]);
						// System.out.println("partAB"+partAB);
						double x = Math.log(partAB);
						// System.out.println("x"+x);
						result[j][i] = new BigDecimal(Double.toString(x)).setScale(
								3, BigDecimal.ROUND_HALF_UP);
					}

				}
			}

			return result;
		}

		private static void KLD_Pvalue(int binSize, int binNumber, String path,
				String inputFileName, int repeat) {
			KLDRandoms myRandoms = new KLDRandoms();

			BigDecimal[][] kldResult = KLD_Builder(binSize, binNumber, path,
					"tempNoSpace.fasta", true);
			int lineLength = myRandoms.cleanLineBreak(path, inputFileName);
			float[][] pValue = new float[Code_table.length][binNumber];
			for (int i = 0; i < repeat; i++) {

				File f = new File(path + "random_" + inputFileName);
				if (f.exists())
					f.delete();

				myRandoms.genIterator(path + "tempFile.fasta", path + "random_"
						+ inputFileName, lineLength);

				BigDecimal[][] tempKldResult = KLD_Builder(binSize, binNumber,
						path, "random_" + inputFileName, false);
				for (int j = 0; j < Code_table.length; j++) {
					for (int k = 0; k < binNumber; k++) {
						if (kldResult[j][k].abs().doubleValue() < tempKldResult[j][k]
								.abs().doubleValue()) {
							pValue[j][k]++;
						}
						// System.out.println("kldResult[j][k].abs().doubleValue()"
						// + kldResult[j][k].abs().doubleValue());
						// System.out
						// .println("tempKldResult[j][k].abs().doubleValue()"
						// + tempKldResult[j][k].abs().doubleValue());
						// System.out.println("pValue[j][k]:"+pValue[j][k]+" j: "+j+" k:"+k);
					}
				}
			}

			// 输出结果
			FileWriter writer;
			try {
				writer = new FileWriter(path + "KLD_PValue.fasta");
				BufferedWriter bw = new BufferedWriter(writer);

				for (int j = 0; j < Code_table.length; j++) {
					for (int k = 0; k < binNumber; k++) {
						pValue[j][k] /= (float) repeat;
						// System.out.println("pValue[j][k]:"+pValue[j][k]);
						bw.write(String.valueOf(pValue[j][k]) + "\t");
					}
					bw.write("\n");
				}
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// 生成无换行临时文件tempNoSpace.fasta
		private static void noSpaceTempFile(String inputFileName, String path) {

			File file = new File(path + inputFileName);
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				FileWriter rwriter = new FileWriter(path + "tempNoSpace.fasta");
				BufferedWriter bw = new BufferedWriter(rwriter);
				String tempString;
				tempString = reader.readLine();
				bw.write(tempString + "\n");
				while ((tempString = reader.readLine()) != null) {
					if (tempString.contains(">")) {
						bw.write("\n" + tempString + "\n");
					} else {
						bw.write(tempString);
					}
				}
				bw.close();
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// KLD计算的分子
		private static double[][] coreKLDPartA(int binSize, int binNumber,
				Map<String, String> synAminoAcidTable, String path,
				String inputFileName) {
			int theCodonCounter = 0;
			int synCodonCounter = 0;
			int[][] countCodon = new int[59][binNumber];
			int[][] countAA = new int[19][binNumber];
			double[][] partA = new double[59][binNumber];

			File file = new File(path + inputFileName);

			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String tempString0;
				while ((tempString0 = reader.readLine()) != null) {
					if (!tempString0.contains(">")) {
						for (int i = 0; i < binNumber; i++) {// 一条基因中的N个bin

							String tempBinSequence = tempString0.substring(3 * i
									* binSize, 3 * (i + 1) * binSize);

							for (int j = 0; j < binSize - 1; j++) {// 遍历一个bin
								for (int k = 0; k < Code_table.length; k++) {
									String tempCodon = tempBinSequence.substring(
											j * 3, (j + 1) * 3);
									if (Code_table[k].equals(tempCodon)) {
										countCodon[k][i]++;
										String tempAA = synAminoAcidTable
												.get(tempCodon);
										for (int m = 0; m < AA_table.length; m++) {
											if (tempAA.equals(AA_table[m])) {
												countAA[m][i]++;
											}
										}
									}
								}
							}
						}
					}
				}
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (int j = 0; j < binNumber; j++) {
				for (int i = 0; i < 59; i++) {
					String tempAA = synAminoAcidTable.get(Code_table[i]);
					for (int k = 0; k < AA_table.length; k++) {
						if (tempAA.equals(AA_table[k])) {
							partA[i][j] = ((double) countCodon[i][j])
									/ countAA[k][j];
						}
					}
				}
			}

			// partA = (((double) theCodonCounter) / synCodonCounter);
			return partA;
		}

		// 算出qi,j.即为密码子在所有基因中的比例，返回一个数组， KLD计算的分母
		private static double[] coreKLDPartB(Map<String, String> synAminoAcidTable,
				String path) {
			int[] countCodon = new int[59];
			int[] countAA = new int[19];
			int oneGenomeCodon = 0;
			int synOneGenomeCodon = 0;
			double[] partB = new double[59];

			BufferedReader reader = null;
			File file = new File(path + "tempNoSpace.fasta");
			try {
				String tempString;
				reader = new BufferedReader(new FileReader(file));
				while ((tempString = reader.readLine()) != null) {
					if (!tempString.contains(">")) {
						for (int i = 0; i < tempString.length() / 3; i = i + 3) {
							for (int j = 0; j < Code_table.length; j++) {
								String aTempString = Code_table[j];
								if (tempString.substring(i, i + 3).equals(
										aTempString)) {
									countCodon[j]++;
									for (int k = 0; k < AA_table.length; k++) {
										String aTempString1 = AA_table[k];
										if (aTempString1.equals(synAminoAcidTable
												.get(aTempString))) {
											countAA[k]++;
										}

									}
								}
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < Code_table.length; i++) {
				for (int j = 0; j < AA_table.length; j++) {
					if ((synAminoAcidTable.get(Code_table[i])).equals(AA_table[j])) {
						partB[i] = ((double) countCodon[i]) / countAA[j];
					}
				}
			}
			// System.out.println("+++" + codon + "oneGenomeCodon:" +
			// oneGenomeCodon);
			// System.out.println("+++" + codon + "synOneGenomeCodon:"
			// + synOneGenomeCodon);
			return partB;
		}

}
