package in.gov.rera.citizen.survey.model.transaction;

public class UserTransactionModel {

	private Long formFiveId;
	private String projectName;
	private String promoterName;
	private String certFromDate;
	private String certToDate;
    private String formFiveStatus;
    private String caNo;

	public Long getFormFiveId() {
		return formFiveId;
	}
	public void setFormFiveId(Long formFiveId) {
		this.formFiveId = formFiveId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPromoterName() {
		return promoterName;
	}
	public void setPromoterName(String promoterName) {
		this.promoterName = promoterName;
	}
	public String getCertFromDate() {
		return certFromDate;
	}
	public void setCertFromDate(String certFromDate) {
		this.certFromDate = certFromDate;
	}
	public String getCertToDate() {
		return certToDate;
	}
	public void setCertToDate(String certToDate) {
		this.certToDate = certToDate;
	}
	public String getFormFiveStatus() {
		return formFiveStatus;
	}
	public void setFormFiveStatus(String formFiveStatus) {
		this.formFiveStatus = formFiveStatus;
	}
	public String getCaNo() {
		return caNo;
	}
	public void setCaNo(String caNo) {
		this.caNo = caNo;
	}
}
