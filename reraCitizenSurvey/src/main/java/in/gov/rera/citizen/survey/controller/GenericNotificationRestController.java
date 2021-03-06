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
import in.gov.rera.citizen.survey.model.GenericNotificationModel;
import in.gov.rera.citizen.survey.services.GenericNotificationService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/citizen_survey/secure/notification")
public class GenericNotificationRestController {
	private static final Logger logger = LogManager.getLogger(GenericNotificationRestController.class);

	@Autowired
	GenericNotificationService service;

	@Autowired
	Environment env;

	@GetMapping("/get-all")
	public ResponseEntity<?> getAllAfsList() throws ResourceNotFoundException, IOException, ParseException {
		List<GenericNotificationModel> list = service.findAll();
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
		GenericNotificationModel model = service.findById(id);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

	@GetMapping("/get-by-process-id/{processId}")
	public ResponseEntity<?> getNotificationByProcessId(@PathVariable(value = "processId") String processId)
			throws ResourceNotFoundException, IOException, ParseException {
		GenericNotificationModel model = service.findByProcessId(processId);
		Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}
	
	@GetMapping("/get-by-process-type/{processType}")
	public ResponseEntity<?> getNotificationByProcessType(@PathVariable(value = "processType") String processType)
			throws ResourceNotFoundException, IOException, ParseException {
		List<GenericNotificationModel> list = service.findByProcessType(processType);
		Optional.of(list).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Records found.");
		rs.setStatus("200");
		rs.setData(list);
		return ResponseEntity.ok().body(rs);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveAfsClause(@RequestBody GenericNotificationModel model)
			throws ResourceNotFoundException {
		Optional.ofNullable(model).orElseThrow(() -> new ResourceNotFoundException(env.getProperty("DATA_INVALID")));
		model = service.save(model);
		ResponseModel rs = new ResponseModel();
		rs.setMessage("Data submitted Successfully.");
		rs.setStatus("200");
		rs.setData(model);
		return ResponseEntity.ok().body(rs);
	}

}
