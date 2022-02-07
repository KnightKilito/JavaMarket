package cn.huitech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import cn.zhetech.JdbcConnection;

public class GoodsInformation {
	private int goods_id;
	private String goods_name;
	private int goods_number;
	private double goods_price;

	public int getGoods_id() {
		return goods_id;
	}

	public GoodsInformation(int goods_id, String goods_name, double goods_price, int goods_number) {

		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_number = goods_number;
		this.goods_price = goods_price;
	}

	@Override
	public String toString() {
		return "goodsInformation [goods_id=" + goods_id + ", goods_name=" + goods_name + ", goods_number="
				+ goods_number + ", goods_price=" + goods_price + "]";
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public int getGoods_number() {
		return goods_number;
	}

	public void setGoods_number(int goods_number) {
		this.goods_number = goods_number;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public static List<GoodsInformation> listgoods() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM good WHERE id";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Connection ct = null;
		Statement sm = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		List<GoodsInformation> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 访问结果
			while (rs.next()) {
				int goods_id = rs.getInt("id");
				String goods_name = rs.getString("name");
				double goods_price = rs.getDouble("price");
				int goods_number = rs.getInt("number");

				GoodsInformation gg = new GoodsInformation(goods_id, goods_name, goods_price, goods_number);
				list.add(gg);
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<GoodsInformation> listgoods_id(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM good WHERE id =" + "'" + id + "'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Connection ct = null;
		Statement sm = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		List<GoodsInformation> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 访问结果
			while (rs.next()) {
				int goods_id = rs.getInt("id");
				String goods_name = rs.getString("name");
				double goods_price = rs.getDouble("price");
				int goods_number = rs.getInt("number");

				GoodsInformation gg = new GoodsInformation(goods_id, goods_name, goods_price, goods_number);
				list.add(gg);
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<GoodsInformation> listgoods_name(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM good WHERE name like" + "'%" + name + "%'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Connection ct = null;
		Statement sm = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		List<GoodsInformation> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 访问结果
			while (rs.next()) {
				int goods_id = rs.getInt("id");
				String goods_name = rs.getString("name");
				double goods_price = rs.getDouble("price");
				int goods_number = rs.getInt("number");

				GoodsInformation gg = new GoodsInformation(goods_id, goods_name, goods_price, goods_number);
				list.add(gg);
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<GoodsInformation> listgoods_price(String price) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM good WHERE price =" + "'" + price + "'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Statement sm = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		List<GoodsInformation> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 访问结果
			while (rs.next()) {
				int goods_id = rs.getInt("id");
				String goods_name = rs.getString("name");
				double goods_price = rs.getDouble("price");
				int goods_number = rs.getInt("number");

				GoodsInformation gg = new GoodsInformation(goods_id, goods_name, goods_price, goods_number);
				list.add(gg);
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static GoodsInformation select_id(String id) {
		String sql = "select *from good where id = " + "'" + id + "'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 访问结果
			while (rs.next()) {
				int goods_id = rs.getInt("id");
				String goods_name = rs.getString("name");
				double goods_price = rs.getDouble("price");
				int goods_number = rs.getInt("number");

				GoodsInformation gg = new GoodsInformation(goods_id, goods_name, goods_price, goods_number);
				return gg;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static GoodsInformation select_name(String name) {
		String sql = "select *from good where name = " + "'" + name + "'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 访问结果
			while (rs.next()) {
				int goods_id = rs.getInt("id");
				String goods_name = rs.getString("name");
				double goods_price = rs.getDouble("price");
				int goods_number = rs.getInt("number");
				GoodsInformation gg = new GoodsInformation(goods_id, goods_name, goods_price, goods_number);
				return gg;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static GoodsInformation select_price(String price) {
		String sql = "select *from good where price = " + "'" + price + "'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 访问结果
			while (rs.next()) {
				int goods_id = rs.getInt("id");
				String goods_name = rs.getString("name");
				double goods_price = rs.getDouble("price");
				int goods_number = rs.getInt("number");

				GoodsInformation gg = new GoodsInformation(goods_id, goods_name, goods_price, goods_number);
				return gg;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<GoodsInformation> listgoods_number(String number) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM good WHERE number =" + "'" + number + "'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		Statement sm = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		List<GoodsInformation> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();// 访问结果
			while (rs.next()) {
				int goods_id = rs.getInt("id");
				String goods_name = rs.getString("name");
				double goods_price = rs.getDouble("price");
				int goods_number = rs.getInt("number");
				GoodsInformation gg = new GoodsInformation(goods_id, goods_name, goods_price, goods_number);
				list.add(gg);
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addGoods() throws NumberFormatException, Exception {
		String sql = "insert into good (id,name,price,number) values(?,?,?,?)";
		PreparedStatement ps = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		Connection conn = jdbcConn.dbConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, getGoods_id());
		ps.setString(2, getGoods_name());
		ps.setDouble(3, getGoods_price());
		ps.setInt(4, getGoods_number());
		ps.execute();// 执行
	}

	public int delGoods(int goodsnum) throws Exception {
		String sql = "UPDATE good SET number = ?-? WHERE id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		if (goods_number - goodsnum < 0) {
			return 0;
		}
		ps = conn.prepareStatement(sql);
		ps.setInt(1, getGoods_number());
		ps.setInt(2, goodsnum);
		ps.setInt(3, getGoods_id());
		ps.execute();// 执行
		return 1;
	}

	public void delGoods() throws Exception {
		String sql = "Delete from good WHERE id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, getGoods_id());
		ps.execute();
	}

	public void updata() throws Exception {
		String sql = "update  good set `name` = ?,number = ?,price=?  where id = ?";
		PreparedStatement ps = null;
		Connection conn = null;
		JdbcConnection jdbcConn = new JdbcConnection();
		conn = jdbcConn.dbConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, getGoods_name());
		ps.setInt(2, getGoods_number());
		ps.setDouble(3, getGoods_price());
		ps.setInt(4, getGoods_id());
		ps.execute();// 执行
	}
}
