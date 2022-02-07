//package supermarket;
package cn.shutech;

import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.zhetech.JdbcConnection;

public class WorkerManage extends JFrame implements ActionListener {
	JLabel label1 = new JLabel("打工人编号：");
	JTextField id = new JTextField("");
	JLabel label2 = new JLabel("打工人姓名：");
	JTextField name = new JTextField("");
	JLabel label3 = new JLabel("打工人工时：");
	JTextField workhour = new JTextField("");
	JLabel label4 = new JLabel("打工人工资：");
	JTextField salary = new JTextField("");
	JLabel label5 = new JLabel("打工人电话号码：");
	JTextField telephone = new JTextField("");
	JLabel label6 = new JLabel("打工人密码：");
	JTextField password = new JTextField("");

	JButton button1 = new JButton("增加");
	JButton button2 = new JButton("删除");
	JButton button3 = new JButton("修改");
	JButton button4 = new JButton("查询");
	JButton button5 = new JButton("计算工资");
	JButton button6 = new JButton("清零工时");
	JButton button7 = new JButton("刷新员工信息");
	JButton button8 = new JButton("退出当前页面");

	JLabel label7 = new JLabel("");// 提示信息

	JTextArea showWorker = new JTextArea("");
	JFrame jFrame = new JFrame();
	ImageIcon ManageWorkerPic = new ImageIcon("imag/WorkerManagementImag/WorkerManagement.jpg");
	JdbcConnection jdbcConn = new JdbcConnection();
	Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
	PreparedStatement stat = null;// 预加载

	public WorkerManage() {

		jFrame.setTitle("打工人信息管理");
		jFrame.setSize(900, 501);// 设置窗口大小
		jFrame.setLocationRelativeTo(null);// 窗口居中
		jFrame.getContentPane().setLayout(null);// 无布局，记得下面添加控件时要设置它们位置和大小
		JPanel imPanel = (JPanel) jFrame.getContentPane();// 注意内容面板必须强转为JPanel才可以实现下面的设置透明
		imPanel.setOpaque(false);// 将内容面板设为透明
		JLabel label = new JLabel(ManageWorkerPic);// 往一个标签中加入图片
		label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());// 设置标签位置大小，记得大小要和窗口一样大
		ManageWorkerPic.setImage(
				ManageWorkerPic.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));// 图片自适应标签大小
		jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));// 标签添加到层面板

		label1.setBounds(60, 20, 100, 20);
		label2.setBounds(60, 60, 100, 20);
		label3.setBounds(60, 100, 100, 20);
		label4.setBounds(60, 140, 100, 20);
		label5.setBounds(60, 180, 100, 20);
		label6.setBounds(60, 220, 100, 20);
		id.setBounds(160, 20, 150, 20);
		name.setBounds(160, 60, 150, 20);
		workhour.setBounds(160, 100, 150, 20);
		salary.setBounds(160, 140, 150, 20);
		telephone.setBounds(160, 180, 150, 20);
		password.setBounds(160, 220, 150, 20);
		button1.setBounds(100, 260, 70, 20);
		button1.addActionListener(this);
		button2.setBounds(180, 260, 70, 20);
		button2.addActionListener(this);
		button3.setBounds(100, 290, 70, 20);
		button3.addActionListener(this);
		button4.setBounds(180, 290, 70, 20);
		button4.addActionListener(this);
		button5.setBounds(105, 330, 140, 20);
		button5.addActionListener(this);
		button6.setBounds(105, 360, 140, 20);
		button6.addActionListener(this);
		button7.setBounds(105, 390, 140, 20);
		button7.addActionListener(this);
		button8.setBounds(105, 420, 140, 20);
		button8.addActionListener(this);

		showWorker.setBounds(340, 20, 500, 330);
		showWorker.setEditable(false);

		jFrame.getContentPane().add(label1);
		jFrame.getContentPane().add(id);
		jFrame.getContentPane().add(label2);
		jFrame.getContentPane().add(name);
		jFrame.getContentPane().add(label3);
		jFrame.getContentPane().add(workhour);
		jFrame.getContentPane().add(label4);
		jFrame.getContentPane().add(label5);
		jFrame.getContentPane().add(salary);
		jFrame.getContentPane().add(label6);
		jFrame.getContentPane().add(telephone);
		jFrame.getContentPane().add(label7);
		jFrame.getContentPane().add(password);
		jFrame.getContentPane().add(button1);
		jFrame.getContentPane().add(button2);
		jFrame.getContentPane().add(button3);
		jFrame.getContentPane().add(button4);
		jFrame.getContentPane().add(button5);
		jFrame.getContentPane().add(button6);
		jFrame.getContentPane().add(button7);
		jFrame.getContentPane().add(button8);
		jFrame.getContentPane().add(label7);
		jFrame.getContentPane().add(showWorker);
		jFrame.setVisible(true);// 设置窗口可见
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// 窗口关闭 如果不含本代码点击X号没有任何反应

	}

	public static void main(String[] args) {
		new WorkerManage();
	}

	public void clear() {
		id.setText("");
		name.setText("");
		workhour.setText("");
		salary.setText("");
		telephone.setText("");
		password.setText("");
	}

	public void Refresh() {// 刷新员工记录
		String search = "select * from user";
		String info = "";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rsCheck = stmt.executeQuery(search);
			while (rsCheck.next()) {
				info += "ID：" + rsCheck.getString("id") + "，姓名：" + rsCheck.getString("name") + "，工时："
						+ rsCheck.getString("workhour") + "，工资：" + rsCheck.getString("salary") + "，电话号码："
						+ rsCheck.getString("telephone") + "\n";
				showWorker.setText(info);

			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("退出当前页面")) {
			jFrame.dispose();
		}
		if (e.getActionCommand().equals("刷新员工信息")) {
			Refresh();
		}
		if (e.getActionCommand().equals("增加")) {

			String sql1 = "insert into user (name,workhour,salary,telephone,password) values(?,?,?,?,?)";
			// "?"表示占位符
			if (id.getText().equals("")) {
				try {
					String str = telephone.getText();
					String pat = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
					Pattern p = Pattern.compile(pat);
					Matcher m = p.matcher(str);
					stat = conn.prepareStatement(sql1);
					// 添加5个参数
					// 电话号码和姓名为String类型，为空不会抛出异常，所以使用if语句
					stat.setString(1, name.getText());// 获取打工人姓名的文本值
					if (name.getText().equals(""))// 判断是否为姓名空
					{
						JOptionPane.showMessageDialog(this, "请输入正确的姓名", "错误", JOptionPane.ERROR_MESSAGE);
					}
					try {
						stat.setInt(2, Integer.valueOf(workhour.getText()).intValue());// 获取打工人工时的文本值
					} catch (Exception a3) {
						System.out.println(a3);
						JOptionPane.showMessageDialog(this, "请输入正确的工时", "错误", JOptionPane.ERROR_MESSAGE);
					}
					try {
						stat.setDouble(3, Double.valueOf(salary.getText()).doubleValue());// 获取打工人工资的文本值
					} catch (Exception a5) {
						System.out.println(a5);
						JOptionPane.showMessageDialog(this, "请输入正确的工资", "错误", JOptionPane.ERROR_MESSAGE);
					}
					stat.setString(4, telephone.getText());// 获取打工人电话号码的文本值
					if (!m.matches()) {
						JOptionPane.showMessageDialog(this, "电话输入格式错误，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
						telephone.setText("");
					}
					stat.setString(5, password.getText());
					if (password.getText().equals(""))// 判断是否为姓名空
					{
						JOptionPane.showMessageDialog(this, "密码不得为空", "错误", JOptionPane.ERROR_MESSAGE);
					}
					// 更新操作
					stat.executeLargeUpdate();
					// 弹出一个提示框
					JOptionPane.showMessageDialog(this, "增加了一个打工人信息");
					Refresh();
					clear();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			} else {
				JOptionPane.showMessageDialog(this, "增加打工人信息时无需填写编号", "错误", JOptionPane.ERROR_MESSAGE);
				id.setText("");
			}
		}
		// 删除操作
		if (e.getActionCommand().equals("删除")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
			PreparedStatement stat = null;// 预加载
			String sql1 = "delete from user where id=?";
			// "?"表示占位符
			int n = 0;// 是=0；否=1
			n = JOptionPane.showConfirmDialog(null, "确定删除该员工吗？", "提示", JOptionPane.YES_NO_OPTION); // 返回值为0或1
			System.out.println("n=" + n);
			if (n == 0) {
				if (id.getText().equals("1")) {
					JOptionPane.showMessageDialog(this, "你小子不能删除自己啊!你是顽童吗?\nˋ( ° ▽、° ) (o(￣皿￣///(斩!!)", "错误",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (name.getText().equals("") && workhour.getText().equals("") && salary.getText().equals("")
							&& password.getText().equals("")) {
						try {
							stat = conn.prepareStatement(sql1);
							// 添加1个参数
							stat.setInt(1, Integer.valueOf(id.getText()).intValue());// 获取打工人编号的文本值
							// 更新操作
							stat.executeLargeUpdate();
							// 弹出一个提示框
							JOptionPane.showMessageDialog(this, "删除了一个打工人信息");
							Refresh();
							clear();
						} catch (Exception e2) {
							System.out.println(e2);
							JOptionPane.showMessageDialog(this, "输入错误", "错误", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(this, "删除信息时只允许输入打工人编号", "错误", JOptionPane.ERROR_MESSAGE);// 输入编号以外的元素时提示错误
						id.setText("");
						name.setText("");
						workhour.setText("");
						salary.setText("");
						telephone.setText("");
						password.setText("");
					}
				}
			} else {
				Refresh();
			}
		}
		// 修改语句
		if (e.getActionCommand().equals("修改")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
			PreparedStatement stat = null;// 预加载
			String sql1 = "update user set name=?,workhour=?,salary=?,telephone=?,password=? where id=?";
			// "?"表示占位符
			try {
				String str = telephone.getText();
				String pat = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";
				Pattern p = Pattern.compile(pat);
				Matcher m = p.matcher(str);
				stat = conn.prepareStatement(sql1);
				stat.setString(1, name.getText());// 获取打工人姓名的文本值
				if (!name.getText().equals(""))// 判断是否为姓名空
				{
					stat.setString(5, password.getText());
					if (!password.getText().equals(""))// 判断是否为姓名空
					{
						try {
							stat.setInt(2, Integer.valueOf(workhour.getText()).intValue());// 获取打工人工时的文本值
						} catch (Exception a3) {
							System.out.println(a3);
							JOptionPane.showMessageDialog(this, "请输入正确的工时", "错误", JOptionPane.ERROR_MESSAGE);
						}
						try {
							stat.setDouble(3, Double.valueOf(salary.getText()).doubleValue());// 获取打工人工资的文本值
						} catch (Exception a5) {
							System.out.println(a5);
							JOptionPane.showMessageDialog(this, "请输入正确的工资", "错误", JOptionPane.ERROR_MESSAGE);
						}
						stat.setString(4, telephone.getText());// 获取打工人电话号码的文本值
						if (!m.matches()) {
							JOptionPane.showMessageDialog(this, "电话输入格式错误，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
							telephone.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(this, "密码不得为空", "错误", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "请输入正确的姓名", "错误", JOptionPane.ERROR_MESSAGE);
				}
				stat.setInt(6, Integer.valueOf(id.getText()).intValue());
				// 更新操作
				stat.executeLargeUpdate();
				// 弹出一个提示框
				Refresh();
				JOptionPane.showMessageDialog(this, "修改了一个记录");
				clear();
			} catch (Exception e2) {
				System.out.println(e2);
				JOptionPane.showMessageDialog(this, "请输入所有参数", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
		// 查询语句
		if (e.getActionCommand().equals("查询")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sql1 = "select * from user where id ='" + id.getText() + "'";
			try {
				ResultSet rs = stmt.executeQuery(sql1);
				if (rs.next()) {
					// 弹出一个提示框
					JOptionPane.showMessageDialog(this,
							"打工人编号：" + rs.getString("id") + "\n打工人姓名" + rs.getString("name") + "\n打工人工时"
									+ rs.getString("workhour") + "\n打工人工资" + rs.getString("salary") + "\n打工人电话号码"
									+ rs.getString("telephone") + "\n打工人密码" + rs.getString("password"));
					System.out.println("查询到了一个打工人信息");
					clear();
					Refresh();
				} else {
					JOptionPane.showMessageDialog(this, "未查询到信息", "错误", JOptionPane.ERROR_MESSAGE);
					System.out.println("未查询到信息");
					id.setText("");
					name.setText("");
					workhour.setText("");
					salary.setText("");
					telephone.setText("");
					password.setText("");
				}
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		// 计算工资
		if (e.getActionCommand().equals("计算工资")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String sql1 = "select * from user where id ='" + id.getText() + "'";// 通过ID搜索对象数据
			if (name.getText().equals("") && workhour.getText().equals("") && salary.getText().equals("")) {
				try {
					ResultSet rs = stmt.executeQuery(sql1);
					if (rs.next()) {
						PreparedStatement stat = null;// 预加载
						String sql2 = "update user set salary=(select workhour*3+salary) where id='" + id.getText()
								+ "'";// 通过工时计算工资
						try {
							stat = conn.prepareStatement(sql2);
							// 更新操作
							stat.executeLargeUpdate();
						} catch (Exception e2) {
							System.out.println(e2);
							JOptionPane.showMessageDialog(this, "计算错误", "错误", JOptionPane.ERROR_MESSAGE);
						}
						String sql3 = "select * from user where id ='" + id.getText() + "'";// 再次通过ID搜索对象数据
						try {
							ResultSet rs1 = stmt.executeQuery(sql3);
							if (rs1.next()) {
								System.out.println("查询到了一个打工人信息");
							}
							// 弹出一个提示框
							JOptionPane.showMessageDialog(this,
									"打工人编号：" + rs1.getString("id") + "\n打工人姓名：" + rs1.getString("name") + "\n打工人工时："
											+ rs1.getString("workhour") + "\n打工人工资：" + rs1.getString("salary"));
						} catch (Exception e3) {
							System.out.println(e3);
						}
						System.out.println("计算了一个打工人的工资");
						Refresh();
						int n = 0;// 是=0；否=1
						n = JOptionPane.showConfirmDialog(null, "需要清零工时吗？", "提示", JOptionPane.YES_NO_OPTION); // 返回值为0或1
						System.out.println("n=" + n);
						if (n == 0) {
							String sql4 = "update user set workhour=0,salary=0 where id='" + id.getText() + "'";// 清空工时
							stat = conn.prepareStatement(sql4);
							stat.executeLargeUpdate();
							Refresh();
							clear();
						} else {
							clear();
						}
					} else {
						JOptionPane.showMessageDialog(this, "无此编号的打工人", "错误", JOptionPane.ERROR_MESSAGE);// 输入错误编号提示错误
						id.setText("");
						System.out.println("无此编号的打工人");
					}
				} catch (Exception e4) {
					System.out.println(e4);
				}
				clear();
				Refresh();
			} else {
				JOptionPane.showMessageDialog(this, "计算工资时只允许输入打工人编号", "错误", JOptionPane.ERROR_MESSAGE);// 输入编号以外的元素时提示错误
				id.setText("");
				name.setText("");
				workhour.setText("");
				salary.setText("");
				telephone.setText("");
				password.setText("");
			}
		}
		if (e.getActionCommand().equals("清零工时")) {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String sql1 = "select * from user where id ='" + id.getText() + "'";// 通过ID搜索对象数据
			if (id.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "请输入打工人编号", "错误", JOptionPane.ERROR_MESSAGE);
				clear();
				Refresh();
			}
			if (name.getText().equals("") && workhour.getText().equals("") && salary.getText().equals("")
					&& telephone.getText().equals("")) {
				try {
					ResultSet rs = stmt.executeQuery(sql1);
					if (rs.next()) {
						PreparedStatement stat = null;// 预加载
						String sql2 = "update user set workhour=0,salary=0 where id='" + id.getText() + "'";// 通过工时计算工资
						try {
							stat = conn.prepareStatement(sql2);
							// 更新操作
							stat.executeLargeUpdate();
							Refresh();
							clear();
						} catch (Exception e2) {
							System.out.println(e2);
							JOptionPane.showMessageDialog(this, "计算错误", "错误", JOptionPane.ERROR_MESSAGE);
						}
					}

				} catch (Exception e2) {
					System.out.println(e2);
					JOptionPane.showMessageDialog(this, "请输入打工人编号", "错误", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "清零工时时只需输入编号", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
//2021年1月4日23点16分 增加计算工资输入编号错误提醒
//2021年1月5日10点18分 优化计算工资后框内信息的显示 优化弹窗内容 优化增加时的报错 
//2021年1月5日10点51分 统一格式
//2021年1月6日10点29分 优化计算工资的方法，改复制整列元素为修改单个元素，解决计算工资时直接计算所有人工资的问题
//2021年1月6日11点08分 设置计算工资时只允许输入编号
//2021年1月6日22点26分 增加判断电话号码的正则语句
//2021年1月6日23点19分 增加关于密码的语句
//2021年1月7日00点40分 设置增加打工人时自动增加id
//2021年1月7日01点41分 优化增加功能，提示增加打工人信息时无需输入id
//2021年1月7日09点05分 增加计算工资后清零工时的操作
//2021年1月7日09点35分 增加删除信息前的提示 增加计算工资后弹窗内的打工人编号和打工人姓名两条信息
//2021年1月7日13点59分 删除所有和sex相关的语句
//2021年1月7日15点07分 禁止老板删除自己的信息
//2021年1月7日19点10分 额外增加清零工时功能，调整工资算法
//2021年1月8日08点27分 优化修改功能