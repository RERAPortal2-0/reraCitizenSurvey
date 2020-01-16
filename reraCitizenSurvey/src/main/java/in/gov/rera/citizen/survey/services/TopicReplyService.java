package in.gov.rera.citizen.survey.services;

import java.util.List;
import in.gov.rera.citizen.survey.model.TopicReplyModel;

public interface TopicReplyService {

	TopicReplyModel saveTopicReply(TopicReplyModel model);

	List<TopicReplyModel> findByTopicId(Long topicId);
    
	TopicReplyModel findById(Long id);
}
