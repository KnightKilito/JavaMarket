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
	JLabel label1 = new JLabel("�������û�����");
	JTextField username = new JTextField("");
	JLabel label2 = new JLabel("���������룺");
	JLabel label3 = new JLabel("");// ��ʾ��Ϣ
	JTextField password = new JTextField("");
	JButton button1 = new JButton("��¼");
	JButton button2 = new JButton("�˳�");
	JButton button3 = new JButton("ע��");
	JButton button4 = new JButton("��������");
	BackImag background;
	BackImag dinosour;
	// JButton button3 = new JButton("�˳�");
	// ����̬������
	static ImageIcon skyImag = new ImageIcon("imag/LoginImag/LoginSky.png");
	static Image groundImag = new ImageIcon("imag/LoginImag/LoginGround.png").getImage();
	static ImageIcon dinoImag = new ImageIcon("imag/LoginImag/Dinosaur1.png");

	public UserLogin2() {
		background = new BackImag();

		label1.setBounds(20, 20, 100, 20);
		label2.setBounds(20, 60, 100, 20);
		label3.setBounds(55, 90, 200, 20);
		username.setBounds(100, 20, 150, 20);
		String info1 = "�������˺�";
		username.setText(info1);
		username.setForeground(Color.GRAY);
		username.addFocusListener(new MyFocusListener(info1, username));
		String info2 = "����������";
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

		setTitle("�û���¼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		setBounds(630, 300, 280, 190);
		setVisible(true);
//		startTimeTask();
	}

	// ��¼���¼�����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("��¼")) {
			int accountFlag=0;
			int passwordFlag=0;
			if(!username.getText().equals("") && !username.getText().equals("�������˺�")) {
				accountFlag++;
			}
			else {
				JOptionPane.showMessageDialog(this, "δ�����˺ţ�", "����", JOptionPane.ERROR_MESSAGE);
			}
			if(!password.getText().equals("") && !password.getText().equals("����������")) {
				passwordFlag++;
			}
			else {
				JOptionPane.showMessageDialog(this, "δ�������룡", "����", JOptionPane.ERROR_MESSAGE);
			}
			if(accountFlag!=0 && passwordFlag!=0) {
				accountFlag=0;
				passwordFlag=0;
				login();
			}
			
		}
		if (e.getActionCommand().equals("�˳�")) {
			dispose();
			background.close();
			System.exit(0);
		}
		if (e.getActionCommand().equals("ע��")) {
			dispose();
			background.close();
			new UserRegister();
		}
		if (e.getActionCommand().equals("��������")) {
			dispose();
			background.close();
			new ForgetPassword();
		}
	}

	public static void main(String[] args) {
		new Welcom();
	}

	public void login() {
		// �������ݿ�
		try {
			JdbcConnection jdbcconn = new JdbcConnection();// ʵ��������
			Connection conn = jdbcconn.dbConnection();// ���÷�����ȡconn

			Statement stmt = conn.createStatement();
			// select * from user where name ='zhangs1' and password ='123456';
			String sql = "select * from user where name ='" + username.getText() + "'" + "and password ='"
					+ password.getText() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println("�����֤�ɹ�");
				label3.setText("�����֤�ɹ�");
				int id = Integer.valueOf(rs.getString("id")).intValue();
				dispose();
				new CashRegister(username.getText(), id);
				background.close();
			} else {
				System.out.println("�����֤ʧ��");
				label3.setText("�˻���������������������룡");
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
		setTitle("��ӭ���볬������ϵͳ��");

		JButton button1 = new JButton("�t(������)�q����ϵͳ");
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
		if (e.getActionCommand().equals("�t(������)�q����ϵͳ")) {
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

		setTitle("�û���¼");
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
		update(getGraphics());//��JPanel��ͬ��JFrame��repaint���������Զ�����update���������������ֱ�����������ˡ�

	}

	public void update(Graphics g) {
//		System.out.println("==1==");
//		if (groundImag == null) {
//			//System.out.println("==2==");
//			groundImag = this.createImage(500, 500); // �½�һ��ͼ�񻺴�ռ�,����ͼ���СΪ800*600
//			Graphics gImage = groundImag.getGraphics(); // �����Ļ����ù���,��gImage������
//			paint(gImage); // ��Ҫ���Ķ�������ͼ�񻺴�ռ�ȥ
//			g.drawImage(groundImag, 0, 0, null); // Ȼ��һ������ʾ����
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
