package db.mannager;

import framework.DadosGlobais;

public class CompanyDAO {
	public boolean insertCompany (Company company){
		
		return true;
	}

	public boolean removeCompany (Company company) {
		return true;
	}

	public Company getCompany (Company company){
		Company foundCompany = new Company("", "", "", null);
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
