package in.gov.rera.citizen.survey.controller;

import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import in.gov.rera.citizen.survey.common.model.ResponseModel;
import in.gov.rera.citizen.survey.constants.ReraConstants;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;
import in.gov.rera.citizen.survey.services.AllotteeAfsService;
import in.gov.rera.citizen.survey.services.ProjectAfsService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/citizen_survey/allottee-project-afs")
public class AllotteeAfsPdfRestController {
	private static final Logger logger = LogManager.getLogger(AllotteeAfsPdfRestController.class);

	@Autowired
	AllotteeAfsService allotteeService;
	
	@Autowired
	ProjectAfsService afsService;
	
	@Autowired
	Environment env;
	

	@GetMapping("/download-afs/{projetId}/{kycId}")
	public void getFormFivePdfDtlById(@PathVariable("projetId")Long projetId,@PathVariable("kycId") Long kycId,
			HttpServletResponse response)
			throws Exception {
		    List<ProjectAfsModel>  afsList = afsService.findByProjectId(projetId);
		    for(ProjectAfsModel model:afsList)
		    {
		    	if(model.getStatus().equals(ReraConstants.APPROVED))
		    	{
		    		InputStream reraLogo=AllotteeAfsPdfRestController.class.getClassLoader().getResourceAsStream("static/images/wmlogo.jpg");
		    		byte[] bt=new byte[reraLogo.available()];
		   		    reraLogo.read(bt);
		   		    reraLogo.close();
		    		allotteeService.generateAllotteefsPdf(model,response,projetId,kycId,env.getProperty("URL_PROJECT_REG"),bt);
		    	}
		    }
			ResponseModel rs = new ResponseModel();
			rs.setMessage("Pdf Generated");
			rs.setStatus("200");
			rs.setData("");
	}
	
	/*
	 *   Gujarati Complaint Pdf
	 * 
	 */
	
	@GetMapping("/download-notice-one/{projetId}/{kycId}")
	public void getNoticeOnePdfDtlById(@PathVariable("projetId")Long projetId,@PathVariable("kycId") Long kycId,
			HttpServletResponse response)
			throws Exception {
		    List<ProjectAfsModel>  afsList = afsService.findByProjectId(projetId);
		    for(ProjectAfsModel model:afsList)
		    {
		    		InputStream reraLogo=AllotteeAfsPdfRestController.class.getClassLoader().getResourceAsStream("static/images/wmlogo.jpg");
		    		byte[] bt=new byte[reraLogo.available()];
		   		    reraLogo.read(bt);
		   		    reraLogo.close();
		    		allotteeService.generateNoticeOne(model,response,projetId,kycId,env.getProperty("URL_PROJECT_REG"),bt);
		    }
			ResponseModel rs = new ResponseModel();
			rs.setMessage("Pdf Generated");
			rs.setStatus("200");
			rs.setData("");
	}
	
	@GetMapping("/download-notice-two/{projetId}/{kycId}")
	public void getNoticeTwoPdfDtlById(@PathVariable("projetId")Long projetId,@PathVariable("kycId") Long kycId,
			HttpServletResponse response)
			throws Exception {
		    List<ProjectAfsModel>  afsList = afsService.findByProjectId(projetId);
		    for(ProjectAfsModel model:afsList)
		    {
		    		InputStream reraLogo=AllotteeAfsPdfRestController.class.getClassLoader().getResourceAsStream("static/images/wmlogo.jpg");
		    		byte[] bt=new byte[reraLogo.available()];
		   		    reraLogo.read(bt);
		   		    reraLogo.close();
		    		allotteeService.generateNoticeTwo(model,response,projetId,kycId,env.getProperty("URL_PROJECT_REG"),bt);
		    }
			ResponseModel rs = new ResponseModel();
			rs.setMessage("Pdf Generated");
			rs.setStatus("200");
			rs.setData("");
	}
	@GetMapping("/download-notice-three/{projetId}/{kycId}")
	public void getNoticeThreePdfDtlById(@PathVariable("projetId")Long projetId,@PathVariable("kycId") Long kycId,
			HttpServletResponse response)
			throws Exception {
		    List<ProjectAfsModel>  afsList = afsService.findByProjectId(projetId);
		    for(ProjectAfsModel model:afsList)
		    {
		    		InputStream reraLogo=AllotteeAfsPdfRestController.class.getClassLoader().getResourceAsStream("static/images/wmlogo.jpg");
		    		byte[] bt=new byte[reraLogo.available()];
		   		    reraLogo.read(bt);
		   		    reraLogo.close();
		    		allotteeService.generateNoticeThree(model,response,projetId,kycId,env.getProperty("URL_PROJECT_REG"),bt);
		    }
			ResponseModel rs = new ResponseModel();
			rs.setMessage("Pdf Generated");
			rs.setStatus("200");
			rs.setData("");
	}
	
	@GetMapping("/download-notice-four/{projetId}/{kycId}")
	public void getNoticeFourPdfDtlById(@PathVariable("projetId")Long projetId,@PathVariable("kycId") Long kycId,
			HttpServletResponse response)
			throws Exception {
		    List<ProjectAfsModel>  afsList = afsService.findByProjectId(projetId);
		    for(ProjectAfsModel model:afsList)
		    {
		    		InputStream reraLogo=AllotteeAfsPdfRestController.class.getClassLoader().getResourceAsStream("static/images/wmlogo.jpg");
		    		byte[] bt=new byte[reraLogo.available()];
		   		    reraLogo.read(bt);
		   		    reraLogo.close();
		    		allotteeService.generateNoticeFour(model,response,projetId,kycId,env.getProperty("URL_PROJECT_REG"),bt);
		    }
			ResponseModel rs = new ResponseModel();
			rs.setMessage("Pdf Generated");
			rs.setStatus("200");
			rs.setData("");
	}
}
