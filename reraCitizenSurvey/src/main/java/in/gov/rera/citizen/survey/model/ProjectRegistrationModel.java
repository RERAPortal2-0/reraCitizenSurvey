package in.gov.rera.citizen.survey.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ProjectRegistrationModel {


	private Long projRegId;

	/* promoter Details */
	@Column(name = "PMTR_ID")
	private Long promoterId;

	@Column(name = "PMTR_EMAIL_ID")
	private String promoteremailId;

	@Column(name = "PMTR_NAME")
	private String promoterName;

	@Column(name = "PRMTR_TYPE")
	private String promoterType;

	@Column(name = "PR_REG_NO")
	private String projRegNo;

	@Column(name = "PR_MOBILE_NO")
	private String promoterMobileNo;

	@Column(name = "PR_JOINTDEVELOPEMNT")
	private String jointDevelopmentFlag;

    @Column(name = "PR_ACK_NO")
	private String projectAckNo;

	@Column(name = "PR_APP_SUBMISSION_DATE")
	private Date appSubmissionDate;

	@Column(name = "PR_STATUS")
	private String status;

	@Column(name = "PR_APPROVED_ON")
	private Calendar approvedOn;

	@Column(name = "PR_EXPRIRED_DATE")
	private Calendar expireDate;

	@Column(name = "PR_LAST_UPDATED_ON")
	private Calendar lastUpdatedOn;

	@Column(name = "PR_APPROVED_BY")
	private String approvedBy;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval=true)
	@JoinColumn(name = "PRJ_REG_ID_DTL_FK")
	private ProjectDetailModel projectDetailsModel;

    public ProjectDetailModel getProjectDetailsModel() {
		return projectDetailsModel;
	}

	public void setProjectDetailsModel(ProjectDetailModel projectDetailsModel) {
		this.projectDetailsModel = projectDetailsModel;
	}




	@Column(name = "PR_ESIGN_STATUS")
	private String esignStatus;
    
    
    /***********************************************************************
	 * Professionals
	 ***********************************************************************/
	/*
	 * @OneToMany(cascade = CascadeType.PERSIST, fetch =
	 * FetchType.LAZY,orphanRemoval=true)
	 * 
	 * @JoinColumn(name = "PRJ_REG_ID_ARC_FK") private Set<ArcheitectDetailsModel>
	 * ArcheitectDetailsModel;
	 * 
	 * @OneToMany(cascade = CascadeType.PERSIST, fetch =
	 * FetchType.LAZY,orphanRemoval=true)
	 * 
	 * @JoinColumn(name = "PRJ_REG_ID_ENG_FK") private Set<EngineerDetailsModel>
	 * engineerDetailsModel;
	 */
	
	
	/*
	 * @OneToMany(cascade = CascadeType.PERSIST, fetch =
	 * FetchType.LAZY,orphanRemoval=true)
	 * 
	 * @JoinColumn(name = "PRJ_REG_ID_CA_FK") private Set<CADetailModel>
	 * caDetailModel;
	 * 
	 * @OneToMany(cascade = CascadeType.PERSIST, fetch =
	 * FetchType.LAZY,orphanRemoval=true)
	 * 
	 * @JoinColumn(name = "PRJ_REG_ID_AGENT_FK") private
	 * Set<ProjectAgentDetailModel> projectAgentDetailSet;
	 * 
	 * @OneToMany(cascade = CascadeType.PERSIST, fetch =
	 * FetchType.LAZY,orphanRemoval=true)
	 * 
	 * @JoinColumn(name = "PRJ_REG_ID_CNTR_FK") private
	 * Set<ContractorProfessionalModel> contacterModelSet;
	 * 
	 */
	




	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PR_CREATED_ON")
	private Date createdOn;
    
    
    
    public Long getProjRegId() {
		return projRegId;
	}

    public void setProjRegId(Long projRegId) {
		this.projRegId = projRegId;
	}




	public Long getPromoterId() {
		return promoterId;
	}




	public void setPromoterId(Long promoterId) {
		this.promoterId = promoterId;
	}




	public String getPromoteremailId() {
		return promoteremailId;
	}




	public void setPromoteremailId(String promoteremailId) {
		this.promoteremailId = promoteremailId;
	}




	public String getPromoterName() {
		return promoterName;
	}




	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}




	public String getPromoterType() {
		return promoterType;
	}




	public void setPromoterType(String promoterType) {
		this.promoterType = promoterType;
	}




	public String getProjRegNo() {
		return projRegNo;
	}




	public void setProjRegNo(String projRegNo) {
		this.projRegNo = projRegNo;
	}




	public String getPromoterMobileNo() {
		return promoterMobileNo;
	}




	public void setPromoterMobileNo(String promoterMobileNo) {
		this.promoterMobileNo = promoterMobileNo;
	}




	public String getJointDevelopmentFlag() {
		return jointDevelopmentFlag;
	}




	public void setJointDevelopmentFlag(String jointDevelopmentFlag) {
		this.jointDevelopmentFlag = jointDevelopmentFlag;
	}




	public String getProjectAckNo() {
		return projectAckNo;
	}




	public void setProjectAckNo(String projectAckNo) {
		this.projectAckNo = projectAckNo;
	}




	public Date getAppSubmissionDate() {
		return appSubmissionDate;
	}




	public void setAppSubmissionDate(Date appSubmissionDate) {
		this.appSubmissionDate = appSubmissionDate;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public Calendar getApprovedOn() {
		return approvedOn;
	}




	public void setApprovedOn(Calendar approvedOn) {
		this.approvedOn = approvedOn;
	}




	public Calendar getExpireDate() {
		return expireDate;
	}




	public void setExpireDate(Calendar expireDate) {
		this.expireDate = expireDate;
	}




	public Calendar getLastUpdatedOn() {
		return lastUpdatedOn;
	}




	public void setLastUpdatedOn(Calendar lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}




	public String getApprovedBy() {
		return approvedBy;
	}




	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}


	public String getEsignStatus() {
		return esignStatus;
	}




	public void setEsignStatus(String esignStatus) {
		this.esignStatus = esignStatus;
	}




	public Date getCreatedOn() {
		return createdOn;
	}




	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}




	// Returning only year of application submission date
	public int getAppSubmissionYear() {
		Calendar c = Calendar.getInstance();
		int year = 0;
		if (appSubmissionDate != null) {
			c.setTime(appSubmissionDate);
		}
//		c.getTime().getYear()
		year = c.get(Calendar.YEAR);
		return year;
	}

}
