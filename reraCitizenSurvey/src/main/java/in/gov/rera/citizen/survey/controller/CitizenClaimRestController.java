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
import in.gov.rera.citizen.survey.model.FormThreeAlloModel;
import in.gov.rera.citizen.survey.model.FormThreeAnnxrTrxModel;
import in.gov.rera.citizen.survey.security.AuthUser;
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
		if (list.size()>0) {
			rs.setMessage("Records found");
		} else {
			rs.setMessage("Record not exists");
		}
		rs.setStatus("200");
		rs.setData(list);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-id{id}")
	public ResponseEntity<?> getAfsDetailsById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException, IOException, ParseException {
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
		List<CitizenClaimModel> list = service.findByProjectId(projectId);
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
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
		HashMap<String,HashSet> btrDtl=new HashMap<String, HashSet>();
		if (Optional.ofNullable(citizenList).isPresent()) {
			btrDtl=service.getBlockDetails(citizenList);
		}
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(btrDtl);
		return ResponseEntity.ok().body(btrDtl);
	}

	@GetMapping("/get-by-kyc/{kyc}")
	public ResponseEntity<?> getAfsDetailsByclauseCode(@PathVariable(value = "kyc") String kyc)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called clauseCode is");
		CitizenClaimModel model = service.findByKyc(kyc);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
	
	@GetMapping("/get-claimed-citizen-list/{userId}/{userType}")
	public ResponseEntity<?> getClaimedCitizenDtl(@PathVariable(value = "userId") String userId,@PathVariable(value = "userType") String userType)
			throws ResourceNotFoundException, IOException, ParseException {
		logger.debug("called clauseCode is");
		List<CitizenClaimModel> list = service.findByUserType(userType);
		List<CitizenClaimModel> newList = new ArrayList<CitizenClaimModel>();
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		for(CitizenClaimModel m:list)
		{
			if(m.getUserId().equals(userId))
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

	@GetMapping("/flat-list/{projectId}/{blockName}")
	public ResponseEntity<?> getFlatDetails(@PathVariable(value = "projectId") Long projectId,@PathVariable(value = "blockName") String blockName)
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
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(newList);
		return ResponseEntity.ok().body(rs);
	}
	
	
	@GetMapping("/allottee-flat-list/{projectId}/{userId}/{userType}")
	public ResponseEntity<?> getCitizenFlatList(@PathVariable(value = "projectId") Long projectId,
			@PathVariable(value = "userId") String userId
			,@PathVariable(value = "userType") String userType)
			throws ResourceNotFoundException, IOException, ParseException {
		System.out.println("called clauseCode is"+userId);
		List<CitizenClaimModel> list = service.findByProjectId(projectId);
		System.out.println("List size is "+list.size());
		List<CitizenClaimModel> newList = new ArrayList<CitizenClaimModel>();
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
	public ResponseEntity<?> saveAfsClause(@RequestBody CitizenClaimModel model) throws ResourceNotFoundException {
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

	
	@PostMapping("/save-allottee-details")
	public ResponseEntity<?> saveAllotteeDetails(@RequestBody FormThreeAnnxrTrxModel project) throws ResourceNotFoundException {
		
		System.out.println("save allottee details called::::::::::::>>>>>>>>>>>");
		System.out.println("saveAllotteeDetails called and allottee list size is "+project.getAllolist());
		
		
		Optional.ofNullable(project).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		
		try {

			System.out.println("SaveAllotteeDetails Project Id is::::::::>>>>>>>>> "+project.getProjectId());
			List<CitizenClaimModel> citizenList = service.findByProjectId(project.getProjectId());
			System.out.println("existing citizen List size is::::::::::::>>>>>>>>>>>>>>>>>>> "+citizenList.size());
			if(citizenList.size()>0) {
			for(FormThreeAlloModel alo:project.getAllolist())
			{
				int flag=0;
				for(CitizenClaimModel model :citizenList)
				{
					if(model.getProjectId().equals(project.getProjectId()) &&
					model.getBlockName().equals(alo.getBlockName()) && 
					model.getFlatNumber().equals(alo.getFlatNumber()) && 
					!(alo.getAllotteekyc().equals(model.getAllotteekyc()) ||  "null".equals(model.getAllotteekyc())))
					{
						model.setAllotteekyc(alo.getAllotteekyc());
						model.setAfsStatus(null);
						model.setAllotteeName(null);
						model.setUserId(null);
						model.setUserType(null);
						service.save(model);
					}
					else if(model.getProjectId().equals(project.getProjectId()) &&
							!model.getBlockName().equals(alo.getBlockName()))
					{
						CitizenClaimModel newModel = new CitizenClaimModel();
						newModel.setAllotteekyc(alo.getAllotteekyc());
						newModel.setBlockName(alo.getBlockName());
						newModel.setProjectId(project.getProjectId());
						newModel.setProjectRegNo(project.getProjectReg());
						newModel.setFlatNumber(alo.getFlatNumber());
						newModel.setMobileNumber(alo.getMobileNumber());
						service.save(newModel);
					}
				}
			}
			}
			else
			{
				System.out.println("Inside New allottee Details :::::::::::::::::>>>>>>>>>>>>>>>> ");
				for(FormThreeAlloModel alo:project.getAllolist())
				{
					System.out.println("Inside save allottee Details:::::::::::>>>>>>>>>>>>>>>>>>  ");
				CitizenClaimModel newModel = new CitizenClaimModel();
				newModel.setAllotteekyc(alo.getAllotteekyc());
				newModel.setBlockName(alo.getBlockName());
				newModel.setProjectId(project.getProjectId());
				newModel.setProjectRegNo(project.getProjectReg());
				newModel.setFlatNumber(alo.getFlatNumber());
				newModel.setMobileNumber(alo.getMobileNumber());
				service.save(newModel);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in saveAllotteeDetails :::::::::::>>>>>>>>>>>>>>>>>>>>>"+e);
		}
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData("");
		return ResponseEntity.ok().body(rs);
	}
	
	@PostMapping("/execute-afs")
	public ResponseEntity<?> saveExecuteAfs(@RequestBody CitizenClaimModel model) throws ResourceNotFoundException {
		Optional.ofNullable(model).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		CitizenClaimModel newModel=new CitizenClaimModel();
		newModel = service.findById(model.getCitizenClaimId());
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
	public ResponseEntity<?> validateAllotteeKyc(@RequestBody CitizenClaimModel model)
			throws ResourceNotFoundException {
		Optional.ofNullable(model).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		CitizenClaimModel returnModel=  null;
		List<CitizenClaimModel> kycList = service.findByProjectRegNo(model.getProjectRegNo());
		if (kycList.size()> 0) {
			System.out.println("inside list:::::::::::::::::::");
			for (CitizenClaimModel m : kycList) {
				if (model.getAllotteekyc().equalsIgnoreCase(m.getAllotteekyc()) && model.getBlockName().equalsIgnoreCase(m.getBlockName())
						&& model.getFlatNumber().equalsIgnoreCase(m.getFlatNumber())) {
					returnModel=new CitizenClaimModel();
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
	public ResponseEntity<?> deleteBankDtl(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Optional.ofNullable(id).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		service.deleteById(id);
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records Deleted.");
		rs.setStatus("200");
		rs.setData("AFS Clause Details Deleted Successfully");
		return ResponseEntity.ok().body(rs);
	}
}
