package zcu.pia.bohmannd.dao;

import java.sql.Timestamp;

public class Chat {
	private int id;
	private int user1_id;
	private int user2_id;
	private boolean seen;
	private Timestamp created_at;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(int user1_id) {
		this.user1_id = user1_id;
	}
	public int getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(int user2_id) {
		this.user2_id = user2_id;
	}
	public boolean isSeen() {
		return seen;
	}
	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}	
}
