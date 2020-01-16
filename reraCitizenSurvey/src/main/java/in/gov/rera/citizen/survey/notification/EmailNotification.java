package in.gov.rera.citizen.survey.notification;

import java.util.Calendar;

public class EmailNotification {

	private Long id;

	private String sendTo;

	private String sender;

	private String subject;

	private String sendAsOtp = "N";

	private String bodyContent;

	private String html;

	private String fromAdd;

	private String ccAdd;

	private String processName;

	private String bccAdd;

	private String status;

	private Calendar createdOn;

	private Calendar sendedOn;

	private Integer attempt;

	public Integer getAttempt() {

		return attempt;

	}

	public void setAttempt(Integer attempt) {
		this.attempt = attempt;
	}

	public String getSendAsOtp() {
		return sendAsOtp;
	}

	public void setSendAsOtp(String sendAsOtp) {
		this.sendAsOtp = sendAsOtp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Calendar getSendedOn() {
		return sendedOn;
	}

	public void setSendedOn(Calendar sendedOn) {
		this.sendedOn = sendedOn;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String getFromAdd() {
		return fromAdd;
	}

	public void setFromAdd(String fromAdd) {
		this.fromAdd = fromAdd;
	}

	public String getCcAdd() {
		return ccAdd;
	}

	public void setCcAdd(String ccAdd) {
		this.ccAdd = ccAdd;
	}

	public String getBccAdd() {
		return bccAdd;
	}

	public void setBccAdd(String bccAdd) {
		this.bccAdd = bccAdd;
	}

}
