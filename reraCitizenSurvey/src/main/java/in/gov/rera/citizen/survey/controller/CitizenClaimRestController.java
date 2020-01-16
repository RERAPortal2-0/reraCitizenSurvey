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
import in.gov.rera.citizen.survey.model.CitizenClaimModel;
import in.gov.rera.citizen.survey.services.AfsClauseService;
import in.gov.rera.citizen.survey.services.CitizenClaimService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/secure/citizen_claim")
public class CitizenClaimRestController {
	private static final Logger logger = LogManager.getLogger(CitizenClaimRestController.class);

	@Autowired
	CitizenClaimService service;

	@Autowired
	Environment env;

	@GetMapping("/get-all")
	public ResponseEntity<?> getAllAfsList() throws ResourceNotFoundException, IOException, ParseException {
		List<CitizenClaimModel> list = service.findAll();
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
		CitizenClaimModel model = service.findById(id);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
	
	@GetMapping("/get-all-by-projectid{projectId}")
	public ResponseEntity<?> getAllProjectAfsList(@PathVariable(value = "projectId") Long projectId)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called id is " + projectId);
		List<CitizenClaimModel> list = service.findByProjectId(projectId);
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(list);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-kyc/{kyc}")
	public ResponseEntity<?> getAfsDetailsByclauseCode(@PathVariable(value = "kyc") String kyc)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called clauseCode is " + kyc);
		CitizenClaimModel model = service.findByKyc(kyc);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
	
	
	
	@PostMapping("/save")
	public ResponseEntity<?> saveAfsClause(@RequestBody CitizenClaimModel model)
			throws ResourceNotFoundException {
		Optional.ofNullable(model)
				.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		    model = service.save(model);
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@PostMapping("/validate-kyc")
	public ResponseEntity<?> validateAllotteeKyc(@RequestBody CitizenClaimModel model)
			throws ResourceNotFoundException {
		Optional.ofNullable(model)
				.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		CitizenClaimModel returnModel=new CitizenClaimModel();
		List<CitizenClaimModel> kycList=service.findByProjectId(model.getProjectId());
		if(kycList.size()>0)
		{
			System.out.println("inside list:::::::::::::::::::");
			for(CitizenClaimModel m:kycList)
			{
			    if(m.getAllotteekyc().equals(model.getAllotteekyc()) 
			    		&& m.getBlockName().equals(model.getBlockName())
			    		&& m.getFlatNumber().equals(model.getFlatNumber()))	
			    {
			    	returnModel=m;
			    }
			}
		}
		ResponseModel rs = new ResponseModel();
		if(!returnModel.equals(null)) {
		rs.setMessage("Data matched Successfully.");
		rs.setStatus("200");
		rs.setData(returnModel);
		}
		else
		{
			rs.setMessage("Data not matched.");
			rs.setStatus("404");
			rs.setData("");
		}
		return ResponseEntity.ok().body(rs);
	}
	
	@PostMapping("/delete{id}")
	public ResponseEntity<?> deleteBankDtl(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Optional.ofNullable(id)
				.orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		service.deleteById(id);
		ResponseModel rs = new ResponseModel();
		
		rs.setMessage("Records Deleted.");
		rs.setStatus("200");
		rs.setData("AFS Clause Details Deleted Successfully");
		return ResponseEntity.ok().body(rs);
	}

}
