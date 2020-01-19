package in.gov.rera.citizen.survey.model.transaction;

import java.util.List;

public class ProjectDetailsTransactionModel {

	private Long projectId;
	private String projectName;
	private String projectType;
	private String promoterId;
	private String promoterName;
	private String promoterType;
	private String loginId;
	private List<BlockDetailTransactionModel> blockList;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(String promoterId) {
		this.promoterId = promoterId;
	}

	public String getPromoterName() {
		return promoterName;
	}

	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}

	public String getPromoterType() {
		return promoterType;
	}

	public void setPromoterType(String promoterType) {
		this.promoterType = promoterType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public List<BlockDetailTransactionModel> getBlockList() {
		return blockList;
	}

	public void setBlockList(List<BlockDetailTransactionModel> blockList) {
		this.blockList = blockList;
	}

}
