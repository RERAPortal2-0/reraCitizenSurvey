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
import in.gov.rera.citizen.survey.exception.ResourceNotFoundException;
import in.gov.rera.citizen.survey.model.AfsClauseModel;
import in.gov.rera.citizen.survey.services.AfsClauseService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/secure/afs-clause")
public class AfsClauseRestController {
	private static final Logger logger = LogManager.getLogger(AfsClauseRestController.class);

	@Autowired
	AfsClauseService afsService;

	@Autowired
	Environment env;

	@GetMapping("/get-all")
	public ResponseEntity<?> getAllAfsList() throws ResourceNotFoundException, IOException, ParseException {
		List<AfsClauseModel> list = afsService.findAll();
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		if(list.size()>0)
		{
			rs.setMessage("Records found.");
		}
		else
		{
			rs.setMessage("Record not exists");
		}
		rs.setStatus("200");
		rs.setData(list);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-id{id}")
	public ResponseEntity<?> getAfsDetailsById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called id is " + id);
		AfsClauseModel model = afsService.findById(id);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-code/{clauseCode}")
	public ResponseEntity<?> getAfsDetailsByclauseCode(@PathVariable(value = "clauseCode") String clauseCode)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called clauseCode is " + clauseCode);
		AfsClauseModel model = afsService.findByClauseCode(clauseCode);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveAfsClause(@RequestBody AfsClauseModel model)
			throws ResourceNotFoundException {
		Optional.ofNullable(model)
				.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		System.out.println("saveAfsClause called");
		   if(model.getAfsClauseId()==null) {
		    model = afsService.saveAfs(model);
		    model.setClauseCode(afsService.generateClauseCode(model.getAfsClauseId()));
			model.setClauseName(afsService.generateClauseName(model.getAfsClauseId()));
			model = afsService.saveAfs(model);
		   }
		   else
		   {
			 AfsClauseModel oldModel=new AfsClauseModel();
			 oldModel=  afsService.findById(model.getAfsClauseId());
			 oldModel.setClauseDtl(model.getClauseDtl());
             model = afsService.saveAfs(oldModel);
		   }
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@PostMapping("/delete{id}")
	public ResponseEntity<?> deleteBankDtl(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Optional.ofNullable(id)
				.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		afsService.deleteById(id);
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records Deleted.");
		rs.setStatus("200");
		rs.setData("AFS Clause Details Deleted Successfully");
		return ResponseEntity.ok().body(rs);
	}

}
