package db.mannager;

public class Job {

	private String title;
	private String postDate;
	private String role;
	private String requirements;
	private double rem;
	
	public Job (String title, String postDate, String role, String requirements, double rem) {
		this.title        = title;
		this.postDate     = postDate;
		this.role         = role;
		this.requirements = requirements;
		this.rem          = rem;
	}
	

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getRequirements() {
		return requirements;
	}
	
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public double getRem() {
		return rem;
	}
	
	public void setRem(double rem) {
		this.rem = rem;
	}
}
