package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Like;
import zcu.pia.bohmannd.model.Status;

public interface LikeService {

	public void insertLike(Like like);

    public List<Like> listLikes();

    public Like getLike(Integer id);
    
    public void deleteLike(Like like);
    
    public List<Like> listLikesByStatus(Status status);
}
