package in.gov.rera.citizen.survey.services;

import java.util.List;
import in.gov.rera.citizen.survey.model.AllotteeAfsDtlModel;
import in.gov.rera.citizen.survey.model.CitizenClaimModel;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;

public interface CitizenClaimService {

	CitizenClaimModel save(CitizenClaimModel model);
    
	CitizenClaimModel findById(Long id);
	
	List<CitizenClaimModel> findAll();

	void deleteById(Long id);

	CitizenClaimModel findByKyc(String kycId);

	List<CitizenClaimModel> findByProjectId(Long projectId);

}
