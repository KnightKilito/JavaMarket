package cn.xuantech;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.zhetech.JdbcConnection;

public class ForgetPassword extends JFrame implements ActionListener {
	String str;
	JPanel panel1 = new JPanel();
	JLabel label1 = new JLabel("�������û���");
	JTextField username = new JTextField("");
	JLabel label2 = new JLabel("������绰");
	JTextField telephone = new JTextField("");
	JLabel label3 = new JLabel("��������֤��");
	JTextField code = new JTextField("");
	JButton button1 = new JButton("ȷ��");
	JButton button2 = new JButton("����");
	ImageIcon Code = new ImageIcon();
	JLabel label;// ��һ����ǩ�м���ͼƬ
	ImageIcon UserForgetPic = new ImageIcon("imag/UserRegisterImag/UserForgetImag.jpg");
	JLabel backImag=new JLabel(UserForgetPic);

	public ForgetPassword() {
		label1.setBounds(160, 40, 80, 30);
		label2.setBounds(160, 80, 80, 30);
		label3.setBounds(160, 120, 80, 30);
		username.setBounds(260, 40, 150, 30);
		telephone.setBounds(260, 80, 150, 30);
		code.setBounds(260, 120, 150, 30);
		button1.setBounds(200, 160, 80, 30);
		button2.setBounds(300, 160, 80, 30);
		button1.addActionListener(this); // ���ô��ڼ���
		button2.addActionListener(this); // ���ô��ڼ���
		VerifyCode verifyCode = new VerifyCode();
		try {
			verifyCode.output(verifyCode.getImage(), new FileOutputStream("imag/test.jpg"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		str = verifyCode.getText();
		System.out.println(str);
		Code = new ImageIcon("imag/test.jpg");
		label = new JLabel(Code);
		label.setBounds(400, 120, 100, 30);
		
		label1.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
		label2.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
		label3.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
		backImag.setBounds(0, 0, UserForgetPic.getIconWidth(), UserForgetPic.getIconHeight());
		JPanel imPanel=(JPanel) this.getContentPane();//ע������������ǿתΪJPanel�ſ���ʵ�����������͸��
        imPanel.setOpaque(false);//�����������Ϊ͸��
		UserForgetPic.setImage(UserForgetPic.getImage().getScaledInstance(backImag.getWidth(), backImag.getHeight(), Image.SCALE_DEFAULT));//ͼƬ����Ӧ��ǩ��С
        this.getLayeredPane().add(backImag, Integer.valueOf(Integer.MIN_VALUE));//��ǩ��ӵ������
		
        this.getContentPane().add(label1);
        this.getContentPane().add(label2);
        this.getContentPane().add(label3);
        this.getContentPane().add(label);
        this.getContentPane().add(username);
        this.getContentPane().add(telephone);
        this.getContentPane().add(code);
        this.getContentPane().add(button1);
        this.getContentPane().add(button2);
		this.setTitle("��������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(500, 230, 600, 400);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ȷ��")) {
			if (username.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "�û�������Ϊ�գ�����������", "����", JOptionPane.ERROR_MESSAGE);
			} else if (telephone.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "�绰����Ϊ�գ�����������", "����", JOptionPane.ERROR_MESSAGE);
			} else if (code.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "��֤�벻��Ϊ�գ�����������", "����", JOptionPane.ERROR_MESSAGE);
			} else if (!code.getText().equals(str)) {
				JOptionPane.showMessageDialog(this, "��֤�������������������", "����", JOptionPane.ERROR_MESSAGE);
				code.setText("");
			} else {
				JdbcConnection jdbcconn = new JdbcConnection();// ʵ��������
				Connection conn = jdbcconn.dbConnection();// ���÷�����ȡconn
				PreparedStatement stat = null;
				String sql1 = "insert into user (sname,telephone) value(?,?)";// "?"��ʾռ�÷�
				try {
					JdbcConnection jdbcconn1 = new JdbcConnection();// ʵ��������
					Connection conn1 = jdbcconn1.dbConnection();// ���÷�����ȡconn
					Statement stmt = conn1.createStatement();
					String sql2 = "select * from user where name ='" + username.getText() + "'";
					ResultSet rs1 = stmt.executeQuery(sql2);
					/*if (!rs1.next()) {
						JOptionPane.showMessageDialog(this, "��ѯ�������û�");
						username.setText("");
					}
					JdbcConnection jdbcconn2 = new JdbcConnection();// ʵ��������
					Connection conn2 = jdbcconn2.dbConnection();// ���÷�����ȡconn
					Statement stmt2 = conn2.createStatement();
					String sql3 = "select * from user where telephone ='" + telephone.getText() + "'";
					ResultSet rs2 = stmt2.executeQuery(sql3);
					if (!rs2.next()) {
						JOptionPane.showMessageDialog(this, "��ѯ�����õ绰");
						telephone.setText("");
					} else */
					if(rs1.next()&&telephone.getText().equals(rs1.getString("telephone"))){
						dispose();
						new ModifyPassword(username.getText());
					}
					else {
						JOptionPane.showMessageDialog(this, "�û������ߵ绰��������������������룡", "����", JOptionPane.ERROR_MESSAGE);
						username.setText("");
						telephone.setText("");
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
	}

	public static void main(String args[]) {
		new ForgetPassword();
	}
}