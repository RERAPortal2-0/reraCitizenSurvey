package in.gov.rera.citizen.survey.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import in.gov.rera.citizen.survey.model.CitizenClaimModel;
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
	public ResponseEntity<ResponseModel> getAllAfsList() throws ResourceNotFoundException, IOException, ParseException {
		List<CitizenClaimModel> list = service.findAll();
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		if (!list.isEmpty()) {
			rs.setMessage("Record found");
		} else {
			rs.setMessage("Record not exists");
		}
		rs.setStatus("200");
		rs.setData(list);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-id{id}")
	public ResponseEntity<ResponseModel> getAfsDetailsById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException, IOException, ParseException {
		CitizenClaimModel model = service.findById(id);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-all-by-projectid{projectId}")
	public ResponseEntity<ResponseModel> getAllProjectAfsList(@PathVariable(value = "projectId") Long projectId)
			throws ResourceNotFoundException, IOException, ParseException {
		List<CitizenClaimModel> list = service.findByProjectId(projectId);
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found");
		rs.setStatus("200");
		rs.setData(list);
		return ResponseEntity.ok().body(rs);
	}

	/************************************************************************************************
	 * get all block details
	 ************************************************************************************************/
	@GetMapping("/get-project-all-block{projectId}")
	public ResponseEntity<?> getProjectAllBlockList(@PathVariable(value = "projectId") Long projectId) {
		logger.debug("called id is");
		ResponseModel rs = new ResponseModel();
		List<CitizenClaimModel> citizenList = service.findByProjectId(projectId);
		HashMap<String,HashSet> btrDtl=new HashMap<>();
		if (Optional.ofNullable(citizenList).isPresent()) {
			btrDtl=service.getBlockDetails(citizenList);
		}
		rs.setMessage("Records found");
		rs.setStatus("200");
		rs.setData(btrDtl);
		return ResponseEntity.ok().body(btrDtl);
	}

	@GetMapping("/get-by-kyc/{kyc}")
	public ResponseEntity<ResponseModel> getAfsDetailsByclauseCode(@PathVariable(value = "kyc") String kyc)
			throws ResourceNotFoundException, IOException, ParseException {
		CitizenClaimModel model = service.findByKyc(kyc);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
	
	@GetMapping("/get-claimed-citizen-list/{userId}/{userType}")
	public ResponseEntity<ResponseModel> getClaimedCitizenDtl(@PathVariable(value = "userId") String userId,@PathVariable(value = "userType") String userType)
			throws ResourceNotFoundException, IOException, ParseException {
		List<CitizenClaimModel> list = service.findByUserType(userType);
		List<CitizenClaimModel> newList = new ArrayList<>();
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		for(CitizenClaimModel m:list)
		{
			if(m.getUserId().equals(userId))
			{
				newList.add(m);
			}
		}
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found");
		rs.setStatus("200");
		rs.setData(newList);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/flat-list/{projectId}/{blockName}")
	public ResponseEntity<ResponseModel> getFlatDetails(@PathVariable(value = "projectId") Long projectId,@PathVariable(value = "blockName") String blockName)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called clauseCode is");
		List<CitizenClaimModel> list = service.findByProjectId(projectId);
		List<CitizenClaimModel> newList = new ArrayList<CitizenClaimModel>();
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		for(CitizenClaimModel m:list)
		{
			if(blockName.equals(m.getBlockName()))
			{
				newList.add(m);
			}
		}
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found");
		rs.setStatus("200");
		rs.setData(newList);
		return ResponseEntity.ok().body(rs);
	}
	
	
	@GetMapping("/allottee-flat-list/{projectId}/{userId}/{userType}")
	public ResponseEntity<ResponseModel> getCitizenFlatList(@PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "userId") String userId
			,@PathVariable(value = "userType") String userType)
			throws ResourceNotFoundException, IOException, ParseException {
		List<CitizenClaimModel> list = service.findByProjectId(projectId);
		List<CitizenClaimModel> newList = new ArrayList<>();
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		for(CitizenClaimModel m:list)
		{
			if(userId.equals(m.getUserId()) && userType.equals(m.getUserType()))
			{
				newList.add(m);
			}
		}
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(newList);
		return ResponseEntity.ok().body(rs);
	}
	
	
	/*
	 * 
	 * Data will come from Form-3
	 * 
	 */
	
	@PostMapping("/save")
	public ResponseEntity<ResponseModel> saveAfsClause(@RequestBody CitizenClaimModel model) throws ResourceNotFoundException {
		Optional.ofNullable(model).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		CitizenClaimModel oldModel = service.findById(model.getCitizenClaimId());
		if(oldModel!=null)
		{
		model = service.save(model);
		}
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	
	@PostMapping("/execute-afs")
	public ResponseEntity<ResponseModel> saveExecuteAfs(@RequestBody CitizenClaimModel model) throws ResourceNotFoundException {
		Optional.ofNullable(model).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		CitizenClaimModel newModel = service.findById(model.getCitizenClaimId());
		Optional.ofNullable(model).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("AFS ID NOT FOUND")));
		newModel.setAfsStatus(ReraConstants.YES);
		newModel = service.save(newModel);
		ResponseModel rs = new ResponseModel();
		rs.setMessage("AFS Status updated Successfully.");
		rs.setStatus("200");
		rs.setData(newModel);
		return ResponseEntity.ok().body(rs);
	}

	@PostMapping("/validate-kyc")
	public ResponseEntity<ResponseModel> validateAllotteeKyc(@RequestBody CitizenClaimModel model)
			throws ResourceNotFoundException {
		Optional.ofNullable(model).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		CitizenClaimModel returnModel=  null;
		List<CitizenClaimModel> kycList = service.findByProjectRegNo(model.getProjectRegNo().trim());
		if (!kycList.isEmpty()) {
			for (CitizenClaimModel m : kycList) {
				if (model.getAllotteekyc().trim().equalsIgnoreCase(m.getAllotteekyc()) && model.getBlockName().trim().equalsIgnoreCase(m.getBlockName())
						&& model.getFlatNumber().trim().equalsIgnoreCase(m.getFlatNumber())) {
					returnModel = m;
				}
			}
		}
		ResponseModel rs = new ResponseModel();
		if (returnModel!=null) {
			rs.setMessage("Data matched Successfully.");
			rs.setStatus("200");
			rs.setData(returnModel);
		} else {
			rs.setMessage("Data not matched.");
			rs.setStatus("404");
			rs.setData("Not Matched");
		}
		return ResponseEntity.ok().body(rs);
	}

	@PostMapping("/delete{id}")
	public ResponseEntity<ResponseModel> deleteBankDtl(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Optional.ofNullable(id).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		service.deleteById(id);
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records Deleted.");
		rs.setStatus("200");
		rs.setData("AFS Clause Details Deleted Successfully");
		return ResponseEntity.ok().body(rs);
	}
}
