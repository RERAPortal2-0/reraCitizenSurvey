package in.gov.rera.citizen.survey.security;

public class AuthUser {

	private Long userId;

	private String loginId;

	private String userName;

	private String userCategory;

	private String email;

	private String mobile;

	private String userType;

	private Long profileId;

	private String userStatus;

	private String team;
	private String department;
	
	
	
	public AuthUser(String[] data) throws Exception {
		if (data.length == 9) {
			this.setUserId(Long.parseLong(data[0]));
			this.setLoginId(data[1]);
			this.setUserName(data[2]);
			this.setEmail(data[3]);
			this.setMobile(data[4]);
			this.setUserCategory(data[5]);
			this.setUserType(data[6]);
			this.setProfileId(Long.parseLong(data[7]));
		} else if (data.length == 11) {
			this.setUserId(Long.parseLong(data[0]));
			this.setLoginId(data[1]);
			this.setUserName(data[2]);
			this.setEmail(data[3]);
			this.setMobile(data[4]);
			this.setUserCategory(data[5]);
			this.setUserType(data[6]);
			this.setDepartment(data[7]);
			this.setTeam(data[8]);
			this.setProfileId(Long.parseLong(data[9]));
		} else  {
			throw new Exception("Invalid Token.");
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
