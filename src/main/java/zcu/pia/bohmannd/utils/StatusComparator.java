package zcu.pia.bohmannd.utils;

import java.util.Comparator;

import zcu.pia.bohmannd.model.Status;

public class StatusComparator implements Comparator<Status> {

	@Override
	public int compare(Status s1, Status s2) {
		return s2.getCreated_at().compareTo(s1.getCreated_at());
	}

}
