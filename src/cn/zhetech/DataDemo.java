package cn.zhetech;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;

public class DataDemo extends JFrame implements ActionListener {
	// JPanel panel1 = new JPanel();
	JLabel label1 = new JLabel("请输入商品编号：");
	JTextField goodId = new JTextField("");
	JLabel label2 = new JLabel("请输入商品名称：");
	JTextField goodName = new JTextField("");
	JLabel label3 = new JLabel("请输入商品价格：");
	JTextField goodPrice = new JTextField("");

	JButton button1 = new JButton("增加");
	JButton button2 = new JButton("删除");
	JButton button3 = new JButton("修改");
	JButton button4 = new JButton("查询");
	// ImageIcon picture1=new
	// ImageIcon("D:\\Program\\Eclipse-schoolspace\\2020.12.28\\imag\\圣诞老人.png");
	JLabel label4 = new JLabel("");// 提示信息

	public DataDemo() {
		//super("商品信息管理");
		// this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));//流失布局
		// panel1.setLayout(new GridLayout(6,1,3,3));
		// this.getContentPane();
		label1.setBounds(60, 20, 100, 20);
		label2.setBounds(60, 60, 100, 20);
		label3.setBounds(60, 100, 100, 20);
		goodId.setBounds(160, 20, 150, 20);
		goodName.setBounds(160, 60, 150, 20);
		goodPrice.setBounds(160, 100, 150, 20);
		button1.setBounds(100, 140, 70, 20);
		button1.addActionListener(this);
		button2.setBounds(180, 140, 70, 20);
		button2.addActionListener(this);
		button3.setBounds(100, 180, 70, 20);
		button3.addActionListener(this);
		button4.setBounds(180, 180, 70, 20);
		button4.addActionListener(this);

		add(label1);
		add(goodId);
		add(label2);

		add(goodName);
		add(label3);
		add(goodPrice);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(label4);

		setTitle("商品管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(550, 300, 380, 280);
		setVisible(true);
	}

	public static void main(String[] args) {
		new DataDemo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JdbcConnection jdbcConn = new JdbcConnection();
		Connection conn = jdbcConn.dbConnection();// 调用方法获取conn
		PreparedStatement stat = null;// 预加载
		if (e.getActionCommand().equals("增加")) {

			String sql1 = "insert into good (id,name,price) values(?,?,?)";
			// "?"表示占位符
			try {
				stat = conn.prepareStatement(sql1);
				// 添加3个参数
				stat.setInt(1, Integer.valueOf(goodId.getText()).intValue());// 获取图书编号的文本值
				stat.setString(2, goodName.getText());// 获取图书名称的文本值
				stat.setDouble(3, Double.valueOf(goodPrice.getText()).doubleValue());// 获取图书价格的文本值
				// 更新操作
				stat.executeLargeUpdate();
				// 弹出一个提示框
				JOptionPane.showMessageDialog(this, "增加了一条记录");
				System.out.println("增加了一条记录");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		// 删除图书信息
		if (e.getActionCommand().equals("删除")) {
			String sql1 = "delete from good where id=?";
			// "?"表示占位符
			try {
				stat = conn.prepareStatement(sql1);
				// 添加3个参数
				stat.setInt(1, Integer.valueOf(goodId.getText()).intValue());// 获取图书编号的文本值
				// 更新操作
				stat.executeLargeUpdate();
				// 弹出一个提示框
				JOptionPane.showMessageDialog(this, "删除了一条记录");
				System.out.println("删除了一个记录");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		// 修改
		if (e.getActionCommand().equals("修改")) {

			String sql1 = "update good set name=?,price=? where id=?";
			// "?"表示占位符
			try {
				stat = conn.prepareStatement(sql1);
				// 添加3个参数
				stat.setInt(3, Integer.valueOf(goodId.getText()).intValue());// 获取图书编号的文本值
				stat.setString(1, goodName.getText());// 获取图书名称的文本值
				stat.setDouble(2, Double.valueOf(goodPrice.getText()).doubleValue());// 获取图书价格的文本值
				// 更新操作
				stat.executeLargeUpdate();
				// 弹出一个提示框
				JOptionPane.showMessageDialog(this, "修改了一条记录");
				System.out.println("修改了一条记录");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}
		// 查询
		if (e.getActionCommand().equals("查询")) {

			String sqlId = "select * from good where id ='" + goodId.getText() + "'";
			String sqlName = "select * from good where name ='" + goodName.getText() + "'";
			String sqlPrice = "select * from good where price ='" + goodPrice.getText() + "'";
			// "?"表示占位符
			try {

				Statement stmt = conn.createStatement();
				if (!goodId.getText().equals("")) {
					searchId(stmt, sqlId);
				} else if (!goodName.getText().equals("")) {
					searchName(stmt, sqlName);

				} else if (!goodPrice.getText().equals("")) {
					searchPrice(stmt, sqlPrice);
				}
				


				goodName.setText("");
				goodPrice.setText("");
				goodId.setText("");

				// 弹出一个提示框

				System.out.println("查询了一条记录");
			} catch (Exception e2) {
				System.out.println(e2);
			}
		}

	}

	public void searchId(Statement stmt, String sqlId) throws SQLException {
		System.out.println("searching goodId");
		ResultSet rsId = stmt.executeQuery(sqlId);
		if (rsId.next()) {

			// 显示图书信息
			JOptionPane.showMessageDialog(this, "查询到一条记录！\n" + "商品编号：" + rsId.getString("id") + "\n商品名称："
					+ rsId.getString("name") + "\n商品价格：" + rsId.getString("price"));
		} else {

			JOptionPane.showMessageDialog(this, "未查询到该信息！", "错误", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void searchName(Statement stmt, String sqlName) throws SQLException {
		System.out.println("searching goodName");
		ResultSet rsName = stmt.executeQuery(sqlName);
		if (rsName.next()) {
			JOptionPane.showMessageDialog(this, "查询到一条记录！\n" + "商品编号：" + rsName.getString("id") + "\n商品名称："
					+ rsName.getString("name") + "\n商品价格：" + rsName.getString("price"));
		} else {

			JOptionPane.showMessageDialog(this, "未查询到该信息！", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void searchPrice(Statement stmt, String sqlPrice) throws SQLException {
		System.out.println("searching goodPrice");
		ResultSet rsPrice = stmt.executeQuery(sqlPrice);

		if (rsPrice.next()) {
			JOptionPane.showMessageDialog(this, "查询到一条记录！\n" + "图书编号：" + rsPrice.getString("id") + "\n图书名称："
					+ rsPrice.getString("name") + "\n图书价格：" + rsPrice.getString("price"));
		} else {
			// JOptionPane.showMessageDialog(this, "未查询到该记录！");
			JOptionPane.showMessageDialog(this, "未查询到该信息！", "错误", JOptionPane.ERROR_MESSAGE);

		}
	}
}
