package cn.zhetech;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.huitech.GoodManage;
import cn.shutech.WorkerManage;

public class CashRegister extends JFrame implements ActionListener,WindowListener {
	long starttime;
	long endtime;
	int workHours;
	int signInTime = 0;
	int signOutTime = 0;
	String userName;
	int userId;
	JTextField text;
	String time = null;
	JLabel label1 = new JLabel("日期：");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	String startTime;
	String endTime;
	JButton button1 = new JButton("新建购物车");
	JButton button2 = new JButton("签到");
	JButton button3 = new JButton("签退");
	JButton button4 = new JButton("管理商品");
	JButton button5 = new JButton("退出系统");
	JButton button6 = new JButton("管理员工");
	JButton hiddenButton=new JButton("");
	JLabel signInTip = new JLabel("");
	JLabel signOutTip = new JLabel("");
	JLabel workTimeTip = new JLabel("");
	JFrame jFrame=new JFrame();
	ImageIcon CashRegisterPic = new ImageIcon("imag/CashRegisterImag/CashRegisterImag.jpg");

	public CashRegister(String userName, int userId) {
		this.userName = userName;
		this.userId = userId;
		JLabel user = new JLabel("当前登录用户：" + userName);
		JLabel id = new JLabel("当前登录用户id：" + userId);
		text = new JTextField();
		setLayout(null);
		text.setBounds(40, 10, 150, 20);
		jFrame.getContentPane().add(text);
		text.setText(getTime());
		Timer timer = new Timer();
		timer.schedule(new RemindTask(), 0, 1000);
		text.setEditable(false);
		
		jFrame.setTitle("收银台");
        jFrame.setSize(700,400);//设置窗口大小
        jFrame.setLocationRelativeTo(null);//窗口居中
        jFrame.getContentPane().setLayout(null);//无布局，记得下面添加控件时要设置它们位置和大小
        JPanel imPanel=(JPanel) jFrame.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
        JLabel label = new JLabel(CashRegisterPic);//往一个标签中加入图片
        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());//设置标签位置大小，记得大小要和窗口一样大
        CashRegisterPic.setImage(CashRegisterPic.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
		
		label1.setBounds(10, 10, 50, 20);
		label1.setForeground(Color.white);//设置标签字体为白色
		user.setBounds(10, 300, 350, 20);
		user.setForeground(Color.white);//设置标签字体为白色
		id.setBounds(10, 320, 350, 20);
		id.setForeground(Color.white);//设置标签字体为白色
		signInTip.setBounds(10, 40, 550, 20);
		signInTip.setForeground(Color.white);//设置标签字体为白色
		signOutTip.setBounds(10, 70, 550, 20);
		signOutTip.setForeground(Color.white);//设置标签字体为白色
		workTimeTip.setBounds(10, 100, 550, 20);
		workTimeTip.setForeground(Color.white);//设置标签字体为白色
		button1.setBounds(150, 140, 140, 20);
		button1.addActionListener(this);
		button2.setBounds(150, 170, 60, 20);
		button2.addActionListener(this);
		button3.setBounds(230, 170, 60, 20);
		button3.addActionListener(this);
		button4.setBounds(150, 200, 140, 20);
		button4.addActionListener(this);
		button5.setBounds(150, 260, 140, 20);
		button5.addActionListener(this);
		button6.setBounds(150, 230, 140, 20);
		button6.addActionListener(this);
		hiddenButton.setBounds(657, 335, 10, 10);
		hiddenButton.addActionListener(this);
		hiddenButton.setOpaque(false);
		hiddenButton.setContentAreaFilled(false); //隐藏按钮的方法

		jFrame.getContentPane().add(label1);
		jFrame.getContentPane().add(user);
		jFrame.getContentPane().add(id);
		jFrame.getContentPane().add(button1);
		jFrame.getContentPane().add(button2);
		jFrame.getContentPane().add(button3);
		jFrame.getContentPane().add(button4);
		jFrame.getContentPane().add(button5);
		jFrame.getContentPane().add(button6);
		jFrame.getContentPane().add(hiddenButton);
		jFrame.getContentPane().add(signInTip);
		jFrame.getContentPane().add(signOutTip);
		jFrame.getContentPane().add(workTimeTip);
		jFrame.setVisible(true);//设置窗口可见
		//jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//窗口关闭 如果含本代码点击X号没有任何反应
		jFrame.setResizable(false);
		jFrame.addWindowListener(this);
		
//		setTitle("收银台");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(null);
//		setBounds(520, 250, 460, 360);
//		setVisible(true);
	}

	public static int carNum = 0;
	ShopCar s[] = new ShopCar[100];

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JdbcConnection jdbcConn = new JdbcConnection();
		Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
		PreparedStatement stat = null;// 预加载
		if (e.getActionCommand().equals("新建购物车")) {
			carNum++;
			s[carNum] = new ShopCar(carNum);
		}
		if (e.getActionCommand().equals("签到")) {
			signInTime++;
			if (signInTime > 1) {
				JOptionPane.showMessageDialog(null, "扣略！你已经签到过了(ー`´ー)！", "重复签到", JOptionPane.WARNING_MESSAGE);
			} else {
				startTime = df.format(new Date());// new Date()为获取当前系统时间
				signInTip.setText("亲，你已成功签到~(づ￣3￣)づ，签到时间为：" + startTime);
				// 获取参数毫秒数
				try {
					starttime = df.parse(startTime).getTime();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (e.getActionCommand().equals("签退")) {

			if (signOutTime >= 1) {
				JOptionPane.showMessageDialog(null, "扣略！你已经签退过了(ー`´ー)！", "重复签退", JOptionPane.WARNING_MESSAGE);
			} else if (signInTime < 1) {
				JOptionPane.showMessageDialog(null, "亲，你都还没有签到哦！Σ( ° △ °|||)", "未签到", JOptionPane.WARNING_MESSAGE);
			} else {
				signOutTime++;
				endTime = df.format(new Date());// new Date()为获取当前系统时间
				signOutTip.setText("亲，你已成功签退~┏(＾0＾)┛，签退时间为：" + endTime);
				try {
					endtime = df.parse(endTime).getTime();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 两者相减，获得时间差的毫秒数
				long mills = endtime - starttime;

				// 将时间差的毫秒数转化成整个的小时数
				//mills = 20000000;// 测试用
				System.out.println("mills=" + mills);// 如果这里的值不为零 证明时间差计算成功
				workHours = (int) (mills / 1000 / 3600);
				if (workHours == 0) {
					JOptionPane.showMessageDialog(this, "铁咩！你在干嘛？\n给老子好好工作啊( ｀д′)！", "老板说",
							JOptionPane.QUESTION_MESSAGE);
				}
				// 获取数据库中存的工时
				int allHours = 0;
				String sqlId = "select * from user where id ='" + userId + "'";
				try {
					Statement stmt = conn.createStatement();
					ResultSet rsId = stmt.executeQuery(sqlId);
					if (rsId.next()) {
						allHours = workHours + Integer.valueOf(rsId.getString("workhour")).intValue();
						System.out.println("allHours=" + allHours);
					} else {
						System.out.println("error");
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				// 将工时存进数据库
				String sql1 = "update user set workhour=? where id=?";
				try {
					stat = conn.prepareStatement(sql1);
					stat.setInt(1, allHours);
					stat.setInt(2, userId);
					stat.executeLargeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				workTimeTip.setText("亲，你本次的工时为：" + workHours + "，总工时：" + allHours);
			}
		}
		if (e.getActionCommand().equals("管理商品")) {
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
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
		if (e.getActionCommand().equals("管理员工")) {
			//只有老板才能用这个功能
			if(userName.equals("boss")) {
				new WorkerManage();
			}
			else {
				JOptionPane.showMessageDialog(null, "小兄弟，这可不是给你乱按的啊w(ﾟДﾟ)w！", "无权限", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		if (e.getActionCommand().equals("退出系统")) {
			if (endTime == null) {
				JOptionPane.showMessageDialog(null, "兄弟你忘了签退！不要钱了吗！w(ﾟДﾟ)w", "未签退", JOptionPane.WARNING_MESSAGE);
			} else {
				jFrame.dispose();
				new SystemClose();
			}

		}
		if (e.getActionCommand().equals("")) {
			new ColoredEgg();
		}
	}

	public static void main(String[] args) {
		new CashRegister("boss", 1);
	}

	public String getTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = (Date) calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = format.format(date);

		return time;
	}

	private class RemindTask extends TimerTask {
		public void run() {
			text.setText(getTime());
		}
	}
	//窗口监听
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (endTime == null) {
			JOptionPane.showMessageDialog(null, "兄弟你忘了签退！不要钱了吗！w(ﾟДﾟ)w", "未签退", JOptionPane.WARNING_MESSAGE);
			jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//窗口关闭 如果含本代码点击X号没有任何反应
		} else {
			jFrame.dispose();
			new SystemClose();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class SystemClose extends JFrame implements ActionListener {
	public int windowWidth = 640;
	public int windowHeight = 640;
	ImageIcon welcomPic = new ImageIcon("imag/敬礼.jpg");

	public SystemClose() {
		setTitle("欢迎下次使用超市收银系统！");

		JButton button1 = new JButton("掰掰");
		button1.setBounds(280, 350, 70, 30);
		button1.addActionListener(this);
		add(button1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(450, 150, windowWidth, windowHeight);
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(welcomPic.getImage(), 0, 0, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
		System.exit(0);
	}
}
