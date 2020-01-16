package in.gov.rera.citizen.survey.exception;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ErrorValidationDetails {
	private Date timestamp;
	private String message;
	private Map<String,String> details;

	

	public ErrorValidationDetails(Date timestamp, String message, Map<String, String> details) {
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

	public Map<String,String> getDetails() {
		return details;
	}
}
