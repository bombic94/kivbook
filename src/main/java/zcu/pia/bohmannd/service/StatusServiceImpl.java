package zcu.pia.bohmannd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.CommentDAO;
import zcu.pia.bohmannd.dao.FriendshipDAO;
import zcu.pia.bohmannd.dao.LikeDAO;
import zcu.pia.bohmannd.dao.StatusDAO;
import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDAO statusDAO;
	
	@Autowired
	private FriendshipDAO friendshipDAO;
	
	@Autowired
	private LikeDAO likeDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Transactional
	@Override
	public void insertStatus(Status status) {
		statusDAO.save(status);
	}

	@Transactional
	@Override
	public List<Status> listStatuss() {
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
		List<Friendship> listF = friendshipDAO.listFriendshipsByUser(user);
		
		List<Status> statuses = new ArrayList<Status>();
						
		for (Friendship f : listF) {
			for (Status s : allStatuses) {		
				if (f.getUser1().getId() == s.getUser().getId() || f.getUser2().getId() == s.getUser().getId()) {
					s.setLikes(likeDAO.listByStatus(s));
					s.setComments(commentDAO.listByStatus(s));
					statuses.add(s);
				}				
			}
		}
		
		return statuses;
	}
}
