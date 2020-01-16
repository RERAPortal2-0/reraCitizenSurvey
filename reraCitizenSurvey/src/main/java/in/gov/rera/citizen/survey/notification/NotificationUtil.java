package in.gov.rera.citizen.survey.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

//@Service
public class NotificationUtil {



	
	  @Value(value="${rera.mail.service.url}") private String mailServiceURL;
	  
	  @Value(value="${rera.sms.service.url}") private String smsServiceURL;
	  
	  public void sendEmail(EmailNotification email){
	  mailServiceURL=mailServiceURL.replaceAll("\"", "");
	  System.out.println("mailServiceURL  "+mailServiceURL); RestTemplate
	  restTemplate = new RestTemplate(); restTemplate.postForEntity(mailServiceURL,
	  email, email.getClass()); }
	  
	  public void sendSms(SmsNotification sms){ RestTemplate restTemplate = new
	  RestTemplate(); smsServiceURL=smsServiceURL.replaceAll("\"", "");
	  System.out.println("smsServiceURL  "+smsServiceURL);
	  restTemplate.postForEntity(smsServiceURL, sms, sms.getClass()); }
	 
}
