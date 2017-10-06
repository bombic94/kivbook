package zcu.pia.bohmannd.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bohmannd_status")
public class Status extends AbstractObject {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="status_text")
	private String status_text;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="created_at")
	private Timestamp created_at;
	
	@OneToMany(mappedBy = "status")
	private List<Like> likes = new ArrayList<Like>();

	@OneToMany(mappedBy = "status")
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatus_text() {
		return status_text;
	}
	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public List<Like> getLikes() {
		return likes;
	}
	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	@Override
    public String toString() {
        return "Status - ID: " + getId() + 
        		", User: " + getUser().toString() + 
        		", Status text: " + getStatus_text() + 
        		", Photo: " + getPhoto() + 
        		", Created at: " + getCreated_at().toString() +
        		", Likes: " + getLikes().size() + 
        		", Comments: " + getComments().size();
    }
}
