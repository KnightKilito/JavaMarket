package cn.huitech;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.huitech.GoodsInformation;
import cn.zhetech.JdbcConnection;

//import demo.goodsInformation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GoodManage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField goodsID;
	private JTextField goodsNAME;
	private JTextField goodsPRICE;
	private JTextField goodsNUMBER;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		JdbcConnection jdbcConn = new JdbcConnection();
		Connection conn = jdbcConn.dbConnection();

		// ����URLΪ jdbc:mysql//��������ַ/���ݿ��� �������2�������ֱ��ǵ�½�û���������
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
	ImageIcon goodManageImage=new ImageIcon("imag/GoodManage.jpg");
	JLabel backImag=new JLabel(goodManageImage);
	
//	public void paint(Graphics g) {
//		g.drawImage(goodManageImage.getImage(), 0, 0, null);
//	}
	
	public GoodManage() {
		setResizable(false);
		setTitle("\u5546\u54C1\u76EE\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		backImag.setBounds(0, 0, goodManageImage.getIconWidth(), goodManageImage.getIconHeight());
		
		
		setBounds(100, 100, 545, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//���ڹر� �������������X��û���κη�Ӧ
		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel label = new JLabel("");

		JLabel label_1 = new JLabel("\u7F16\u53F7");

		JLabel label_2 = new JLabel("\u8D27\u7269\u540D");

		goodsID = new JTextField();
		goodsID.setColumns(10);

		goodsNAME = new JTextField();
		goodsNAME.setColumns(10);

		JLabel label_3 = new JLabel("\u6570\u91CF");

		goodsPRICE = new JTextField();
		goodsPRICE.setColumns(10);

		goodsNUMBER = new JTextField();
		goodsNUMBER.setColumns(10);
		JLabel label_4 = new JLabel("\u4EF7\u683C");
		JButton btnNewButton = new JButton("\u67E5\u627E\u5546\u54C1");

		btnNewButton.addActionListener(new ActionListener() {// ��ť�����ҡ���Ʒ�¼�
			public void actionPerformed(ActionEvent e) {
				String goodsid = goodsID.getText();
				String goodsname = goodsNAME.getText();
				String goodsprice = goodsPRICE.getText();
				String goodsnumber = goodsNUMBER.getText();

				DefaultTableModel dtm = (DefaultTableModel) table.getModel();// �������ģ��
				dtm.setRowCount(0);// ���ó�0��
				if (goodsid.trim().length() != 0) {

					List<GoodsInformation> g = GoodsInformation.listgoods_id(goodsid);
					for (GoodsInformation a : g) {
						Vector<Object> v = new Vector<>();
						v.add(a.getGoods_id());
						v.add(a.getGoods_name());
						v.add(a.getGoods_price());
						v.add(a.getGoods_number());
						dtm.addRow(v);
					}
				} else if (goodsname.trim().length() != 0) {

					List<GoodsInformation> g = GoodsInformation.listgoods_name(goodsname);
					for (GoodsInformation a : g) {
						Vector<Object> v = new Vector<>();
						v.add(a.getGoods_id());
						v.add(a.getGoods_name());
						v.add(a.getGoods_price());
						v.add(a.getGoods_number());
						dtm.addRow(v);
					}
				}

				else if (goodsprice.trim().length() != 0) {
					List<GoodsInformation> g = GoodsInformation.listgoods_price(goodsprice);
					for (GoodsInformation a : g) {
						Vector<Object> v = new Vector<>();
						v.add(a.getGoods_id());
						v.add(a.getGoods_name());
						v.add(a.getGoods_price());
						v.add(a.getGoods_number());
						dtm.addRow(v);
					}
				} else if(goodsname.trim().length() != 0){
					List<GoodsInformation> g = GoodsInformation.listgoods_number(goodsnumber);
					for (GoodsInformation a : g) {
						Vector<Object> v = new Vector<>();
						v.add(a.getGoods_id());
						v.add(a.getGoods_name());
						v.add(a.getGoods_price());
						v.add(a.getGoods_number());
						dtm.addRow(v);
					}
					
				}
				clear();
			}
		});

		JButton btnNewButton_2 = new JButton("\u6DFB\u52A0\u5546\u54C1");
		btnNewButton_2.addActionListener(new ActionListener() {// ��ť�����ӡ������¼�
			public void actionPerformed(ActionEvent e) {
				String goodsid = goodsID.getText();
				String goodsname = goodsNAME.getText();
				String goodsprice = goodsPRICE.getText();
				String goodsnumber = goodsNUMBER.getText();

				if (goodsid == null || goodsid.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��Ʒ��Ų���Ϊ��");
				} else if (goodsname == null || goodsname.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��Ʒ���ֲ���Ϊ��");
				} else if (goodsprice == null || goodsprice.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��Ʒ�۸񲻿�Ϊ��");
				} else if (goodsnumber == null || goodsnumber.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��Ʒ��������Ϊ��");
				} else {
					try {
						int goodsidd = Integer.parseInt(goodsid);
						double goodspricee = Double.parseDouble(goodsprice);
						int goodsnumberr = Integer.parseInt(goodsnumber);
						GoodsInformation go = new GoodsInformation(goodsidd, goodsname, goodspricee, goodsnumberr);
						try {
							go.addGoods();
							JOptionPane.showMessageDialog(null, "����ɹ�������������");
							fillTable();
							clear();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							System.out.println("��Ʒ����ظ�");
							JOptionPane.showMessageDialog(null, "��Ʒ����ظ�");
						}
					} catch (NumberFormatException e3) {
						System.out.println("�������");
						JOptionPane.showMessageDialog(null, "�������ݴ�������");
					}

				}
			}
		});

		JButton button = new JButton("\u9000\u51FA");
		button.addActionListener(new ActionListener() {// ��ť���˳����¼�
			public void actionPerformed(ActionEvent e) {
				exitActionPerformed(e);
			}
		});

		JButton btnNewButton_3 = new JButton("\u663E\u793A\u5B8C\u6574\u5546\u54C1\u76EE\u5F55");
		btnNewButton_3.addActionListener(new ActionListener() {// ��ť����ʾ������ƷĿ¼���¼�
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});

		JButton btnNewButton_4 = new JButton("\u5220\u9664\u5546\u54C1");
		btnNewButton_4.addActionListener(new ActionListener() {// ��ť��ɾ����Ʒ���¼�
			public void actionPerformed(ActionEvent e) {
				String goodsid = goodsID.getText();
				GoodsInformation gg = GoodsInformation.select_id(goodsid);
				try {
					gg.delGoods();
					System.out.println("ɾ���ɹ�");
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					fillTable();
					clear();
				} catch (Exception e1) {
// TODO Auto-generated catch block
					System.out.println("error");
					JOptionPane.showMessageDialog(null, "�޸���Ʒ");
				}
			}

		});

		JButton button_1 = new JButton("\u4FEE\u6539\u5546\u54C1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String goodsid = goodsID.getText();
				String goodsname = goodsNAME.getText();
				String goodsprice = goodsPRICE.getText();
				String goodsnumber = goodsNUMBER.getText();

				if (goodsid == null || goodsid.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��Ʒ��Ų���Ϊ��");
				}
				if (goodsname == null || goodsname.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��Ʒ���ֲ���Ϊ��");
				} else if (goodsprice == null || goodsprice.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��Ʒ�۸񲻿�Ϊ��");
				} else if (goodsnumber == null || goodsnumber.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "��Ʒ��������Ϊ��");
				} else {
					try {
						int goodsidd = Integer.parseInt(goodsid);
						double goodspricee = Double.parseDouble(goodsprice);
						int goodsnumberr = Integer.parseInt(goodsnumber);
						GoodsInformation go = new GoodsInformation(goodsidd, goodsname, goodspricee, goodsnumberr);
						try {
							go.updata();
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�������������");
							fillTable();
							clear();
						} catch (Exception e2) {
							e2.printStackTrace();
							// TODO Auto-generated catch block
							// System.out.println("�޸���Ʒ");
							// JOptionPane.showMessageDialog(null, "�޸���Ʒ");
						}
					} catch (NumberFormatException e3) {
						System.out.println("��������");
						JOptionPane.showMessageDialog(null, "������������");
					}
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
								.createSequentialGroup().addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(
												btnNewButton_3, GroupLayout.PREFERRED_SIZE, 198,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_3)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(goodsNUMBER, 0, 0, Short.MAX_VALUE))
												.addGroup(gl_contentPane.createSequentialGroup().addComponent(label_1)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(goodsID, GroupLayout.PREFERRED_SIZE, 57,
																GroupLayout.PREFERRED_SIZE)))
												.addGap(27)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
														.addComponent(label_2).addComponent(label_4))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
														.addComponent(goodsNAME, Alignment.LEADING,
																GroupLayout.PREFERRED_SIZE, 58,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(goodsPRICE, GroupLayout.PREFERRED_SIZE, 58,
																GroupLayout.PREFERRED_SIZE))))
								.addGap(25)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
										.createSequentialGroup()
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 233,
												GroupLayout.PREFERRED_SIZE)
										.addGap(189, 189, Short.MAX_VALUE).addComponent(label))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(btnNewButton_4)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(button_1, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGroup(Alignment.LEADING,
														gl_contentPane.createSequentialGroup()
																.addComponent(btnNewButton_2)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(btnNewButton)))))
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 514,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(
										gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
														.createParallelGroup(Alignment.BASELINE).addComponent(label_1)
														.addComponent(goodsID, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(label_2)
														.addComponent(goodsNAME, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 23,
																GroupLayout.PREFERRED_SIZE))
														.addGap(0)
														.addComponent(
																scrollPane, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(label_3)
																.addComponent(goodsNUMBER, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(label_4)
																.addComponent(goodsPRICE, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(btnNewButton_4,
																		GroupLayout.PREFERRED_SIZE, 22,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 23,
																		GroupLayout.PREFERRED_SIZE))
														.addGap(19)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE,
																		23, Short.MAX_VALUE)
																.addComponent(button, GroupLayout.PREFERRED_SIZE, 23,
																		GroupLayout.PREFERRED_SIZE)))
												.addComponent(label))
								.addContainerGap()));

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {// �������� ���ı�����ʾ������Ϣ
			@Override
			public void mousePressed(MouseEvent e) {
				goodsmousepressed(e);

			}

			public void goodsmousepressed(MouseEvent e) {
// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				goodsID.setText(table.getValueAt(row, 0).toString());// getvalue��ȡ�������Ӧ�����ͣ���Ҫת��Ϊstring���ͣ�ǿ�ƣ�string�����ɹ���ʹ��tostring�����ɹ�
				goodsNAME.setText(table.getValueAt(row, 1).toString());
				goodsPRICE.setText(table.getValueAt(row, 2).toString());

			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u7F16\u53F7", "\u8D27\u7269\u540D", "\u4EF7\u683C", "\u6570\u91CF" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setPreferredWidth(124);
		table.getColumnModel().getColumn(2).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		scrollPane_1.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		fillTable();

	}

	protected void exitActionPerformed(ActionEvent e) {// �˳�����
// TODO Auto-generated method stub
		this.dispose();
	}

//��ʼ�����
	public void fillTable() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();// �������ģ��
		dtm.setRowCount(0);// ���ó�0��

		List<GoodsInformation> g = GoodsInformation.listgoods();
		for (GoodsInformation a : g) {
			Vector<Object> v = new Vector<>();

			v.add(a.getGoods_id());
			v.add(a.getGoods_name());
			v.add(a.getGoods_price());
			v.add(a.getGoods_number());
			dtm.addRow(v);
		}
	}
	public void clear() {
		goodsID.setText("");
		goodsNAME.setText("");
		goodsNUMBER.setText("");
		goodsPRICE.setText("");
	}
}
