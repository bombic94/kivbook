package zcu.pia.bohmannd.model;

import java.sql.Timestamp;

public class Chat_Line {
	private int id;
	private int chat_id;
	private int sender_id;
	private String line_text;
	private Timestamp created_at;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChat_id() {
		return chat_id;
	}
	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}
	public int getSender_id() {
		return sender_id;
	}
	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
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
}
