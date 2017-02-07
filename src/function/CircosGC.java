package function;

import java.io.IOException;

import core.CleanBreak;
import core.WorkComplete;

public class CircosGC implements Operation {

	@Override
	public void workNew(String path, String fileName) {
		// TODO Auto-generated method stub

		CleanBreak cleanBreak = new CleanBreak();
		cleanBreak.cleanLineBreak(path, fileName);
		String cmd = "cmd /F java 1221.jar";//System.getProperty()
		Runtime rt = Runtime.getRuntime(); 
		try {
			Process proc = rt.exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cleanBreak.deleteTempFile(path, fileName);
		new WorkComplete();
	}

}
