package zcu.pia.bohmannd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zcu.pia.bohmannd.dao.StatusDAO;
import zcu.pia.bohmannd.model.Status;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDAO statusDAO;
	
	@Transactional
	@Override
	public void insertStatus(Status status) {
		statusDAO.save(status);
	}

	@Transactional
	@Override
	public List<Status> listStatuss() {
		return statusDAO.list();
	}

	@Transactional
	@Override
	public Status getStatus(Integer id) {
		return statusDAO.getById(id);
	}

	@Transactional
	@Override
	public void deleteStatus(Status status) {
		statusDAO.delete(status);
	}

}
