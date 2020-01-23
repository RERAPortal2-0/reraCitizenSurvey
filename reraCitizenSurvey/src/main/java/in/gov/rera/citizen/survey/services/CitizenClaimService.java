package in.gov.rera.citizen.survey.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import in.gov.rera.citizen.survey.model.AllotteeAfsDtlModel;
import in.gov.rera.citizen.survey.model.CitizenClaimModel;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;
import in.gov.rera.citizen.survey.model.transaction.BlockDetailTransactionModel;
import in.gov.rera.citizen.survey.model.transaction.ProjectDetailsTransactionModel;

public interface CitizenClaimService {

	CitizenClaimModel save(CitizenClaimModel model);

	CitizenClaimModel findById(Long id);

	List<CitizenClaimModel> findAll();

	void deleteById(Long id);

	CitizenClaimModel findByKyc(String kycId);

	List<CitizenClaimModel> findByProjectId(Long projectId);

	HashMap<String,HashSet> getBlockDetails(List<CitizenClaimModel> l);

	List<CitizenClaimModel> findByUserType(String userType);

	List<CitizenClaimModel> findByProjectRegNo(String projectRegNo);

}
