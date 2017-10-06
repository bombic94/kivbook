package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Friendship;
import zcu.pia.bohmannd.model.User;

public interface FriendshipService {

	public void insertFriendship(Friendship friendship);

    public List<Friendship> listFriendships();

    public Friendship getFriendship(Integer id);
    
    public void deleteFriendship(Friendship friendship);
    
    public List<Friendship> listFriendshipByUser(User user);
    
    public void acceptFriendship(Friendship friendship);
}
