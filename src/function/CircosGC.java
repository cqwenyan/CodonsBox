package function;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import core.MainMenu;
import core.MainMenu.PulsType;
import core.WorkComplete;

public class CircosGC implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		MainMenu.RefreshPlus(PulsType.CircosGCtype);

		if (!MainMenu.getPlusfield().equals("0")) {
			// CleanBreak cleanBreak = new CleanBreak();
			// cleanBreak.cleanLineBreak(path, fileName);
			genIterator(path, fileName,
					Integer.parseInt(MainMenu.getPlusfield()), "CodonsBoxGC");
			// cleanBreak.deleteTempFile(path, fileName);
			new WorkComplete();
		}
	}

	public static void genIterator(String path, String inputFileName,
			int frameSize, String chromosomeName) {
		try {
			File writeTo = new File(path + "GenomeGCcircos_" + inputFileName);
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(writeTo, true));
			String tempString = null;
			StringBuilder stringBud = new StringBuilder();

			File file = new File(inputFileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			int counter = 0;
			while ((tempString = reader.readLine()) != null) {
				stringBud.append(tempString.trim());

				while (stringBud.length() >= frameSize) {
					int GCcounter = 0;
					for (int i = 0; i < frameSize; i++) {
						if (("c".equals(stringBud.subSequence(i, i + 1)))
								|| ("C".equals(stringBud.subSequence(i, i + 1)))
								|| ("g".equals(stringBud.subSequence(i, i + 1)))
								|| ("G".equals(stringBud.subSequence(i, i + 1)))) {
							GCcounter++;
						}
					}
					bw.write(chromosomeName + " " + counter + " "
							+ (counter + frameSize) + " " + ((float) GCcounter)
							/ frameSize + "\n");
					counter += frameSize;
					stringBud.delete(0, frameSize);
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
