package in.gov.rera.citizen.survey.services.Impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import in.gov.rera.citizen.survey.common.RestTamplateUtility;
import in.gov.rera.citizen.survey.dao.ProjectForumDao;
import in.gov.rera.citizen.survey.exception.ResourceNotFoundException;
import in.gov.rera.citizen.survey.model.ProjectForumModel;
import in.gov.rera.citizen.survey.model.ProjectRegistrationModel;
import in.gov.rera.citizen.survey.services.ProjectForumService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class ProjectForumServiceImpl implements ProjectForumService {

	private static final Logger logger = LogManager.getLogger(ProjectForumServiceImpl.class);
	@Autowired
	ProjectForumDao projectFormDao;
	

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public ProjectForumModel findByProjectId(Long projectId) {
		/*
		 * ProjectForumModel pForumModel = new ProjectForumModel(); try { pForumModel =
		 * projectFormDao.findByProjectId(projectId); if (null == pForumModel) {
		 * ProjectRegistrationModel project = RestTamplateUtility.projectDtlP(projectId,
		 * projectDtlUrl); Optional.of(project).orElseThrow(() -> new
		 * ResourceAccessException("NOT FOUND")); logger.debug("project details are " +
		 * project); pForumModel=new ProjectForumModel();
		 * pForumModel.setProjectId(project.getProjRegId());
		 * pForumModel.setProjectName(project.getProjectDetailsModel().getProjectName())
		 * ; pForumModel.setPromoterName(project.getPromoterName());
		 * pForumModel.setForumName(project.getProjectDetailsModel().getProjectName());
		 * pForumModel = projectFormDao.save(pForumModel); } else { } } catch
		 * (ResourceNotFoundException e) { e.printStackTrace(); }
		 */
		return projectFormDao.findByProjectId(projectId);
	}

	@Override
	public ProjectForumModel saveProjectForum(ProjectForumModel model) {
		return projectFormDao.save(model);
	}

	@Override
	public ProjectForumModel findById(Long id) {
		return projectFormDao.findById(id).get();
	}

}
