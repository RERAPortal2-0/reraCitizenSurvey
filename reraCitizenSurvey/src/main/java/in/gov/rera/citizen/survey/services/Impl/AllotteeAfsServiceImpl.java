package in.gov.rera.citizen.survey.services.Impl;

import java.io.OutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
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
		try {
			
			Format f = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = f.format(new Date());
			ProjectRegistrationModel project = new ProjectRegistrationModel();
			CitizenClaimModel chModel = citizenService.findById(kycId);
			project = RestTamplateUtility.projectDtlP(projetId, property);
			Optional.of(project).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=" + chModel.getAllotteekyc()+strDate + ".pdf");
		Font fontUL = new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.UNDERLINE);
		Font itlaicfont = new Font(Font.FontFamily.TIMES_ROMAN, 11f, Font.ITALIC);
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new GreyBorder());
			document.open();
			Paragraph p1 = new Paragraph(project.getProjectDetailsModel().getProjectName(), font1);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p1);
			if (project.getProjRegNo() != null) {
				Paragraph p2 = new Paragraph("" + project.getProjRegNo(), smallBold);
				p2.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p2);
			}
			if (project.getProjectAckNo() != null) {
				Paragraph p3 = new Paragraph("" + project.getProjectAckNo(), smallBold);
				p3.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p3);
			}
			
			/*
			 * PdfPTable table = new PdfPTable(4); PdfPCell cell = new PdfPCell(new
			 * Phrase(" 1,1 "));
			 * 
			 * table.addCell(cell); PdfPCell cell23 = new PdfPCell(new
			 * Phrase("multi 1,3 and 1,4")); cell23.setColspan(4); cell23.setRowspan(4);
			 * table.addCell(cell23); cell = new PdfPCell(new Phrase(" 2,1 "));
			 * 
			 * table.addCell(cell); cell = new PdfPCell(new Phrase(" 2,1 "));
			 * 
			 * table.addCell(cell); cell = new PdfPCell(new Phrase(" 2,1 "));
			 * 
			 * table.addCell(cell); document.add(table);
			 */
			
			
			
					
			String projectRegNo = chModel.getProjectRegNo()==null?"":chModel.getProjectRegNo();
		    System.out.println("Project Reg no is "+projectRegNo);	
		    
		    String allotteeName = chModel.getAllotteeName()==null?"":chModel.getAllotteeName();
		    System.out.println("Allottee Name is "+allotteeName);	
		    
		    String qrCodeText = projectRegNo;
		    
		    if(allotteeName!="")
		    qrCodeText = qrCodeText==""?allotteeName:qrCodeText+ ",\n" + allotteeName;
		    else
		    {
		    	 qrCodeText = qrCodeText==""?allotteeName:qrCodeText;
		    }
		    
		    qrCodeText = qrCodeText+ ",\n" + chModel.getBlockName()+",\n" + chModel.getFlatNumber() + ",\n" + chModel.getAllotteekyc();
			
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
	
			document.add(Chunk.NEWLINE);
			for (int i = 0; i < model.getAfsClauseList().size(); i++) {
				ProjectAfsClauseModel m = model.getAfsClauseList().get(i);
				String str = m.getClauseDtl();
				str = str.replaceAll("\\<.*?\\>", "");
				int j=i+1;
				Paragraph p5 = new Paragraph((j) + ".  " + str, clauseFont);
				p5.setAlignment(Paragraph.ALIGN_LEFT);
				document.add(p5);
			}
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
		      PdfPTable table2 = new PdfPTable(new float[] { 50, 50 });
		      table2.setTotalWidth(document.right(document.rightMargin())
				    - document.left(document.leftMargin()));
		      table2.getDefaultCell().setBorder(0);
			if (project.getPromoterName() != null) {
				table2.addCell(new Phrase("Promoter Name : " + project.getPromoterName(),smallBold));
			}
			if (chModel.getAllotteeName() != null) {
				table2.addCell(createCellByAlignRight("Allottee Name : " + chModel.getAllotteeName()));
			}
			else
			{
				table2.addCell(createCellByAlignRight(" "));
			}
			if (project.getPromoterType() != null) {
				table2.addCell(new Phrase("Promoter Type : " + project.getPromoterType(),smallBold));
			}
			if (chModel.getBlockName() != null) {
				table2.addCell(createCellByAlignRight("Block Name : " + chModel.getBlockName()));
			}
			
			if (project.getPromoteremailId() != null) {
				table2.addCell(new Phrase("Promoter EmailId : " + project.getPromoteremailId(),smallBold));
			}
			if (chModel.getFlatNumber() != null) {
				table2.addCell(createCellByAlignRight("Flat No : " + chModel.getFlatNumber()));
			}
			
			if (project.getPromoterMobileNo() != null) {
				table2.addCell(new Phrase("Promoter Mobile No. : " + project.getPromoterMobileNo(),smallBold));
			}
			if (chModel.getAllotteekyc() != null) {
				table2.addCell(createCellByAlignRight("KYC No. : " + chModel.getAllotteekyc()));
			}
			table2.writeSelectedRows(0, -1,
				    document.left(0),
				    table2.getTotalHeight() + document.bottom(document.bottomMargin()),
				    writer.getDirectContent());
			document.close();
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
				
				PdfContentByte canvas = writer.getDirectContent();
				
				Rectangle rect = new Rectangle(20, 20, 580, 830);
				rect.setBorder(Rectangle.BOX); //
				rect.setBorderColor(BaseColor.GRAY);
				rect.setBorderWidth(2);
				canvas.rectangle(rect);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
