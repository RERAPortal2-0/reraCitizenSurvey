package in.gov.rera.citizen.survey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import in.gov.rera.citizen.survey.security.AuthSecurity;

@SpringBootApplication
public class ReraCitizenSurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReraCitizenSurveyApplication.class, args);
		System.out.println("Citizen Survey server Started");
		
	}
	@Bean
	public FilterRegistrationBean<AuthSecurity> dawsonApiFilter() {
		FilterRegistrationBean<AuthSecurity> registration = new FilterRegistrationBean<AuthSecurity>();
		registration.setFilter(new AuthSecurity());
		registration.addUrlPatterns("/citizen_survey/secure/*");
		return registration;
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
