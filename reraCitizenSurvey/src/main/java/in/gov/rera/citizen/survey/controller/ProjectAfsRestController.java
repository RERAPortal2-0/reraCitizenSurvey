package in.gov.rera.citizen.survey.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import in.gov.rera.citizen.survey.constants.ReraConstants;
import in.gov.rera.citizen.survey.exception.ResourceNotFoundException;
import in.gov.rera.citizen.survey.model.AfsClauseModel;
import in.gov.rera.citizen.survey.model.ProjectAfsClauseModel;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;
import in.gov.rera.citizen.survey.services.AfsClauseService;
import in.gov.rera.citizen.survey.services.ProjectAfsService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/secure/project-clause")
public class ProjectAfsRestController {
	private static final Logger logger = LogManager.getLogger(ProjectAfsRestController.class);

	@Autowired
	ProjectAfsService afsService;

	@Autowired
	Environment env;

	@GetMapping("/get-by-id{id}")
	public ResponseEntity<?> getProjectAfsDetailsById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException, IOException, ParseException {
		ProjectAfsModel model = afsService.findById(id);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-project-id{projectId}")
	public ResponseEntity<?> getProjectAfsDetailsByProjectId(@PathVariable(value = "projectId") Long projectId)
			throws ResourceNotFoundException, IOException, ParseException {
		List<ProjectAfsModel> model = afsService.findByProjectId(projectId);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-all-by-status{status}")
	public ResponseEntity<?> getProjectAfsDetailsByStatus(@PathVariable(value = "status") String status)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called status is " + status);
		List<ProjectAfsModel> model = afsService.findByStatus(status);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveAfsClause(@RequestBody ProjectAfsModel model) throws ResourceNotFoundException {
		Optional.ofNullable(model).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		logger.debug("saveAfsClause called");
		if (model.getStatus().equals(ReraConstants.PENDING_WITH_AUTH)) {
			model = afsService.findById(model.getProjectAfsId());
			int i=0;
			for(ProjectAfsClauseModel m:model.getAfsClauseList())
			{
				if(m.getAction()!=null)
				{
					i=1;
				}
			}
			if(i==1) {
				model.setStatus(ReraConstants.APPROVED);
			}
			else
			{
				model.setStatus(ReraConstants.PENDING_WITH_AUTH);
			}
			model.setAuthRemarks("");
			model = afsService.saveProjectAfs(model);
		} else if (model.getStatus().equals(ReraConstants.REVIEW)) {
			ProjectAfsModel isPresent = new ProjectAfsModel();
			isPresent = afsService.findById(model.getProjectAfsId());
			Optional.ofNullable(isPresent).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("ID IS NOT PRESENT")));
			model = afsService.saveProjectAfs(model);
			ProjectAfsModel newModel = new ProjectAfsModel();
			List<ProjectAfsClauseModel> newChlList = new ArrayList<ProjectAfsClauseModel>();
			newModel.setAfsLogId(model.getProjectAfsId());
			newModel.setAuthRemarks(model.getAuthRemarks());
			newModel.setMobileNo(model.getMobileNo());
			newModel.setProjectId(model.getProjectId());
			newModel.setProjectName(model.getProjectName());
			newModel.setProjectType(model.getProjectType());
			newModel.setPromoterEmailId(model.getPromoterEmailId());
			newModel.setStatus(ReraConstants.NEW);
			newModel.setPromoterId(model.getPromoterId());
			newModel.setPromoterName(model.getPromoterName());
			for (ProjectAfsClauseModel l : model.getAfsClauseList()) {
				ProjectAfsClauseModel m = new ProjectAfsClauseModel();
				m.setClauseCode(l.getClauseCode());
				m.setClauseName(l.getClauseName());
				m.setClauseDtl(l.getClauseDtl());
				if("DELETE".equals(l.getAction()))
				{
					m.setAction(null);
				}	
				else
				{
					m.setAction(l.getAction());
				}
				m.setAuthorityStatus(l.getAuthorityStatus());
				newChlList.add(m);
			}
			newModel.setAfsClauseList(newChlList);
			model = afsService.saveProjectAfs(newModel);
		} else if (model.getStatus().equals(ReraConstants.APPROVED)) {
			model = afsService.saveProjectAfs(model);
		}
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
}
