package cn.zhetech;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import demo.goodsInformation;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GoodManage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoodManage frame = new GoodManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GoodManage() {
		setTitle("\u5546\u54C1\u76EE\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel label = new JLabel("");

		JLabel label_1 = new JLabel("\u7F16\u53F7");

		JLabel label_2 = new JLabel("\u8D27\u7269\u540D");

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\u67E5\u627E", "\u51FA\u552E", "\u6DFB\u52A0" }));

		JButton btnNewButton = new JButton("\u5F00\u59CB");

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel label_3 = new JLabel("\u6570\u91CF");

		textField_2 = new JTextField();
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);

		JLabel label_4 = new JLabel("\u4EF7\u683C");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3).addComponent(label_1))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_2)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField_1,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(label_4)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(textField_2,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(34)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(76).addComponent(label))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnNewButton).addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE))
				.addGap(30)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup()
								.addGap(164).addGroup(gl_contentPane
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane
												.createSequentialGroup().addGap(18).addGroup(gl_contentPane
														.createParallelGroup(Alignment.TRAILING)
														.addComponent(
																scrollPane, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane
																		.createParallelGroup(Alignment.BASELINE)
																		.addComponent(textField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(label_2)
																		.addComponent(textField_1,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(label_1))
																.addComponent(label)))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(label_3).addComponent(
																		textField_3, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(label_4)
																.addComponent(textField_2, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_contentPane.createSequentialGroup().addGap(41)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnNewButton))))
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(scrollPane_1,
								GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(44, Short.MAX_VALUE)));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u7F16\u53F7", "\u8D27\u7269\u540D", "\u4EF7\u683C", "\u6570\u91CF" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setPreferredWidth(124);
		table.getColumnModel().getColumn(2).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		scrollPane_1.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
// 初始化表格
/*
 * public void fillTable() { DefaultTableModel dtm = (DefaultTableModel)
 * table.getModel();//创建表格模型 dtm.setRowCount(0);//摄影成0行
 * 
 * //List<goodsInformation> g = goodsInformation.listgoods(); } }
 */
