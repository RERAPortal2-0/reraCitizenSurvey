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


@Entity(name = "AllotteeAfsDtlModel")
@Table(name = "TL_ALLOTTEE_AFS_DTL")
public class AllotteeAfsDtlModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ALLOTTE_AFS_ID")
	private Long allotteeAfsId;

	@Column(name = "PROJECT_ID")
	private Long projectId;
	
	@Column(name="ALLOTTEE_KYC_ID", length = 500)
	private String kycId;
	
	@Column(name="TOPIC_NAME")
	private String topicName;
	
	@Column(name="DOC_ID")
	private Long docId;
	
	@Column(name="DOC_UID")
	private String docUId;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;
	
	@Column(name="CREATED_BY")
	private String createdBy;

	public Long getAllotteeAfsId() {
		return allotteeAfsId;
	}

	public void setAllotteeAfsId(Long allotteeAfsId) {
		this.allotteeAfsId = allotteeAfsId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getKycId() {
		return kycId;
	}

	public void setKycId(String kycId) {
		this.kycId = kycId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocUId() {
		return docUId;
	}

	public void setDocUId(String docUId) {
		this.docUId = docUId;
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
