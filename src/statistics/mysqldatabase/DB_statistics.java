package statistics.mysqldatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import common.statics.ResultMessage;

public class DB_statistics {
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/analysis";
	private String dbUser = "root";
	private String dbPass = "1234";
	private Connection connection;
	private Statement statement;
	private static DB_statistics db = null;

	public static DB_statistics getInstance() {
		synchronized (DB_statistics.class) {
			if (db == null) {
				db = new DB_statistics();
			}
		}
		return db;
	}

	private DB_statistics() {
		this.connection = this.getConn();
		try {
			this.statement = (Statement) this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "数据库连接失败!请重新启动服务器", "错误", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "数据库连接失败!请重新启动服务器", "错误", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return conn;
	} // 连接数据库

	public ResultMessage update(String sql) {
		try {
			this.statement.executeUpdate(sql);
			return ResultMessage.SUCCEED;
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败!请重新启动服务器", "错误", JOptionPane.ERROR_MESSAGE);
			return ResultMessage.DB_FAULT;
		}
	}// 更新表格

	public ResultSet find(String sql) {
		try {
			ResultSet result = this.statement.executeQuery(sql);
			return result;// 得到符合条件的集合
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据库连接失败!请重新启动服务器", "错误", JOptionPane.ERROR_MESSAGE);
			return null;// 数据库连接错误
		}// 根据表格名称和语句查找
	}
}
