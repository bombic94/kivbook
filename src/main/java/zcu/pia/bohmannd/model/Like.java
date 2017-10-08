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
@Table(name="bohmannd_like")
public class Like extends AbstractObject {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="created_at")
	private Timestamp created_at;
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
		
		sb.append("Like - ID: ").append(getId());		
		sb.append(", Status: ");
		if (getStatus() != null) {
			sb.append(getStatus().toString());
		} else {
			sb.append("null");
		}
		sb.append(", User: ");
		if (getUser() != null) {
			sb.append(getUser().toString());
		} else {
			sb.append("null");
		}
		sb.append(", Created at: ");
		if (getCreated_at() != null) {
			sb.append(getCreated_at().toString());
		} else {
			sb.append("null");
		}
		
        return sb.toString();
    }
}
