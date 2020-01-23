package in.gov.rera.citizen.survey.services.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
import in.gov.rera.citizen.survey.model.transaction.BlockDetailTransactionModel;
import in.gov.rera.citizen.survey.model.transaction.FlatModelTransactionModel;
import in.gov.rera.citizen.survey.model.transaction.ProjectDetailsTransactionModel;
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

	@Override
	public HashMap<String,HashSet> getBlockDetails(List<CitizenClaimModel> l) {
		ProjectDetailsTransactionModel pm = new ProjectDetailsTransactionModel();
		List<BlockDetailTransactionModel> newbmList = new ArrayList<BlockDetailTransactionModel>();
		List<FlatModelTransactionModel> newfmList = new ArrayList<FlatModelTransactionModel>();
	    HashMap<String,HashSet> map =new HashMap<String, HashSet>();
		l.forEach(e->{
			if(map.containsKey(e.getBlockName())) {
				map.get(e.getBlockName()).add(e.getFlatNumber());
			}else {
				HashSet<String> flist= new HashSet<String>();
				flist.add(e.getFlatNumber());
				map.put(e.getBlockName(),flist);
			}
		});
		return map;

	}

	@Override
	public List<CitizenClaimModel> findByUserType(String userType) {
		return citizenDao.findByUserType(userType);

	}

	@Override
	public List<CitizenClaimModel> findByProjectRegNo(String projectRegNo) {
		return citizenDao.findByProjectRegNo(projectRegNo);
	}


}
