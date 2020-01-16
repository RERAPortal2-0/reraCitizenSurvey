package in.gov.rera.citizen.survey.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.AllotteeAfsDtlModel;


@Repository
public interface AllotteeAfsDao extends CrudRepository<AllotteeAfsDtlModel , Long> {
	
	AllotteeAfsDtlModel findByKycId(String kycId);
	
}
