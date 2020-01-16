package in.gov.rera.citizen.survey.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;
import in.gov.rera.citizen.survey.common.model.ResponseModel;
import in.gov.rera.citizen.survey.model.ProjectForumModel;
import in.gov.rera.citizen.survey.services.ProjectForumService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/citizen_survey/secure")
public class ProjectForumController {
	private static final Logger logger = LogManager.getLogger(ProjectForumController.class);

	@Autowired
	ProjectForumService pForumService;

	@Autowired
	Environment env;

	@GetMapping("/project-id{projectId}") 
	  public ResponseEntity<?> getFormFivePdfDtlById(@PathVariable("projectId")Long projectId) throws Exception {
	  System.out.println("project id is " + projectId);
	  ProjectForumModel model = pForumService.findByProjectId(projectId,env.getProperty("URL_PROJECT_REG"));  
	  Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
	  ResponseModel rs = new ResponseModel();
	  rs.setMessage("Records found.");
	  rs.setStatus("200");
	  rs.setData(model);
	return ResponseEntity.ok().body(rs); 
}
	
	
}
