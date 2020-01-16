package in.gov.rera.citizen.survey.notification;

import java.util.Calendar;


public class SmsNotification {

	private Long id;
	

	private String mobileNo;
	

	private String message;

	private String processName;


	private String sendAsOtp="N";

	private String status;
	

	private Calendar createdOn;
	

	private Calendar sendedOn;
	

	
	

	

	public String getProcessName() {
		return processName;
	}




	public void setProcessName(String processName) {
		this.processName = processName;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
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




	public String getSendAsOtp() {
		return this.sendAsOtp; 
	}




	public void setSendAsOtp(String sendAsOtp) {
		this.sendAsOtp = sendAsOtp;
	}



	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}





	
	
	
}
