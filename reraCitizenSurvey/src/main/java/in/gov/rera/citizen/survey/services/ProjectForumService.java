package in.gov.rera.citizen.survey.services;

import in.gov.rera.citizen.survey.model.ProjectForumModel;;

public interface ProjectForumService {

	ProjectForumModel saveProjectForum(ProjectForumModel model);

	ProjectForumModel findByProjectId(Long projectId, String projectDtlUrl);
    
	ProjectForumModel findById(Long id);
}
