package in.gov.rera.citizen.survey.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.gov.rera.citizen.survey.common.model.ResponseModel;
import in.gov.rera.citizen.survey.exception.ResourceNotFoundException;
import in.gov.rera.citizen.survey.model.CitizenClaimModel;
import in.gov.rera.citizen.survey.model.FormThreeAlloModel;
import in.gov.rera.citizen.survey.model.FormThreeAnnxrTrxModel;
import in.gov.rera.citizen.survey.services.CitizenClaimService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/citizen_claim")
public class CitizenClaimPublicRestController {
	private static final Logger logger = LogManager.getLogger(CitizenClaimPublicRestController.class);

	@Autowired
	CitizenClaimService service;

	@Autowired
	Environment env;

	

	
	@PostMapping("/save-allottee-details")
	public ResponseEntity<ResponseModel> saveAllotteeDetails(@RequestBody FormThreeAnnxrTrxModel project) throws ResourceNotFoundException {
		
		logger.debug("save allottee details called::::::::::::>>>>>>>>>>>");
		logger.debug("saveAllotteeDetails called and allottee list size is "+project.getAllolist());
		
		
		Optional.ofNullable(project).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		
		try {

			logger.debug("SaveAllotteeDetails Project Id is::::::::>>>>>>>>> "+project.getProjectId());
			List<CitizenClaimModel> citizenList = service.findByProjectId(project.getProjectId());
			logger.debug("existing citizen List size is::::::::::::>>>>>>>>>>>>>>>>>>> "+citizenList.size());
			if(!citizenList.isEmpty()) {
			for(FormThreeAlloModel alo:project.getAllolist())
			{
				int flag=0;
				
				CitizenClaimModel oldModel=new CitizenClaimModel();
				for(CitizenClaimModel model :citizenList)
				{
					if(model.getProjectId().equals(project.getProjectId()) &&
					model.getBlockName().equals(alo.getBlockName()) && 
					model.getFlatNumber().equals(alo.getFlatNumber()) && 
					!(alo.getAllotteekyc().equals(model.getAllotteekyc()) ||  "null".equals(model.getAllotteekyc())))
					{
						flag=1;
						oldModel=model;
						break;
					}
					else if(model.getProjectId().equals(project.getProjectId()) &&
							!model.getBlockName().equals(alo.getBlockName()))
					{
					   flag=2;
					   break;
					}
				}
				
				if(flag==1)
				{
					oldModel.setAllotteekyc(alo.getAllotteekyc());
					oldModel.setAfsStatus(null);
					oldModel.setAllotteeName(null);
					oldModel.setUserId(null);
					oldModel.setUserType(null);
					service.save(oldModel);
				}
				else if(flag==2)
				{
					CitizenClaimModel newModel = new CitizenClaimModel();
					newModel.setAllotteekyc(alo.getAllotteekyc());
					newModel.setBlockName(alo.getBlockName());
					newModel.setProjectId(project.getProjectId());
					newModel.setProjectRegNo(project.getProjectReg());
					newModel.setFlatNumber(alo.getFlatNumber());
					newModel.setMobileNumber(alo.getMobileNumber());
					newModel.setProjectName(project.getProjectName());
					service.save(newModel);
				}
			}
			}
			else
			{
				logger.debug("Inside New allottee Details :::::::::::::::::>>>>>>>>>>>>>>>> ");
				for(FormThreeAlloModel alo:project.getAllolist())
				{
					logger.debug("Inside save allottee Details:::::::::::>>>>>>>>>>>>>>>>>>  ");
				CitizenClaimModel newModel = new CitizenClaimModel();
				newModel.setAllotteekyc(alo.getAllotteekyc());
				newModel.setBlockName(alo.getBlockName());
				newModel.setProjectId(project.getProjectId());
				newModel.setProjectRegNo(project.getProjectReg());
				newModel.setFlatNumber(alo.getFlatNumber());
				newModel.setMobileNumber(alo.getMobileNumber());
				newModel.setProjectName(project.getProjectName());
				service.save(newModel);
				}
			}
		
		}
		catch(Exception e)
		{
			logger.debug("Exception in saveAllotteeDetails :::::::::::>>>>>>>>>>>>>>>>>>>>>"+e);
		}
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData("");
		return ResponseEntity.ok().body(rs);
	}
	
	
}
