package zcu.pia.bohmannd.utils;

import zcu.pia.bohmannd.model.User;

public interface EmailService {

	/**
	 * Send mail to given user provided mail
	 * @param user User to mail
	 */
	public void sendMail(User user);
}
