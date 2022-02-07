//package supermarket;
package cn.shutech;

import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.zhetech.JdbcConnection;

public class WorkerManage extends JFrame implements ActionListener {
	JLabel label1 = new JLabel("���˱�ţ�");
	JTextField id = new JTextField("");
	JLabel label2 = new JLabel("����������");
	JTextField name = new JTextField("");
	JLabel label3 = new JLabel("���˹�ʱ��");
	JTextField workhour = new JTextField("");
	JLabel label4 = new JLabel("���˹��ʣ�");
	JTextField salary = new JTextField("");
	JLabel label5 = new JLabel("���˵绰���룺");
	JTextField telephone = new JTextField("");
	JLabel label6 = new JLabel("�������룺");
	JTextField password = new JTextField("");

	JButton button1 = new JButton("����");
	JButton button2 = new JButton("ɾ��");
	JButton button3 = new JButton("�޸�");
	JButton button4 = new JButton("��ѯ");
	JButton button5 = new JButton("���㹤��");
	JButton button6 = new JButton("���㹤ʱ");
	JButton button7 = new JButton("ˢ��Ա����Ϣ");
	JButton button8 = new JButton("�˳���ǰҳ��");

	JLabel label7 = new JLabel("");// ��ʾ��Ϣ

	JTextArea showWorker = new JTextArea("");
	JFrame jFrame = new JFrame();
	ImageIcon ManageWorkerPic = new ImageIcon("imag/WorkerManagementImag/WorkerManagement.jpg");
	JdbcConnection jdbcConn = new JdbcConnection();
	Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
	PreparedStatement stat = null;// Ԥ����

	public WorkerManage() {

		jFrame.setTitle("������Ϣ����");
		jFrame.setSize(900, 501);// ���ô��ڴ�С
		jFrame.setLocationRelativeTo(null);// ���ھ���
		jFrame.getContentPane().setLayout(null);// �޲��֣��ǵ�������ӿؼ�ʱҪ��������λ�úʹ�С
		JPanel imPanel = (JPanel) jFrame.getContentPane();// ע������������ǿתΪJPanel�ſ���ʵ�����������͸��
		imPanel.setOpaque(false);// �����������Ϊ͸��
		JLabel label = new JLabel(ManageWorkerPic);// ��һ����ǩ�м���ͼƬ
		label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());// ���ñ�ǩλ�ô�С���ǵô�СҪ�ʹ���һ����
		ManageWorkerPic.setImage(
				ManageWorkerPic.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));// ͼƬ����Ӧ��ǩ��С
		jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));// ��ǩ��ӵ������

		label1.setBounds(60, 20, 100, 20);
		label2.setBounds(60, 60, 100, 20);
		label3.setBounds(60, 100, 100, 20);
		label4.setBounds(60, 140, 100, 20);
		label5.setBounds(60, 180, 100, 20);
		label6.setBounds(60, 220, 100, 20);
		id.setBounds(160, 20, 150, 20);
		name.setBounds(160, 60, 150, 20);
		workhour.setBounds(160, 100, 150, 20);
		salary.setBounds(160, 140, 150, 20);
		telephone.setBounds(160, 180, 150, 20);
		password.setBounds(160, 220, 150, 20);
		button1.setBounds(100, 260, 70, 20);
		button1.addActionListener(this);
		button2.setBounds(180, 260, 70, 20);
		button2.addActionListener(this);
		button3.setBounds(100, 290, 70, 20);
		button3.addActionListener(this);
		button4.setBounds(180, 290, 70, 20);
		button4.addActionListener(this);
		button5.setBounds(105, 330, 140, 20);
		button5.addActionListener(this);
		button6.setBounds(105, 360, 140, 20);
		button6.addActionListener(this);
		button7.setBounds(105, 390, 140, 20);
		button7.addActionListener(this);
		button8.setBounds(105, 420, 140, 20);
		button8.addActionListener(this);

		showWorker.setBounds(340, 20, 500, 330);
		showWorker.setEditable(false);

		jFrame.getContentPane().add(label1);
		jFrame.getContentPane().add(id);
		jFrame.getContentPane().add(label2);
		jFrame.getContentPane().add(name);
		jFrame.getContentPane().add(label3);
		jFrame.getContentPane().add(workhour);
		jFrame.getContentPane().add(label4);
		jFrame.getContentPane().add(label5);
		jFrame.getContentPane().add(salary);
		jFrame.getContentPane().add(label6);
		jFrame.getContentPane().add(telephone);
		jFrame.getContentPane().add(label7);
		jFrame.getContentPane().add(password);
		jFrame.getContentPane().add(button1);
		jFrame.getContentPane().add(button2);
		jFrame.getContentPane().add(button3);
		jFrame.getContentPane().add(button4);
		jFrame.getContentPane().add(button5);
		jFrame.getContentPane().add(button6);
		jFrame.getContentPane().add(button7);
		jFrame.getContentPane().add(button8);
		jFrame.getContentPane().add(label7);
		jFrame.getContentPane().add(showWorker);
		jFrame.setVisible(true);// ���ô��ڿɼ�
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// ���ڹر� ���������������X��û���κη�Ӧ

	}

	public static void main(String[] args) {
		new WorkerManage();
	}

	public void clear() {
		id.setText("");
		name.setText("");
		workhour.setText("");
		salary.setText("");
		telephone.setText("");
		password.setText("");
	}

	public void Refresh() {// ˢ��Ա����¼
		String search = "select * from user";
		String info = "";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsCheck = stmt.executeQuery(search);
			while (rsCheck.next()) {
				info += "ID��" + rsCheck.getString("id") + "��������" + rsCheck.getString("name") + "����ʱ��"
						+ rsCheck.getString("workhour") + "�����ʣ�" + rsCheck.getString("salary") + "���绰���룺"
						+ rsCheck.getString("telephone") + "\n";
				showWorker.setText(info);

			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("�˳���ǰҳ��")) {
			jFrame.dispose();
		}
		if (e.getActionCommand().equals("ˢ��Ա����Ϣ")) {
			Refresh();
		}
		if (e.getActionCommand().equals("����")) {

			String sql1 = "insert into user (name,workhour,salary,telephone,password) values(?,?,?,?,?)";
			// "?"��ʾռλ��
			if (id.getText().equals("")) {
				try {
					String str = telephone.getText();
					String pat = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
					Pattern p = Pattern.compile(pat);
					Matcher m = p.matcher(str);
					stat = conn.prepareStatement(sql1);
					// ���5������
					// �绰���������ΪString���ͣ�Ϊ�ղ����׳��쳣������ʹ��if���
					stat.setString(1, name.getText());// ��ȡ�����������ı�ֵ
					if (name.getText().equals(""))// �ж��Ƿ�Ϊ������
					{
						JOptionPane.showMessageDialog(this, "��������ȷ������", "����", JOptionPane.ERROR_MESSAGE);
					}
					try {
						stat.setInt(2, Integer.valueOf(workhour.getText()).intValue());// ��ȡ���˹�ʱ���ı�ֵ
					} catch (Exception a3) {
						System.out.println(a3);
						JOptionPane.showMessageDialog(this, "��������ȷ�Ĺ�ʱ", "����", JOptionPane.ERROR_MESSAGE);
					}
					try {
						stat.setDouble(3, Double.valueOf(salary.getText()).doubleValue());// ��ȡ���˹��ʵ��ı�ֵ
					} catch (Exception a5) {
						System.out.println(a5);
						JOptionPane.showMessageDialog(this, "��������ȷ�Ĺ���", "����", JOptionPane.ERROR_MESSAGE);
					}
					stat.setString(4, telephone.getText());// ��ȡ���˵绰������ı�ֵ
					if (!m.matches()) {
						JOptionPane.showMessageDialog(this, "�绰�����ʽ��������������", "����", JOptionPane.ERROR_MESSAGE);
						telephone.setText("");
					}
					stat.setString(5, password.getText());
					if (password.getText().equals(""))// �ж��Ƿ�Ϊ������
					{
						JOptionPane.showMessageDialog(this, "���벻��Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					}
					// ���²���
					stat.executeLargeUpdate();
					// ����һ����ʾ��
					JOptionPane.showMessageDialog(this, "������һ��������Ϣ");
					Refresh();
					clear();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			} else {
				JOptionPane.showMessageDialog(this, "���Ӵ�����Ϣʱ������д���", "����", JOptionPane.ERROR_MESSAGE);
				id.setText("");
			}
		}
		// ɾ������
		if (e.getActionCommand().equals("ɾ��")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
			PreparedStatement stat = null;// Ԥ����
			String sql1 = "delete from user where id=?";
			// "?"��ʾռλ��
			int n = 0;// ��=0����=1
			n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����Ա����", "��ʾ", JOptionPane.YES_NO_OPTION); // ����ֵΪ0��1
			System.out.println("n=" + n);
			if (n == 0) {
				if (id.getText().equals("1")) {
					JOptionPane.showMessageDialog(this, "��С�Ӳ���ɾ���Լ���!������ͯ��?\n�A( �� ������ ) (o(�����///(ն!!)", "����",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (name.getText().equals("") && workhour.getText().equals("") && salary.getText().equals("")
							&& password.getText().equals("")) {
						try {
							stat = conn.prepareStatement(sql1);
							// ���1������
							stat.setInt(1, Integer.valueOf(id.getText()).intValue());// ��ȡ���˱�ŵ��ı�ֵ
							// ���²���
							stat.executeLargeUpdate();
							// ����һ����ʾ��
							JOptionPane.showMessageDialog(this, "ɾ����һ��������Ϣ");
							Refresh();
							clear();
						} catch (Exception e2) {
							System.out.println(e2);
							JOptionPane.showMessageDialog(this, "�������", "����", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(this, "ɾ����Ϣʱֻ����������˱��", "����", JOptionPane.ERROR_MESSAGE);// �����������Ԫ��ʱ��ʾ����
						id.setText("");
						name.setText("");
						workhour.setText("");
						salary.setText("");
						telephone.setText("");
						password.setText("");
					}
				}
			} else {
				Refresh();
			}
		}
		// �޸����
		if (e.getActionCommand().equals("�޸�")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
			PreparedStatement stat = null;// Ԥ����
			String sql1 = "update user set name=?,workhour=?,salary=?,telephone=?,password=? where id=?";
			// "?"��ʾռλ��
			try {
				String str = telephone.getText();
				String pat = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
				Pattern p = Pattern.compile(pat);
				Matcher m = p.matcher(str);
				stat = conn.prepareStatement(sql1);
				stat.setString(1, name.getText());// ��ȡ�����������ı�ֵ
				if (!name.getText().equals(""))// �ж��Ƿ�Ϊ������
				{
					stat.setString(5, password.getText());
					if (!password.getText().equals(""))// �ж��Ƿ�Ϊ������
					{
						try {
							stat.setInt(2, Integer.valueOf(workhour.getText()).intValue());// ��ȡ���˹�ʱ���ı�ֵ
						} catch (Exception a3) {
							System.out.println(a3);
							JOptionPane.showMessageDialog(this, "��������ȷ�Ĺ�ʱ", "����", JOptionPane.ERROR_MESSAGE);
						}
						try {
							stat.setDouble(3, Double.valueOf(salary.getText()).doubleValue());// ��ȡ���˹��ʵ��ı�ֵ
						} catch (Exception a5) {
							System.out.println(a5);
							JOptionPane.showMessageDialog(this, "��������ȷ�Ĺ���", "����", JOptionPane.ERROR_MESSAGE);
						}
						stat.setString(4, telephone.getText());// ��ȡ���˵绰������ı�ֵ
						if (!m.matches()) {
							JOptionPane.showMessageDialog(this, "�绰�����ʽ��������������", "����", JOptionPane.ERROR_MESSAGE);
							telephone.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(this, "���벻��Ϊ��", "����", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "��������ȷ������", "����", JOptionPane.ERROR_MESSAGE);
				}
				stat.setInt(6, Integer.valueOf(id.getText()).intValue());
				// ���²���
				stat.executeLargeUpdate();
				// ����һ����ʾ��
				Refresh();
				JOptionPane.showMessageDialog(this, "�޸���һ����¼");
				clear();
			} catch (Exception e2) {
				System.out.println(e2);
				JOptionPane.showMessageDialog(this, "���������в���", "����", JOptionPane.ERROR_MESSAGE);
			}
		}
		// ��ѯ���
		if (e.getActionCommand().equals("��ѯ")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sql1 = "select * from user where id ='" + id.getText() + "'";
			try {
				ResultSet rs = stmt.executeQuery(sql1);
				if (rs.next()) {
					// ����һ����ʾ��
					JOptionPane.showMessageDialog(this,
							"���˱�ţ�" + rs.getString("id") + "\n��������" + rs.getString("name") + "\n���˹�ʱ"
									+ rs.getString("workhour") + "\n���˹���" + rs.getString("salary") + "\n���˵绰����"
									+ rs.getString("telephone") + "\n��������" + rs.getString("password"));
					System.out.println("��ѯ����һ��������Ϣ");
					clear();
					Refresh();
				} else {
					JOptionPane.showMessageDialog(this, "δ��ѯ����Ϣ", "����", JOptionPane.ERROR_MESSAGE);
					System.out.println("δ��ѯ����Ϣ");
					id.setText("");
					name.setText("");
					workhour.setText("");
					salary.setText("");
					telephone.setText("");
					password.setText("");
				}
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		// ���㹤��
		if (e.getActionCommand().equals("���㹤��")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sql1 = "select * from user where id ='" + id.getText() + "'";// ͨ��ID������������
			if (name.getText().equals("") && workhour.getText().equals("") && salary.getText().equals("")) {
				try {
					ResultSet rs = stmt.executeQuery(sql1);
					if (rs.next()) {
						PreparedStatement stat = null;// Ԥ����
						String sql2 = "update user set salary=(select workhour*3+salary) where id='" + id.getText()
								+ "'";// ͨ����ʱ���㹤��
						try {
							stat = conn.prepareStatement(sql2);
							// ���²���
							stat.executeLargeUpdate();
						} catch (Exception e2) {
							System.out.println(e2);
							JOptionPane.showMessageDialog(this, "�������", "����", JOptionPane.ERROR_MESSAGE);
						}
						String sql3 = "select * from user where id ='" + id.getText() + "'";// �ٴ�ͨ��ID������������
						try {
							ResultSet rs1 = stmt.executeQuery(sql3);
							if (rs1.next()) {
								System.out.println("��ѯ����һ��������Ϣ");
							}
							// ����һ����ʾ��
							JOptionPane.showMessageDialog(this,
									"���˱�ţ�" + rs1.getString("id") + "\n����������" + rs1.getString("name") + "\n���˹�ʱ��"
											+ rs1.getString("workhour") + "\n���˹��ʣ�" + rs1.getString("salary"));
						} catch (Exception e3) {
							System.out.println(e3);
						}
						System.out.println("������һ�����˵Ĺ���");
						Refresh();
						int n = 0;// ��=0����=1
						n = JOptionPane.showConfirmDialog(null, "��Ҫ���㹤ʱ��", "��ʾ", JOptionPane.YES_NO_OPTION); // ����ֵΪ0��1
						System.out.println("n=" + n);
						if (n == 0) {
							String sql4 = "update user set workhour=0,salary=0 where id='" + id.getText() + "'";// ��չ�ʱ
							stat = conn.prepareStatement(sql4);
							stat.executeLargeUpdate();
							Refresh();
							clear();
						} else {
							clear();
						}
					} else {
						JOptionPane.showMessageDialog(this, "�޴˱�ŵĴ���", "����", JOptionPane.ERROR_MESSAGE);// �����������ʾ����
						id.setText("");
						System.out.println("�޴˱�ŵĴ���");
					}
				} catch (Exception e4) {
					System.out.println(e4);
				}
				clear();
				Refresh();
			} else {
				JOptionPane.showMessageDialog(this, "���㹤��ʱֻ����������˱��", "����", JOptionPane.ERROR_MESSAGE);// �����������Ԫ��ʱ��ʾ����
				id.setText("");
				name.setText("");
				workhour.setText("");
				salary.setText("");
				telephone.setText("");
				password.setText("");
			}
		}
		if (e.getActionCommand().equals("���㹤ʱ")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String sql1 = "select * from user where id ='" + id.getText() + "'";// ͨ��ID������������
			if (id.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "��������˱��", "����", JOptionPane.ERROR_MESSAGE);
				clear();
				Refresh();
			}
			if (name.getText().equals("") && workhour.getText().equals("") && salary.getText().equals("")
					&& telephone.getText().equals("")) {
				try {
					ResultSet rs = stmt.executeQuery(sql1);
					if (rs.next()) {
						PreparedStatement stat = null;// Ԥ����
						String sql2 = "update user set workhour=0,salary=0 where id='" + id.getText() + "'";// ͨ����ʱ���㹤��
						try {
							stat = conn.prepareStatement(sql2);
							// ���²���
							stat.executeLargeUpdate();
							Refresh();
							clear();
						} catch (Exception e2) {
							System.out.println(e2);
							JOptionPane.showMessageDialog(this, "�������", "����", JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (Exception e2) {
					System.out.println(e2);
					JOptionPane.showMessageDialog(this, "��������˱��", "����", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "���㹤ʱʱֻ��������", "����", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
//2021��1��4��23��16�� ���Ӽ��㹤�������Ŵ�������
//2021��1��5��10��18�� �Ż����㹤�ʺ������Ϣ����ʾ �Ż��������� �Ż�����ʱ�ı��� 
//2021��1��5��10��51�� ͳһ��ʽ
//2021��1��6��10��29�� �Ż����㹤�ʵķ������ĸ�������Ԫ��Ϊ�޸ĵ���Ԫ�أ�������㹤��ʱֱ�Ӽ��������˹��ʵ�����
//2021��1��6��11��08�� ���ü��㹤��ʱֻ����������
//2021��1��6��22��26�� �����жϵ绰������������
//2021��1��6��23��19�� ���ӹ�����������
//2021��1��7��00��40�� �������Ӵ���ʱ�Զ�����id
//2021��1��7��01��41�� �Ż����ӹ��ܣ���ʾ���Ӵ�����Ϣʱ��������id
//2021��1��7��09��05�� ���Ӽ��㹤�ʺ����㹤ʱ�Ĳ���
//2021��1��7��09��35�� ����ɾ����Ϣǰ����ʾ ���Ӽ��㹤�ʺ󵯴��ڵĴ��˱�źʹ�������������Ϣ
//2021��1��7��13��59�� ɾ�����к�sex��ص����
//2021��1��7��15��07�� ��ֹ�ϰ�ɾ���Լ�����Ϣ
//2021��1��7��19��10�� �����������㹤ʱ���ܣ����������㷨
//2021��1��8��08��27�� �Ż��޸Ĺ���