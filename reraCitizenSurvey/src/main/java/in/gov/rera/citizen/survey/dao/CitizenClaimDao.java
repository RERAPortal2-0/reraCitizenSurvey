package in.gov.rera.citizen.survey.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import in.gov.rera.citizen.survey.model.CitizenClaimModel;

@Repository
public interface CitizenClaimDao extends CrudRepository<CitizenClaimModel , Long> {
	CitizenClaimModel findByAllotteekyc(String kycId);
	List<CitizenClaimModel> findByProjectId(Long projectId);
	List<CitizenClaimModel> findByUserType(String processType);
}
