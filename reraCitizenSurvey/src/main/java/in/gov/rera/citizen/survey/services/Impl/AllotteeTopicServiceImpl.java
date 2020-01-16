package in.gov.rera.citizen.survey.services.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import in.gov.rera.citizen.survey.dao.AllotteeForumTopicDao;
import in.gov.rera.citizen.survey.dao.ProjectForumDao;
import in.gov.rera.citizen.survey.model.AllotteeForumTopicModel;
import in.gov.rera.citizen.survey.model.ProjectForumModel;
import in.gov.rera.citizen.survey.services.AllotteeTopicService;
import in.gov.rera.citizen.survey.services.ProjectForumService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class AllotteeTopicServiceImpl implements AllotteeTopicService {

	private static final Logger logger = LogManager.getLogger(AllotteeTopicServiceImpl.class);
	@Autowired
	AllotteeForumTopicDao allotteeDao;

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public List<AllotteeForumTopicModel> findByProjectId(Long projectId, String projectDtlUrl) {
		// TODO Auto-generated method stub
		/*
		 * ProjectFormFiveModel prjFormFiveModel = new ProjectFormFiveModel();
		 * prjFormFiveModel = projectFormFiveDao.findByProjectId(projectId); if (null ==
		 * prjFormFiveModel) { ProjectRegistrationModel project; try { project =
		 * RestTamplateUtility.projectDtl(projectId,projectDtlUrl );
		 * Optional.of(project).orElseThrow(() -> new
		 * ResourceAccessException("NOT FOUND")); logger.debug("project details are " +
		 * project); ProjectFormFiveModel model = new ProjectFormFiveModel();
		 * model.setProjectId(projectId);
		 * model.setPromoterName(project.getPromoterName());
		 * model.setPromoterEmailId(project.getPromoteremailId());
		 * model.setPromoterMobileNo(project.getPromoterMobileNo());
		 * model.setProjectName(project.getProjectDetailsModel().getProjectName());
		 * Calendar startDate = Calendar.getInstance(); Calendar endDate =
		 * Calendar.getInstance(); Calendar toDay = Calendar.getInstance(); String
		 * currentFYear = toDay.get(toDay.YEAR) + "-" + (toDay.get(toDay.YEAR) + 1);
		 * logger.debug("current Financial year is " + currentFYear);
		 * startDate.setTime(project.getProjectDetailsModel().getStartDate());
		 * endDate.setTime(project.getProjectDetailsModel().getCompletionDate());
		 * List<String> fYearlist = Util.getFinancialYear(startDate, endDate);
		 * List<FormFiveModel> fList = new ArrayList<FormFiveModel>();
		 * logger.debug(fYearlist); for (String f : fYearlist) { FormFiveModel fModel =
		 * new FormFiveModel(); fModel.setFinancialYear(f);
		 * fModel.setProjectId(projectId);
		 * fModel.setProjectName(project.getProjectDetailsModel().getProjectName());
		 * fModel.setPromoterName(project.getPromoterName());
		 * fModel.setCertFromDate("01-04-"+f.substring(0,4));
		 * fModel.setCertToDate("31-03-"+f.substring(5,9));
		 * 
		 * if (currentFYear.equals(f))
		 * fModel.setFinancialYearStatus(ReraConstants.ACTIVE); else {
		 * fModel.setFinancialYearStatus(ReraConstants.INACTIVE); } fList.add(fModel); }
		 * model.setFormFiveList(fList); model = saveProjectFormFive(model);
		 * prjFormFiveModel = model; } catch (ResourceNotFoundException e) {
		 * e.printStackTrace(); } } else { }
		 */
		return  allotteeDao.findByProjectId(projectId);
	}

	@Override
	public AllotteeForumTopicModel saveTopicForum(AllotteeForumTopicModel model) {
		return allotteeDao.save(model);
	}


	@Override
	public AllotteeForumTopicModel findById(Long id) {
		return allotteeDao.findById(id).get();
	}

	



}
