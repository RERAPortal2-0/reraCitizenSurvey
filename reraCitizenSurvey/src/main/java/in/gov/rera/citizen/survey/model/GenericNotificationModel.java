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

@Entity(name = "GenericNotificationModel")
@Table(name = "TM_GENERIC_NOTIFICATION")
public class GenericNotificationModel implements Serializable {

	private static final long serialVersionUID = 1803590463071163237L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTIFICATION_ID")
	private Long 	notificationId;

	@Column(name = "PROCESS_ID")
	private String processId;

	@Column(name = "PROCESS_TYPE")
	private String processType;

	@Column(name = "NOTIFICATION_TEXT", length=2000)
	private String notificationText;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON", updatable=false)
	private Calendar createdOn;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedOn")
	private java.util.Calendar modifiedOn;

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getNotificationText() {
		return notificationText;
	}

	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
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
