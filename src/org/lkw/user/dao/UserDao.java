package org.lkw.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lkw.user.entity.UserPO;
import org.lkw.user.utils.JDBCUtil;

public class UserDao {
		
	
	public int save(UserPO userPO) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into tb_user values(?,?,?)");
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = null;
		int sum = 0;
		try {
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, userPO.getId());
			statement.setString(2, userPO.getAccount());
			statement.setString(3, userPO.getPassword());
			sum = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(connection,statement,null);
			
		}
		return sum;
	}
	
	public UserPO getByAccoutAndPwd(String accout,String password) {
		StringBuilder sql = new StringBuilder();
		sql.append("select u.c_oid id,u.c_name name from tb_user where u.c_name = ? and u.c_password = ? ");
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement statement = null;
		ResultSet result = null;
		UserPO userPO = null;
		try {
			statement = connection.prepareStatement(sql.toString());
			statement.setString(1, accout);
			statement.setString(2, password);
			result = statement.executeQuery();
			if (null != result) {
				userPO = new UserPO();
				while (result.next()) {
					userPO.setId(result.getString("id"));
					userPO.setAccount(result.getString("name"));
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JDBCUtil.close(connection,statement,result);
			
		}
		return userPO;
	}
}
