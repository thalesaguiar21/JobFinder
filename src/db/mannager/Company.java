package db.mannager;

import java.util.List;

public class Company {

	private String name;
	private String address;
	private String description;
	private List<Job> jobOffers;
	
	public Company(String name, String address, String description, List<Job> jobs) {
		this.name        = name;
		this.address     = address;
		this.description = description;
		this.jobOffers   = jobs;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Job> getJobOffers() {
		return jobOffers;
	}
	
	public void setJobOffers(List<Job> jobOffers) {
		this.jobOffers = jobOffers;
	}
}
