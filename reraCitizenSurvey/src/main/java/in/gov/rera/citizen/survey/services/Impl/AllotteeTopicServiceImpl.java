package in.gov.rera.citizen.survey.services.Impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import in.gov.rera.citizen.survey.dao.AllotteeForumTopicDao;
import in.gov.rera.citizen.survey.model.AllotteeForumTopicModel;
import in.gov.rera.citizen.survey.services.AllotteeTopicService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class AllotteeTopicServiceImpl implements AllotteeTopicService {

	@Autowired
	AllotteeForumTopicDao allotteeDao;

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public List<AllotteeForumTopicModel> findByProjectId(Long projectId, String projectDtlUrl) {
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
