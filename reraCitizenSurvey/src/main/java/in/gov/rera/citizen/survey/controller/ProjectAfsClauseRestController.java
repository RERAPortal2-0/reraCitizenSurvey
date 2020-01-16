package in.gov.rera.citizen.survey.controller;

import java.io.IOException;
import java.text.ParseException;
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
import in.gov.rera.citizen.survey.services.AfsClauseService;
import in.gov.rera.citizen.survey.services.ProjectAfsClauseService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/secure/project-afs-clause")
public class ProjectAfsClauseRestController {
	private static final Logger logger = LogManager.getLogger(ProjectAfsClauseRestController.class);

	@Autowired
	ProjectAfsClauseService afsService;
	
	@Autowired
	AfsClauseService mService;

	@Autowired
	Environment env;

	@GetMapping("/get-by-id{id}")
	public ResponseEntity<?> getAfsDetailsById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called id is " + id);
		ProjectAfsClauseModel model = afsService.findById(id);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-project-afs-id{ProjectAfsId}")
	public ResponseEntity<?> getProjectAfsClauseDetailsByProjectAfsId(@PathVariable(value = "ProjectAfsId") Long ProjectAfsId)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called id is " + ProjectAfsId);
		List<ProjectAfsClauseModel> model = afsService.findByProjectAfsId(ProjectAfsId);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveAfsClause(@RequestBody ProjectAfsClauseModel model)
			throws ResourceNotFoundException {
		Optional.ofNullable(model)
				.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		System.out.println("saveAfsClause called");
	    if(model.getAction().equals(ReraConstants.NEW)) {
	    	List<ProjectAfsClauseModel> afsClauseList = afsService.findByProjectAfsId(model.getProjectAfsId());
	    	int size=afsClauseList.size();
	    	++size;
	    	String code= "NEWCL"+size;
	    	String name= "NEW CLAUSE-"+size;
	    	model.setClauseCode(code);
	    	model.setClauseName(name);
	    	model=afsService.saveProjectAfsClause(model);
	    }
	    else if(model.getAction().equals(ReraConstants.MODIFIED))
	    {
	    	ProjectAfsClauseModel oldModel=afsService.findById(model.getProjectAfsClauseId());
	    	Optional.ofNullable(oldModel)
			.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("CLAUSE NOT FOUND")));
	    	oldModel.setAction(ReraConstants.MODIFIED);
	    	oldModel.setClauseDtl(model.getClauseDtl());
	    	model=afsService.saveProjectAfsClause(oldModel);
	    }
	    else if(model.getAction().equals(ReraConstants.DELETE))
	    {
	    	ProjectAfsClauseModel oldModel=afsService.findById(model.getProjectAfsClauseId());
	    	Optional.ofNullable(oldModel)
			.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("CLAUSE NOT FOUND")));
	    	oldModel.setAction(ReraConstants.DELETE);
	    	model=afsService.saveProjectAfsClause(oldModel);
	    }
	    else if(model.getAction().equals(ReraConstants.REVIEW))
	    {
	    	ProjectAfsClauseModel oldModel=afsService.findById(model.getProjectAfsClauseId());
	    	Optional.ofNullable(oldModel)
			.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("CLAUSE NOT FOUND")));
	    	oldModel.setAuthorityStatus(ReraConstants.REVIEW);
	    	model=afsService.saveProjectAfsClause(oldModel);
	    }
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	/*
	 * @PostMapping("/delete{id}") public ResponseEntity<?>
	 * deleteBankDtl(@PathVariable(value = "id") Long id) throws
	 * ResourceNotFoundException { Optional.ofNullable(id) .orElseThrow(() -> new
	 * ResourceNotFoundException(env.getProperty("DATA_INVALID")));
	 * afsService.deleteById(id); ResponseModel rs = new ResponseModel();
	 * rs.setMessage("Records Deleted."); rs.setStatus("200");
	 * rs.setData("AFS Clause Details Deleted Successfully"); return
	 * ResponseEntity.ok().body(rs); }
	 */

}
