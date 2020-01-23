package in.gov.rera.citizen.survey.model;

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

@Entity(name = "AllotteeForumTopicModel")
@Table(name = "TL_ALLOTTEE_FORUM_TOPIC")
public class AllotteeForumTopicModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ALLOTTE_FORUM_ID")
	private Long allotteeForumId;

	@Column(name = "PROJECT_ID")
	private Long projectId;

	@Column(name="PROJECT_FORUM_FK")
	private Long projectForumId;
	
	@Column(name="PROMOTER_NAME", length = 500)
	private String promoterName;
	
	@Column(name="TOPIC_NAME", length = 1000)
	private String topicName;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;
	
	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATER_ID")
	private Long createrId;
	
	@Column(name="CREATER_NAME")
	private String createrName;
	
	@Column(name="CREATER_TYPE")
	private String createrType;
	
	@Column(name="CREATER_EMAILID")
	private String createrEmailId;
	
	@Column(name="CREATER_MOBILE")
	private String createrMobile;
	
	public String getCreaterMobile() {
		return createrMobile;
	}

	public void setCreaterMobile(String createrMobile) {
		this.createrMobile = createrMobile;
	}

	public String getCreaterType() {
		return createrType;
	}

	public void setCreaterType(String createrType) {
		this.createrType = createrType;
	}

	public String getCreaterEmailId() {
		return createrEmailId;
	}

	public void setCreaterEmailId(String createrEmailId) {
		this.createrEmailId = createrEmailId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "TOPIC_ID_FK") 
	private List<TopicReplyModel> topicReplyList;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "TOPIC_LIKES_FK") 
	private List<TopicLikesModel> topicLikesList;

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public Long getAllotteeForumId() {
		return allotteeForumId;
	}

	public void setAllotteeForumId(Long allotteeForumId) {
		this.allotteeForumId = allotteeForumId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getProjectForumId() {
		return projectForumId;
	}

	public void setProjectForumId(Long projectForumId) {
		this.projectForumId = projectForumId;
	}

	public String getPromoterName() {
		return promoterName;
	}

	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public List<TopicReplyModel> getTopicReplyList() {
		return topicReplyList;
	}

	public void setTopicReplyList(List<TopicReplyModel> topicReplyList) {
		this.topicReplyList = topicReplyList;
	}

	public List<TopicLikesModel> getTopicLikesList() {
		return topicLikesList;
	}

	public void setTopicLikesList(List<TopicLikesModel> topicLikesList) {
		this.topicLikesList = topicLikesList;
	}

}
