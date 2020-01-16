package in.gov.rera.citizen.survey.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.gov.rera.citizen.survey.model.AfsClauseModel;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;

public interface ProjectAfsService {

	ProjectAfsModel saveProjectAfs(ProjectAfsModel model);
    
	ProjectAfsModel findById(Long id);
	
	List<ProjectAfsModel> findByProjectId(Long projectId);
	
	void deleteById(Long id);

	List<ProjectAfsModel> findByStatus(String status);

	void generateProjeAfsPdf(ProjectAfsModel model, HttpServletResponse response, Long projetId, String property);

	
}
