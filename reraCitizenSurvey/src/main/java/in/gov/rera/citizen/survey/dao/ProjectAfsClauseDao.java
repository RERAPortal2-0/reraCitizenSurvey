package in.gov.rera.citizen.survey.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.ProjectAfsClauseModel;


@Repository
public interface ProjectAfsClauseDao extends CrudRepository<ProjectAfsClauseModel, Long> {
	
	List<ProjectAfsClauseModel> findByProjectAfsId(Long projectAfsId);
	
}
