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
	JLabel label1 = new JLabel("请输入用户名");
	JTextField username = new JTextField("");
	JLabel label2 = new JLabel("请输入电话");
	JTextField telephone = new JTextField("");
	JLabel label3 = new JLabel("请输入验证码");
	JTextField code = new JTextField("");
	JButton button1 = new JButton("确定");
	JButton button2 = new JButton("返回");
	ImageIcon Code = new ImageIcon();
	JLabel label;// 往一个标签中加入图片
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
		button1.addActionListener(this); // 设置窗口监听
		button2.addActionListener(this); // 设置窗口监听
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
		
		label1.setForeground(Color.white);//设置标签字体为白色
		label2.setForeground(Color.white);//设置标签字体为白色
		label3.setForeground(Color.white);//设置标签字体为白色
		backImag.setBounds(0, 0, UserForgetPic.getIconWidth(), UserForgetPic.getIconHeight());
		JPanel imPanel=(JPanel) this.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
		UserForgetPic.setImage(UserForgetPic.getImage().getScaledInstance(backImag.getWidth(), backImag.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        this.getLayeredPane().add(backImag, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
		
        this.getContentPane().add(label1);
        this.getContentPane().add(label2);
        this.getContentPane().add(label3);
        this.getContentPane().add(label);
        this.getContentPane().add(username);
        this.getContentPane().add(telephone);
        this.getContentPane().add(code);
        this.getContentPane().add(button1);
        this.getContentPane().add(button2);
		this.setTitle("忘记密码");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(500, 230, 600, 400);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("确定")) {
			if (username.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "用户名不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (telephone.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "电话不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (code.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "验证码不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (!code.getText().equals(str)) {
				JOptionPane.showMessageDialog(this, "验证码输入错误，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
				code.setText("");
			} else {
				JdbcConnection jdbcconn = new JdbcConnection();// 实例化对象
				Connection conn = jdbcconn.dbConnection();// 调用方法获取conn
				PreparedStatement stat = null;
				String sql1 = "insert into user (sname,telephone) value(?,?)";// "?"表示占用符
				try {
					JdbcConnection jdbcconn1 = new JdbcConnection();// 实例化对象
					Connection conn1 = jdbcconn1.dbConnection();// 调用方法获取conn
					Statement stmt = conn1.createStatement();
					String sql2 = "select * from user where name ='" + username.getText() + "'";
					ResultSet rs1 = stmt.executeQuery(sql2);
					/*if (!rs1.next()) {
						JOptionPane.showMessageDialog(this, "查询不到该用户");
						username.setText("");
					}
					JdbcConnection jdbcconn2 = new JdbcConnection();// 实例化对象
					Connection conn2 = jdbcconn2.dbConnection();// 调用方法获取conn
					Statement stmt2 = conn2.createStatement();
					String sql3 = "select * from user where telephone ='" + telephone.getText() + "'";
					ResultSet rs2 = stmt2.executeQuery(sql3);
					if (!rs2.next()) {
						JOptionPane.showMessageDialog(this, "查询不到该电话");
						telephone.setText("");
					} else */
					if(rs1.next()&&telephone.getText().equals(rs1.getString("telephone"))){
						dispose();
						new ModifyPassword(username.getText());
					}
					else {
						JOptionPane.showMessageDialog(this, "用户名或者电话号码输入错误，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
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