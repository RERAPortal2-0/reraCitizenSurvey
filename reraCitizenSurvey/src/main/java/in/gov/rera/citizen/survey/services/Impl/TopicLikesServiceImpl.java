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
import in.gov.rera.citizen.survey.dao.TopicLikesDao;
import in.gov.rera.citizen.survey.model.TopicLikesModel;
import in.gov.rera.citizen.survey.services.TopicLikesService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class TopicLikesServiceImpl implements TopicLikesService {

	private static final Logger logger = LogManager.getLogger(TopicLikesServiceImpl.class);
	@Autowired
	TopicLikesDao likesDao;
	

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public TopicLikesModel saveTopicLikes(TopicLikesModel model) {
		return likesDao.save(model);
	}

	@Override
	public List<TopicLikesModel> findByTopicId(Long topicId) {
		return likesDao.findByTopicId(topicId);
	}

	@Override
	public TopicLikesModel findById(Long id) {
		return likesDao.findById(id).get();
	}

	

	



}
