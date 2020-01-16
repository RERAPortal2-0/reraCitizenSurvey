package in.gov.rera.citizen.survey.controller;

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
import in.gov.rera.citizen.survey.constants.ReraConstants;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;
import in.gov.rera.citizen.survey.services.ProjectAfsService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/citizen_survey/project-afs")
public class ProjectAfsPdfRestController {
	private static final Logger logger = LogManager.getLogger(ProjectAfsPdfRestController.class);

	@Autowired
	ProjectAfsService afsService;
	
	@Autowired
	Environment env;
	
	@GetMapping("/pdf/{projetId}")
	public void getFormFivePdfDtlById(@PathVariable("projetId")Long projetId,
			HttpServletResponse response)
			throws Exception {
		    System.out.println("Project AFS PDF called id is " + projetId);
		    List<ProjectAfsModel>  afsList = afsService.findByProjectId(projetId);
		    for(ProjectAfsModel model:afsList)
		    {
		    	
		    	if(model.getStatus().equals(ReraConstants.APPROVED))
		    	{
		    		afsService.generateProjeAfsPdf(model,response,projetId,env.getProperty("URL_PROJECT_REG"));
		    	}
		    	else
		    	{
		    		
		    	}
		    	
		    	//Optional.of(model).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		    }
		/*
		 * ProjectFormFiveModel pModel=
		 * projectformFiveService.findByProjectId(formFiveModel.getProjectId(),""); Long
		 * projectRegId= pModel.getProjectId(); InputStream
		 * caImage=GenerateFormFivePDF.class.getClassLoader().getResourceAsStream(
		 * "static/images/calogo.jpg");
		 * System.out.println("INput stream is::::::::::::::: "+caImage); byte[] bt=new
		 * byte[caImage.available()]; caImage.read(bt); caImage.close();
		 * Optional.of(formFiveModel).orElseThrow(() -> new
		 * ResourceAccessException(env.getProperty("NOT_FOUND"))); GenerateFormFivePDF
		 * util=new GenerateFormFivePDF(formFiveModel,projectRegId,env.getProperty(
		 * "URL_PROJECT_REG"),env.getProperty("USER_REG_CA"),bt);
		 * util.generateFormFive(response);
		 */
		/*
		 * Document document = new Document(); PdfWriter
		 * pw=PdfWriter.getInstance(document, response.getOutputStream());
		 * document.open(); util.generateFormFive(document, formFiveModel);
		 * document.close();
		 */
			//Document document = new Document();
	}
}
