package cn.zhetech;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.huitech.GoodSearch;

//新增购物车，增删改购物车中的商品，查购物车内容，根据购物车内容对后台商品删减，结算总额、清空购物车类，统计工资

public class ShopCar extends JFrame implements ActionListener {
	JdbcConnection jdbcConn = new JdbcConnection();
	Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
	PreparedStatement stat = null;// 预加载
	private int countNum=0;
	JLabel label1 = new JLabel("请输入商品编号：");
	JTextField goodId = new JTextField("");
	JLabel label2 = new JLabel("请输入商品名称：");
	JTextField goodName = new JTextField("");
	JLabel label3 = new JLabel("商品价格：");
	JTextField goodPrice = new JTextField("");
	JLabel label4 = new JLabel("请输入记录编号：");
	JTextField goodRecord = new JTextField("");

	JTextArea showShopcar = new JTextArea("");

	JButton button1 = new JButton("增加");
	JButton button2 = new JButton("删除");
	JButton button3 = new JButton("修改");
	JButton button4 = new JButton("刷新");
	JButton button5 = new JButton("查看商品列表");
	JButton button6 = new JButton("删除购物车");
	JButton button7 = new JButton("结算购物车");
	JLabel warningText = new JLabel("");// 提示信息
	JFrame jFrame=new JFrame();
	ImageIcon shopCarImage=new ImageIcon("imag/ShopCarImage.jpg");
	

	public ShopCar(int carNum) {
		this.carNum = carNum;
		setCar();
		
		jFrame.setTitle("购物车" + carNum + "号");
        jFrame.setSize(780,400);//设置窗口大小
        jFrame.setLocationRelativeTo(null);//窗口居中
        jFrame.getContentPane().setLayout(null);//无布局，记得下面添加控件时要设置它们位置和大小
        JPanel imPanel=(JPanel) jFrame.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
        JLabel label = new JLabel(shopCarImage);//往一个标签中加入图片
        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());//设置标签位置大小，记得大小要和窗口一样大
        shopCarImage.setImage(shopCarImage.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
		
        label1.setBounds(60, 20, 100, 20);
        //label1.setForeground(Color.white);
		label2.setBounds(60, 60, 100, 20);
		//label2.setForeground(Color.white);
		label3.setBounds(60, 100, 100, 20);
		//label3.setForeground(Color.white);
		label4.setBounds(60, 140, 100, 20);
		//label4.setForeground(Color.white);
		goodId.setBounds(160, 20, 150, 20);
		goodName.setBounds(160, 60, 150, 20);
		goodPrice.setBounds(160, 100, 150, 20);
		goodRecord.setBounds(160, 140, 150, 20);
		warningText.setBounds(180, 180, 150, 20);
		button1.setBounds(100, 210, 70, 20);
		button1.addActionListener(this);
		button2.setBounds(180, 210, 70, 20);
		button2.addActionListener(this);
		button3.setBounds(100, 240, 70, 20);
		button3.addActionListener(this);
		button4.setBounds(180, 240, 70, 20);
		button4.addActionListener(this);
		button5.setBounds(100, 270, 150, 20);
		button5.addActionListener(this);
		button6.setBounds(100, 300, 150, 20);
		button6.addActionListener(this);
		button7.setBounds(100, 330, 150, 20);
		button7.addActionListener(this);
		showShopcar.setBounds(340, 20, 400, 330);
		showShopcar.setEditable(false);
        
        
        jFrame.getContentPane().add(label1);
        jFrame.getContentPane().add(goodId);
        jFrame.getContentPane().add(label2);
        jFrame.getContentPane().add(goodName);
        jFrame.getContentPane().add(label3);
        jFrame.getContentPane().add(goodPrice);
        jFrame.getContentPane().add(label4);
        jFrame.getContentPane().add(goodRecord);

        jFrame.getContentPane().add(button1);
        jFrame.getContentPane().add(button2);
        jFrame.getContentPane().add(button3);
        jFrame.getContentPane().add(button4);
        jFrame.getContentPane().add(button5);
        jFrame.getContentPane().add(button6);
        jFrame.getContentPane().add(button7);
        jFrame.getContentPane().add(showShopcar);
        jFrame.getContentPane().add(warningText);
        jFrame.setVisible(true);//设置窗口可见
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//禁用窗口关闭 如果含本代码点击X号没有任何反应
		jFrame.setResizable(false);
//		setTitle("购物车" + carNum + "号");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(null);
//		setBounds(450, 200, 780, 410);
//		setVisible(true);
//		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);

	}



	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("删除购物车")) {
			
			int n=0;//是=0；否=1
			n = JOptionPane.showConfirmDialog(null, "你确定要删除购物车吗?", "震惊(ΩДΩ)！",JOptionPane.YES_NO_OPTION); //返回值为0或1
			
			System.out.println("n="+n);
			if(n==0) {
				goodName.setText("");
				goodPrice.setText("");
				goodId.setText("");

				String delete = "DROP TABLE shopcar" + carNum;
				try {
					stat = conn.prepareStatement(delete);
					stat.executeUpdate(delete);
					// carNum--;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jFrame.dispose();
			}
			else {
				//啥都不干
			}
			
			
		}
		if (e.getActionCommand().equals("增加")) {
			
			String sql1 = "insert into shopcar" + carNum + " (goodid,goodname,goodprice) values(?,?,?)";
			// "?"表示占位符
			// 增加商品编号的时候要跟商品数据库比对
			// 可以只输入编号添加进购物车（跟商品数据库链接）
			// 判空
			//仅允许输入商品编号
			if(goodRecord.getText().equals("")) {
				if(!goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")) {
					try {
						String check = "select * from good where id='" + goodId.getText() + "'";
						Statement stmt = conn.createStatement();
						ResultSet rsCheck = stmt.executeQuery(check);
						if (rsCheck.next()) {
							
							stat = conn.prepareStatement(sql1);
							// 添加3个参数
							
							stat.setInt(1, Integer.valueOf(goodId.getText()).intValue());
							stat.setString(2, rsCheck.getString("name"));
							stat.setDouble(3, Double.valueOf(rsCheck.getString("price")).doubleValue());
							// 更新操作
							stat.executeLargeUpdate();
							// 弹出一个提示框
							JOptionPane.showMessageDialog(this, "增加了一条记录");
							System.out.println("增加了一条记录");
							Refresh();
							cleanText();
						}
						else {
							JOptionPane.showMessageDialog(this, "未查到该编号商品！", "错误", JOptionPane.ERROR_MESSAGE);
							cleanText();
						}
						
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
				else if(!goodId.getText().equals("") && (!goodName.getText().equals("") || !goodPrice.getText().equals(""))) {
					JOptionPane.showMessageDialog(this, "添加商品进入购物车时仅允许通过商品编号添加！", "错误", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
				else {
					JOptionPane.showMessageDialog(this, "未输入商品编号！", "错误", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "添加商品进入购物车时仅允许通过商品编号添加！\n记录编号只能用于修改以及删除购物车内容", "错误", JOptionPane.ERROR_MESSAGE);
				cleanText();
			}
			
		}
		// 删除图书信息
		if (e.getActionCommand().equals("删除")) {
			if (!goodRecord.getText().equals("") && goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")) {
				String delete = "delete from shopcar" + carNum + " where goodrecord=?";
				String check = "select * from shopcar" + carNum + " where goodrecord='" + goodRecord.getText() + "'";
				// 备忘：删除后编号要递减
				try {
					Statement stmt = conn.createStatement();
					ResultSet rsCheck = stmt.executeQuery(check);
					if (rsCheck.next()) {
						System.out.println("delete");
						stat = conn.prepareStatement(delete);
						stat.setInt(1, Integer.valueOf(goodRecord.getText()).intValue());
						// 更新操作
						stat.executeLargeUpdate();
						// 弹出一个提示框
						JOptionPane.showMessageDialog(this, "删除了一条记录");
						System.out.println("删除了一个记录");
						
						//重新设置主键，好让删除后的商品记录编号可以继续正常增加走而不会中间空一段
						//1,删除原有主键：
						String deleteKey="ALTER TABLE `shopcar" + carNum + "` DROP `goodrecord`;";
						stat = conn.prepareStatement(deleteKey);
						stat.executeLargeUpdate();
						//2,添加新主键字段：
						String addKey="ALTER TABLE `shopcar" + carNum + "` ADD `goodrecord` int NOT NULL FIRST;";
						stat = conn.prepareStatement(addKey);
						stat.executeLargeUpdate();
						//3,设置新主键：
						String newKey="ALTER TABLE `shopcar" + carNum + "` MODIFY COLUMN `goodrecord` int NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY(goodrecord);";
						stat = conn.prepareStatement(newKey);
						// 更新操作
						stat.executeLargeUpdate();
						
						Refresh();
					} else {
						JOptionPane.showMessageDialog(this, "不存在该记录！", "错误", JOptionPane.ERROR_MESSAGE);
					}
					cleanText();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			} 
			else if(goodRecord.getText().equals("")&& goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")){
				JOptionPane.showMessageDialog(this, "未输入记录编号！", "错误", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "删除时仅允许通过记录编号删除！", "错误", JOptionPane.ERROR_MESSAGE);
			}
			cleanText();
		}
		// 修改
		if (e.getActionCommand().equals("修改")) {
			//不输入默认不修改
			String check = "select * from shopcar" + carNum + " where goodrecord='" + goodRecord.getText() + "'";
			
			// "?"表示占位符
			if(goodId.getText().equals("")) {
				if(!goodRecord.getText().equals("")) {
					String modify = "update shopcar" + carNum + " set goodname=?,goodprice=? where goodrecord="+ goodRecord.getText();
					try {
						Statement stmt = conn.createStatement();
						ResultSet rsCheck = stmt.executeQuery(check);
						if (rsCheck.next()) {
							stat = conn.prepareStatement(modify);
							// 添加参数,判空
							
							if (!goodName.getText().equals("")) {
								stat.setString(1, goodName.getText());
							} else {
								stat.setString(1, rsCheck.getString("goodName"));
							}
							if (!goodPrice.getText().equals("")) {
								stat.setDouble(2, Double.valueOf(goodPrice.getText()).doubleValue());
							} else {
								stat.setDouble(2, Double.valueOf(rsCheck.getString("goodprice")).doubleValue());
							}
							// 更新操作
							stat.executeLargeUpdate();
							// 弹出一个提示框
							JOptionPane.showMessageDialog(this, "修改了一条记录");
							System.out.println("修改了一条记录");
							Refresh();
						} else {
							JOptionPane.showMessageDialog(this, "不存在该记录！", "错误", JOptionPane.ERROR_MESSAGE);
						}
						cleanText();
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "请输入要修改的购物车中的商品记录编号！", "错误", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "商品ID无法更改！", "错误", JOptionPane.ERROR_MESSAGE);
				cleanText();
			}
			
		}
		if (e.getActionCommand().equals("刷新")) {
			Refresh();
		}
		if (e.getActionCommand().equals("查看商品列表")) {
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GoodSearch frame = new GoodSearch();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		if (e.getActionCommand().equals("结算购物车")) {
			countNum++;
			int n=0;//是=0；否=1
			if(countNum>1) {
				n = JOptionPane.showConfirmDialog(null, "已经结算过了，你确定要再次结算吗?", "震惊(ΩДΩ)！",JOptionPane.YES_NO_OPTION); //返回值为0或1
			}
			System.out.println("n="+n);
			if(n==0) {
				new CountShopCar(carNum);
			}
			else {
				//啥都不干
			}
			
		}

	}

	public void cleanText() {
		goodId.setText("");
		goodName.setText("");
		goodPrice.setText("");
		goodRecord.setText("");
	}

	public void Refresh() {
		String search = "select * from shopcar" + carNum;
		String info = "";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsCheck = stmt.executeQuery(search);
			while (rsCheck.next()) {
				info += "记录编号：" + rsCheck.getString("goodrecord")
				+ "，商品编号：" + rsCheck.getString("goodid")
				+ "，商品名称："+ rsCheck.getString("goodname") 
				+ "，商品价格：" + rsCheck.getString("goodprice") + "\n";
				showShopcar.setText(info);

			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}

	public static void main(String[] args) {
		// new ShopCar();
	}

	public int carNum = 0;

	public void setCar() {
		// carNum++;
		String check = "show tables like 'shopcar" + carNum + "';";// 只能用show table，不然select会报错
		String delete = "DROP TABLE shopcar" + carNum;
		String set = "CREATE TABLE shopcar" + carNum + " (" + "`goodrecord` INT UNSIGNED AUTO_INCREMENT,"
				+ "`goodid` INT(11)not null," + "`goodname` VARCHAR(255)not null,"
				+ "`goodprice` decimal(11,2)not null," + "PRIMARY KEY ( `goodrecord` )"
				+ ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		try {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
			Statement stmt = conn.createStatement();
			System.out.println("check1");
			ResultSet rsCheck = stmt.executeQuery(check);
			System.out.println("check2");
			if (rsCheck.next()) {
				System.out.println("delete");
				stat = conn.prepareStatement(delete);
				stat.executeLargeUpdate();
			}
			System.out.println("creat");
			stat = conn.prepareStatement(set);
			stat.executeUpdate(set);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

class CountShopCar extends JFrame implements ActionListener{
	JLabel money = new JLabel("");
	JButton alipay = new JButton("AliPay");
	JButton wechatpay = new JButton("WeChatPay");
	private int carNum;
	private double allMoney;
	ImageIcon PayWayPic = new ImageIcon("imag/PayWay.jpg");
	JFrame jFrame=new JFrame(); //创建一个窗口
	public CountShopCar(int carNum) {
		this.carNum = carNum;
		countMoney();
	}

	public void countMoney() {
		try {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
			Statement stmt = conn.createStatement();
			String search = "select * from shopcar" + carNum;
			ResultSet rsCheck = stmt.executeQuery(search);
			int flag=0;
			while (rsCheck.next()) {
				flag++;
				allMoney += Double.valueOf(rsCheck.getString("goodprice")).doubleValue();
			}
			if(flag==0) {
				//dispose();
				JOptionPane.showMessageDialog(this, "购物车为空！", "错误", JOptionPane.ERROR_MESSAGE);
			}
			else {
				
				jFrame.setTitle("购物车" + carNum + "号");
		        jFrame.setSize(400,600);//设置窗口大小
		        jFrame.setLocationRelativeTo(null);//窗口居中
		        jFrame.getContentPane().setLayout(null);//无布局，记得下面添加控件时要设置它们位置和大小
		        JPanel imPanel=(JPanel) jFrame.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
		        imPanel.setOpaque(false);//将内容面板设为透明
		        JLabel label = new JLabel(PayWayPic);//往一个标签中加入图片
		        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());//设置标签位置大小，记得大小要和窗口一样大
		        PayWayPic.setImage(PayWayPic.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
		        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
		        
		        alipay.setBounds(60, 330, 120, 20);
				alipay.addActionListener(this);
				wechatpay.setBounds(210, 330, 120, 20);
				wechatpay.addActionListener(this);
				jFrame.getContentPane().add(money);
				jFrame.getContentPane().add(alipay);
				jFrame.getContentPane().add(wechatpay);
		        money.setText("商品总价为："+allMoney);
		        money.setFont(new Font("方正舒体", Font.PLAIN, 20));//设置字体
		        money.setForeground(Color.white);//设置标签字体颜色
		        money.setBounds(jFrame.getWidth()/4,jFrame.getHeight()/2-28,jFrame.getWidth()/2,50);//设置标签位置和大小
		        money.setHorizontalAlignment(JLabel.CENTER);//设置标签文字水平居中
		        jFrame.getContentPane().add(money);//将一个标签添加到内容面板
		        jFrame.setVisible(true);//设置窗口可见
				
				
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("allMoney="+allMoney);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("AliPay")) {
			jFrame.dispose();
			new AliPay(carNum);
		}
		if (e.getActionCommand().equals("WeChatPay")) {
			jFrame.dispose();
			new WeChatPay(carNum);
		}
	}
	
}

class AliPay extends JFrame implements ActionListener{
	JButton done=new JButton("支付完成");
	ImageIcon AliPayPic = new ImageIcon("imag/Gathering/AliPay.png");
	public AliPay(int carNum) {
		done.setBounds(160, 80, 120, 20);
		done.addActionListener(this);
		add(done);
		setTitle("购物车" + carNum + "号");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(550, 140, 450, 638);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//禁用窗口关闭 如果含本代码点击X号没有任何反应
	}
	public void paint(Graphics g) {
		g.drawImage(AliPayPic.getImage(), 0, 20, null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("支付完成")) {
			dispose();
		}
	}
	
}
class WeChatPay extends JFrame implements ActionListener{
	JButton done=new JButton("支付完成");
	ImageIcon WeChatPayPic = new ImageIcon("imag/Gathering/WeChatPay.png");
	public WeChatPay(int carNum) {
		done.setBounds(150, 450, 120, 20);
		done.addActionListener(this);
		add(done);
		setTitle("购物车" + carNum + "号");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(550, 140, 450, 638);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//禁用窗口关闭 如果含本代码点击X号没有任何反应
		setVisible(true);
	}
	public void paint(Graphics g) {
		g.drawImage(WeChatPayPic.getImage(), 0, 20, null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("支付完成")) {
			dispose();
		}
	}
	
}
