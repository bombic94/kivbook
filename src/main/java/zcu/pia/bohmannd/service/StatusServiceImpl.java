package zcu.pia.bohmannd.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.StatusDAO;
import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

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
		statusDAO.delete(status);
	}

	@Override
	public List<Status> listStatusesForUser(User user) {
		List<Status> allStatuses = statusDAO.list();
		List<Friendship> listF = friendshipService.listFriendshipByUser(user);

		List<Status> statuses = new ArrayList<Status>();

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
		Collections.reverse(statuses);

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
}
