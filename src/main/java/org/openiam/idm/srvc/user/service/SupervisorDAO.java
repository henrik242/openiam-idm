package org.openiam.idm.srvc.user.service;

import java.util.List;

import org.openiam.idm.srvc.user.dto.Supervisor;

public interface SupervisorDAO {

	public abstract void add(Supervisor transientInstance);

	public abstract void remove(Supervisor persistentInstance);

	public abstract Supervisor update(Supervisor detachedInstance);

	public abstract Supervisor findById(java.lang.String id);

	public abstract List<Supervisor> findByExample(Supervisor instance);

    /**
     * Returns a list of Supervisor objects that represents the employees or users for this supervisor
     * @param supervisorId
     * @return
     */
	 public List<Supervisor> findEmployees(String supervisorId);
	 
	  /**
	   * Returns a List of supervisor objects that represents the supervisors for this employee or user.
	   * @param employeeId
	   * @return
	   */
	 public List<Supervisor> findSupervisors(String employeeId);
	 
	  /**
	   * Returns the primary supervisor for this employee. Null if no primary is defined.
	   * @param employeeId
	   * @return
	   */
	 public Supervisor findPrimarySupervisor(String employeeId);
}