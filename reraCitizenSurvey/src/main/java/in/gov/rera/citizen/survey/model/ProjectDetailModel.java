package in.gov.rera.citizen.survey.model;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;

public class ProjectDetailModel {
    private Long id;
    
    private Long prjRegId; // reference

	@Column(name = "PRD_NAME")
	private String projectName;

	@Column(name = "PRD_TYPE")
	private String projectType;

    @Column(name = "PRD_START_DATE")
	private Date startDate;

	@Column(name = "PRD_COMPLETION_DATE")
	private Date completionDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	@Column(name = "PRD_STATUS")
	private String projectStatus;

	@Column(name = "PRD_DESC")
	private String projectDesc;

	@Column(name = "PRD_ADDRESS")
	private String projectAddress;

	@Column(name = "PRD_ADDRESS2")
	private String projectAddress2;

	@Column(name = "PRD_STATE_CODE")
	private String stateCode;

	@Column(name = "PRD_SATE_NAME")
	private String stateName;

	@Column(name = "PRD_DISTRICT_CODE")
	private String distCode;

	@Column(name = "PRD_DISTRICT_NAME")
	private String distName;

	@Column(name = "PRD_SUB_DISTRICT_CODE")
	private String subDistCode;

	@Column(name = "PRD_SUB_DISTRICT_NAME")
	private String subDistName;

	@Column(name = "PRD_OPEN_PARKING_AREA")
	private String openParkingArea;

	@Column(name = "PRD_AIRPORT_KM")
	private String airportDistance;
	@Column(name = "PRD_UBLSERVICES_KM")
	private String ulbServiceDelivery;
	@Column(name = "PRD_PUBLICTRANSMIT_KM")
	private String publicTransit;
	@Column(name = "PRD_PUBLICGARDEN_KM")
	private String publicGarden;
	@Column(name = "PRD_POLICE_STATION_KM")
	private String policeStation;
	@Column(name = "PRD_MAIN_RAILWAYSTATION_KM")
	private String mainRailwayStation;
	@Column(name = "PRD_MLTSPCLITY_HOSPITAL_KM")
	private String multiSpecialityHospital;
	@Column(name = "PRD_FIRE_STATION_KM")
	private String fireStation;

	/* Land details */
	@Column(name = "PRD_PIN_CODE")
	private String pinCode;
	@Column(name = "PRD_MOJE")
	private String moje; // multiple value addmore
	@Column(name = "PRD_REVENUE_SURVEY_NO")
	private String revenueSurveyNo;
	@Column(name = "PRD_BLOCK_NO")
	private String blockNo;
	@Column(name = "PRD_CITY_SURVEY_NO")
	private String citySurveyNo;

	/* TP details */

	@Column(name = "PRD_TP_NAME")
	private String validTpName;

	/* Non TP details */
	@Column(name = "PRD_TP_NO")
	private String tPNo;
	@Column(name = "PRD_PLOT_NO")
	private String plotNo;
	@Column(name = "PRD_TPNAME_NO")
	private String tPName;
	@Column(name = "PRD_FINAL_PLOT__NO")
	private String finalPlotNo;
	@Column(name = "PRD_SUB_PLOT_NO")
	private String subPlotNumber;
	@Column(name = "PRD_BUILDING_BLOCK_NO")
	private String buildingBlocks;

	@Column(name = "PRD_TOTAL_LAND_AREA")
	private Double totAreaOfLand;

	@Column(name = "PRD_TOTAL_LAND_AREA_LAYOUT")
	private Double totAreaOfLandLayout;

	@Column(name = "PRD_TTL_LAND_AREA_FOR_PRJ_UNDER_REG")
	private Double totLandAreaForProjectUnderReg;

	@Column(name = "PRD_TTL_CARPET_AREA")
	private Double totCarpetArea;

	@Column(name = "PRD_TTL_CARPET_AREA_SELLABLE")
	private Double totalCarpetAreaForSellable;

	@Column(name = "PRD_TTL_CARPET_AREA_UNDER_LAYOUT")
	private Double totCarpetAreaUnderLayout;

	@Column(name = "PRD_TTL_CARPET_AREA_FOR_PROJECT_UNDER_REG")
	private Double totCarpetAreaForProjectUnderReg;

	@Column(name = "PRD_TTL_COVERED_AREA")
	private Double totCoverdArea;

	@Column(name = "PRD_TTL_OPEN_AREA")
	private Double totOpenArea;

	@Column(name = "PRD_AREA_OF_GARAGE_FOR_SALE")
	private Double areaOfGarageForSale;

	@Column(name = "PRD_HARD_COPY_SUBMITION_OFFICE")
	private Double hardCopySubmitionOffice;

	@Column(name = "PRD_AREA_OF_PARKING_FOR_SALE")
	private Double areaOfParkinfForSale;

	@Column(name = "PRD_AREA_OF_COVERED_PARKING")
	private Double coveredParkingArea;

	@Column(name = "PRD_NO_OF_COVERED_PARKING")
	private Integer coveredParking;

	@Column(name = "PRD_NO_OF_PARKING_FOR_SALE")
	private Integer noOfParkinfForSale;

	@Column(name = "PRD_NO_OF_GARAGE_FOR_SALE")
	private Integer noOfGarageForSale;

	@Column(name = "PRD_GARAGE_SOLDOUT")
	private Integer garageSoldout;

	@Column(name = "PRD_NO_OF_MONTHS_DELAYED")
	private Integer noOfMonthDelayed;

	@Column(name = "PRD_COST_OF_LAND")
	private Long costOfLand;

	@Column(name = "PRD_ESTIMATED_COST")
	private Long estimatedCost;

	@Column(name = "PRD_TOTAL_PROJECT_COST")
	private Long totalProjectCost;

	@Column(name = "PRD_REASON_FOR_DELAYED")
	private String reasonForDelayed;

	@Column(name = "PRD_RERA_OFFICE_CODE")
	private String officeCode;

	@Column(name = "PRD_RERA_OFFICE_NAME")
	private String officeName;

	/*************************************************
	 * Document
	 *************************************************/
	@Column(name = "PRD_COMPELETION_CERT_DOC_ID")
	private Long compeletionCertDocId;

	@Column(name = "PRD_COMPELETION_CERT_DOC_UID")
	private String compeletionCertDocUId;

	@Column(name = "PRD_DECLR_FORM_B_NEW_DOC_ID")
	private Long declarationFormbNewDocId;

	@Column(name = "PRD_DECLR_FORM_B_NEW_DOC_UID")
	private String declarationFormbNewDocUId;

	@Column(name = "PRD_CERT_DOC_ID")
	private Long projectCertDocId;

	@Column(name = "PRD_CERT_DOC_UID")
	private String projectCertDocUId;

	@Column(name = "PRD_REVOKE_DOC_ID")
	private Long projectRevokeDocId;

	@Column(name = "PRD_REVOKE_DOC_UID")
	private String projectRevokeDocUId;

	/*************************************************
	 * Approver
	 *************************************************/

	@Column(name = "PRD_APPROVING_AUTHORITY")
	private String approvingAuthority;

	@Column(name = "PRD_MODIFIED_ON")
	private Date modifiedOn;

	@Column(name = "PRD_MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "PRD_LAST_UPDATED_ON")
	private Calendar updatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrjRegId() {
		return prjRegId;
	}

	public void setPrjRegId(Long prjRegId) {
		this.prjRegId = prjRegId;
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


	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistCode() {
		return distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getSubDistCode() {
		return subDistCode;
	}

	public void setSubDistCode(String subDistCode) {
		this.subDistCode = subDistCode;
	}

	public String getSubDistName() {
		return subDistName;
	}

	public void setSubDistName(String subDistName) {
		this.subDistName = subDistName;
	}

	public Double getTotAreaOfLand() {
		return totAreaOfLand;
	}

	public void setTotAreaOfLand(Double totAreaOfLand) {
		this.totAreaOfLand = totAreaOfLand;
	}

	public Double getTotLandAreaForProjectUnderReg() {
		return totLandAreaForProjectUnderReg;
	}

	public void setTotLandAreaForProjectUnderReg(Double totLandAreaForProjectUnderReg) {
		this.totLandAreaForProjectUnderReg = totLandAreaForProjectUnderReg;
	}

	public Double getTotCarpetAreaUnderLayout() {
		return totCarpetAreaUnderLayout;
	}

	public void setTotCarpetAreaUnderLayout(Double totCarpetAreaUnderLayout) {
		this.totCarpetAreaUnderLayout = totCarpetAreaUnderLayout;
	}

	public Double getTotCarpetAreaForProjectUnderReg() {
		return totCarpetAreaForProjectUnderReg;
	}

	public void setTotCarpetAreaForProjectUnderReg(Double totCarpetAreaForProjectUnderReg) {
		this.totCarpetAreaForProjectUnderReg = totCarpetAreaForProjectUnderReg;
	}

	public Double getTotCoverdArea() {
		return totCoverdArea;
	}

	public void setTotCoverdArea(Double totCoverdArea) {
		this.totCoverdArea = totCoverdArea;
	}

	public Double getTotOpenArea() {
		return totOpenArea;
	}

	public void setTotOpenArea(Double totOpenArea) {
		this.totOpenArea = totOpenArea;
	}

	public Double getAreaOfGarageForSale() {
		return areaOfGarageForSale;
	}

	public void setAreaOfGarageForSale(Double areaOfGarageForSale) {
		this.areaOfGarageForSale = areaOfGarageForSale;
	}

	public Double getAreaOfParkinfForSale() {
		return areaOfParkinfForSale;
	}

	public void setAreaOfParkinfForSale(Double areaOfParkinfForSale) {
		this.areaOfParkinfForSale = areaOfParkinfForSale;
	}

	public Double getCoveredParkingArea() {
		return coveredParkingArea;
	}

	public void setCoveredParkingArea(Double coveredParkingArea) {
		this.coveredParkingArea = coveredParkingArea;
	}

	public Integer getCoveredParking() {
		return coveredParking;
	}

	public void setCoveredParking(Integer coveredParking) {
		this.coveredParking = coveredParking;
	}

	public Integer getNoOfParkinfForSale() {
		return noOfParkinfForSale;
	}

	public void setNoOfParkinfForSale(Integer noOfParkinfForSale) {
		this.noOfParkinfForSale = noOfParkinfForSale;
	}

	public Integer getNoOfGarageForSale() {
		return noOfGarageForSale;
	}

	public void setNoOfGarageForSale(Integer noOfGarageForSale) {
		this.noOfGarageForSale = noOfGarageForSale;
	}

	public Integer getGarageSoldout() {
		return garageSoldout;
	}

	public void setGarageSoldout(Integer garageSoldout) {
		this.garageSoldout = garageSoldout;
	}

	public Integer getNoOfMonthDelayed() {
		return noOfMonthDelayed;
	}

	public void setNoOfMonthDelayed(Integer noOfMonthDelayed) {
		this.noOfMonthDelayed = noOfMonthDelayed;
	}

	public Long getCostOfLand() {
		return costOfLand;
	}

	public void setCostOfLand(Long costOfLand) {
		this.costOfLand = costOfLand;
	}

	public Long getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Long estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Long getTotalProjectCost() {
		return totalProjectCost;
	}

	public void setTotalProjectCost(Long totalProjectCost) {
		this.totalProjectCost = totalProjectCost;
	}

	public String getReasonForDelayed() {
		return reasonForDelayed;
	}

	public void setReasonForDelayed(String reasonForDelayed) {
		this.reasonForDelayed = reasonForDelayed;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Long getCompeletionCertDocId() {
		return compeletionCertDocId;
	}

	public void setCompeletionCertDocId(Long compeletionCertDocId) {
		this.compeletionCertDocId = compeletionCertDocId;
	}

	public String getCompeletionCertDocUId() {
		return compeletionCertDocUId;
	}

	public void setCompeletionCertDocUId(String compeletionCertDocUId) {
		this.compeletionCertDocUId = compeletionCertDocUId;
	}

	public Long getDeclarationFormbNewDocId() {
		return declarationFormbNewDocId;
	}

	public void setDeclarationFormbNewDocId(Long declarationFormbNewDocId) {
		this.declarationFormbNewDocId = declarationFormbNewDocId;
	}

	public String getDeclarationFormbNewDocUId() {
		return declarationFormbNewDocUId;
	}

	public void setDeclarationFormbNewDocUId(String declarationFormbNewDocUId) {
		this.declarationFormbNewDocUId = declarationFormbNewDocUId;
	}

	public Long getProjectCertDocId() {
		return projectCertDocId;
	}

	public void setProjectCertDocId(Long projectCertDocId) {
		this.projectCertDocId = projectCertDocId;
	}

	public String getProjectCertDocUId() {
		return projectCertDocUId;
	}

	public void setProjectCertDocUId(String projectCertDocUId) {
		this.projectCertDocUId = projectCertDocUId;
	}

	public Long getProjectRevokeDocId() {
		return projectRevokeDocId;
	}

	public void setProjectRevokeDocId(Long projectRevokeDocId) {
		this.projectRevokeDocId = projectRevokeDocId;
	}

	public String getProjectRevokeDocUId() {
		return projectRevokeDocUId;
	}

	public void setProjectRevokeDocUId(String projectRevokeDocUId) {
		this.projectRevokeDocUId = projectRevokeDocUId;
	}

	public String getApprovingAuthority() {
		return approvingAuthority;
	}

	public void setApprovingAuthority(String approvingAuthority) {
		this.approvingAuthority = approvingAuthority;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Calendar getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Calendar updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Double getHardCopySubmitionOffice() {
		return hardCopySubmitionOffice;
	}

	public void setHardCopySubmitionOffice(Double hardCopySubmitionOffice) {
		this.hardCopySubmitionOffice = hardCopySubmitionOffice;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getMoje() {
		return moje;
	}

	public void setMoje(String moje) {
		this.moje = moje;
	}

	public String getRevenueSurveyNo() {
		return revenueSurveyNo;
	}

	public void setRevenueSurveyNo(String revenueSurveyNo) {
		this.revenueSurveyNo = revenueSurveyNo;
	}

	public String getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
	}

	public String getCitySurveyNo() {
		return citySurveyNo;
	}

	public void setCitySurveyNo(String citySurveyNo) {
		this.citySurveyNo = citySurveyNo;
	}

	public String gettPNo() {
		return tPNo;
	}

	public void settPNo(String tPNo) {
		this.tPNo = tPNo;
	}

	public String getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	public String gettPName() {
		return tPName;
	}

	public void settPName(String tPName) {
		this.tPName = tPName;
	}

	public String getFinalPlotNo() {
		return finalPlotNo;
	}

	public void setFinalPlotNo(String finalPlotNo) {
		this.finalPlotNo = finalPlotNo;
	}

	public String getSubPlotNumber() {
		return subPlotNumber;
	}

	public void setSubPlotNumber(String subPlotNumber) {
		this.subPlotNumber = subPlotNumber;
	}

	public String getBuildingBlocks() {
		return buildingBlocks;
	}

	public void setBuildingBlocks(String buildingBlocks) {
		this.buildingBlocks = buildingBlocks;
	}

	public String getProjectAddress2() {
		return projectAddress2;
	}

	public void setProjectAddress2(String projectAddress2) {
		this.projectAddress2 = projectAddress2;
	}

	public String getValidTpName() {
		return validTpName;
	}

	public void setValidTpName(String validTpName) {
		this.validTpName = validTpName;
	}

	public String getOpenParkingArea() {
		return openParkingArea;
	}

	public void setOpenParkingArea(String openParkingArea) {
		this.openParkingArea = openParkingArea;
	}

	public Double getTotAreaOfLandLayout() {
		return totAreaOfLandLayout;
	}

	public void setTotAreaOfLandLayout(Double totAreaOfLandLayout) {
		this.totAreaOfLandLayout = totAreaOfLandLayout;
	}

	public Double getTotCarpetArea() {
		return totCarpetArea;
	}

	public void setTotCarpetArea(Double totCarpetArea) {
		this.totCarpetArea = totCarpetArea;
	}

	public Double getTotalCarpetAreaForSellable() {
		return totalCarpetAreaForSellable;
	}

	public void setTotalCarpetAreaForSellable(Double totalCarpetAreaForSellable) {
		this.totalCarpetAreaForSellable = totalCarpetAreaForSellable;
	}

	public String getAirportDistance() {
		return airportDistance;
	}

	public void setAirportDistance(String airportDistance) {
		this.airportDistance = airportDistance;
	}

	public String getUlbServiceDelivery() {
		return ulbServiceDelivery;
	}

	public void setUlbServiceDelivery(String ulbServiceDelivery) {
		this.ulbServiceDelivery = ulbServiceDelivery;
	}

	public String getPublicTransit() {
		return publicTransit;
	}

	public void setPublicTransit(String publicTransit) {
		this.publicTransit = publicTransit;
	}

	public String getPublicGarden() {
		return publicGarden;
	}

	public void setPublicGarden(String publicGarden) {
		this.publicGarden = publicGarden;
	}

	public String getPoliceStation() {
		return policeStation;
	}

	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}

	public String getMainRailwayStation() {
		return mainRailwayStation;
	}

	public void setMainRailwayStation(String mainRailwayStation) {
		this.mainRailwayStation = mainRailwayStation;
	}

	public String getMultiSpecialityHospital() {
		return multiSpecialityHospital;
	}

	public void setMultiSpecialityHospital(String multiSpecialityHospital) {
		this.multiSpecialityHospital = multiSpecialityHospital;
	}

	public String getFireStation() {
		return fireStation;
	}

	public void setFireStation(String fireStation) {
		this.fireStation = fireStation;
	}

}
