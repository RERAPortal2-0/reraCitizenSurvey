package in.gov.rera.citizen.survey.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;

@Repository
public interface ProjectAfsDao extends CrudRepository<ProjectAfsModel, Long> {
	
	List<ProjectAfsModel> findByProjectId(Long projectId);
	List<ProjectAfsModel> findByStatus(String status);
}
