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
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "ProjectAfsClauseModel")
@Table(name = "TL_PROJECT_AFS_CLAUSE")
public class ProjectAfsClauseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_AFS_CLAUSE_ID")
	private Long projectAfsClauseId;

	@Column(name = "PROJECT_AFS_FK")
	private Long projectAfsId;

	@Column(name = "CLAUSE_CODE")
	private String clauseCode;
	@Column(name = "CLAUSE_NAME")
	private String clauseName;

	@Column(name = "ACTION")
	private String action;

	@Column(name = "CLAUSE_DTL",length=2000)
	private String clauseDtl;
	
	@Column(name = "AUTHORITY_STATUS")
	private String authorityStatus;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable=false)
	private Calendar createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedOn")
	private java.util.Calendar modifiedOn;

	public Long getProjectAfsClauseId() {
		return projectAfsClauseId;
	}

	public void setProjectAfsClauseId(Long projectAfsClauseId) {
		this.projectAfsClauseId = projectAfsClauseId;
	}

	public Long getProjectAfsId() {
		return projectAfsId;
	}

	public void setProjectAfsId(Long projectAfsId) {
		this.projectAfsId = projectAfsId;
	}

	public String getClauseCode() {
		return clauseCode;
	}

	public void setClauseCode(String clauseCode) {
		this.clauseCode = clauseCode;
	}

	public String getClauseName() {
		return clauseName;
	}

	public void setClauseName(String clauseName) {
		this.clauseName = clauseName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClauseDtl() {
		return clauseDtl;
	}

	public void setClauseDtl(String clauseDtl) {
		this.clauseDtl = clauseDtl;
	}

	public String getAuthorityStatus() {
		return authorityStatus;
	}

	public void setAuthorityStatus(String authorityStatus) {
		this.authorityStatus = authorityStatus;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

	
}
