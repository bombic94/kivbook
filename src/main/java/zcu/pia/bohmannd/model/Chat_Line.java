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
@Table(name = "bohmannd_chat_line")
public class Chat_Line extends AbstractObject {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "chat_id")
	private Chat chat;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;

	@Column(name = "line_text")
	private String line_text;

	@Column(name = "created_at")
	private Timestamp created_at;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getLine_text() {
		return line_text;
	}

	public void setLine_text(String line_text) {
		this.line_text = line_text;
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

		sb.append("Chat_Line - ID: ").append(getId());
		sb.append(", Chat ");
		if (getChat() != null) {
			sb.append(getChat().toString());
		} else {
			sb.append("null");
		}
		sb.append(", Sender: ");
		if (getSender() != null) {
			sb.append(getSender().toString());
		} else {
			sb.append("null");
		}
		sb.append(", Line text: ").append(getLine_text());
		sb.append(", Created at: ");
		if (getCreated_at() != null) {
			sb.append(getCreated_at().toString());
		} else {
			sb.append("null");
		}

		return sb.toString();
	}
}
