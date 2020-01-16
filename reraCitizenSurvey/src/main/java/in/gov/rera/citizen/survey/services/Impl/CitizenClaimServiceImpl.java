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
import in.gov.rera.citizen.survey.dao.CitizenClaimDao;
import in.gov.rera.citizen.survey.model.CitizenClaimModel;
import in.gov.rera.citizen.survey.services.CitizenClaimService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class CitizenClaimServiceImpl implements CitizenClaimService {

	private static final Logger logger = LogManager.getLogger(CitizenClaimServiceImpl.class);
	@Autowired
	CitizenClaimDao citizenDao;
	
	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public CitizenClaimModel save(CitizenClaimModel model) {
		return citizenDao.save(model);
	}

	@Override
	public CitizenClaimModel findById(Long id) {
		return citizenDao.findById(id).get();
	}

	@Override
	public List<CitizenClaimModel> findAll() {
		return (List<CitizenClaimModel>) citizenDao.findAll();
	}

	@Override
	public void deleteById(Long id) {
	}

	@Override
	public CitizenClaimModel findByKyc(String kycId) {
		return citizenDao.findByAllotteekyc(kycId);
	}

	@Override
	public List<CitizenClaimModel> findByProjectId(Long projectId) {
		return citizenDao.findByProjectId(projectId);
	}

	

	
}
