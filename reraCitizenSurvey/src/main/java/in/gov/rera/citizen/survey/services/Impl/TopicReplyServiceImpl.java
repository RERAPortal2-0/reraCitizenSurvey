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
import in.gov.rera.citizen.survey.dao.TopicReplyDao;
import in.gov.rera.citizen.survey.model.TopicReplyModel;
import in.gov.rera.citizen.survey.services.TopicReplyService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class TopicReplyServiceImpl implements TopicReplyService {

	private static final Logger logger = LogManager.getLogger(TopicReplyServiceImpl.class);
	@Autowired
	TopicReplyDao replyDao;
	

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public TopicReplyModel saveTopicReply(TopicReplyModel model) {
		return replyDao.save(model);
	}

	@Override
	public List<TopicReplyModel> findByTopicId(Long topicId) {
		return replyDao.findByTopicId(topicId);
	}

	@Override
	public TopicReplyModel findById(Long id) {
		return replyDao.findById(id).get();
	}

	



}
