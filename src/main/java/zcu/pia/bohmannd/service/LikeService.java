package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;
import zcu.pia.bohmannd.model.User;

public interface LikeService {

	public void insertLike(Like like);

	public List<Like> listLikes();

	public Like getLike(Integer id);

	public void deleteLike(Like like);

	public List<Like> listLikesByStatus(Status status);

	public Like isLiked(Like like);

	public List<Like> listLikesByUser(User user);
}
