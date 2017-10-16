package zcu.pia.bohmannd.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.StatusDAO;
import zcu.pia.bohmannd.model.Comment;
import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;
import zcu.pia.bohmannd.utils.StatusComparator;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDAO statusDAO;
	@Autowired
	private FriendshipService friendshipService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private CommentService commentService;

	@Transactional
	@Override
	public void insertStatus(Status status) {
		statusDAO.save(status);
	}

	@Transactional
	@Override
	public List<Status> listStatuses() {
		return statusDAO.list();
	}

	@Transactional
	@Override
	public Status getStatus(Integer id) {
		return statusDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteStatus(Status status) {
		
		List<Like> likes = likeService.listLikesByStatus(status);
		List<Comment> comments = commentService.listCommentsByStatus(status);
		
		for (Like like : likes) {
			likeService.deleteLike(like);
		}
		for (Comment comment : comments) {
			commentService.deleteComment(comment);
		}
		
		statusDAO.delete(status);
	}

	@Override
	public List<Status> listStatusesForUser(User user) {
		List<Status> allStatuses = statusDAO.list();
		List<Friendship> listF = friendshipService.listFriendshipByUser(user);

		List<Status> statuses = new ArrayList<Status>();//statusDAO.listByUser(user);
		
		for (Iterator<Status> iterator = allStatuses.iterator(); iterator.hasNext();) {
			Status s = iterator.next();
				if (s.getUser().getId() == user.getId()) {
					
					s.setLikes(likeService.listLikesByStatus(s));
					s.setComments(commentService.listCommentsByStatus(s));
					statuses.add(s);
					iterator.remove();
				}
		}
		
		for (Friendship f : listF) {
			for (Iterator<Status> iterator = allStatuses.iterator(); iterator.hasNext();) {
				Status s = iterator.next();
					if (f.getUser1().getId() == s.getUser().getId() || f.getUser2().getId() == s.getUser().getId()) {
						
						s.setLikes(likeService.listLikesByStatus(s));
						s.setComments(commentService.listCommentsByStatus(s));
						statuses.add(s);
						iterator.remove();
					}
			}
		}
		Collections.sort(statuses, new StatusComparator());

		return statuses;
	}

	@Override
	public List<Status> getNstatuses(List<Status> allStatuses, Integer id, Integer n) {
		List<Status> result = new ArrayList<Status>();

		int start = ((id - 1) * n);
		int end = id * n;
		if (allStatuses.size() < end) {
			end = allStatuses.size();
		}

		for (int i = start; i < end; i++) {
			result.add(allStatuses.get(i));
		}

		return result;
	}

	@Transactional
	@Override
	public List<Status> listStatusesByUser(User user) {
		return statusDAO.listByUser(user);
	}
}
