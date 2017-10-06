package zcu.pia.bohmannd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.CommentDAO;
import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Status;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;
	
	@Transactional
	@Override
	public void insertComment(Comment comment) {
		commentDAO.save(comment);
	}

	@Transactional
	@Override
	public List<Comment> listComments() {
		return commentDAO.list();
	}

	@Transactional
	@Override
	public Comment getComment(Integer id) {
		return commentDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteComment(Comment comment) {
		commentDAO.delete(comment);
	}

	@Transactional
	@Override
	public List<Comment> listCommentsByStatus(Status status) {
		return commentDAO.listByStatus(status);
	}

}
