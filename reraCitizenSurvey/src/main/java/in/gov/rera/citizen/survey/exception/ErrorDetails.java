package in.gov.rera.citizen.survey.exception;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
	Map<String,String> detailsMap;
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public ErrorDetails(Date timestamp, String message, Map<String,String> detailsMap) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	public Map<String,String> getDetailsMap() {
		return detailsMap;
	}
}
