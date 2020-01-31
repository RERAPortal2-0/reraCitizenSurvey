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

	@GetMapping("/get-modified-by-id{id}")
	public ResponseEntity<?> getProjectModifiedAfsById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException, IOException, ParseException {
		ProjectAfsModel model = afsService.findById(id);
		List<ProjectAfsClauseModel> clauseList = new ArrayList<>();
		for (ProjectAfsClauseModel m : model.getAfsClauseList()) {
			if (!m.getAction().equals("")) {
				clauseList.add(m);
			}
		}
		model.setAfsClauseList(clauseList);
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

	@GetMapping("/get-project-afs-log/{projectId}")
	public ResponseEntity<?> getProjectAfsLogDetailsByProjectId(@PathVariable(value = "projectId") Long projectId)
			throws ResourceNotFoundException, IOException, ParseException {
		List<ProjectAfsModel> list = afsService.findByAfsLogByProjectId(projectId);
		List<ProjectAfsModel> logList = new ArrayList<ProjectAfsModel>();
		ResponseModel rs = new ResponseModel();
		for (int i = list.size() - 1; i >= 0; i--) {
			ProjectAfsModel m = list.get(i);
			if (m.getAfsLogId() != null) {
				m.setAfsClauseList(null);
				logList.add(m);
			}
		}
		if (logList.size() > 0) {
			rs.setMessage("Log Found.");
			rs.setStatus("200");
			rs.setData(logList);
		} else {
			rs.setMessage("No Log Found.");
			rs.setStatus("404");
			rs.setData("");
		}
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-all-by-status{status}")
	public ResponseEntity<?> getProjectAfsDetailsByStatus(@PathVariable(value = "status") String status)
			throws ResourceNotFoundException, IOException, ParseException {
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
		if (model.getStatus().equals(ReraConstants.APPROVED)) {
			model = afsService.findById(model.getProjectAfsId());
			model.setStatus(ReraConstants.APPROVED);
			model = afsService.saveProjectAfs(model);
		} else if (model.getStatus().equals(ReraConstants.PENDING_WITH_AUTH)) {
			model = afsService.findById(model.getProjectAfsId());
			int i = 0;
			for (ProjectAfsClauseModel m : model.getAfsClauseList()) {
				if (!"".equals(m.getAction())) {
					i = 1;
				}
			}
			if (i == 1) {
				model.setStatus(ReraConstants.PENDING_WITH_AUTH);
			} else {
				model.setStatus(ReraConstants.APPROVED);
			}
			model.setAuthRemarks("");
			model = afsService.saveProjectAfs(model);
		} else if (model.getStatus().equals(ReraConstants.REVIEW)) {
			ProjectAfsModel isPresent = afsService.findById(model.getProjectAfsId());
			Optional.ofNullable(isPresent)
					.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("ID IS NOT PRESENT")));

			isPresent.setStatus(ReraConstants.REVIEW);
			isPresent.setAuthRemarks(model.getAuthRemarks());
			isPresent = afsService.saveProjectAfs(isPresent);

			List<ProjectAfsClauseModel> newList = new ArrayList<ProjectAfsClauseModel>();
			for (ProjectAfsClauseModel l : isPresent.getAfsClauseList()) {
				l.setAuthorityStatus(null);
				newList.add(l);
			}
			isPresent.setAfsClauseList(newList);

			// model.getAfsClauseList()
			// isPresent.getAfsClauseList();

			for (ProjectAfsClauseModel m : model.getAfsClauseList()) {
				for(ProjectAfsClauseModel m2:isPresent.getAfsClauseList()) {
					if(m.getClauseCode().contentEquals(m2.getClauseCode())) {
						m2.setAuthorityStatus(m.getAuthorityStatus());
					}
				}
			}

			ProjectAfsModel newModel = new ProjectAfsModel();
			List<ProjectAfsClauseModel> newChlList = new ArrayList<ProjectAfsClauseModel>();
			newModel.setAfsLogId(isPresent.getProjectAfsId());
			newModel.setAuthRemarks(isPresent.getAuthRemarks());
			newModel.setMobileNo(isPresent.getMobileNo());
			newModel.setProjectId(isPresent.getProjectId());
			newModel.setProjectName(isPresent.getProjectName());
			newModel.setProjectType(isPresent.getProjectType());
			newModel.setPromoterEmailId(isPresent.getPromoterEmailId());
			newModel.setStatus(ReraConstants.NEW);
			newModel.setPromoterId(isPresent.getPromoterId());
			newModel.setPromoterName(isPresent.getPromoterName());
			for (ProjectAfsClauseModel l : isPresent.getAfsClauseList()) {
				ProjectAfsClauseModel m = new ProjectAfsClauseModel();
				m.setClauseCode(l.getClauseCode());
				m.setClauseName(l.getClauseName());
				m.setClauseDtl(l.getClauseDtl());
				if ("DELETE".equals(l.getAction())) {
					m.setAction(null);
				} else {
					m.setAction(l.getAction());
				}
				m.setAuthorityStatus(l.getAuthorityStatus());
				newChlList.add(m);
			}
			newModel.setAfsClauseList(newChlList);
			model = afsService.saveProjectAfs(newModel);
		}
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
}
