package cn.zhetech;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataDemo extends JFrame implements ActionListener {
	// JPanel panel1 = new JPanel();
	JLabel label1 = new JLabel("��������Ʒ��ţ�");
	JTextField goodId = new JTextField("");
	JLabel label2 = new JLabel("��������Ʒ���ƣ�");
	JTextField goodName = new JTextField("");
	JLabel label3 = new JLabel("��������Ʒ�۸�");
	JTextField goodPrice = new JTextField("");

	JButton button1 = new JButton("����");
	JButton button2 = new JButton("ɾ��");
	JButton button3 = new JButton("�޸�");
	JButton button4 = new JButton("��ѯ");
	// ImageIcon picture1=new
	// ImageIcon("D:\\Program\\Eclipse-schoolspace\\2020.12.28\\imag\\ʥ������.png");
	JLabel label4 = new JLabel("");// ��ʾ��Ϣ

	public DataDemo() {
		//super("��Ʒ��Ϣ����");
		// this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));//��ʧ����
		// panel1.setLayout(new GridLayout(6,1,3,3));
		// this.getContentPane();
		label1.setBounds(60, 20, 100, 20);
		label2.setBounds(60, 60, 100, 20);
		label3.setBounds(60, 100, 100, 20);
		goodId.setBounds(160, 20, 150, 20);
		goodName.setBounds(160, 60, 150, 20);
		goodPrice.setBounds(160, 100, 150, 20);
		button1.setBounds(100, 140, 70, 20);
		button1.addActionListener(this);
		button2.setBounds(180, 140, 70, 20);
		button2.addActionListener(this);
		button3.setBounds(100, 180, 70, 20);
		button3.addActionListener(this);
		button4.setBounds(180, 180, 70, 20);
		button4.addActionListener(this);

		add(label1);
		add(goodId);
		add(label2);

		add(goodName);
		add(label3);
		add(goodPrice);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(label4);

		setTitle("��Ʒ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(550, 300, 380, 280);
		setVisible(true);
	}

	public static void main(String[] args) {
		new DataDemo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JdbcConnection jdbcConn = new JdbcConnection();
		Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
		PreparedStatement stat = null;// Ԥ����
		if (e.getActionCommand().equals("����")) {

			String sql1 = "insert into good (id,name,price) values(?,?,?)";
			// "?"��ʾռλ��
			try {
				stat = conn.prepareStatement(sql1);
				// ���3������
				stat.setInt(1, Integer.valueOf(goodId.getText()).intValue());// ��ȡͼ���ŵ��ı�ֵ
				stat.setString(2, goodName.getText());// ��ȡͼ�����Ƶ��ı�ֵ
				stat.setDouble(3, Double.valueOf(goodPrice.getText()).doubleValue());// ��ȡͼ��۸���ı�ֵ
				// ���²���
				stat.executeLargeUpdate();
				// ����һ����ʾ��
				JOptionPane.showMessageDialog(this, "������һ����¼");
				System.out.println("������һ����¼");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		// ɾ��ͼ����Ϣ
		if (e.getActionCommand().equals("ɾ��")) {
			String sql1 = "delete from good where id=?";
			// "?"��ʾռλ��
			try {
				stat = conn.prepareStatement(sql1);
				// ���3������
				stat.setInt(1, Integer.valueOf(goodId.getText()).intValue());// ��ȡͼ���ŵ��ı�ֵ
				// ���²���
				stat.executeLargeUpdate();
				// ����һ����ʾ��
				JOptionPane.showMessageDialog(this, "ɾ����һ����¼");
				System.out.println("ɾ����һ����¼");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		// �޸�
		if (e.getActionCommand().equals("�޸�")) {

			String sql1 = "update good set name=?,price=? where id=?";
			// "?"��ʾռλ��
			try {
				stat = conn.prepareStatement(sql1);
				// ���3������
				stat.setInt(3, Integer.valueOf(goodId.getText()).intValue());// ��ȡͼ���ŵ��ı�ֵ
				stat.setString(1, goodName.getText());// ��ȡͼ�����Ƶ��ı�ֵ
				stat.setDouble(2, Double.valueOf(goodPrice.getText()).doubleValue());// ��ȡͼ��۸���ı�ֵ
				// ���²���
				stat.executeLargeUpdate();
				// ����һ����ʾ��
				JOptionPane.showMessageDialog(this, "�޸���һ����¼");
				System.out.println("�޸���һ����¼");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		// ��ѯ
		if (e.getActionCommand().equals("��ѯ")) {

			String sqlId = "select * from good where id ='" + goodId.getText() + "'";
			String sqlName = "select * from good where name ='" + goodName.getText() + "'";
			String sqlPrice = "select * from good where price ='" + goodPrice.getText() + "'";
			// "?"��ʾռλ��
			try {

				Statement stmt = conn.createStatement();
				if (!goodId.getText().equals("")) {
					searchId(stmt, sqlId);
				} else if (!goodName.getText().equals("")) {
					searchName(stmt, sqlName);

				} else if (!goodPrice.getText().equals("")) {
					searchPrice(stmt, sqlPrice);
				}
				


				goodName.setText("");
				goodPrice.setText("");
				goodId.setText("");

				// ����һ����ʾ��

				System.out.println("��ѯ��һ����¼");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	public void searchId(Statement stmt, String sqlId) throws SQLException {
		System.out.println("searching goodId");
		ResultSet rsId = stmt.executeQuery(sqlId);
		if (rsId.next()) {

			// ��ʾͼ����Ϣ
			JOptionPane.showMessageDialog(this, "��ѯ��һ����¼��\n" + "��Ʒ��ţ�" + rsId.getString("id") + "\n��Ʒ���ƣ�"
					+ rsId.getString("name") + "\n��Ʒ�۸�" + rsId.getString("price"));
		} else {

			JOptionPane.showMessageDialog(this, "δ��ѯ������Ϣ��", "����", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void searchName(Statement stmt, String sqlName) throws SQLException {
		System.out.println("searching goodName");
		ResultSet rsName = stmt.executeQuery(sqlName);
		if (rsName.next()) {
			JOptionPane.showMessageDialog(this, "��ѯ��һ����¼��\n" + "��Ʒ��ţ�" + rsName.getString("id") + "\n��Ʒ���ƣ�"
					+ rsName.getString("name") + "\n��Ʒ�۸�" + rsName.getString("price"));
		} else {

			JOptionPane.showMessageDialog(this, "δ��ѯ������Ϣ��", "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void searchPrice(Statement stmt, String sqlPrice) throws SQLException {
		System.out.println("searching goodPrice");
		ResultSet rsPrice = stmt.executeQuery(sqlPrice);

		if (rsPrice.next()) {
			JOptionPane.showMessageDialog(this, "��ѯ��һ����¼��\n" + "ͼ���ţ�" + rsPrice.getString("id") + "\nͼ�����ƣ�"
					+ rsPrice.getString("name") + "\nͼ��۸�" + rsPrice.getString("price"));
		} else {
			// JOptionPane.showMessageDialog(this, "δ��ѯ���ü�¼��");
			JOptionPane.showMessageDialog(this, "δ��ѯ������Ϣ��", "����", JOptionPane.ERROR_MESSAGE);

		}
	}
}
