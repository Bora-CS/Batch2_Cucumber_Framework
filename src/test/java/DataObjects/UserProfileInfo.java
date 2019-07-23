package DataObjects;

public class UserProfileInfo {
	public String handle;
	public String company;
	public String location;
	public String status;
	public String skills;

	public UserProfileInfo(String handle, String company, String location, String status, String skills) {
		this.handle = handle;
		this.company = company;
		this.location = location;
		this.status = status;
		this.skills = skills;

	}

	public UserProfileInfo() {
	}

}