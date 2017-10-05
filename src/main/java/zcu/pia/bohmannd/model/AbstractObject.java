package zcu.pia.bohmannd.model;

import javax.persistence.Id;
import javax.persistence.Transient;

public class AbstractObject {
	 
	@Id
	private Integer id;

    @Transient
    public boolean isNew() {
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
