package com.eriktest.validation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcPostDAO implements PostDAO {


	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}

	@Override
	public void insert(BoardPost boardPost) {
		String sql = "INSERT INTO posts (THREADID, NAME, MESSAGE) Values ( ?, ?, ? )";
		if( boardPost.getName().isEmpty() ){
			boardPost.setName("Anonymous");
		}
		try {
			//Replace 1 with thread id post belongs to later
			this.jdbcTemplate.update( sql, 1, boardPost.getName(), boardPost.getMessage() );
		} 
		catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<BoardPost> getPosts() {
		String sql = "SELECT ID, THREADID, NAME, MESSAGE FROM POSTS";
		List<BoardPost> boardPosts= this.jdbcTemplate.query(sql, 
			new RowMapper<BoardPost>() {
            	public BoardPost mapRow(ResultSet rs, int rowNum) throws SQLException {
                BoardPost post = new BoardPost();
                post.setId(rs.getInt("ID"));
                post.setMessage(rs.getString("MESSAGE"));
                post.setName(rs.getString("NAME"));
                //Get threadid and place posts to threads
                return post;
        	};
		}); 

		return boardPosts;
	}

}
