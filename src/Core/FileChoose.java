package core;

import java.io.File;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileView;

public class FileChoose extends JFrame {

	private static final long serialVersionUID = 1L;
	private static File file;

	public FileChoose() {
		final JFileChooser jfchs = new JFileChooser();
		jfchs.setDialogType(JFileChooser.OPEN_DIALOG);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfchs.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// 使用操作系统对应的文件的图标
		jfchs.setFileView(new FileView() {
			public Icon getIcon(File f) {
				return jfchs.getFileSystemView().getSystemIcon(f);
			}
		});

		jfchs.showDialog(new JLabel(), "选择");

		File file = jfchs.getSelectedFile();
		if (file != null) {
			if (file.isFile()) {
				Controller controllerInstance = Controller.getController();
				controllerInstance.setFileName(file.getName());
				controllerInstance.setFilePath(file.getAbsolutePath());
				MainMenu mainMenuInstance = MainMenu.getMainMenu();
				mainMenuInstance.setFileNameIntextfield();
			}
		}
	}

	public static void getFile() {
		MainMenu.inputFileName = file;
	}
}
