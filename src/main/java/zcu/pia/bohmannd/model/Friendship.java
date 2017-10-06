package zcu.pia.bohmannd.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bohmannd_friendship")
public class Friendship extends AbstractObject {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user1_id")
	private User user1;
	
	@ManyToOne
	@JoinColumn(name="user2_id")
	private User user2;
	
	@Column(name="accepted")
	private boolean accepted;
	
	@Column(name="created_at")
	private Timestamp created_at;
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	@Override
    public String toString() {
        return "Friendship - ID: " + getId() + 
        		", User 1: " + getUser1().toString() + 
        		", User 2: " + getUser2().toString() + 
        		", Accepted: " + isAccepted() + 
        		", Created at: " + getCreated_at().toString();
    }
}
