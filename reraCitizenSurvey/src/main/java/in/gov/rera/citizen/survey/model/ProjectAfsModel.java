package in.gov.rera.citizen.survey.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "ProjectAfsModel")
@Table(name = "TT_PROJECT_AFS")
public class ProjectAfsModel implements Serializable {

	private static final long serialVersionUID = 1803590463071163237L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_AFS_ID")
	private Long projectAfsId;

	@Column(name = "PROMOTER_ID")
	private String promoterId;

	@Column(name = "PROMOTER_MOBILE_NO")
	private String mobileNo;

	@Column(name = "PROMOTER_EMAIL")
	private String promoterEmailId;

	@Column(name = "PROMOTER_NAME")
	private String promoterName;

	@Column(name = "PROJECT_ID")
	private Long projectId;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "PROJECT_TYPE")
	private String projectType;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "AUTHORITY_REMARKS")
	private String authRemarks;

	@Column(name = "PAFS_LOG_ID")
	private Long afsLogId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_AFS_FK")
	private List<ProjectAfsClauseModel> afsClauseList;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable = false)
	private Calendar createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedOn")
	private java.util.Calendar modifiedOn;

	public Long getProjectAfsId() {
		return projectAfsId;
	}

	public void setProjectAfsId(Long projectAfsId) {
		this.projectAfsId = projectAfsId;
	}

	public String getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(String promoterId) {
		this.promoterId = promoterId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPromoterEmailId() {
		return promoterEmailId;
	}

	public void setPromoterEmailId(String promoterEmailId) {
		this.promoterEmailId = promoterEmailId;
	}

	public String getPromoterName() {
		return promoterName;
	}

	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
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

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthRemarks() {
		return authRemarks;
	}

	public void setAuthRemarks(String authRemarks) {
		this.authRemarks = authRemarks;
	}

	public Long getAfsLogId() {
		return afsLogId;
	}

	public void setAfsLogId(Long afsLogId) {
		this.afsLogId = afsLogId;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public java.util.Calendar getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(java.util.Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public List<ProjectAfsClauseModel> getAfsClauseList() {
		return afsClauseList;
	}

	public void setAfsClauseList(List<ProjectAfsClauseModel> afsClauseList) {
		this.afsClauseList = afsClauseList;
	}

}
