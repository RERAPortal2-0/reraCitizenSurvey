package in.gov.rera.citizen.survey.model;

import java.io.Serializable;
import java.sql.Date;
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


@Entity(name = "ProjectForumModel")
@Table(name = "TT_PROJECT_FORUM")
public class ProjectForumModel implements Serializable {

	private static final long serialVersionUID = 1803590463071163237L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_FORUM_ID")
	private Long projectForumId;

	@Column(name = "PROJECT_ID")
	private Long projectId;

	@Column(name="PROJECT_NAME")
	private String projectName;
	
	@Column(name="FORUM_NAME")
	private String forumName;
	
	@Column(name="PROMOTER_NAME")
	private String promoterName;
	
	@Column(name="STATUS")
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;
	
	  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	  @JoinColumn(name = "PROJECT_FORUM_FK") 
	  private List<AllotteeForumTopicModel>
	  allotteeTopicList;

	public Long getProjectForumId() {
		return projectForumId;
	}

	public void setProjectForumId(Long projectForumId) {
		this.projectForumId = projectForumId;
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

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	public String getPromoterName() {
		return promoterName;
	}

	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}


	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public List<AllotteeForumTopicModel> getAllotteeTopicList() {
		return allotteeTopicList;
	}

	public void setAllotteeTopicList(List<AllotteeForumTopicModel> allotteeTopicList) {
		this.allotteeTopicList = allotteeTopicList;
	}

}
