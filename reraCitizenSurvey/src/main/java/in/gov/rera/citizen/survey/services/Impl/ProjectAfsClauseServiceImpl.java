package in.gov.rera.citizen.survey.services.Impl;

import java.util.List;
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
import in.gov.rera.citizen.survey.dao.AfsClauseDao;
import in.gov.rera.citizen.survey.dao.ProjectAfsClauseDao;
import in.gov.rera.citizen.survey.dao.ProjectAfsDao;
import in.gov.rera.citizen.survey.dao.ProjectForumDao;
import in.gov.rera.citizen.survey.exception.ResourceNotFoundException;
import in.gov.rera.citizen.survey.model.AfsClauseModel;
import in.gov.rera.citizen.survey.model.ProjectAfsClauseModel;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;
import in.gov.rera.citizen.survey.model.ProjectForumModel;
import in.gov.rera.citizen.survey.model.ProjectRegistrationModel;
import in.gov.rera.citizen.survey.services.AfsClauseService;
import in.gov.rera.citizen.survey.services.ProjectAfsClauseService;
import in.gov.rera.citizen.survey.services.ProjectAfsService;
import in.gov.rera.citizen.survey.services.ProjectForumService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class ProjectAfsClauseServiceImpl implements ProjectAfsClauseService {

	private static final Logger logger = LogManager.getLogger(ProjectAfsClauseServiceImpl.class);
	@Autowired
	ProjectAfsClauseDao afsDao;
	

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public ProjectAfsClauseModel saveProjectAfsClause(ProjectAfsClauseModel model) {
		return afsDao.save(model);
	}

	@Override
	public ProjectAfsClauseModel findById(Long id) {
		return afsDao.findById(id).get();
	}

	@Override
	public List<ProjectAfsClauseModel> findByProjectAfsId(Long projectAfsId) {
		return afsDao.findByProjectAfsId(projectAfsId);
	}

	@Override
	public void deleteById(Long id) {
		
	}

	

	
}
