package in.gov.rera.citizen.survey.services;

import java.util.List;
import in.gov.rera.citizen.survey.model.TopicLikesModel;

public interface TopicLikesService {

	TopicLikesModel saveTopicLikes(TopicLikesModel model);

	List<TopicLikesModel> findByTopicId(Long topicId);
    
	TopicLikesModel findById(Long id);
}
