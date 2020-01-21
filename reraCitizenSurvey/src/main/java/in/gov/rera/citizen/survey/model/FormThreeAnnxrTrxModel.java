package in.gov.rera.citizen.survey.model;

import java.util.List;

public class FormThreeAnnxrTrxModel {
	
	private Long formThreeId;
	private String projectReg;
	private Long projectId;
	private List<FormThreeAlloModel> allolist;
	
	public String getProjectReg() {
		return projectReg;
	}
	public void setProjectReg(String projectReg) {
		this.projectReg = projectReg;
	}
	public Long getFormThreeId() {
		return formThreeId;
	}
	public void setFormThreeId(Long formThreeId) {
		this.formThreeId = formThreeId;
	}
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public List<FormThreeAlloModel> getAllolist() {
		return allolist;
	}
	public void setAllolist(List<FormThreeAlloModel> allolist) {
		this.allolist = allolist;
	}
}
