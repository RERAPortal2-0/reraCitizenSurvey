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


@Entity(name = "TopicReplyModel")
@Table(name = "TL_TOPIC_REPLY_DTL")
public class TopicReplyModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOPIC_REPLY_ID")
	private Long tReplyId;

	@Column(name = "TOPIC_ID_FK")
	private Long topicId;

	@Column(name="REPLYED_TEXT")
	private String replyedText;
	
	@Column(name="REPLIER_ID")
	private String replierId;
	
	@Column(name="REPLIER_NAME")
	private String replierName;
	
	@Column(name="REPLIER_TYPE")
	private String replierType;
	
	@Column(name="REPLIER_MOBILE_NO")
	private String replierMobileNo;
	
	@Column(name="REPLIER_EMAIL")
	private String replierEmail;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPLIED_ON")
	private Calendar repliedOn;

	public String getReplyedText() {
		return replyedText;
	}

	public void setReplyedText(String replyedText) {
		this.replyedText = replyedText;
	}

	public String getReplierId() {
		return replierId;
	}

	public void setReplierId(String replierId) {
		this.replierId = replierId;
	}

	public String getReplierName() {
		return replierName;
	}

	public void setReplierName(String replierName) {
		this.replierName = replierName;
	}

	public String getReplierType() {
		return replierType;
	}

	public void setReplierType(String replierType) {
		this.replierType = replierType;
	}

	public String getReplierMobileNo() {
		return replierMobileNo;
	}

	public void setReplierMobileNo(String replierMobileNo) {
		this.replierMobileNo = replierMobileNo;
	}

	public String getReplierEmail() {
		return replierEmail;
	}

	public void setReplierEmail(String replierEmail) {
		this.replierEmail = replierEmail;
	}

	public Long gettReplyId() {
		return tReplyId;
	}

	public void settReplyId(Long tReplyId) {
		this.tReplyId = tReplyId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}


	public Calendar getRepliedOn() {
		return repliedOn;
	}

	public void setRepliedOn(Calendar repliedOn) {
		this.repliedOn = repliedOn;
	}
	
	

	


	
}
