package in.gov.rera.citizen.survey.notification;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailContents {
	private static final Logger logger = LogManager.getLogger(MailContents.class);


	/*
	 * public static EmailNotification acceptanceMailToCA(FormFiveModel model) {
	 * logger.debug("Form Five Acceptance mail ::Start"); EmailNotification email =
	 * new EmailNotification();
	 * email.setSubject("GujRERA Project Form Five Acceptance");
	 * email.setSendTo(model.getCaEmailId());
	 * email.setProcessName(ReraConstants.FORM_FIVE); email.setSendAsOtp("N");
	 * StringBuilder sb = new StringBuilder();
	 * sb.append("Dear ").append(model.getCaName()).append("\n\n");
	 * sb.append("Project Form Five accepted: \n\n");
	 * //sb.append("Project Name: ").append(model.getProjectDetailModel().
	 * getProjectName()).append(" \n\n");
	 * //sb.append("Promoter Name: ").append(model.getPromoterName()).append(" \n\n"
	 * ); //sb.append("Promoter Emaild: ").append(model.getPromoteremailId()).
	 * append(" \n\n"); sb.append("Regards: \n").append("Gujarat RERA \n\n\n");
	 * email.setBodyContent(sb.toString());
	 * logger.debug("Form Five Acceptance mail::end"); return email; }
	 */

	/*
	 * public static EmailNotification acceptanceMailToPromoter(FormFiveModel model) {
	 * logger.debug("formOneRejectMailToArchitect::start"); EmailNotification email
	 * = new EmailNotification();
	 * email.setSubject("GujRERA Project Alteration Form One Assigment");
	 * email.setSendTo(model.getFormOneModel().getEmailId());
	 * email.setProcessName(ReraConstants.PROJECT_ALT); email.setSendAsOtp("N");
	 * StringBuilder sb = new StringBuilder();
	 * sb.append("Dear ").append(model.getPromoterName()).append("\n\n"); sb.
	 * append("Project formOneRejectMailToArchitect to Architect the details  are below: \n\n"
	 * ); sb.append("Project Name: ").append(model.getProjectDetailModel().
	 * getProjectName()).append(" \n\n");
	 * sb.append("Promoter Name: ").append(model.getPromoterName()).append(" \n\n");
	 * sb.append("Promoter Emaild: ").append(model.getPromoteremailId()).
	 * append(" \n\n"); sb.append("Regards: \n").append("Gujarat RERA \n\n\n");
	 * email.setBodyContent(sb.toString());
	 * logger.debug("formOneRejectMailToArchitect::end"); return email; }
	 */

	

}
