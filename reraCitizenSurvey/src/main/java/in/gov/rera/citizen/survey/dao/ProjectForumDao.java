package in.gov.rera.citizen.survey.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.ProjectForumModel;


@Repository
public interface ProjectForumDao extends CrudRepository<ProjectForumModel, Long> {
	
	ProjectForumModel findByProjectId(Long id);

	
}
