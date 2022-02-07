package cn.zhetech;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

import cn.xuantech.ForgetPassword;
import cn.xuantech.UserRegister;

public class UserLogin2 extends JFrame implements ActionListener {
	// JPanel panel1 = new JPanel();
	JLabel label1 = new JLabel("请输入用户名：");
	JTextField username = new JTextField("");
	JLabel label2 = new JLabel("请输入密码：");
	JLabel label3 = new JLabel("");// 提示信息
	JTextField password = new JTextField("");
	JButton button1 = new JButton("登录");
	JButton button2 = new JButton("退出");
	JButton button3 = new JButton("注册");
	JButton button4 = new JButton("忘记密码");
	BackImag background;
	BackImag dinosour;
	// JButton button3 = new JButton("退出");
	// 做动态背景！
	static ImageIcon skyImag = new ImageIcon("imag/LoginImag/LoginSky.png");
	static Image groundImag = new ImageIcon("imag/LoginImag/LoginGround.png").getImage();
	static ImageIcon dinoImag = new ImageIcon("imag/LoginImag/Dinosaur1.png");

	public UserLogin2() {
		background = new BackImag();

		label1.setBounds(20, 20, 100, 20);
		label2.setBounds(20, 60, 100, 20);
		label3.setBounds(55, 90, 200, 20);
		username.setBounds(100, 20, 150, 20);
		String info1 = "请输入账号";
		username.setText(info1);
		username.setForeground(Color.GRAY);
		username.addFocusListener(new MyFocusListener(info1, username));
		String info2 = "请输入密码";
		password.setBounds(100, 60, 150, 20);
		password.setText(info2);
		password.setForeground(Color.GRAY);
		password.addFocusListener(new MyFocusListener(info2, password));
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if ((char) e.getKeyChar() == KeyEvent.VK_ENTER) {
					System.out.println("ENTER");
					login();
				}
			}
		});
		button1.setBounds(40, 120, 90, 20);
		button1.addActionListener(this);
		button2.setBounds(160, 120, 90, 20);
		button2.addActionListener(this);
		button3.setBounds(40, 150, 90, 20);
		button3.addActionListener(this);
		button4.setBounds(160, 150, 90, 20);
		button4.addActionListener(this);
//		this.getContentPane().add(panel);
		add(label1);
		add(username);
		add(label2);

		add(password);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(label3);
//		contentPane.add(panel, BorderLayout.CENTER);
//		panel.setBounds(500, 230, 450, 448);  
//		panel.setVisible(true); 
		this.setUndecorated(true);

		setTitle("用户登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		setBounds(630, 300, 280, 190);
		setVisible(true);
//		startTimeTask();
	}

	// 登录的事件处理
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("登录")) {
			int accountFlag=0;
			int passwordFlag=0;
			if(!username.getText().equals("") && !username.getText().equals("请输入账号")) {
				accountFlag++;
			}
			else {
				JOptionPane.showMessageDialog(this, "未输入账号！", "错误", JOptionPane.ERROR_MESSAGE);
			}
			if(!password.getText().equals("") && !password.getText().equals("请输入密码")) {
				passwordFlag++;
			}
			else {
				JOptionPane.showMessageDialog(this, "未输入密码！", "错误", JOptionPane.ERROR_MESSAGE);
			}
			if(accountFlag!=0 && passwordFlag!=0) {
				accountFlag=0;
				passwordFlag=0;
				login();
			}
			
		}
		if (e.getActionCommand().equals("退出")) {
			dispose();
			background.close();
			System.exit(0);
		}
		if (e.getActionCommand().equals("注册")) {
			dispose();
			background.close();
			new UserRegister();
		}
		if (e.getActionCommand().equals("忘记密码")) {
			dispose();
			background.close();
			new ForgetPassword();
		}
	}

	public static void main(String[] args) {
		new Welcom();
	}

	public void login() {
		// 连接数据库
		try {
			JdbcConnection jdbcconn = new JdbcConnection();// 实例化对象
			Connection conn = jdbcconn.dbConnection();// 调用方法获取conn

			Statement stmt = conn.createStatement();
			// select * from user where name ='zhangs1' and password ='123456';
			String sql = "select * from user where name ='" + username.getText() + "'" + "and password ='"
					+ password.getText() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println("身份验证成功");
				label3.setText("身份验证成功");
				int id = Integer.valueOf(rs.getString("id")).intValue();
				dispose();
				new CashRegister(username.getText(), id);
				background.close();
			} else {
				System.out.println("身份验证失败");
				label3.setText("账户名或密码错误，请重新输入！");
				username.setText("");
				password.setText("");
			}
		} catch (Exception e2) {

			System.out.println(e2);
		}
	}

}

class MyFocusListener implements FocusListener {
	String info;
	JTextField jtf;

	public MyFocusListener(String info, JTextField jtf) {
		this.info = info;
		this.jtf = jtf;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		String tmp = jtf.getText();
		if (tmp.equals(info)) {
			jtf.setText("");
			jtf.setForeground(Color.BLUE);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		String tmp = jtf.getText();
		if (tmp.equals("")) {
			jtf.setText(info);
			jtf.setForeground(Color.GRAY);
		}
	}
}

class Welcom extends JFrame implements ActionListener {
	public static int windowWidth = 580;
	public static int windowHeight = 367;
	ImageIcon welcomPic = new ImageIcon("imag/welcomPic.jpg");

	public Welcom() {
		setTitle("欢迎进入超市收银系统！");

		JButton button1 = new JButton("╰(￣▽￣)╭进入系统");
		button1.setBounds(160, 210, 270, 30);
		button1.addActionListener(this);
		add(button1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(450, 250, windowWidth, windowHeight);
		setVisible(true);

	}

	public void paint(Graphics g) {
		g.drawImage(welcomPic.getImage(), 0, 0, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("╰(￣▽￣)╭进入系统")) {
			dispose();
			new UserLogin2();

		}
	}
}

class BackImag extends JFrame {
	int skyX = 0;
	int skyY = 0;
	int groundX = 0;
	int groundY = 0;
	int dinoX = 30;
	int dinoY = 230;
	ImageIcon skyImag;
	Image groundImag;
	ImageIcon[] dinoImag;
	ImageIcon dinosaurImag;
	int skyXSpeed;
	int groundXSpeed;
	int index;

	public BackImag() {
		skyImag = UserLogin2.skyImag;
		groundImag = UserLogin2.groundImag;
		dinosaurImag = UserLogin2.dinoImag;
		skyXSpeed = -3;
		groundXSpeed = -7;
		dinoImag = new ImageIcon[4];

		for (int i = 0; i < dinoImag.length; i++) {

			dinoImag[i] = new ImageIcon("imag/LoginImag/Dinosaur" + (i + 1) + ".png");
//			System.out.println("i=" + i);
		}

		index = 0;

		setTitle("用户登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		setBounds(300, 200, 950, 448);
		setVisible(true);
		startTimeTask();

	}

	public void close() {
		// TODO Auto-generated method stub
		dispose();
	}

	public void paint(Graphics g) {
		g.drawImage(skyImag.getImage(), skyX, skyY, null);
		g.drawImage(groundImag, groundX, groundY, null);
		g.drawImage(dinosaurImag.getImage(), dinoX, dinoY, null);
		update(getGraphics());//跟JPanel不同，JFrame的repaint方法不会自动调用update方法。所以我这边直接让它调用了。

	}

	public void update(Graphics g) {
//		System.out.println("==1==");
//		if (groundImag == null) {
//			//System.out.println("==2==");
//			groundImag = this.createImage(500, 500); // 新建一个图像缓存空间,这里图像大小为800*600
//			Graphics gImage = groundImag.getGraphics(); // 把它的画笔拿过来,给gImage保存着
//			paint(gImage); // 将要画的东西画到图像缓存空间去
//			g.drawImage(groundImag, 0, 0, null); // 然后一次性显示出来
//		}
	}

	public void startTimeTask() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				skyMove();
				groundMove();
				dinoMove();
				repaint();
			}
		};
		timer.schedule(task, 10, 20);
	}

	public void skyMove() {
		this.skyX += skyXSpeed;
		// System.out.println("this.Y="+this.Y);
		if (this.skyX <= -5104) {
			this.skyX = 0;
		}
	}

	public void groundMove() {
		this.groundX += groundXSpeed;
		// System.out.println("this.Y="+this.Y);
		if (this.groundX <= -5104) {
			this.groundX = 0;
		}
	}

	public void dinoMove() {
		index++;
		dinosaurImag = dinoImag[index / 4 % 3];// 0012
//		System.out.println("-----");
//		System.out.println(index);
//		System.out.println(index/4%3);
//		System.out.println("-----");
	}

}
