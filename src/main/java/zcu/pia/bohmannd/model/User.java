package zcu.pia.bohmannd.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="bohmannd_user")
public class User extends AbstractObject {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="dateofbirth")
	private Date dateofbirth;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="photo")
	private String photo;
	
	@ManyToOne(targetEntity=Chat.class, fetch=FetchType.EAGER, cascade=CascadeType.REMOVE, optional=true)
	@JoinColumn(name="active_chat_id")
    private Chat active_chat;
	
	@Column(name="created_at")
	private Timestamp created_at;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.REMOVE)
	private List<Like> likes = new ArrayList<Like>();

	@OneToMany(mappedBy = "user", cascade=CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.REMOVE)
	private List<Status> statuses = new ArrayList<Status>();
	
	@OneToMany(mappedBy = "user1", cascade=CascadeType.REMOVE)
	private List<Friendship> friendships1 = new ArrayList<Friendship>();

	@OneToMany(mappedBy = "user2", cascade=CascadeType.REMOVE)
	private List<Friendship> friendships2 = new ArrayList<Friendship>();
	
	@OneToMany(mappedBy = "user1", cascade=CascadeType.REMOVE)
	private List<Chat> chats1 = new ArrayList<Chat>();
	
	@OneToMany(mappedBy = "user2", cascade=CascadeType.REMOVE)
	private List<Chat> chats2 = new ArrayList<Chat>();
	
	@Transient
    public boolean isNew() {
        return id == null;
    }
	
	@Transient
    private boolean rememberMe;

	public Chat getActiveChat() {
		return active_chat;
	}

	public void setActiveChat(Chat activeChat) {
		this.active_chat = activeChat;
	}
	
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	public void setCreated_at(Timestamp created) {
		this.created_at = created;
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

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public List<Friendship> getFriendships1() {
		return friendships1;
	}

	public void setFriendships1(List<Friendship> friendships1) {
		this.friendships1 = friendships1;
	}

	public List<Friendship> getFriendships2() {
		return friendships2;
	}

	public void setFriendships2(List<Friendship> friendships2) {
		this.friendships2 = friendships2;
	}

	public List<Chat> getChats1() {
		return chats1;
	}

	public void setChats1(List<Chat> chats1) {
		this.chats1 = chats1;
	}

	public List<Chat> getChats2() {
		return chats2;
	}

	public void setChats2(List<Chat> chats2) {
		this.chats2 = chats2;
	}
	
	@Override
    public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("User - ID: ").append(getId());		
		sb.append(", Email: ").append(getEmail());
		sb.append(", Username: ").append(getUsername());
		sb.append(", Password: ").append(getPassword());
		sb.append(", Date of birth: ");
		if (getDateofbirth() != null) {
			sb.append(getDateofbirth().toString());
		} else {
			sb.append("null");
		}
		sb.append(", Gender: ").append(getGender());
		sb.append(", First name: ").append(getFirstname());
		sb.append(", Last name: ").append(getLastname());
		sb.append(", Photo: ").append(getPhoto());
		sb.append(", Created at: ");
		if (getCreated_at() != null) {
			sb.append(getCreated_at().toString());
		} else {
			sb.append("null");
		}

        return sb.toString();
    }
}
