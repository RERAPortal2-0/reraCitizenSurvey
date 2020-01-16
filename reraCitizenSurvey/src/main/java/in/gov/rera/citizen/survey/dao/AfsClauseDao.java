package in.gov.rera.citizen.survey.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.AfsClauseModel;


@Repository
public interface AfsClauseDao extends CrudRepository<AfsClauseModel, Long> {
	
	AfsClauseModel findByClauseCode(String clauseCode);

	
}
