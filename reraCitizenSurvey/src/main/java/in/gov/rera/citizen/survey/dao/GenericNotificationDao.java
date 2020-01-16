package in.gov.rera.citizen.survey.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.GenericNotificationModel;


@Repository
public interface GenericNotificationDao extends CrudRepository<GenericNotificationModel, Long> {
	
	GenericNotificationModel findByProcessId(String processId);
	List<GenericNotificationModel> findByProcessType(String processType);
}
