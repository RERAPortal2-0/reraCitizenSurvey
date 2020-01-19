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


@Entity(name = "TopicLikesModel")
@Table(name = "TL_TOPIC_LIKES_DTL")
public class TopicLikesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOPIC_LIKES_ID")
	private Long tLikesId;

	@Column(name = "TOPIC_LIKES_FK")
	private Long topicId;
	
	@Column(name="LIKED_BY_ID")
	private Long likedById;
	
	@Column(name="LIKED_BY_TYPE")
	private String likedByType;
	
	@Column(name="LIKED_BY_NAME")
	private String likedByName;
	
	@Column(name="LIKED_BY_MOBILE_NO")
	private String likedByMobileNo;

	@Column(name="LIKED_BY_EMAID_ID")
	private String likedByEmailId;

	public Long getLikedById() {
		return likedById;
	}

	public void setLikedById(Long likedById) {
		this.likedById = likedById;
	}

	public String getLikedByMobileNo() {
		return likedByMobileNo;
	}

	public void setLikedByMobileNo(String likedByMobileNo) {
		this.likedByMobileNo = likedByMobileNo;
	}

	public String getLikedByType() {
		return likedByType;
	}

	public void setLikedByType(String likedByType) {
		this.likedByType = likedByType;
	}

	public String getLikedByName() {
		return likedByName;
	}

	public void setLikedByName(String likedByName) {
		this.likedByName = likedByName;
	}

	public String getLikedByEmailId() {
		return likedByEmailId;
	}

	public void setLikedByEmailId(String likedByEmailId) {
		this.likedByEmailId = likedByEmailId;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LIKED_ON")
	private Calendar likedOn;

	public Long gettLikesId() {
		return tLikesId;
	}

	public void settLikesId(Long tLikesId) {
		this.tLikesId = tLikesId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Calendar getLikedOn() {
		return likedOn;
	}

	public void setLikedOn(Calendar likedOn) {
		this.likedOn = likedOn;
	}

	
	
	

	


	
}
