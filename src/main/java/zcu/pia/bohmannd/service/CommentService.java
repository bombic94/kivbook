package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface CommentService {

	
	/**
	 * Insert new comment
	 * @param comment Object to insert
	 */
	public void insertComment(Comment comment);

	/**
	 * List all comments
	 * @return list of all comments
	 */
	public List<Comment> listComments();

	/**
	 * Retrieve comment by given id
	 * @param id ID of comment
	 * @return Comment Object
	 */
	public Comment getComment(Integer id);

	/**
	 * Delete given comment
	 * @param comment Object to delete
	 */
	public void deleteComment(Comment comment);

	/**
	 * List all comments for given status
	 * @param status Status Object
	 * @return list of comments for status
	 */
	public List<Comment> listCommentsByStatus(Status status);

	/**
	 * List all comments for given user
	 * @param user User object
	 * @return list of comments for user
	 */
	public List<Comment> listCommentsByUser(User user);
}
