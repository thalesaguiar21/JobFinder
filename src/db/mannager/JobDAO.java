package db.mannager;

import java.util.ArrayList;
import java.util.List;

import framework.DadosGlobais;

public class JobDAO {
	
	/*private String title;
	private String postDate;
	private String role;
	private String requirements;
	private double rem;
	private String companyName;*/
	public boolean insertJob ( String title, String postDate, String role
						  	 , String requeriments, String rem, String companyName){
		DadosGlobais.getDados().getMyDb().update("INSERT INTO Job (jobTitle, jobRole, jobRequirements" +
						  	                     ", jobPostDate");
		return true;
	}
	
	public boolean removeJob (String title) {
		return true;
	}
	
	public List<Job> getJob (String title){
		List<Job> foundJobs = new ArrayList<Job>();
		return foundJobs;
	}
	
	public boolean updateJob (String title) {
		return true;
	}

}
