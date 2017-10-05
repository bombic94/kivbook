package zcu.pia.bohmannd.model;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AaaMainApp {

	public static void main(String[] args) {
	      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	      UserJdbcTemplate userJdbcTemplate = (UserJdbcTemplate)context.getBean("userJdbcTemplate");
	      StatusJdbcTemplate statusJdbcTemplate = (StatusJdbcTemplate)context.getBean("statusJdbcTemplate"); 
	      FriendshipJdbcTemplate friendshipJdbcTemplate = (FriendshipJdbcTemplate)context.getBean("friendshipJdbcTemplate");
	      ChatJdbcTemplate chatJdbcTemplate = (ChatJdbcTemplate)context.getBean("chatJdbcTemplate");
	      Chat_LineJdbcTemplate chat_LineJdbcTemplate = (Chat_LineJdbcTemplate)context.getBean("chat_LineJdbcTemplate");
	      CommentJdbcTemplate commentJdbcTemplate = (CommentJdbcTemplate)context.getBean("commentJdbcTemplate");
	      LikeJdbcTemplate likeJdbcTemplate = (LikeJdbcTemplate)context.getBean("likeJdbcTemplate");
	      
//	      commentJdbcTemplate.create(1, 2, "textCommentu1234456798");
//	      commentJdbcTemplate.create(1, 3, "textCommentu1234456798");
//	      commentJdbcTemplate.create(2, 1, "textCommentu1234456798");
//	      commentJdbcTemplate.create(2, 3, "textCommentu1234456798");
//	      likeJdbcTemplate.create(1, 3);
//	      likeJdbcTemplate.create(1, 2);
//	      likeJdbcTemplate.create(2, 3);
//	      likeJdbcTemplate.create(2, 4);
//	      chat_LineJdbcTemplate.create(1, 1, "ahoj");
//	      chat_LineJdbcTemplate.create(1, 2, "no nazdar");
//	      chat_LineJdbcTemplate.create(1, 1, "tvoje máma je tak tlustá že nejde zderivovat");
//	      chat_LineJdbcTemplate.create(1, 2, "máma už nejde ani zintegrovat, není kam");
//	      chatJdbcTemplate.create(1, 2, false);
//	      chatJdbcTemplate.create(2, 3, false);
//	      chatJdbcTemplate.create(3, 4, false);
//	      chatJdbcTemplate.create(4, 5, false);
//	      System.out.println("------Records Creation--------" );
//	      statusJdbcTemplate.create(1, "textStatusu123", null);
//	      statusJdbcTemplate.create(2, "textStatusu123", null);
//	      statusJdbcTemplate.create(3, "textStatusu123", null);

	      System.out.println("------Listing Multiple Records--------" );
	      List<User> users = userJdbcTemplate.listUsers();
	      
	      for (User record : users) {
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", Name : " + record.getFirstname() + " " + record.getLastname());
	         System.out.print(", Date of birth: " + record.getDateofbirth().toString());
	         System.out.println(", Email : " + record.getEmail());
	      }
	      System.out.println("Count: " + userJdbcTemplate.getUserCount());
	      
	      List<Status> statuses = statusJdbcTemplate.listStatuses();
	      for (Status record : statuses){
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", Status : " + record.getStatus_text());
	         System.out.println(", UserID: " + record.getUser_id());
	      }
	      
	      System.out.println("------Friendships--------" );
	      
	      
	      List<Friendship> friendships = friendshipJdbcTemplate.listFriendships(3);
	      for (Friendship record : friendships){
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", User1 : " + record.getUser1_id());
	         System.out.print(", User2: " + record.getUser2_id());
	         System.out.println(", Accepted: " + record.isAccepted());
	      }
	      
	      System.out.println("------Chats--------" );
	      List<Chat> chats = chatJdbcTemplate.listChats();
	      for (Chat record : chats){
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", User1 : " + record.getUser1_id());
	         System.out.print(", User2: " + record.getUser2_id());
	         System.out.println(", Seen: " + record.isSeen());
	      }
	      
	      chatJdbcTemplate.seeChat(2);
	      
	      chats = chatJdbcTemplate.listChats(3);
	      for (Chat record : chats){
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", User1 : " + record.getUser1_id());
	         System.out.print(", User2: " + record.getUser2_id());
	         System.out.println(", Seen: " + record.isSeen());
	      }
	      
	      System.out.println("------ChatsLines--------" );
	      List<Chat_Line> chat_Lines = chat_LineJdbcTemplate.listChat_Lines();
	      for (Chat_Line record : chat_Lines){
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", SenderID : " + record.getSender_id());
	         System.out.print(", ChatId: " + record.getChat_id());
	         System.out.println(", Text: " + record.getLine_text());
	      }
	      
	      System.out.println("------Likes and comments--------" );
	      List<Comment> coments = commentJdbcTemplate.listComments(1);
	      for (Comment record : coments){
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", StatusID : " + record.getStatus_id());
	         System.out.print(", userId: " + record.getUser_id());
	         System.out.println(", Text: " + record.getComment_text());
	      }
	      List<Like> likes = likeJdbcTemplate.listLikes(2);
	      for (Like record : likes){
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", StatusID : " + record.getStatus_id());
	         System.out.println(", userId: " + record.getUser_id());
	      }
	   }
}
