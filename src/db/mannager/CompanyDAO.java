package db.mannager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import framework.DadosGlobais;

public class CompanyDAO {
	public boolean insertCompany (Company company){
		String query = "INSERT INTO Company(companyName, companyAddress, companyDescription) "
				+ "VALUES ('"+ company.getName() + "', '" + company.getAddress() + "', '" + company.getDescription() + "');";
		DadosGlobais.getDados().getMyDb().update(query);
		return true;
	}

	public boolean removeCompany (Company company) {
		String query = "DELETE FROM Company WHERE companyName = '" + company.getName() + "';";
		DadosGlobais.getDados().getMyDb().update(query);
		return true;
	}

	public Company getCompany (String companyName){
		Company foundCompany = new Company("", "", "", null);
		String query = "SELECT * FROM Company WHERE companyName = '" + companyName + "' LIMIT 1;";
		// Buscar a empresa
		List<Map<String, Object>> result = DadosGlobais.getDados().getMyDb().select(query);
		Integer id         = (Integer) result.get(0).get("idcompany");
		String name        = (String) result.get(0).get("companyname");
		String address     = (String) result.get(0).get("companyaddress");
		String description = (String) result.get(0).get("companydescription");
		
		// Buscar as ofertas empregos de uma empresa
		String queryJobs = "SELECT * FROM Job WHERE company_idCompany = " + id;
		List<Job> compJobs = new ArrayList<Job>();
		List<Map<String, Object>> jobResult = DadosGlobais.getDados().getMyDb().select(queryJobs);
		for (Map<String, Object> job : jobResult) {
			String title = (String) job.get("jobtitle");
			String role  = (String) job.get("jobrole");
			String req   = (String) job.get("jobrequeirements");
			String date  = (String) job.get("jobpostdate");
			String rem   = (String) job.get("jobrem");
			compJobs.add(new Job(title, date, role, req, Double.parseDouble(rem)));
		}
		
		foundCompany = new Company(name, address, description, compJobs);
		return foundCompany;
	}

	public boolean updateCompany (Company olderCompany, Company newCompany) {
		String query = "UPDATE Company SET companyName = '" + newCompany.getName() + "', companyAddress = '"
				+ newCompany.getAddress() + "', companyDescription = '" + newCompany.getDescription()
				+ "' WHERE companyName = '" + olderCompany.getName() + "');";
		DadosGlobais.getDados().getMyDb().update(query);
		return true;
	}
}
