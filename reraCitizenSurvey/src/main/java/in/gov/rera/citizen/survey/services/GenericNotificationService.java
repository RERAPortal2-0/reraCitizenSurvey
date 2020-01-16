package in.gov.rera.citizen.survey.services;

import java.util.List;

import in.gov.rera.citizen.survey.model.AfsClauseModel;
import in.gov.rera.citizen.survey.model.GenericNotificationModel;

public interface GenericNotificationService {

	GenericNotificationModel save(GenericNotificationModel model);
    
	GenericNotificationModel findById(Long id);
	
	List<GenericNotificationModel> findAll();

	void deleteById(Long id);

	GenericNotificationModel findByProcessId(String processId);
	
	List<GenericNotificationModel> findByProcessType(String processType);
}
