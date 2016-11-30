import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class FileChoose extends JFrame {

//	private JPanel contentPane;
	private static File file;
	private FileChoose() {
		JFileChooser jfchs=new JFileChooser();
		jfchs.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
		jfchs.showDialog(new JLabel(), "选择");
		
		
		File file=jfchs.getSelectedFile();
		if(file!=null){
			if(file.isDirectory()){
				System.out.println("文件夹:"+file.getAbsolutePath());
			}else if(file.isFile()){
				System.out.println("文件:"+file.getAbsolutePath());
			}
			System.out.println(jfchs.getSelectedFile().getName());
		}
		int returnVal = jfchs.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("aaa");
		} 
		
	}
	public static void getFile(){
		MainMenu.inputFileName = file;
	}
	private static FileChoose instance = new FileChoose();
	public static FileChoose getInstance(){
		return instance;
	}
	
}
