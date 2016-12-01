package Core;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.UIManager;

import function.FunctionFactory;
import function.Operation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Font;


public class MainMenu extends JFrame {

	private JPanel contentPane;
	private JTextField txtFilePath;
	public static File inputFileName;
	private static MainMenu frame;
//	Controller controllerInstance = Controller.getController();
	
	public static MainMenu getMainMenu(){
		return frame;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//------------------------
		
	}

	/**
	 * Create the frame.
	 */
	private MainMenu() {
		setTitle("CodonsBox");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenu.class.getResource("/img/Mulberry.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(8, 100, 615, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addItem("GC123");
		comboBox.addItem("Cuter");
		comboBox.addItem("军官证");
		
		
		txtFilePath = new JTextField();
		txtFilePath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if("File path".equals(txtFilePath.getText())){
					txtFilePath.setText("");
				}
			}
		});
		txtFilePath.setToolTipText("");
		txtFilePath.setForeground(Color.LIGHT_GRAY);
		txtFilePath.setText("File path");
		txtFilePath.setColumns(10);
		
		JButton btnNewButton = new JButton("Select file");
		btnNewButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
					new FileChoose();
//					frame.setVisible(true);
			}
		});
		
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Start");
		btnNewButton_1.setFont(new Font("SimSun-ExtB", Font.BOLD, 25));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Controller controllerInstance = Controller.getController();
				controllerInstance.setTestName("");//-------------
				String testName=comboBox.getSelectedItem().toString();
				controllerInstance.setTestName(testName);
//				String s=(String)comboBox.getSelectedItem();
				
				Operation op;
				op = FunctionFactory.CreateOperation(controllerInstance.getTestName());
				op.workNew(controllerInstance.getFilePath(),controllerInstance.getFileName());
			}
		});
		btnNewButton_1.setBackground(UIManager.getColor("Button.light"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addGap(207)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(128))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public void setFileNameIntextfield(){
		Controller controllerInstance =Controller.getController();
		txtFilePath.setText(controllerInstance.getFilePath()+controllerInstance.getFileName());
	}
	
	
}
