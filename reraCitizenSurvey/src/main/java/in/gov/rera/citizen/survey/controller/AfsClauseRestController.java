package in.gov.rera.citizen.survey.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
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
import in.gov.rera.citizen.survey.services.AfsClauseService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/secure/afs-clause")
public class AfsClauseRestController {

	@Autowired
	AfsClauseService afsService;

	@Autowired
	Environment env;

	@GetMapping("/get-all")
	public ResponseEntity<ResponseModel> getAllAfsList() throws ResourceNotFoundException, IOException, ParseException {
		List<AfsClauseModel> list = afsService.findAll();
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(ReraConstants.NOT_FOUND));
		ResponseModel rs = new ResponseModel();
		if(!list.isEmpty())
		{
			rs.setMessage("Records found");
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
	public ResponseEntity<ResponseModel> getAfsDetailsById(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException, IOException, ParseException {
		AfsClauseModel model = afsService.findById(id);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(ReraConstants.NOT_FOUND));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-code/{clauseCode}")
	public ResponseEntity<ResponseModel> getAfsDetailsByclauseCode(@PathVariable(value = "clauseCode") String clauseCode)
			throws ResourceNotFoundException, IOException, ParseException {
		AfsClauseModel model = afsService.findByClauseCode(clauseCode);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(ReraConstants.NOT_FOUND));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseModel> saveAfsClause(@RequestBody AfsClauseModel model)
			throws ResourceNotFoundException {
		Optional.ofNullable(model)
				.orElseThrow(() -> new ResourceNotFoundException(ReraConstants.NOT_FOUND));
		   if(model.getAfsClauseId()==null) {
		    model = afsService.saveAfs(model);
		    model.setClauseCode(afsService.generateClauseCode(model.getAfsClauseId()));
			model.setClauseName(afsService.generateClauseName(model.getAfsClauseId()));
			model = afsService.saveAfs(model);
		   }
		   else
		   {
			 AfsClauseModel oldModel=  afsService.findById(model.getAfsClauseId());
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
	public ResponseEntity<ResponseModel> deleteBankDtl(@PathVariable(value = "id") Long id)
			throws ResourceNotFoundException {
		Optional.ofNullable(id)
				.orElseThrow(() -> new ResourceNotFoundException(ReraConstants.NOT_FOUND));
		afsService.deleteById(id);
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records Deleted.");
		rs.setStatus("200");
		rs.setData("AFS Clause Details Deleted Successfully");
		return ResponseEntity.ok().body(rs);
	}

}
