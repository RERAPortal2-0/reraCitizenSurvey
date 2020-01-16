package in.gov.rera.citizen.survey.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.TopicReplyModel;


@Repository
public interface TopicReplyDao extends CrudRepository<TopicReplyModel, Long> {
	
	List<TopicReplyModel> findByTopicId(Long id);

	
}
