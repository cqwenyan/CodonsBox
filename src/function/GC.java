package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import tool.CleanBreak;
import core.WorkComplete;

public class GC implements Operation {

	@Override
	public void workNew(String path, String fileName) {

		CleanBreak cleanBreak = new CleanBreak();
		cleanBreak.cleanLineBreak(path, fileName);
		CountGC(path, fileName);
		cleanBreak.deleteTempFile(path);
		new WorkComplete();
	}

	private void CountGC(String path, String fileName) {
		File file = new File(path + "tempFile.fasta");
		BufferedReader reader = null;
		BufferedWriter bwgc = null;
		BufferedWriter bwgc1 = null;
		BufferedWriter bwgc2 = null;
		BufferedWriter bwgc12 = null;
		BufferedWriter bwgc3 = null;
		try {
			String tempString = null;
			FileWriter writergc = new FileWriter(path + "gc.fasta", true);
			bwgc = new BufferedWriter(writergc);
			FileWriter writergc1 = new FileWriter(path + "gc1.fasta", true);
			bwgc1 = new BufferedWriter(writergc1);
			FileWriter writergc2 = new FileWriter(path + "gc2.fasta", true);
			bwgc2 = new BufferedWriter(writergc2);
			FileWriter writergc12 = new FileWriter(path + "gc12.fasta", true);
			bwgc12 = new BufferedWriter(writergc12);
			FileWriter writergc3 = new FileWriter(path + "gc3.fasta", true);
			bwgc3 = new BufferedWriter(writergc3);
			reader = new BufferedReader(new FileReader(file));
			while ((tempString = reader.readLine()) != null) {
				float gc = 0f;
				float gc1 = 0f;
				int Totail = 0;
				float gc2 = 0f;
				float gc3 = 0f;
				int length = tempString.length();
				if (tempString.contains(">")) {
					bwgc.write(tempString.substring(1, length) + "\t");
					bwgc1.write(tempString.substring(1, length) + "\t");
					bwgc2.write(tempString.substring(1, length) + "\t");
					bwgc12.write(tempString.substring(1, length) + "\t");
					bwgc3.write(tempString.substring(1, length) + "\t");
				} else {
					int codonNumber = length / 3;
					for (int j = 0; j < codonNumber; j++) {
						String tempCodon = tempString.substring(3 * j,
								3 * j + 3);
						Totail++;
						// gc1中的GC
						if ("C".equals(tempCodon.substring(0, 1))
								|| "G".equals(tempCodon.substring(0, 1))) {
							gc1++;
							gc++;
						}
						// gc2中的GC
						if ("C".equals(tempCodon.substring(1, 2))
								|| "G".equals(tempCodon.substring(1, 2))) {
							gc2++;
							gc++;
						}
						// gc3中的GC
						if ("C".equals(tempCodon.substring(2, 3))
								|| "G".equals(tempCodon.substring(2, 3))) {
							gc3++;
							gc++;
						}
					}
					gc = gc / (Totail * 3);
					gc1 = gc1 / Totail;
					gc2 = gc2 / Totail;
					gc3 = gc3 / Totail;
					bwgc.write(String.valueOf(gc) + "\n");
					bwgc1.write(String.valueOf(gc1) + "\n");
					bwgc2.write(String.valueOf(gc2) + "\n");
					bwgc12.write(String.valueOf((gc1 + gc2) / 2) + "\n");
					bwgc3.write(String.valueOf(gc3) + "\n");
				}
			}
			bwgc.flush();
			bwgc1.flush();
			bwgc2.flush();
			bwgc12.flush();
			bwgc3.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				bwgc3.close();
				bwgc12.close();
				bwgc2.close();
				bwgc1.close();
				bwgc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
