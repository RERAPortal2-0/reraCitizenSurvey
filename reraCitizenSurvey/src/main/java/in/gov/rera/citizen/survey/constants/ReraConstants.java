package in.gov.rera.citizen.survey.constants;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReraConstants {
	
	private static final Logger logger = LogManager.getLogger(ReraConstants.class);
	
	public static final String PROJECT_REG = "PROJECT_REG";
	public static final String PROJECT_ALT = "PROJECT_ALT";
	public static final String PROJECT_USER = "PROJECT";
	public static final String PROJECT_OTHER_PROMOTER = "PROJECT_OTHER_PROMOTER";
	public static final String NOT_FOUND = "No any content found.";
	
	//
	public static final String AGENT_USER = "AGENT";
	public static final String OPERATOR_USER = "OPERATOR";
	public static final String AUTHORITY_USER = "AUTHORITY";
	public static final String CHIEFAUTHORITY_USER = "CHIEFAUTHORITY";
	public static final String CHIEFAUTHORITY_SUPER_USER = "L2-SUPER";
	public static final String ADMIN_USER = "ADMIN";
	public static final String FINANCE_USER = "FINANCE";
	public static final String REGISTRY_USER = "REGISTRY";
	public static final String GUJRERA_VIEW = "SUPER_VIEW";

	public static final String SES_USER_ATTR = "RERA_USER_ATTR";
	public static final String SES_USER_ROLE = "RERA_USER_ROLE";

	public static final String SAVE_AS_DRAFT = "SAVE_AS_DRAFT";
	public static final String PENDING_OPRATOR = "PENDING_OPRATOR";
	public static final String PENDING_AUTHORITY = "PENDING_AUTHORITY";
	public static final String PENDING_HEADOFFICE = "PENDING_HEADOFFICE";
	public static final String PENDING_FINANCE = "PENDING_FINANCE";
	public static final String PENDING_HARDCOPY = "PENDING_HARDCOPY";
	public static final String PENDING_VERIFICATION = "PENDING_VERIFICATION";
	public static final String REVICED_PAYMENT = "REVISED_PAYMENT";
	public static final String REVISED_LAND_STATUS_TYPE = "REVISED_PROJECT_LAND_STATUS_TYPE";
	public static final String PENDING_PAYMENT = "PENDING_PAYMENT";
	public static final String R2 = "PENDING_R2";
	
	public static final String DEFAULTER = "DEFAULTER";
	public static final String EXEMPT = "EXEMPT";
	public static final String SUBMITTED = "SUBMITTED";
	public static final String VERIFIED = "VERIFIED";
	public static final String APPROVED = "APPROVED";
	public static final String EXPIRED = "EXPIRED";
	public static final String REJECTED = "REJECTED";
	public static final String REVOKED = "REVOKED";
	public static final String EXTENDED = "EXTENDED";
	public static final String INQUIRY_OPERATOR = "INQUIRY_OPERATOR";
	public static final String INQUIRY_AUTHORITY = "INQUIRY_AUTHORITY";
	public static final String INQUIRY_HEADOFFICE = "INQUIRY_HEADOFFICE";

	
	public static final String RESCIND = "RESCIND";
	public static final String MODIFIED = "MODIFIED";
	public static final String DELETE = "DELETE";
	public static final String REVIEW = "REVIEW";
	public static final String FORM_FIVE = "RESCIND";
	
	public static final String ACTIVE_USER = "FORM_FIVE";
	public static final String ACTIVE = "ACTIVE";
	public static final String PENDING_WITH_AUTH = "PENDING_WITH_AUTH";
	public static final String INACTIVE_USER = "INACTIVE";
	public static final String INACTIVE = "INACTIVE";
	public static final String DEFAULT_USER = "DEFAULT";

	public static final String VENDOR_REQ_PENDING = "PENDING";
	public static final String VENDOR_REQ_CONFIRMED = "CONFIRMED";
	public static final String VENDOR_REQ_SEPARATED = "SEPARATED";
	public static final String VENDOR_REQ_REJECTED = "REJECTED";
	
	public static final String UNDER_PROCESS = "UNDER_PROCESS";
	public static final String PAID = "Paid";
	public static final String NOT_PAID = "Not Paid";
	public static final String PARTIALLY_PAID = "Partial Paid";
	

	public static int PAY_MODE_DD = 1;
	public static int PAY_MODE_CHEQUE = 2;
	public static int PAY_MODE_ONLINE = 3;

	public static int FIRM_SOCIETIES = 1;
	public static int FIRM_COMPANY = 2;
	public static int FIRM_PARTNERSHIP = 3;
	public static int FIRM_COMPETENT_AUTHORITY = 4;
	public static int FIRM_LIMITED_PERTNERSHIP = 5;
	public static int FIRM_TRUST = 6;

	public static Map<Long, Long[]> ZONE_WITH_DISTRICT;
	public static final String SUO_MOTTO_COMPLAINT = "Suo Motu Complaint";

	
	public static final String GENERAL_COMPLAINT = "General Complaint";
	public static final String CONCILIATION_COMPLAINT = "Conciliation Complaint";
	public static final String BOTH = "Both";
	static {
		ZONE_WITH_DISTRICT = new HashMap<Long, Long[]>();
		ZONE_WITH_DISTRICT.put(1l, new Long[] { 1141l });
		ZONE_WITH_DISTRICT.put(2l, new Long[] { 1141l, 1153l, 1157l, 1143l,1164l, 1150l, 1148l, 1665l, 1159l });
		ZONE_WITH_DISTRICT.put(3l, new Long[] { 1160l, 1145l, 1663l, 1165l,1158l, 1144l });
		ZONE_WITH_DISTRICT.put(4l, new Long[] { 1664l });
		ZONE_WITH_DISTRICT.put(5l, new Long[] { 1664l, 1162l, 1163l, 1666l,1668l, 1151l });
		ZONE_WITH_DISTRICT.put(6l, new Long[] { 1667l, 1149l, 1146l });
		ZONE_WITH_DISTRICT.put(7l, new Long[] { 1142l, 1147l, 1152l, 1154l,1155l, 1156l, 1161l, 1166l, 1167l });
	}
	
	public static final DateFormat DATE_FORMATE = new SimpleDateFormat("ddMMyy");
	public static final Integer MAX_RESULT = 10;
	public static final Integer OFF_SET = 0;
	
	
/* Added By AJ::23rd-NOV-2017-11:51AM::	*/
	public static final String WITHDRAWAL = "WITHDRAWAL";
	public static final String DUPLICATE = "DUPLICATE";
	public static final String ARCHIEVED = "ARCHIEVED";
	public static final String REFUNDED = "REFUNDED";
	public static final String MnA = "MnA";
	public static final String SUSPENDED = "SUSPENDED";
	public static final String APPLY_REFUND = "APPLY_REFUND";
	public static final String BOOKED_REFUND = "BOOKED_REFUND";
/* Ended By AJ	*/

	public static int DELETE_DRAFT_PROJECT_AFTER_DAYS = 15;
	public static int DELETE_DRAFT_AGENT_AFTER_DAYS = 15;
	public static long ePAY_MAX_PERIOD = 20;
	static {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("in.gov.rera.common.config.RERAProperties");
			DELETE_DRAFT_PROJECT_AFTER_DAYS = Integer.parseInt(rb.getString("DELETE_DRAFT_PROJECT_AFTER_DAYS"));
			DELETE_DRAFT_AGENT_AFTER_DAYS = Integer.parseInt(rb.getString("DELETE_DRAFT_AGENT_AFTER_DAYS"));
//			For Payment Gateway Configuration.
			rb = ResourceBundle.getBundle("in.gov.rera.transaction.paymentgateway.paymentgatewayconfig");
			ePAY_MAX_PERIOD = Long.parseLong(rb.getString("ePAY_MAX_PERIOD"));
		} catch (Exception ex) {
			logger.error(" Exception :: ");
			logger.error(ex);
		}
	}
	public static final String ePAY_SENT = "ePAY_SENT";
	public static final String ePAY_ABORT = "ABORT";
	public static final String ePAY_BOOKED = "BOOKED";
	public static final String ePAY_CANCELLED = "CANCELLED";
	public static final String ePAY_CLOSED = "CLOSED";
	public static final String ePAY_EXPIRE = "EXPIRED";
	public static final String ePAY_FAIL = "FAIL";
	public static final String ePAY_NoRecordsFound = "No records found";
	public static final String ePAY_PENDING = "PENDING";
	public static final String ePAY_REFUND = "REFUND";
	public static final String ePAY_REJECT = "REJECT";
	public static final String ePAY_SUCCESS = "SUCCESS";
	public static final String ePAY_ERROR_MSG = "Transaction Failed by GujRERA.";
	
	public static final DecimalFormat DECIMALF2 = new DecimalFormat(".##");
	
	
	/*
	COMPLAINTS CONSTANTSssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
	*/

	public static final String L2_AUTHORITY = "AUTHORITY";
	public static final String L2_ADJUDICATING_OFFICER = "ADJUDICATING_OFFICER";
	public static final String L2_APPELLATE_TRIBUNAL = "APPELLATE_TRIBUNAL";
	public static final String L2_CHIEF = "L2_CHIEF";
	public static final String CONCILIATION_OFFICER = "CONCILIATION_OFFICER";
	
	public static final String PENDING_HEARING = "PENDING_HEARING";
	public static final String HEARING_IN_PROGRESS = "HEARING_IN_PROGRESS";
	public static final String JUDGEMENT_PASS = "JUDGEMENT_PASS";
	public static final String CLOSED = "CLOSED";
	public static final String DISMISSED = "DISMISSED";
	public static final String ASSIGN_TO = "ASSIGN_TO";
	
	public static final String OPEN = "OPEN";
	
	public static final String CMP_PAY_TOTAL = "CMP_PAY_TOTAL";
	public static final String CMP_L2_AUTH_TOTAL = "CMP_L2_AUTH_TOTAL";
	public static final String CMP_L2_ADJU_TOTAL = "CMP_L2_ADJU_TOTAL";
	public static final String CMP_L2_BOTH_TOTAL = "CMP_L2_BOTH_TOTAL";
	
	/*
	COMPLAINTS CONSTANTSssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
	*/
	
	public static final String REVISED_LAND_DIFFERENCE_FEES_REFUND = "REVISED_LAND_DIFFERENCE_FEES_REFUND";
	public static final String REVISED_LAND_DIFFERENCE_FEES_ZERO = "REVISED_LAND_DIFFERENCE_FEES_ZERO";
	public static final String REVISED_LAND_DIFFERENCE_FEES_GET = "REVISED_LAND_DIFFERENCE_FEES_GET";
	
	
	public static final Calendar HC_DATE_LIMIT = new GregorianCalendar(2018, Calendar.MAY, 19);
	
	public static final String KEY_ENC_DNC = "FFFFFF3229A0B371EDDDDFFFFFAABBFFABBD2D9441B830D21A390C3";
	
	
	public static final String MISC_PAY_HEAD_TYPE_FC = "FINANCE_CREATED";
	public static final String MISC_PAY_HEAD_TYPE_SC = "SYSTEM_CREATED";
	public static final String MISC_PAY_HEAD_TYPE_EUC = "END_USER_CREATED";
	public static final String CA_REGS = "CA_REGISTRATION";
	
	public static final String NEW = "NEW";
	public static final String BANKERS="BANKERS";
	public static final String ARCHITECT_REG="ARCHITECT_REG";
	
	
	public static final String DECLINE = "DECLINE";
	public static final String ESIGNED = "ESIGNED";
	
	
	public static final String AWAITING = "REQUEST FOR ACKNOWLEDGEMENT";
	
	public static final String BCR = "BCR";
	public static final String PR = "PR";
	public static final String ALT = "ALT";
	public static final String EXT = "EXT";
	public static final String QTR = "QTR";
	public static final String PENDING = "PENDING";
	public static final String OFFLINE_COMPLAINT = "Offline Complaint";
	public static final String REVIEW_COMP_APP = "Review Complaint Application";
	public static final String REVIEW_PROJ_APP = "Review Project Application";
	public static final String NCQR = "NCQR";
	public static final String NCAR = "NCAR";
	public static final String SUOMOTO = "SUOMOTO";
	
	public static final String INTERNAL_USER_CATEGORY = "INTERNAL";
	public static final String EXTERNAL_USER_CATEGORY = "EXTERNAL";
	
	public static final String INDVISUAL_REG = "INDVISUAL_REG";
	public static final String FIRM_COMP_REG = "FIRM_COMP_REG";
	
	
	
	public static final String INDVISUAL ="Individual/Propritorship";
	/*******************************************************************************
	 *  User Type
	 ******************************************************************************/
	
	
	public static final String ARCHITECT = "ARCHITECT";
	public static final String ENGINEER = "ENGINEER";
	public static final String CONTRACTOR = "CONTRACTOR";
	public static final String COSTACCONTANT = "COSTACCONTANT";
	public static final String COMPANYSECRETARY = "COMPANYSECRETARY";
	public static final String LAWYER = "LAWYER";
	public static final String CITIZEN = "CITIZEN";
	public static final String PROMOTER = "PROMOTER";
	public static final String CA = "CA_USER";
	public static final String REGISTERED = "REGISTERED";
	
	public static final String ARCHITECT_PAN_DOC = "ARCHITECT_PAN_DOC";
	
	public static final String AGREEMENT_FILE = "AGREEMENT_FILE";
	public static final String BUILDING_PLAN ="ApprovedBuildingPlanPlottingPlan";

	public static final String ASSIGNED="ASSIGNED";
	public static final String INPROGRESS="INPROGRESS";
	public static final String UPLOADED="UPLOADED";
	
	public static final String SENT="SENT";
	
	public static final String SAVEASDRAFT="SAVEASDRAFT";

	public static final String YES = "YES";
	
}
