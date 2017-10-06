package zcu.pia.bohmannd.service;

import java.util.List;

import zcu.pia.bohmannd.model.Status;

public interface StatusService {

	public void insertStatus(Status status);

    public List<Status> listStatuss();

    public Status getStatus(Integer id);
    
    public void deleteStatus(Status status);
}
