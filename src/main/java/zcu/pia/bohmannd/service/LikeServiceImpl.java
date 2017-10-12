package zcu.pia.bohmannd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.LikeDAO;
import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDAO likeDAO;
	
	@Transactional
	@Override
	public void insertLike(Like like) {
		likeDAO.save(like);
	}

	@Transactional
	@Override
	public List<Like> listLikes() {
		return likeDAO.list();
	}

	@Transactional
	@Override
	public Like getLike(Integer id) {
		return likeDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteLike(Like like) {
		likeDAO.delete(like);
	}

	@Transactional
	@Override
	public List<Like> listLikesByStatus(Status status) {
		return likeDAO.listByStatus(status);
	}

	@Transactional
	@Override
	public Like isLiked(Like like) {
		Like l = likeDAO.getExists(like);
		return l;
	}

	@Transactional
	@Override
	public List<Like> listLikesByUser(User user) {
		return likeDAO.listByUser(user);
	}

}
