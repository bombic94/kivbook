package zcu.pia.bohmannd.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import zcu.pia.bohmannd.model.User;

@Service("email")
public class EmailServiceImpl implements EmailService{

	@Autowired
	private MailSender mailSender;
	
	@Override
	public void sendMail(User user) {
		
		String from = "kivbook.zcu@gmail.com";
		String to = user.getEmail();
		String subject = new StringBuilder("Welcome to Kivbook ")
				.append(user.getFirstname())
				.append("!")
				.toString();
		String body = new StringBuilder("Welcome to Kivbook ")
				.append(user.getFirstname())
				.append("!")
				.append(System.lineSeparator())
				.append(System.lineSeparator())
				.append("Your login credentials are: ")
				.append(System.lineSeparator())
				.append("Username: ")
				.append(user.getUsername())
				.append(System.lineSeparator())
				.append("Password: ")
				.append(user.getPassword())
				.toString();
		
		SimpleMailMessage kivbookMsg = new SimpleMailMessage();
		
		kivbookMsg.setFrom(from);
		kivbookMsg.setTo(to);
		kivbookMsg.setSubject(subject);
		kivbookMsg.setText(body);
		
		mailSender.send(kivbookMsg);
	}
}
