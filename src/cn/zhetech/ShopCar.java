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

//�������ﳵ����ɾ�Ĺ��ﳵ�е���Ʒ���鹺�ﳵ���ݣ����ݹ��ﳵ���ݶԺ�̨��Ʒɾ���������ܶ��չ��ﳵ�࣬ͳ�ƹ���

public class ShopCar extends JFrame implements ActionListener {
	JdbcConnection jdbcConn = new JdbcConnection();
	Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
	PreparedStatement stat = null;// Ԥ����
	private int countNum=0;
	JLabel label1 = new JLabel("��������Ʒ��ţ�");
	JTextField goodId = new JTextField("");
	JLabel label2 = new JLabel("��������Ʒ���ƣ�");
	JTextField goodName = new JTextField("");
	JLabel label3 = new JLabel("��Ʒ�۸�");
	JTextField goodPrice = new JTextField("");
	JLabel label4 = new JLabel("�������¼��ţ�");
	JTextField goodRecord = new JTextField("");

	JTextArea showShopcar = new JTextArea("");

	JButton button1 = new JButton("����");
	JButton button2 = new JButton("ɾ��");
	JButton button3 = new JButton("�޸�");
	JButton button4 = new JButton("ˢ��");
	JButton button5 = new JButton("�鿴��Ʒ�б�");
	JButton button6 = new JButton("ɾ�����ﳵ");
	JButton button7 = new JButton("���㹺�ﳵ");
	JLabel warningText = new JLabel("");// ��ʾ��Ϣ
	JFrame jFrame=new JFrame();
	ImageIcon shopCarImage=new ImageIcon("imag/ShopCarImage.jpg");
	

	public ShopCar(int carNum) {
		this.carNum = carNum;
		setCar();
		
		jFrame.setTitle("���ﳵ" + carNum + "��");
        jFrame.setSize(780,400);//���ô��ڴ�С
        jFrame.setLocationRelativeTo(null);//���ھ���
        jFrame.getContentPane().setLayout(null);//�޲��֣��ǵ�������ӿؼ�ʱҪ��������λ�úʹ�С
        JPanel imPanel=(JPanel) jFrame.getContentPane();//ע������������ǿתΪJPanel�ſ���ʵ�����������͸��
        imPanel.setOpaque(false);//�����������Ϊ͸��
        JLabel label = new JLabel(shopCarImage);//��һ����ǩ�м���ͼƬ
        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());//���ñ�ǩλ�ô�С���ǵô�СҪ�ʹ���һ����
        shopCarImage.setImage(shopCarImage.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//ͼƬ����Ӧ��ǩ��С
        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//��ǩ��ӵ������
		
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
        jFrame.setVisible(true);//���ô��ڿɼ�
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//���ô��ڹر� �������������X��û���κη�Ӧ
		jFrame.setResizable(false);
//		setTitle("���ﳵ" + carNum + "��");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(null);
//		setBounds(450, 200, 780, 410);
//		setVisible(true);
//		this.enableEvents(AWTEvent.WINDOW_EVENT_MASK);

	}



	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getActionCommand().equals("ɾ�����ﳵ")) {
			
			int n=0;//��=0����=1
			n = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ�����ﳵ��?", "��(������)��",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
			
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
				//ɶ������
			}
			
			
		}
		if (e.getActionCommand().equals("����")) {
			
			String sql1 = "insert into shopcar" + carNum + " (goodid,goodname,goodprice) values(?,?,?)";
			// "?"��ʾռλ��
			// ������Ʒ��ŵ�ʱ��Ҫ����Ʒ���ݿ�ȶ�
			// ����ֻ��������ӽ����ﳵ������Ʒ���ݿ����ӣ�
			// �п�
			//������������Ʒ���
			if(goodRecord.getText().equals("")) {
				if(!goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")) {
					try {
						String check = "select * from good where id='" + goodId.getText() + "'";
						Statement stmt = conn.createStatement();
						ResultSet rsCheck = stmt.executeQuery(check);
						if (rsCheck.next()) {
							
							stat = conn.prepareStatement(sql1);
							// ���3������
							
							stat.setInt(1, Integer.valueOf(goodId.getText()).intValue());
							stat.setString(2, rsCheck.getString("name"));
							stat.setDouble(3, Double.valueOf(rsCheck.getString("price")).doubleValue());
							// ���²���
							stat.executeLargeUpdate();
							// ����һ����ʾ��
							JOptionPane.showMessageDialog(this, "������һ����¼");
							System.out.println("������һ����¼");
							Refresh();
							cleanText();
						}
						else {
							JOptionPane.showMessageDialog(this, "δ�鵽�ñ����Ʒ��", "����", JOptionPane.ERROR_MESSAGE);
							cleanText();
						}
						
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
				else if(!goodId.getText().equals("") && (!goodName.getText().equals("") || !goodPrice.getText().equals(""))) {
					JOptionPane.showMessageDialog(this, "�����Ʒ���빺�ﳵʱ������ͨ����Ʒ�����ӣ�", "����", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
				else {
					JOptionPane.showMessageDialog(this, "δ������Ʒ��ţ�", "����", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "�����Ʒ���빺�ﳵʱ������ͨ����Ʒ�����ӣ�\n��¼���ֻ�������޸��Լ�ɾ�����ﳵ����", "����", JOptionPane.ERROR_MESSAGE);
				cleanText();
			}
			
		}
		// ɾ��ͼ����Ϣ
		if (e.getActionCommand().equals("ɾ��")) {
			if (!goodRecord.getText().equals("") && goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")) {
				String delete = "delete from shopcar" + carNum + " where goodrecord=?";
				String check = "select * from shopcar" + carNum + " where goodrecord='" + goodRecord.getText() + "'";
				// ������ɾ������Ҫ�ݼ�
				try {
					Statement stmt = conn.createStatement();
					ResultSet rsCheck = stmt.executeQuery(check);
					if (rsCheck.next()) {
						System.out.println("delete");
						stat = conn.prepareStatement(delete);
						stat.setInt(1, Integer.valueOf(goodRecord.getText()).intValue());
						// ���²���
						stat.executeLargeUpdate();
						// ����һ����ʾ��
						JOptionPane.showMessageDialog(this, "ɾ����һ����¼");
						System.out.println("ɾ����һ����¼");
						
						//������������������ɾ�������Ʒ��¼��ſ��Լ������������߶������м��һ��
						//1,ɾ��ԭ��������
						String deleteKey="ALTER TABLE `shopcar" + carNum + "` DROP `goodrecord`;";
						stat = conn.prepareStatement(deleteKey);
						stat.executeLargeUpdate();
						//2,����������ֶΣ�
						String addKey="ALTER TABLE `shopcar" + carNum + "` ADD `goodrecord` int NOT NULL FIRST;";
						stat = conn.prepareStatement(addKey);
						stat.executeLargeUpdate();
						//3,������������
						String newKey="ALTER TABLE `shopcar" + carNum + "` MODIFY COLUMN `goodrecord` int NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY(goodrecord);";
						stat = conn.prepareStatement(newKey);
						// ���²���
						stat.executeLargeUpdate();
						
						Refresh();
					} else {
						JOptionPane.showMessageDialog(this, "�����ڸü�¼��", "����", JOptionPane.ERROR_MESSAGE);
					}
					cleanText();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			} 
			else if(goodRecord.getText().equals("")&& goodId.getText().equals("") && goodName.getText().equals("") && goodPrice.getText().equals("")){
				JOptionPane.showMessageDialog(this, "δ�����¼��ţ�", "����", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, "ɾ��ʱ������ͨ����¼���ɾ����", "����", JOptionPane.ERROR_MESSAGE);
			}
			cleanText();
		}
		// �޸�
		if (e.getActionCommand().equals("�޸�")) {
			//������Ĭ�ϲ��޸�
			String check = "select * from shopcar" + carNum + " where goodrecord='" + goodRecord.getText() + "'";
			
			// "?"��ʾռλ��
			if(goodId.getText().equals("")) {
				if(!goodRecord.getText().equals("")) {
					String modify = "update shopcar" + carNum + " set goodname=?,goodprice=? where goodrecord="+ goodRecord.getText();
					try {
						Statement stmt = conn.createStatement();
						ResultSet rsCheck = stmt.executeQuery(check);
						if (rsCheck.next()) {
							stat = conn.prepareStatement(modify);
							// ��Ӳ���,�п�
							
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
							// ���²���
							stat.executeLargeUpdate();
							// ����һ����ʾ��
							JOptionPane.showMessageDialog(this, "�޸���һ����¼");
							System.out.println("�޸���һ����¼");
							Refresh();
						} else {
							JOptionPane.showMessageDialog(this, "�����ڸü�¼��", "����", JOptionPane.ERROR_MESSAGE);
						}
						cleanText();
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "������Ҫ�޸ĵĹ��ﳵ�е���Ʒ��¼��ţ�", "����", JOptionPane.ERROR_MESSAGE);
					cleanText();
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "��ƷID�޷����ģ�", "����", JOptionPane.ERROR_MESSAGE);
				cleanText();
			}
			
		}
		if (e.getActionCommand().equals("ˢ��")) {
			Refresh();
		}
		if (e.getActionCommand().equals("�鿴��Ʒ�б�")) {
			// ����URLΪ jdbc:mysql//��������ַ/���ݿ��� �������2�������ֱ��ǵ�½�û���������
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
		if (e.getActionCommand().equals("���㹺�ﳵ")) {
			countNum++;
			int n=0;//��=0����=1
			if(countNum>1) {
				n = JOptionPane.showConfirmDialog(null, "�Ѿ�������ˣ���ȷ��Ҫ�ٴν�����?", "��(������)��",JOptionPane.YES_NO_OPTION); //����ֵΪ0��1
			}
			System.out.println("n="+n);
			if(n==0) {
				new CountShopCar(carNum);
			}
			else {
				//ɶ������
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
				info += "��¼��ţ�" + rsCheck.getString("goodrecord")
				+ "����Ʒ��ţ�" + rsCheck.getString("goodid")
				+ "����Ʒ���ƣ�"+ rsCheck.getString("goodname") 
				+ "����Ʒ�۸�" + rsCheck.getString("goodprice") + "\n";
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
		String check = "show tables like 'shopcar" + carNum + "';";// ֻ����show table����Ȼselect�ᱨ��
		String delete = "DROP TABLE shopcar" + carNum;
		String set = "CREATE TABLE shopcar" + carNum + " (" + "`goodrecord` INT UNSIGNED AUTO_INCREMENT,"
				+ "`goodid` INT(11)not null," + "`goodname` VARCHAR(255)not null,"
				+ "`goodprice` decimal(11,2)not null," + "PRIMARY KEY ( `goodrecord` )"
				+ ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		try {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
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
	JFrame jFrame=new JFrame(); //����һ������
	public CountShopCar(int carNum) {
		this.carNum = carNum;
		countMoney();
	}

	public void countMoney() {
		try {
			JdbcConnection jdbcConn = new JdbcConnection();
			Connection conn = jdbcConn.dbConnection();// ���÷�����ȡconn
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
				JOptionPane.showMessageDialog(this, "���ﳵΪ�գ�", "����", JOptionPane.ERROR_MESSAGE);
			}
			else {
				
				jFrame.setTitle("���ﳵ" + carNum + "��");
		        jFrame.setSize(400,600);//���ô��ڴ�С
		        jFrame.setLocationRelativeTo(null);//���ھ���
		        jFrame.getContentPane().setLayout(null);//�޲��֣��ǵ�������ӿؼ�ʱҪ��������λ�úʹ�С
		        JPanel imPanel=(JPanel) jFrame.getContentPane();//ע������������ǿתΪJPanel�ſ���ʵ�����������͸��
		        imPanel.setOpaque(false);//�����������Ϊ͸��
		        JLabel label = new JLabel(PayWayPic);//��һ����ǩ�м���ͼƬ
		        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());//���ñ�ǩλ�ô�С���ǵô�СҪ�ʹ���һ����
		        PayWayPic.setImage(PayWayPic.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//ͼƬ����Ӧ��ǩ��С
		        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//��ǩ��ӵ������
		        
		        alipay.setBounds(60, 330, 120, 20);
				alipay.addActionListener(this);
				wechatpay.setBounds(210, 330, 120, 20);
				wechatpay.addActionListener(this);
				jFrame.getContentPane().add(money);
				jFrame.getContentPane().add(alipay);
				jFrame.getContentPane().add(wechatpay);
		        money.setText("��Ʒ�ܼ�Ϊ��"+allMoney);
		        money.setFont(new Font("��������", Font.PLAIN, 20));//��������
		        money.setForeground(Color.white);//���ñ�ǩ������ɫ
		        money.setBounds(jFrame.getWidth()/4,jFrame.getHeight()/2-28,jFrame.getWidth()/2,50);//���ñ�ǩλ�úʹ�С
		        money.setHorizontalAlignment(JLabel.CENTER);//���ñ�ǩ����ˮƽ����
		        jFrame.getContentPane().add(money);//��һ����ǩ��ӵ��������
		        jFrame.setVisible(true);//���ô��ڿɼ�
				
				
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
	JButton done=new JButton("֧�����");
	ImageIcon AliPayPic = new ImageIcon("imag/Gathering/AliPay.png");
	public AliPay(int carNum) {
		done.setBounds(160, 80, 120, 20);
		done.addActionListener(this);
		add(done);
		setTitle("���ﳵ" + carNum + "��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(550, 140, 450, 638);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//���ô��ڹر� �������������X��û���κη�Ӧ
	}
	public void paint(Graphics g) {
		g.drawImage(AliPayPic.getImage(), 0, 20, null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("֧�����")) {
			dispose();
		}
	}
	
}
class WeChatPay extends JFrame implements ActionListener{
	JButton done=new JButton("֧�����");
	ImageIcon WeChatPayPic = new ImageIcon("imag/Gathering/WeChatPay.png");
	public WeChatPay(int carNum) {
		done.setBounds(150, 450, 120, 20);
		done.addActionListener(this);
		add(done);
		setTitle("���ﳵ" + carNum + "��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(550, 140, 450, 638);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//���ô��ڹر� �������������X��û���κη�Ӧ
		setVisible(true);
	}
	public void paint(Graphics g) {
		g.drawImage(WeChatPayPic.getImage(), 0, 20, null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("֧�����")) {
			dispose();
		}
	}
	
}
