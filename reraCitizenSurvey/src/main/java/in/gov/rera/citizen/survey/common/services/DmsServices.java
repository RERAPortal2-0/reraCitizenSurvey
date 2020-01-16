package in.gov.rera.citizen.survey.common.services;

import com.itextpdf.text.Document;

public interface DmsServices {

	//public FormFiveOtherDocModel commitOthDoc(FormFiveOtherDocModel model,String url);
	//public FormFiveDocumentModel commitDoc(FormFiveDocumentModel model,String url);
	public String commitAllotteeAfsPdf(Document doc, String url);
}
