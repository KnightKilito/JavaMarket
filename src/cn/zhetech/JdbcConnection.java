package cn.zhetech;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	//����һ�������ʾ�������������ݿ�֮�������
	private static Connection conn=null;
	//�������ݿ����ӵķ���
	public static Connection dbConnection() {
		//1.������������
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			System.out.println(e);
		}
		//2.����Ҫ���ӵ����ݿ�ĵ�ַ
		try {
			String url="jdbc:mysql://localhost:3306/javamarket?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
			String user="root";
			String password="123456";
			//3.���������ݿ�����ӣ����ڹ���jdbc���������һ���ӿ�
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("���ݿ����ӳɹ�");
		}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	
	public static void main(String[] args) {
		conn=dbConnection();
		System.out.println("conn��ֵΪ��"+conn);
		
	}
}
