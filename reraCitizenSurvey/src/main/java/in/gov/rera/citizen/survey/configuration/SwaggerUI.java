package in.gov.rera.citizen.survey.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerUI {

	@Bean
	public Docket api(){
	    return  new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(
	            Arrays.asList(new ParameterBuilder()
	                    .name("RERA_USER_SIGNATURE")
	                    .description("Result Type: JSON(application/json)-XML(application/xml)")
	                    .modelRef(new ModelRef("string"))
	                    .parameterType("header")
	                    .required(true)
	                    .build()));
	}

}
