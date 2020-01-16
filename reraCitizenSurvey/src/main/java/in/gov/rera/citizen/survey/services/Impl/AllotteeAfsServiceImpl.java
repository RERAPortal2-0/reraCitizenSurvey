package in.gov.rera.citizen.survey.services.Impl;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import in.gov.rera.citizen.survey.common.RestTamplateUtility;
import in.gov.rera.citizen.survey.dao.AfsClauseDao;
import in.gov.rera.citizen.survey.model.AllotteeAfsDtlModel;
import in.gov.rera.citizen.survey.model.CitizenClaimModel;
import in.gov.rera.citizen.survey.model.ProjectAfsClauseModel;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;
import in.gov.rera.citizen.survey.model.ProjectRegistrationModel;
import in.gov.rera.citizen.survey.services.AllotteeAfsService;
import in.gov.rera.citizen.survey.services.CitizenClaimService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class AllotteeAfsServiceImpl implements AllotteeAfsService {

	private static final Logger logger = LogManager.getLogger(AllotteeAfsServiceImpl.class);
	@Autowired
	AfsClauseDao afsDao;

	@Autowired
	CitizenClaimService citizenService;

	@Value("${URL_PROJECT_REG}")
	String projectRegUrl;

	@Autowired
	Environment env;

	@Override
	public AllotteeAfsDtlModel saveAfs(AllotteeAfsDtlModel model) {
		return null;
	}

	@Override
	public AllotteeAfsDtlModel findById(Long id) {
		return null;
	}

	@Override
	public List<AllotteeAfsDtlModel> findAll() {
		return null;
	}

	@Override
	public void deleteById(Long id) {
	}

	@Override
	public AllotteeAfsDtlModel findByKycId(String kycId) {
		return null;
	}

	
	public static PdfPCell createPdfCell(Paragraph para, int i) {
		PdfPCell c0 = new PdfPCell(para);
		c0.setHorizontalAlignment(i);
		c0.setPaddingLeft(10);
		return c0;
	}

	public static PdfPCell createPdfCellByPhrase(Phrase ph, int i) {
		PdfPCell c0 = new PdfPCell(ph);
		c0.setHorizontalAlignment(i);
		c0.setPaddingLeft(10);

		return c0;
	}

	public static PdfPCell createCellByAlignBold(String str) {
		PdfPCell c0 = createPdfCellByPhrase(new Phrase(str, smallBold), 0);
		c0.setHorizontalAlignment(Element.ALIGN_CENTER);
		c0.setPaddingLeft(10);
		return c0;
	}

	public static PdfPCell createCellByAlignNormal(String str) {
		PdfPCell c0 = createPdfCellByPhrase(new Phrase(str, smallNormal), 0);
		c0.setHorizontalAlignment(Element.ALIGN_LEFT);
		c0.setPaddingLeft(10);
		return c0;
	}
	
	public static PdfPCell createCellByAlignRight(String str) {
		PdfPCell c0 = createPdfCellByPhrase(new Phrase(str, smallBold), 0);
		c0.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c0.setPaddingLeft(10);
		c0.setBorder(0);
		return c0;
	}
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	private static Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
	
	public static byte[] reraLogo;

	@Override
	public void generateAllotteefsPdf(ProjectAfsModel model, HttpServletResponse response, Long projetId, Long kycId,
			String property, byte[] bt) {
		Document document = new Document();
		this.reraLogo = bt;
		document.setPageSize(PageSize.A4);
		document.setMargins(40, 40, 40, 40);
		Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
		Font clauseFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
		Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9f, Font.BOLD);
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=" + "Project-AFS" + ".pdf");
		Font fontUL = new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.UNDERLINE);
		Font itlaicfont = new Font(Font.FontFamily.TIMES_ROMAN, 11f, Font.ITALIC);
		try {
			ProjectRegistrationModel project = new ProjectRegistrationModel();
			CitizenClaimModel chModel = citizenService.findById(kycId);
			project = RestTamplateUtility.projectDtlP(projetId, property);
            
			Optional.of(project).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new GreyBorder());
			document.open();
			Paragraph p1 = new Paragraph(project.getProjectDetailsModel().getProjectName(), font1);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p1);
			String qrCodeText = project.getProjectDetailsModel().getProjectName() + ",\n" + chModel.getAllotteeName()
					+ ",\n" + chModel.getBlockName()+",\n" + chModel.getFlatNumber() + ",\n" + chModel.getAllotteekyc();
			BarcodeQRCode barcodeQRCode = new BarcodeQRCode(qrCodeText, 1000, 1000, null);
			Image codeQrImage = barcodeQRCode.getImage();
			codeQrImage.scaleAbsolute(90, 90);
			codeQrImage.setAlignment(Image.ALIGN_RIGHT);
			document.add(codeQrImage);
			if (project.getProjectDetailsModel().getStateName() != null) {
				Paragraph p5 = new Paragraph("State : " + project.getProjectDetailsModel().getStateName(), smallBold);
				p5.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(p5);
			}
			if (project.getProjectDetailsModel().getDistName() != null) {
				Paragraph p5 = new Paragraph("District : " + project.getProjectDetailsModel().getDistName(),
						smallBold);
				p5.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(p5);
			}
			if (project.getProjectDetailsModel().getPinCode() != null) {
				Paragraph p5 = new Paragraph("Pincode : " + project.getProjectDetailsModel().getPinCode(), smallBold);
				p5.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(p5);
			}
			if (project.getProjectDetailsModel().getProjectAddress() != null) {
				Paragraph p4 = new Paragraph("Address : " + project.getProjectDetailsModel().getProjectAddress(),
						smallBold);
				p4.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(p4);
			}
			if (project.getProjRegNo() != null) {
				Paragraph p2 = new Paragraph("Project REG. No : " + project.getProjRegNo(), smallBold);
				p2.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(p2);
			}
			if (project.getProjectAckNo() != null) {
				Paragraph p3 = new Paragraph("Project ACK. No : " + project.getProjectAckNo(), smallBold);
				p3.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(p3);
			}

			document.add(Chunk.NEWLINE);

			for (int i = 0; i < model.getAfsClauseList().size(); i++) {
				ProjectAfsClauseModel m = model.getAfsClauseList().get(i);
				String str = m.getClauseDtl();
				str = str.replaceAll("\\<.*?\\>", "");
				Paragraph p5 = new Paragraph((++i) + ".  " + str, clauseFont);
				p5.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(p5);
			}

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			
		      PdfPTable table = new PdfPTable(new float[] { 50, 50 });
		        table.setWidthPercentage(100);
		        table.getDefaultCell().setBorder(0);
			
			if (project.getPromoterName() != null) {
				table.addCell(new Phrase("Promoter Name : " + project.getPromoterName(),smallBold));
			}
			if (chModel.getAllotteeName() != null) {
				table.addCell(createCellByAlignRight("Allottee Name : " + chModel.getAllotteeName()));
			}
			
			if (project.getPromoterType() != null) {
				table.addCell(new Phrase("Promoter Type : " + project.getPromoterType(),smallBold));
			}
			if (chModel.getBlockName() != null) {
				table.addCell(createCellByAlignRight("Block Name : " + chModel.getBlockName()));
			}
			
			if (project.getPromoteremailId() != null) {
				table.addCell(new Phrase("Promoter EmailId : " + project.getPromoteremailId(),smallBold));
			}
			if (chModel.getFlatNumber() != null) {
				table.addCell(createCellByAlignRight("Flat No : " + chModel.getFlatNumber()));
			}
			
			if (project.getPromoterMobileNo() != null) {
				table.addCell(new Phrase("Promoter Mobile No. : " + project.getPromoterMobileNo(),normalFont));
			}
			if (chModel.getAllotteekyc() != null) {
				table.addCell(createCellByAlignRight("KYC No. : " + chModel.getAllotteekyc()));
			}
			 document.add(table);
			/*
			 * Paragraph p7 = new
			 * Paragraph(project.getProjectDetailsModel().getProjectName(), normalFont);
			 * p7.setAlignment(Paragraph.ALIGN_RIGHT); document.add(p7);
			 */
			
		
		
			
			
			
			
			document.close();

			// model=dmsService.commitDoc(formFiveModel.getFormFiveDocModel(),
			// env.getProperty("URL_CREATE_VDMS"));

			writer.close();
		} catch (Exception e) {

		}
		
		
		
		
	}

	class GreyBorder extends PdfPageEventHelper {

		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			try {
				PdfContentByte canvas1 = writer.getDirectContentUnder();
				Image image = Image.getInstance(AllotteeAfsServiceImpl.reraLogo);
				image.setAbsolutePosition(50f, 180f);
				image.scaleAbsolute(505, 480);
				canvas1.saveState();
				PdfGState state = new PdfGState();
				state.setFillOpacity(0.1f);
				canvas1.setGState(state);
				canvas1.addImage(image);
				canvas1.restoreState();
				canvas1 = writer.getDirectContent();
				Rectangle rect = new Rectangle(20, 20, 580, 830);
				rect.setBorder(Rectangle.BOX); //
				rect.setBorderColor(new BaseColor(192, 0, 0));
				rect.setBorderColor(BaseColor.GRAY);
				rect.setBorderWidth(2);
				canvas1.rectangle(rect);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
