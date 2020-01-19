package in.gov.rera.citizen.survey.model;

import java.io.Serializable;
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

@Entity(name = "AfsClauseModel")
@Table(name = "TM_AFS_CLAUSE")
public class AfsClauseModel implements Serializable {

	private static final long serialVersionUID = 1803590463071163237L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AFS_CLAUSE_ID")
	private Long afsClauseId;

	@Column(name = "CLAUSE_CODE")
	private String clauseCode;

	@Column(name = "CLAUSE_NAME")
	private String clauseName;

	@Column(name = "CLAUSE_DETAIL", length=2000)
	private String clauseDtl;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable=false)
	private Calendar createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedOn")
	private java.util.Calendar modifiedOn;
	
	public java.util.Calendar getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(java.util.Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Column(name = "CREATED_BY")
	private String createdBy;

	public Long getAfsClauseId() {
		return afsClauseId;
	}

	public void setAfsClauseId(Long afsClauseId) {
		this.afsClauseId = afsClauseId;
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

	public String getClauseDtl() {
		return clauseDtl;
	}

	public void setClauseDtl(String clauseDtl) {
		this.clauseDtl = clauseDtl;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
