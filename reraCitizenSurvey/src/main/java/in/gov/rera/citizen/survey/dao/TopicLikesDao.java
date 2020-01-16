package in.gov.rera.citizen.survey.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.TopicLikesModel;

@Repository
public interface TopicLikesDao extends CrudRepository<TopicLikesModel, Long> {
	
	List<TopicLikesModel> findByTopicId(Long id);

}
