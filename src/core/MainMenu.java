package core;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
import java.awt.GridLayout;

//主界面
public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFilePath;
	public static File inputFileName;
	private static MainMenu frame;
	private static JTextField txtA;
	private static JPanel panel_1;
	private static JLabel lblNewLabel;
	public static enum PulsType{No,CircosGCtype,SynonymCodonShuffleType,KLD_PvalueTpye, KLDtype, CodonShuffleType, SVMLibType};
	public static PulsType testType = PulsType.No;
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
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Filter");
		comboBox.addItem("300");
		comboBox.addItem("Codon shuffle");
		comboBox.addItem("Synonym codon shuffle");
		comboBox.addItem("KLD");
		comboBox.addItem("KLD_Pvalue");
		comboBox.addItem("PR2 Plot");
		comboBox.addItem("Neutrality plot");
		comboBox.addItem("CircosGC");
		comboBox.addItem("SVMData");
		
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
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(35)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFilePath, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(48))
		);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//
		panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setBackground(UIManager.getColor("Button.light"));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel = new JLabel("片段大小(bp)");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setEnabled(false);
		lblNewLabel.setBounds(14, 13, 317, 27);
		panel_1.add(lblNewLabel);
		
		txtA = new JTextField();
		txtA.setText("0");
		txtA.setBounds(14, 53, 317, 27);
		panel_1.add(txtA);
		txtA.setColumns(10);
		contentPane.setLayout(gl_contentPane);
	}
	
	//刷新plus区域
	public static void RefreshPlus(PulsType pulsType){
		switch (pulsType) {
		case CircosGCtype:
			panel_1.setVisible(true);
			break;
		case SynonymCodonShuffleType:
			lblNewLabel.setText("每种氨基酸对应同义密码子的交换次数");
			panel_1.setVisible(true);
			break;
		case KLD_PvalueTpye:
			lblNewLabel.setText("片段大小 片段数量 同义密码子空模型个数(如:10 5 100)");
			panel_1.setVisible(true);
			break;
		case KLDtype:
			lblNewLabel.setText("片段大小 片段数量(如:10 5)");
			panel_1.setVisible(true);
			break;
		case CodonShuffleType:
			lblNewLabel.setText("每种氨基酸对应同义密码子的交换次数");
			panel_1.setVisible(true);
			break;
		case SVMLibType:
			lblNewLabel.setText("数据类型");
			panel_1.setVisible(true);
			break;
		default:
			break;
		}
	}
	
	public void setFileNameIntextfield(){
		Controller controllerInstance =Controller.getController();
		txtFilePath.setText(controllerInstance.getFilePath()+controllerInstance.getFileName());
	}
	
	public static String getPlusfield(){
			return txtA.getText();
	}
}
