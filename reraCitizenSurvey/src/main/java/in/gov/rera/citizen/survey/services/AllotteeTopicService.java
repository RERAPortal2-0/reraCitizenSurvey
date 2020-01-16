package in.gov.rera.citizen.survey.services;

import java.util.List;

import in.gov.rera.citizen.survey.model.AllotteeForumTopicModel;

public interface AllotteeTopicService {

	AllotteeForumTopicModel saveTopicForum(AllotteeForumTopicModel model);

	List<AllotteeForumTopicModel> findByProjectId(Long projectId, String projectDtlUrl);
    
	AllotteeForumTopicModel findById(Long id);
}
