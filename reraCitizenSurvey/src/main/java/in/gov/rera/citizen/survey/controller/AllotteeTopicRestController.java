package in.gov.rera.citizen.survey.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import in.gov.rera.citizen.survey.common.model.ResponseModel;
import in.gov.rera.citizen.survey.exception.ResourceNotFoundException;
import in.gov.rera.citizen.survey.model.AllotteeForumTopicModel;
import in.gov.rera.citizen.survey.security.AuthSecurity;
import in.gov.rera.citizen.survey.security.AuthUser;
import in.gov.rera.citizen.survey.services.AllotteeTopicService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/secure/allottee")
public class AllotteeTopicRestController {
	private static final Logger logger = LogManager.getLogger(AllotteeTopicRestController.class);

	@Autowired
	AllotteeTopicService allotteeServ;

	@Autowired
	Environment env;

	@GetMapping("/all-topic{projectId}")
	public ResponseEntity<?> getAllProjectTopic(@PathVariable(value = "projectId") Long projectId)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called id is " + projectId);
		List<AllotteeForumTopicModel> allotteeList = new ArrayList<AllotteeForumTopicModel>();
		allotteeList = allotteeServ.findByProjectId(projectId, env.getProperty("URL_PROJECT_REG"));
		Optional.of(allotteeList).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Record found.");
		rs.setStatus("200");
		rs.setData(allotteeList);
		return ResponseEntity.ok().body(rs);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveBankDtl(@RequestBody AllotteeForumTopicModel allotteeModel, HttpServletRequest req) throws ResourceNotFoundException{
		    Optional.ofNullable(allotteeModel)
						.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		    AuthUser user = (AuthUser) req.getAttribute(AuthSecurity.AUTH_USER_ATTR);
		    allotteeModel.setCreaterId(user.getUserId());
		    allotteeModel.setCreaterName(user.getUserName());
		    allotteeModel.setCreatedBy(String.valueOf(user.getUserId()));
		    allotteeModel.setCreaterEmailId(user.getEmail());
		    allotteeModel.setCreaterMobile(user.getMobile());
		    allotteeModel.setCreaterType(user.getUserType());
		    allotteeModel = allotteeServ.saveTopicForum(allotteeModel);
		    ResponseModel rs = new ResponseModel();
			rs.setMessage("Data sbmitted Successfully.");
			rs.setStatus("200");
			rs.setData(allotteeModel);
		return ResponseEntity.ok().body(rs);
	}
}
