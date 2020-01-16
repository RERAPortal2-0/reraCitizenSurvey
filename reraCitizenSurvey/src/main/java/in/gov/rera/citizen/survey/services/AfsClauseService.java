package in.gov.rera.citizen.survey.services;

import java.util.List;

import in.gov.rera.citizen.survey.model.AfsClauseModel;

public interface AfsClauseService {

	AfsClauseModel saveAfs(AfsClauseModel model);
    
	AfsClauseModel findById(Long id);
	
	List<AfsClauseModel> findAll();

	void deleteById(Long id);
	
	public String generateClauseCode(Long id);
	
	public String generateClauseName(Long id);

	AfsClauseModel findByClauseCode(String clauseCode);
}
