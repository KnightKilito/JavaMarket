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
		jFrame.setTitle("С�ʵ���");
        jFrame.setSize(1000,1000);//���ô��ڴ�С
        jFrame.setLocationRelativeTo(null);//���ھ���
        jFrame.getContentPane().setLayout(null);//�޲��֣��ǵ�������ӿؼ�ʱҪ��������λ�úʹ�С
        JPanel imPanel=(JPanel) jFrame.getContentPane();//ע������������ǿתΪJPanel�ſ���ʵ�����������͸��
        imPanel.setOpaque(false);//�����������Ϊ͸��
        JLabel label = new JLabel(coloredEggImage);//��һ����ǩ�м���ͼƬ
        label.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());//���ñ�ǩλ�ô�С���ǵô�СҪ�ʹ���һ����
        coloredEggImage.setImage(coloredEggImage.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));//ͼƬ����Ӧ��ǩ��С
        jFrame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));//��ǩ��ӵ������
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
        developer1.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
        developer2.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
        developer3.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
        developer4.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
        developer5.setForeground(Color.white);//���ñ�ǩ����Ϊ��ɫ
        
        
        close.setBounds(100, 550, 100, 20);
        close.addActionListener(this);
        
        jFrame.getContentPane().add(developer1);
        jFrame.getContentPane().add(developer2);
        jFrame.getContentPane().add(developer3);
        jFrame.getContentPane().add(developer4);
        jFrame.getContentPane().add(developer5);
        jFrame.getContentPane().add(close);
        
        jFrame.setVisible(true);//���ô��ڿɼ�
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//���ô��ڹر� �������������X��û���κη�Ӧ
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
