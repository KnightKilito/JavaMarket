package cn.xuantech;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.zhetech.JdbcConnection;
import cn.zhetech.UserLogin2;

public class ModifyPassword extends JFrame implements ActionListener {
	private String username;
	JLabel label1 = new JLabel("�����룺");
	JTextField password = new JTextField("");
	JLabel label2 = new JLabel("ȷ������");
	JTextField password1 = new JTextField("");
	JButton button1 = new JButton("ȷ��");
	JButton button2 = new JButton("����");
	ImageIcon UserForgetPic = new ImageIcon("imag/UserRegisterImag/UserForgetImag.jpg");
	JLabel backImag = new JLabel(UserForgetPic);

	public ModifyPassword(String username) {
		this.username = username;
		label1.setBounds(160, 80, 80, 30);
		label2.setBounds(160, 120, 80, 30);
		password.setBounds(260, 80, 150, 30);
		password1.setBounds(260, 120, 150, 30);
		button1.setBounds(250, 210, 100, 30);
		button2.setBounds(250, 250, 100, 30);

		button1.addActionListener(this); // ���ô��ڼ���
		button2.addActionListener(this); // ���ô��ڼ���
		
		label1.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
		label2.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
		backImag.setBounds(0, 0, UserForgetPic.getIconWidth(), UserForgetPic.getIconHeight());
		JPanel imPanel=(JPanel) this.getContentPane();//ע������������ǿתΪJPanel�ſ���ʵ�����������͸��
        imPanel.setOpaque(false);//�����������Ϊ͸��
		UserForgetPic.setImage(UserForgetPic.getImage().getScaledInstance(backImag.getWidth(), backImag.getHeight(), Image.SCALE_DEFAULT));//ͼƬ����Ӧ��ǩ��С
        this.getLayeredPane().add(backImag, Integer.valueOf(Integer.MIN_VALUE));//��ǩ��ӵ������
		
        this.getLayeredPane().add(label1);
        this.getLayeredPane().add(label2);
        this.getLayeredPane().add(password);
        this.getLayeredPane().add(password1);
        this.getLayeredPane().add(button1);
        this.getLayeredPane().add(button2);
		setTitle("�޸�����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(500, 230, 600, 400);
		// setResizable(true);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ȷ��")) {
			JdbcConnection jdbcconn = new JdbcConnection();// ʵ��������
			Connection conn = jdbcconn.dbConnection();// ���÷�����ȡconn
			PreparedStatement stat = null;
			String sql1 = "update user set password=? where name=? ";// "?"��ʾռ�÷�
			if (password.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "���벻��Ϊ�գ�����������", "����", JOptionPane.ERROR_MESSAGE);
			} else if (password1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "ȷ�����벻��Ϊ�գ�����������", "����", JOptionPane.ERROR_MESSAGE);
			} else if (!password.getText().equals(password1.getText())) {
				JOptionPane.showMessageDialog(this, "������������벻ͬ������������", "����", JOptionPane.ERROR_MESSAGE);
				password.setText("");
				password1.setText("");
			} else {
				try {
					stat = conn.prepareStatement(sql1);
					// ���һ������
					stat.setString(1, password.getText());
					stat.setString(2, username);
					// ���²���
					stat.executeUpdate();
					// ����һ����ʾ��
					JOptionPane.showMessageDialog(this, "�����޸ĳɹ�,Ϊ����ת����¼����");
					dispose();
					new UserLogin2();
				} catch (Exception e2) {
					System.out.println(e2);
				}
				if (e.getActionCommand().equals("����")) {
					dispose();
					new UserLogin2();
				}
			}
		}
	}
}
