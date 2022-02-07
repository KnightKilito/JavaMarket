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
	JLabel label1 = new JLabel("新密码：");
	JTextField password = new JTextField("");
	JLabel label2 = new JLabel("确认密码");
	JTextField password1 = new JTextField("");
	JButton button1 = new JButton("确定");
	JButton button2 = new JButton("返回");
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

		button1.addActionListener(this); // 设置窗口监听
		button2.addActionListener(this); // 设置窗口监听
		
		label1.setForeground(Color.white);//设置标签字体为白色
		label2.setForeground(Color.white);//设置标签字体为白色
		backImag.setBounds(0, 0, UserForgetPic.getIconWidth(), UserForgetPic.getIconHeight());
		JPanel imPanel=(JPanel) this.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
		UserForgetPic.setImage(UserForgetPic.getImage().getScaledInstance(backImag.getWidth(), backImag.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        this.getLayeredPane().add(backImag, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
		
        this.getLayeredPane().add(label1);
        this.getLayeredPane().add(label2);
        this.getLayeredPane().add(password);
        this.getLayeredPane().add(password1);
        this.getLayeredPane().add(button1);
        this.getLayeredPane().add(button2);
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(500, 230, 600, 400);
		// setResizable(true);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("确定")) {
			JdbcConnection jdbcconn = new JdbcConnection();// 实例化对象
			Connection conn = jdbcconn.dbConnection();// 调用方法获取conn
			PreparedStatement stat = null;
			String sql1 = "update user set password=? where name=? ";// "?"表示占用符
			if (password.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "密码不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (password1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "确认密码不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (!password.getText().equals(password1.getText())) {
				JOptionPane.showMessageDialog(this, "两次输入的密码不同，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
				password.setText("");
				password1.setText("");
			} else {
				try {
					stat = conn.prepareStatement(sql1);
					// 添加一个参数
					stat.setString(1, password.getText());
					stat.setString(2, username);
					// 更新操作
					stat.executeUpdate();
					// 弹出一个提示框
					JOptionPane.showMessageDialog(this, "密码修改成功,为您跳转到登录界面");
					dispose();
					new UserLogin2();
				} catch (Exception e2) {
					System.out.println(e2);
				}
				if (e.getActionCommand().equals("返回")) {
					dispose();
					new UserLogin2();
				}
			}
		}
	}
}
