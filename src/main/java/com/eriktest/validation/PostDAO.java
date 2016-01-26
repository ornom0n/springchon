package com.eriktest.validation;

import java.util.List;

public interface PostDAO {
	public void insert(BoardPost boardPost);
	public List<BoardPost> getPosts();
}
