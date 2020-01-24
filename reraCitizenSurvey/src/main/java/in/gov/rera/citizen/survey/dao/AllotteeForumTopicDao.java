package in.gov.rera.citizen.survey.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.AllotteeForumTopicModel;


@Repository
public interface AllotteeForumTopicDao extends CrudRepository<AllotteeForumTopicModel, Long> {
	
	List<AllotteeForumTopicModel> findByProjectId(Long projectId);
	
}
