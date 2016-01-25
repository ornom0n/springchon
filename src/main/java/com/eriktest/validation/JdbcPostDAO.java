package com.eriktest.validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcPostDAO implements PostDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(BoardPost boardPost) {
		String sql = "INSERT INTO posts (THREADID, NAME, MESSAGE) Values ( ?, ?, ? )";
		Connection conn = null;
		if( boardPost.getName().isEmpty() ){
			boardPost.setName("Anonymous");
		}
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setString(2, boardPost.getName());
			ps.setString(3, boardPost.getMessage());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}

	}

}
