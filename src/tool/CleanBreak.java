package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CleanBreak implements ICleanBreak {
	private int lineLenght = -1;

	@Override
	public void cleanLineBreak(String path, String inputFileName) {
		File file = new File(path + inputFileName);
		BufferedReader reader = null;
		FileWriter rwriter = null;
		BufferedWriter bw = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			rwriter = new FileWriter(path + "tempFile.fasta", true);
			bw = new BufferedWriter(rwriter);
			String tempString = null;
			tempString = reader.readLine().toUpperCase();
			bw.write(tempString + "\n");
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains(">")) {
					bw.write("\n" + tempString.toUpperCase() + "\n");
				} else {
					if (lineLenght == -1)
						lineLenght = tempString.length();
					bw.write(tempString.toUpperCase());
				}
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				rwriter.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// get the line length
	public int GetLineLength() {
		return lineLenght;
	}

	@Override
	public void deleteTempFile(String path) {
		File file = new File(path + "tempFile.fasta");
		if (file.exists()) {
			file.delete();
		}
	}
}
