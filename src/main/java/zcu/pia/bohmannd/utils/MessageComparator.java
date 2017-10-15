package zcu.pia.bohmannd.utils;

import java.util.Comparator;
import java.util.Date;

import zcu.pia.bohmannd.model.Chat;

public class MessageComparator implements Comparator<Chat> {

	@Override
	public int compare(Chat m1, Chat m2) {
		
		Date d1, d2;
		
		if (m1.getChat_Lines().size() > 0) {
			d1 = m1.getChat_Lines().get( m1.getChat_Lines().size() - 1).getCreated_at();
		} else {
			d1 = new Date();
		}
		
		if (m2.getChat_Lines().size() > 0) {
			d2 = m2.getChat_Lines().get( m2.getChat_Lines().size() - 1).getCreated_at();
		}else {
			d2 = new Date();
		}
		
		return d2.compareTo(d1);
	}


}
