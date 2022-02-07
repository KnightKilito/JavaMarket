package cn.zhetech;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection {
	//创建一个对象表示驱动程序与数据库之间的连接
	private static Connection conn=null;
	//定义数据库连接的方法
	public static Connection dbConnection() {
		//1.加载驱动程序
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			System.out.println(e);
		}
		//2.定义要连接的数据库的地址
		try {
			String url="jdbc:mysql://localhost:3306/javamarket?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
			String user="root";
			String password="123456";
			//3.建立与数据库的连接，用于管理jdbc驱动程序的一个接口
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("数据库连接成功");
		}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	
	public static void main(String[] args) {
		conn=dbConnection();
		System.out.println("conn的值为："+conn);
		
	}
}
