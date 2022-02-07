package cn.zhetech;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColoredEgg implements ActionListener {
	JFrame jFrame=new JFrame();
	ImageIcon coloredEggImage=new ImageIcon("imag/boboCat.gif");
	JLabel developer1=new JLabel("");
	JLabel developer2=new JLabel("");
	JLabel developer3=new JLabel("");
	JLabel developer4=new JLabel("");
	JLabel developer5=new JLabel("");
	JButton close=new JButton("Exit");
	
	public ColoredEgg() {
		jFrame.setTitle("小彩蛋！");
        jFrame.setSize(1000,1000);//设置窗口大小
        jFrame.setLocationRelativeTo(null);//窗口居中
        jFrame.getContentPane().setLayout(null);//无布局，记得下面添加控件时要设置它们位置和大小
        JPanel imPanel=(JPanel) jFrame.getContentPane();//注意内容面板必须强转为JPanel才可以实现下面的设置透明
        imPanel.setOpaque(false);//将内容面板设为透明
        JLabel label = new JLabel(coloredEggImage);//往一个标签中加入图片
        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());//设置标签位置大小，记得大小要和窗口一样大
        coloredEggImage.setImage(coloredEggImage.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//图片自适应标签大小
        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//标签添加到层面板
        String text1="Developer Group: KNIGHTS OF JAVA";
        developer1.setText(text1);
        developer1.setBounds(100, 100, 250, 20);
        String text2="Hongzhe Wu";
        developer2.setText(text2);
        developer2.setBounds(100, 130, 200, 20);
        String text3="Jiahui Guo";
        developer3.setText(text3);
        developer3.setBounds(100, 160, 200, 20);
        String text4="Yuanshu Xie";
        developer4.setText(text4);
        developer4.setBounds(100, 190, 200, 20);
        String text5="Junxuan Wu";
        developer5.setText(text5);
        developer5.setBounds(100, 210, 200, 20);
        developer1.setForeground(Color.white);//设置标签字体为白色
        developer2.setForeground(Color.white);//设置标签字体为白色
        developer3.setForeground(Color.white);//设置标签字体为白色
        developer4.setForeground(Color.white);//设置标签字体为白色
        developer5.setForeground(Color.white);//设置标签字体为白色
        
        
        close.setBounds(100, 550, 100, 20);
        close.addActionListener(this);
        
        jFrame.getContentPane().add(developer1);
        jFrame.getContentPane().add(developer2);
        jFrame.getContentPane().add(developer3);
        jFrame.getContentPane().add(developer4);
        jFrame.getContentPane().add(developer5);
        jFrame.getContentPane().add(close);
        
        jFrame.setVisible(true);//设置窗口可见
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//禁用窗口关闭 如果含本代码点击X号没有任何反应
		jFrame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Exit")) {
			jFrame.dispose();
		}
	}
	public static void main(String[] args) {
		new ColoredEgg();
		
	}
}
