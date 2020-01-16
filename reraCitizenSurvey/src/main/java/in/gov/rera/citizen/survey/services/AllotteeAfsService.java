package in.gov.rera.citizen.survey.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.gov.rera.citizen.survey.model.AllotteeAfsDtlModel;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;

public interface AllotteeAfsService {

	AllotteeAfsDtlModel saveAfs(AllotteeAfsDtlModel model);
    
	AllotteeAfsDtlModel findById(Long id);
	
	List<AllotteeAfsDtlModel> findAll();

	void deleteById(Long id);

	AllotteeAfsDtlModel findByKycId(String kycId);

	void generateAllotteefsPdf(ProjectAfsModel model,HttpServletResponse response, Long projetId, Long kycId, String property, byte[] bt);
}
