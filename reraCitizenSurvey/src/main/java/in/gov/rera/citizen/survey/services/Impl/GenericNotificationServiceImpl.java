package in.gov.rera.citizen.survey.services.Impl;

import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import in.gov.rera.citizen.survey.dao.AfsClauseDao;
import in.gov.rera.citizen.survey.dao.GenericNotificationDao;
import in.gov.rera.citizen.survey.model.AfsClauseModel;
import in.gov.rera.citizen.survey.model.GenericNotificationModel;
import in.gov.rera.citizen.survey.services.AfsClauseService;
import in.gov.rera.citizen.survey.services.GenericNotificationService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class GenericNotificationServiceImpl implements GenericNotificationService {

	private static final Logger logger = LogManager.getLogger(GenericNotificationServiceImpl.class);
	
	@Autowired
	GenericNotificationDao  dao;

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public GenericNotificationModel save(GenericNotificationModel model) {
		return dao.save(model);
	}

	@Override
	public GenericNotificationModel findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<GenericNotificationModel> findAll() {
		return (List<GenericNotificationModel>) dao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		
	}

	@Override
	public GenericNotificationModel findByProcessId(String processId) {
		return dao.findByProcessId(processId);
	}

	@Override
	public List<GenericNotificationModel> findByProcessType(String processType) {
		return dao.findByProcessType(processType);
	}

	
}
