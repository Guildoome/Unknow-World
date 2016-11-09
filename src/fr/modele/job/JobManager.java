package fr.modele.job;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author JGC
 *
 */
public class JobManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2947862855569814580L;
	
	private ArrayList<JobItem> listeJobItem;
	
	public JobManager() {
		listeJobItem = new ArrayList<>();
	}

	public ArrayList<JobItem> getListeJobItem() {
		return listeJobItem;
	}

	/**
	 * Add a job to the list
	 * 
	 * @param item
	 * 			The job to add
	 */
	public void addJob(JobItem item) {
		if(item != null) {
			listeJobItem.add(item);
		}
	}

	/**
	 * Executes all the jobs in the list and automatically removes all those that are finished
	 */
	public void processJobs() {		
		int itemCount = 0;
		JobItem item = null;
		
		while((itemCount < listeJobItem.size()) && ((item = listeJobItem.get(itemCount)) != null)) {
			item.execute();
			
			if(item.getState() == EJobState.JOB_FINISHED) {
				listeJobItem.remove(item);
			}
			else {
				itemCount++;
			}
		}
	}
	
	/**
	 * Clears the job list
	 */
	public void clearJobs() {
		listeJobItem.clear();
	}
}
