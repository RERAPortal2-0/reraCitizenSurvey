package in.gov.rera.citizen.survey.model;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;


@Entity(name = "CitizenClaimModel")
@Table(name = "TL_CITIZEN_CLAIM_DTL")
public class CitizenClaimModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CITIZEN_CLAIM_ID")
	private Long citizenClaimId;

	@Column(name = "PROJECT_ID")
	private Long projectId;
	
	@Column(name="PROJECT_NAME")
	private String projectName;
	
	@Column(name="ALLOTTEE_NAME")
	private String allotteeName;

	public String getAllotteeName() {
		return allotteeName;
	}

	public void setAllotteeName(String allotteeName) {
		this.allotteeName = allotteeName;
	}

	@Column(name="ALLOTTEE_KYC")
	private String allotteekyc;
	
	@Column(name="BLOCK_NAME")
	private String blockName;
	
	@Column(name="FLAT_NUMBER")
	private String flatNumber;

	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name="AFS_STATUS")
	private String afsStatus;
	
	@Column(name="DOC_UID")
	private String docUId;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_TYPE")
	private String userType;
	
	@Column(name="PROJECT_REG_NO")
	private String projectRegNo;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	public Long getCitizenClaimId() {
		return citizenClaimId;
	}

	public void setCitizenClaimId(Long citizenClaimId) {
		this.citizenClaimId = citizenClaimId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAllotteekyc() {
		return allotteekyc;
	}

	public void setAllotteekyc(String allotteekyc) {
		this.allotteekyc = allotteekyc;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAfsStatus() {
		return afsStatus;
	}

	public void setAfsStatus(String afsStatus) {
		this.afsStatus = afsStatus;
	}

	public String getDocUId() {
		return docUId;
	}

	public void setDocUId(String docUId) {
		this.docUId = docUId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}
	
	
}

