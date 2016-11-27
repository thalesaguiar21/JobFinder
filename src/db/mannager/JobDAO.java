package db.mannager;

import java.util.List;
import java.util.Map;

import framework.DadosGlobais;

public class JobDAO {
	
	public boolean insertJob (Job job, int companyId){
		String query = "INSERT INTO Job(company_idCompany, jobTitle, jobRole, jobRequierements, jobPostDate, jobRem) "
				+ "VALUES (" + companyId + ", '" + job.getTitle() + ", '" + job.getRole() + ", '" + job.getRequirements()
				+ ", '" + job.getPostDate() + ", '" + job.getRem() + "');";
		DadosGlobais.getDados().getMyDb().update(query);
		return true;
	}
	
	public boolean removeJob (String title) {
		String query = "DELETE FROM Job WHERE jobTitle = '" + title + "';";
		DadosGlobais.getDados().getMyDb().update(query);
		return true;
	}
	
	public Job getJob (String title){
		String query = "SELECT * FROM Job WHERE jobTitle = '" + title + "' LIMIT 1;";
		
		List<Map<String, Object>> result = DadosGlobais.getDados().getMyDb().select(query);
		String tit   = (String) result.get(0).get("jobtitle");
		String role  = (String) result.get(0).get("jobrole");
		String req   = (String) result.get(0).get("jobrequeirements");
		String date  = (String) result.get(0).get("jobpostdate");
		String rem   = (String) result.get(0).get("jobrem");
		
		Job job = new Job(tit, date, role, req, Double.parseDouble(rem));
		return job;
	}
	
	public boolean updateJob (Job oldJob, Job newJob) {
		String query = "UPDATE Job SET jobTitle = '" + newJob.getTitle() + "', jobRole = '"
				+ newJob.getRole() + "', jobRequirements = '" + newJob.getRequirements()
				+ "', jobPostDate = '" + newJob.getPostDate() + "', jobRem = " + newJob.getRem()
				+ " WHERE jobTitle = '" + oldJob.getTitle() + "');";
		DadosGlobais.getDados().getMyDb().update(query);
		return true;
	}

}
