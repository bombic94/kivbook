package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Status;

public interface CommentService {

	public void insertComment(Comment comment);

	public List<Comment> listComments();

	public Comment getComment(Integer id);

	public void deleteComment(Comment comment);

	public List<Comment> listCommentsByStatus(Status status);
}
