package zcu.pia.bohmannd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.FriendshipDAO;
import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.User;

@Service
public class FriendshipServiceImpl implements FriendshipService {

	@Autowired
	private FriendshipDAO friendshipDAO;
	
	@Transactional
	@Override
	public void insertFriendship(Friendship friendship) {
		friendshipDAO.save(friendship);
	}

	@Transactional
	@Override
	public List<Friendship> listFriendships() {
		return friendshipDAO.list();
	}

	@Transactional
	@Override
	public Friendship getFriendship(Integer id) {
		return friendshipDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteFriendship(Friendship friendship) {
		friendshipDAO.delete(friendship);
	}

	@Transactional
	@Override
	public List<Friendship> listFriendshipByUser(User user) {
		return friendshipDAO.listFriendshipsByUser(user);
	}

	@Transactional
	@Override
	public void acceptFriendship(Friendship friendship) {
		friendshipDAO.accept(friendship);
	}

	@Transactional
	@Override
	public List<Friendship> listPendingFriendshipByUser(User user) {
		return friendshipDAO.listPendingFriendshipsByUser(user);
	}

	@Transactional
	@Override
	public List<Friendship> listPossibleFriendshipByUser(User user) {	
		return friendshipDAO.listAllFriendshipsByUser(user);
	}

}
