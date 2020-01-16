package in.gov.rera.citizen.survey.services;

import java.util.List;
import in.gov.rera.citizen.survey.model.ProjectAfsClauseModel;
public interface ProjectAfsClauseService {

	ProjectAfsClauseModel saveProjectAfsClause(ProjectAfsClauseModel model);
    
	ProjectAfsClauseModel findById(Long id);
	
	List<ProjectAfsClauseModel> findByProjectAfsId(Long projectAfsId);
	
	void deleteById(Long id);
	
}
