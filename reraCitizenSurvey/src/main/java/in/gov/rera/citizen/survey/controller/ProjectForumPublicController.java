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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;
import in.gov.rera.citizen.survey.common.model.ResponseModel;
import in.gov.rera.citizen.survey.model.AllotteeForumTopicModel;
import in.gov.rera.citizen.survey.model.ProjectForumModel;
import in.gov.rera.citizen.survey.services.ProjectForumService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/citizen_survey/project-forum")
public class ProjectForumPublicController {
	private static final Logger logger = LogManager.getLogger(ProjectForumPublicController.class);

	@Autowired
	ProjectForumService pForumService;

	@Autowired
	Environment env;

	@PostMapping("/save") 
	  public ResponseEntity<?> saveOrUpdateProjectForum(@RequestBody ProjectForumModel model) throws Exception {
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ProjectForumModel oldModel = pForumService.findByProjectId(model.getProjectId()); 
		if(oldModel==null)
		{
			model=pForumService.saveProjectForum(model);
		}
		else
		{
			oldModel.setProjectName(model.getProjectName());
			oldModel.setPromoterName(model.getPromoterName());
			oldModel.setForumName(model.getForumName());
			oldModel.setStatus(model.getStatus());
			model = pForumService.saveProjectForum(oldModel);
		}
	    ResponseModel rs = new ResponseModel();
	    rs.setMessage("Submitted successfully.");
	    rs.setStatus("200");
	    rs.setData(model);
	  return ResponseEntity.ok().body(rs); 
}
	
	
}
