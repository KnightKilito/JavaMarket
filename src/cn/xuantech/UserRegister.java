package cn.xuantech;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.zhetech.JdbcConnection;
import cn.zhetech.UserLogin2;

public class UserRegister extends JFrame implements ActionListener {
	JPanel panel1 = new JPanel();
	JLabel label1 = new JLabel("用户名：");
	JTextField username = new JTextField("");
	JLabel label2 = new JLabel("设置密码：");
	JTextField password = new JTextField("");
	JLabel label3 = new JLabel("确认密码");
	JTextField password1 = new JTextField("");
	JLabel label4 = new JLabel("手机号码：");
	JTextField telephone = new JTextField("");
	JButton button1 = new JButton("立即注册");
	JButton button2 = new JButton("返回");
	ImageIcon UserRegisterPic = new ImageIcon("imag/UserRegisterImag/UserRegisterImag.jpg");
	JLabel backImag=new JLabel(UserRegisterPic);

	public UserRegister() {
		label1.setBounds(60, 40, 80, 30);
		label2.setBounds(60, 80, 80, 30);
		label3.setBounds(60, 120, 80, 30);
		label4.setBounds(60, 160, 80, 30);
		label1.setForeground(Color.white);//设置标签字体为白色
		label2.setForeground(Color.white);//设置标签字体为白色
		label3.setForeground(Color.white);//设置标签字体为白色
		label4.setForeground(Color.white);//设置标签字体为白色
		backImag.setBounds(0, 0, UserRegisterPic.getIconWidth(), UserRegisterPic.getIconHeight());
		username.setBounds(160, 40, 150, 30);
		password.setBounds(160, 80, 150, 30);
		password1.setBounds(160, 120, 150, 30);
		telephone.setBounds(160, 160, 150, 30);
		button1.setBounds(150, 210, 100, 30);
		button2.setBounds(150, 250, 100, 30);

		button1.addActionListener(this); // 设置窗口监听
		button2.addActionListener(this); // 设置窗口监听
		
		JPanel imPanel=(JPanel) this.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
		UserRegisterPic.setImage(UserRegisterPic.getImage().getScaledInstance(backImag.getWidth(), backImag.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        this.getLayeredPane().add(backImag, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
		
        this.getContentPane().add(label1);
        this.getContentPane().add(label2);
        this.getContentPane().add(label3);
        this.getContentPane().add(label4);
        this.getContentPane().add(username);
        this.getContentPane().add(password);
        this.getContentPane().add(password1);
        this.getContentPane().add(telephone);
        this.getContentPane().add(button1);
        this.getContentPane().add(button2);
		setTitle("注册用户信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(500, 230, 410, 350);
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("立即注册")) {
			String str = telephone.getText();
			String pat = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
			Pattern p = Pattern.compile(pat);
			Matcher m = p.matcher(str);
			if (username.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "用户名不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			}
			/*else if(){	
				JOptionPane.showMessageDialog(this, "用户名已存在，请重新输入");
			}*/
			else if (password.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "密码不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (password1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "确认密码不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (telephone.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "电话不能为空，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
			} else if (!password1.getText().equals(password.getText())) {
				JOptionPane.showMessageDialog(this, "两次输入的密码不同，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
				password.setText("");
				password1.setText("");
			} else if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "电话输入格式错误，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
				telephone.setText("");
			} else {
				JdbcConnection jdbcconn = new JdbcConnection();// 实例化对象
				System.out.println("123");
				Connection conn = jdbcconn.dbConnection();// 调用方法获取conn
				PreparedStatement stat = null;
				String sql1 = "insert into user (name,password,telephone,workhour,salary) value(?,?,?,0,0)";// "?"表示占用符
				try {
					JdbcConnection jdbcconn1 = new JdbcConnection();// 实例化对象
					Connection conn1 = jdbcconn1.dbConnection();// 调用方法获取conn
					Statement stmt = conn1.createStatement();
					String sql2 = "select * from user where name ='" + username.getText() + "'";
					ResultSet rs1 = stmt.executeQuery(sql2);
					if (rs1.next()) {
						JOptionPane.showMessageDialog(this, "用户名已存在，请重新输入");
						username.setText("");
					}
					JdbcConnection jdbcconn2 = new JdbcConnection();// 实例化对象
					Connection conn2 = jdbcconn2.dbConnection();// 调用方法获取conn
					Statement stmt2 = conn2.createStatement();
					String sql3 = "select * from user where telephone ='" + telephone.getText() + "'";
					ResultSet rs2 = stmt2.executeQuery(sql3);
					if(rs2.next()) {
						JOptionPane.showMessageDialog(this, "电话已被使用，请更换手机号码");
						telephone.setText("");
					}
					else {
						try {
							stat = conn.prepareStatement(sql1);
							stat.setString(1, username.getText());//
							stat.setString(2, password.getText());
							stat.setString(3, telephone.getText());
							// 更新操作
							stat.executeUpdate();
							// 弹出一个提示框
							JOptionPane.showMessageDialog(this, "恭喜您注册成功，接下来为您跳转到登录界面");
							dispose();
							new UserLogin2();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} catch (Exception e2) {

					System.out.println(e2);
				}
			}
		}
		if (e.getActionCommand().equals("返回"))
		{
			dispose();
			new UserLogin2();
		}
		
	}

	public static void main(String[] args) {
		new UserRegister();
	}
}
