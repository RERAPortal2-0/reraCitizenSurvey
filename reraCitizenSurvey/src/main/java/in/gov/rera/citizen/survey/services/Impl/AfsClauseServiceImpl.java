package in.gov.rera.citizen.survey.services.Impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import in.gov.rera.citizen.survey.dao.AfsClauseDao;
import in.gov.rera.citizen.survey.model.AfsClauseModel;
import in.gov.rera.citizen.survey.services.AfsClauseService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class AfsClauseServiceImpl implements AfsClauseService {

	@Autowired
	AfsClauseDao afsDao;
	

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public AfsClauseModel saveAfs(AfsClauseModel model) {
		return afsDao.save(model);
	}

	@Override
	public AfsClauseModel findById(Long id) {
		return afsDao.findById(id).get();
	}

	@Override
	public List<AfsClauseModel> findAll() {
		return (List<AfsClauseModel>) afsDao.findAll();
	}

	@Override
	public void deleteById(Long id) {
		afsDao.deleteById(id);
	}
	
	@Override
	public String generateClauseCode(Long id)
	{
		return "CL"+id;
	}
	
	@Override
	public String generateClauseName(Long id)
	{
		return "CLAUSE-"+id;
	}

	@Override
	public AfsClauseModel findByClauseCode(String clauseCode) {
		return afsDao.findByClauseCode(clauseCode);
	}
}
