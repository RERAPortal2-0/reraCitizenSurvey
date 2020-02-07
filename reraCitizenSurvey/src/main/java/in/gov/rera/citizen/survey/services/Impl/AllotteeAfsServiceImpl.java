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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
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
		response.setHeader("Content-Disposition", "attachment;filename="+"AFS Document.pdf");
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
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
					
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
			codeQrImage.setAbsolutePosition(480f, 660f);
			codeQrImage.scaleAbsolute(85, 80);
			
			//codeQrImage.scaleAbsolute(90, 90);
			//codeQrImage.setAlignment(Image.ALIGN_RIGHT);
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
				str = str.replace("&nbsp;"," ");
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
			/*
			 * table2.writeSelectedRows(0, -1, document.left(0), table2.getTotalHeight() +
			 * document.bottom(document.bottomMargin()), writer.getDirectContent());
			 */
			document.add(table2);
			
			document.close();
			writer.close();
		} catch (Exception e) {
		}
	}

	
	
	
	
	
	@Override
	public void generateNoticeOne(ProjectAfsModel model, HttpServletResponse response, Long projetId, Long kycId,
			String property, byte[] bt) {
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		document.setMargins(40, 40, 40, 40);
        this.reraLogo=bt;
	       Font marFont = FontFactory.getFont("static/font/ARIALUNI.TTF",BaseFont.IDENTITY_H,true);
		try {
			
			Format f = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = f.format(new Date());
			ProjectRegistrationModel project = new ProjectRegistrationModel();
			project = RestTamplateUtility.projectDtlP(projetId, property);
			Optional.of(project).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename="+"AFS Document.pdf");
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new GreyBorder());
			document.open();
			Image image = Image.getInstance(AllotteeAfsServiceImpl.reraLogo);
			 image.setAbsolutePosition(42f, 720f);
		     image.scaleAbsolute(85, 80);
			document.add(image);
			
			Paragraph h1 = new Paragraph("ગુજરાત રીયલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી", marFont);
			Paragraph h3 = new Paragraph("ચોથો માળ, સહયોગ સંકુલ,", marFont);
			Paragraph  h4 = new Paragraph("સેકટર-૧૧, ગાંધીનગર – ૩૮૨૦૧૦", marFont);
			Paragraph  h5 = new Paragraph("ટેલીફોન નંબર:- (૦૭૯)૨૩૨-૫૮૬૫૯", marFont);
			Paragraph  h6 = new Paragraph("વેબ સાઈટ:- https://gujrera.gujarat.gov.in", marFont);
			h1.setAlignment(Paragraph.ALIGN_RIGHT);
			h3.setAlignment(Paragraph.ALIGN_RIGHT);
			h4.setAlignment(Paragraph.ALIGN_RIGHT);
			h5.setAlignment(Paragraph.ALIGN_RIGHT);
			h6.setAlignment(Paragraph.ALIGN_RIGHT);
			
			document.add(h1);
			document.add(h3);
			document.add(h4);
			document.add(h5);
			document.add(h6);
			document.add(Chunk.NEWLINE);
			
			Paragraph p11= new Paragraph("ક્રમાંક:-ગુજરેરા/વહટ/વસુલાત/                                   /૨૦		       				તા.                        /     /૨૦",marFont);
			document.add(p11);
			
			 PdfContentByte contentByte = writer.getDirectContent();
		        Rectangle rectangleRegNo = new Rectangle(60, 632, 550, 610);// (L,,width,)
				rectangleRegNo.setBorder(Rectangle.BOX);
				contentByte.setColorStroke(BaseColor.BLACK);
				rectangleRegNo.setBorderWidth(1);
				contentByte.rectangle(rectangleRegNo);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				Paragraph p20= new Paragraph("ગુજરાત જમીન મહેસુલ કાયદા કલમ ૧૫૨ મુજબની નોટીસ",marFont);
				p20.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p20);
		
			document.add(Chunk.NEWLINE);
			Paragraph p12= new Paragraph("વિષય:- સચિવશ્રી ગુજરેરા ગાંધીનગરની બાકી નીકળતી રકમ રૂ.  <Penalty Amount> /- તથા સરચાર્જ \r\n" + 
					"         રકમ રૂ. 		 /- કુલ રકમ 		/- ભરવા બાબત.\r\n" + 
					"\r\n" + 
					"સંદર્ભ:- સુઓ મોટો ફરીયાદ નં.  ___<Complaint Number>__  તા.    /      /૨૦___    તથા                                             હુકમ ક્રમાંક: ",marFont);
			document.add(p12);
			document.add(Chunk.NEWLINE);
			
			Paragraph p13= new Paragraph("આથી તમોને જમીન મહેસુલ કાયદાની કલમ ૧૫૨ મુજબની નોટીસ આપતા જણાવવાનું કે, તમોએ ઉપરોક્ત વિષયમાં દર્શાવેલ રકમ જે તે ખાતામાં ભરપાઇ કફ્રેલ નથી જેથી આ રકમ વસુલ કરવા તે ખાતા તરફથી વસુલ કરવા હુકમ થઇ આવેલ હોઇ સખ્તાઇના ભારે ઇલાજો લેવા અત્રેની કચેરીને ફરજ થઇ પડેલ છે. સખ્તાઇના ભારે ઇલાજોમાં  જમીન મહેસુલ કાયદા કલમ ૧૫૪ મુજબની તમારી જંગમ મિલકત ટાંચમાં લઇ હરાજી કરી વસુલ કરવામાં આવેશે. અથવા જમીન મહેસુલ કાયદા કલમ ૧૫૫ મુજબ સ્થાવર મિલકત મિલકત ટાંચમાં લઇ હરાજી કરવામાં આવશે. ",marFont);
			document.add(p13);
			document.add(Chunk.NEWLINE);
			
			Paragraph p14= new Paragraph("ઉપરોક્ત સખ્તાઇ ના પગલા આ કચેરીને લેવા ન પડે એ સારૂ આ નોટીસ મળેથી દિન-૭ માં ઓન લાઇન ચલણથી જમા કરાવી દેવા અગર નાણા ભરપાઇ કરેલ હોય પહોંચ અથવા ચલણ રજુ કરશો આ અંગે તમારે કોઇ પણ રજુઆત કરવાની હોય તો નીચે જણાવેલ તારીખે પુરાવા સાથે અત્રેની કચેરીમાં હાજર રહેવા નોંધ લેશો. ",marFont);
			document.add(p14);
			document.add(Chunk.NEWLINE);
			
			Paragraph p15= new Paragraph("નોંધ : રૂબરૂ આવતી વખતે આ નોટીસ સાથે લેતા આવવી તેમજ સરચાર્જની રકમ અત્રેની કચેરીમાં રૂબરૂ લાવવાની રહેશે અથવા તેટલી રકમનો માન્ય બેંકનો ડ્રાફ્ટ થી મોકલી આપવાનો રહેશે. ",marFont);
			document.add(p15);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Paragraph p16= new Paragraph("                 મામલતદાર\r\n" + 
					" ગુજરાત રીઅલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી\r\n" + 
					"                  ગાંધીનગર\r\n" + 
					"રજી.પો.એ.ડી.\r\n" + 
					"પ્રતિ,\r\n" + 
					"શ્રી\r\n" + 
					"પ્રમોટર નામ...<Promoter Name>.............................\r\n" + 
					"પ્રમોટર એડ્રેસ...<Promoter address with pincode>............\r\n" + 
					"ઇ-મેઇલ:- .<Email address>....................... ",marFont);
			document.add(p16);
			
			
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









	@Override
	public void generateNoticeTwo(ProjectAfsModel model, HttpServletResponse response, Long projetId, Long kycId,
			String property, byte[] bt) {
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		document.setMargins(40, 40, 40, 40);
        this.reraLogo=bt;
	       Font marFont = FontFactory.getFont("static/font/ARIALUNI.TTF",BaseFont.IDENTITY_H,true);
		try {
			
			Format f = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = f.format(new Date());
			ProjectRegistrationModel project = new ProjectRegistrationModel();
			project = RestTamplateUtility.projectDtlP(projetId, property);
			Optional.of(project).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename="+"AFS Document.pdf");
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new GreyBorder());
			document.open();
			Image image = Image.getInstance(AllotteeAfsServiceImpl.reraLogo);
			 image.setAbsolutePosition(42f, 720f);
		     image.scaleAbsolute(85, 80);
			document.add(image);
			
			
			
			Paragraph h1 = new Paragraph("ગુજરાત રીયલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી", marFont);
			
			Paragraph h3 = new Paragraph("ચોથો માળ, સહયોગ સંકુલ,", marFont);
			Paragraph  h4 = new Paragraph("સેકટર-૧૧, ગાંધીનગર – ૩૮૨૦૧૦", marFont);
			Paragraph  h5 = new Paragraph("ટેલીફોન નંબર:- (૦૭૯)૨૩૨-૫૮૬૫૯", marFont);
			Paragraph  h6 = new Paragraph("વેબ સાઈટ:- https://gujrera.gujarat.gov.in", marFont);
			
			h1.setAlignment(Paragraph.ALIGN_RIGHT);
			h3.setAlignment(Paragraph.ALIGN_RIGHT);
			h4.setAlignment(Paragraph.ALIGN_RIGHT);
			h5.setAlignment(Paragraph.ALIGN_RIGHT);
			h6.setAlignment(Paragraph.ALIGN_RIGHT);
			
			document.add(h1);
			document.add(h3);
			document.add(h4);
			document.add(h5);
			document.add(h6);
			document.add(Chunk.NEWLINE);
			
			Paragraph p11= new Paragraph("ક્રમાંક:-ગુજરેરા/વહટ/વસુલાત/                                   /૨૦		       				તા.                        /     /૨૦",marFont);
			document.add(p11);
			
			 PdfContentByte contentByte = writer.getDirectContent();
		        Rectangle rectangleRegNo = new Rectangle(60, 632, 550, 610);// (L,,width,)
				rectangleRegNo.setBorder(Rectangle.BOX);
				contentByte.setColorStroke(BaseColor.BLACK);
				rectangleRegNo.setBorderWidth(1);
				contentByte.rectangle(rectangleRegNo);
				document.add(Chunk.NEWLINE);
				document.add(Chunk.NEWLINE);
				Paragraph p20= new Paragraph("ગુજરાત જમીન મહેસુલ કાયદા કલમ ૧૫૪ મુજબની નોટીસ",marFont);
				p20.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(p20);
		
			document.add(Chunk.NEWLINE);
			Paragraph p12= new Paragraph("વિષય:- સચિવશ્રી ગુજરેરા ગાંધીનગરની બાકી નીકળતી રકમ રૂ. <Penalty Amount> /- તથા સરચાર્જ \r\n" + 
					"         રકમ રૂ. 		 /- કુલ રકમ 		/- ભરવા બાબત.\r\n" + 
					"\r\n" + 
					"સંદર્ભ:- સુઓ મોટો ફરીયાદ નં. __<Complaint No>    તા.    /      /૨૦___    તથા                                             હુકમ ક્રમાંક: 				તા.     /    /૨૦___",marFont);
			document.add(p12);
			document.add(Chunk.NEWLINE);
			
			Paragraph p13= new Paragraph("આથી તમોને જમીન મહેસુલ કાયદાની કલમ ૧૫૪,૧૫૫ નીચે નોટીસ આપતા જણાવવાનું કે, તમોએ ઉપરોક્ત વિષયની બાકી વસુલાત અન્વયે અત્રેની કચેરીથી તમોને જમીન મહેસુલ કાયદા કલમ ૧૫૨ મુજબની નોટીસ તા.   /    /૨૦___ થી આપવામાં આવેલ છે. ",marFont);
			document.add(p13);
			document.add(Chunk.NEWLINE);
			
			Paragraph p14= new Paragraph("સબબ જમીન મહેસુલ કાયદા હેઠળ વસુલાતના સખ્તાઇના ઇલાજો લેવાની અત્રેની કચેરીને ના છુટકે ફરજ થઇ પડેલ છે. વસુલાતના સખ્તાઇના ભારે ઇલાજોમાં જમીન મહેસુલ કાયદાની કલમ- ૧૫૪ મુજબ તમારી જંગમ મિલ્કત ટાંચમાં લઇ હરાજી કરી રકમ વસુલાત કરવામાં આવશે અથવા જમીન મહેસુલ કાયદાની કલમ- ૧૫૫ મુજબ તમારી સ્થાવર મિલ્કત ટાંચમાં લઇ જાહેર હરાજી કરી રકમ વસુલ કરવામાં આવશે. તેમ જણાવેલ હતુ. તેમજ તા.   /     /૨૦____ ના રોજ રાખેલ જપ્તી સંદર્ભે આપેલ રૂબરૂ જવાબ મુજબ બાકી રહેતી રકમ સરચાર્જ સહિત તા.   /    /૨૦____ સુધી માં ભરપાઇ કરી આપવાની બાંહેધરી આપેલ હતી. તેમ છતા સદરહું રકમ ભરપાઇ કરવામાં નિષ્ફળ ગયેલ છો. અને યેનકેન પ્રકારે સદરહું રકમ ભરપાઇ કરવા માંગતા નથી તેમ સ્પષ્ટ પણે ફલીત થાય છે. ",marFont);
			document.add(p14);
			document.add(Chunk.NEWLINE);
			
			Paragraph p15= new Paragraph("સબબ આપની બાકી રહેતી દંડની રકમ સરચાર્જ સહિત તા.    /     /૨૦____ સુધીમાં ભરપાઇ કરવા જણાવવામાં આવે છે. જો તેમ કરવામાં કસુર થશે તો ત્યાર પછી કોઇ પણ સમયે લેન્ડ રેવન્યુ કોડ.૧૫૪ મુજબ સુર્યોદયથી સુર્યાસ્ત સુધીમાં જંગમ મિલ્કત ટાંચમાં લઇ જાહેર હરાજી કરવામાં આવશે. જેની ગંભીર નોંધ લેવી.",marFont);
			document.add(p15);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Paragraph p16= new Paragraph("                 મામલતદાર\r\n" + 
					" ગુજરાત રીઅલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી\r\n" + 
					"                  ગાંધીનગર\r\n" + 
					"રજી.પો.એ.ડી.\r\n" + 
					"પ્રતિ,\r\n" + 
					"શ્રી\r\n" + 
					"પ્રમોટર નામ...<Promoter Name>.............................\r\n" + 
					"પ્રમોટર એડ્રેસ...<Promoter address with pincode>............\r\n" + 
					"ઇ-મેઇલ:- .<Email address>....................... ",marFont);
			document.add(p16);
			
			
			document.close();
			writer.close();
		} catch (Exception e) {
		}
		
	}

	@Override
	public void generateNoticeThree(ProjectAfsModel model, HttpServletResponse response, Long projetId, Long kycId,
			String property, byte[] bt) {
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		document.setMargins(40, 40, 40, 40);
        this.reraLogo=bt;
	       Font marFont = FontFactory.getFont("static/font/ARIALUNI.TTF",BaseFont.IDENTITY_H,true);
		try {
			
			Format f = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = f.format(new Date());
			ProjectRegistrationModel project = new ProjectRegistrationModel();
			project = RestTamplateUtility.projectDtlP(projetId, property);
			Optional.of(project).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename="+"AFS Document.pdf");
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new GreyBorder());
			document.open();
			Image image = Image.getInstance(AllotteeAfsServiceImpl.reraLogo);
			 image.setAbsolutePosition(42f, 720f);
		     image.scaleAbsolute(85, 80);
			document.add(image);
			
			
			
			Paragraph h1 = new Paragraph("ગુજરાત રીયલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી", marFont);
			
			Paragraph h3 = new Paragraph("ચોથો માળ, સહયોગ સંકુલ,", marFont);
			Paragraph  h4 = new Paragraph("સેકટર-૧૧, ગાંધીનગર – ૩૮૨૦૧૦", marFont);
			Paragraph  h5 = new Paragraph("ટેલીફોન નંબર:- (૦૭૯)૨૩૨-૫૮૬૫૯", marFont);
			Paragraph  h6 = new Paragraph("વેબ સાઈટ:- https://gujrera.gujarat.gov.in", marFont);
			
			h1.setAlignment(Paragraph.ALIGN_RIGHT);
			h3.setAlignment(Paragraph.ALIGN_RIGHT);
			h4.setAlignment(Paragraph.ALIGN_RIGHT);
			h5.setAlignment(Paragraph.ALIGN_RIGHT);
			h6.setAlignment(Paragraph.ALIGN_RIGHT);
			
			document.add(h1);
			document.add(h3);
			document.add(h4);
			document.add(h5);
			document.add(h6);
			document.add(Chunk.NEWLINE);
			
			Paragraph p11= new Paragraph("ક્રમાંક:-ગુજરેરા/વહટ/વસુલાત/                                   /૨૦		       				તા.                        /     /૨૦",marFont);
			document.add(p11);
			
		
		
			document.add(Chunk.NEWLINE);
			Paragraph p12= new Paragraph("વંચાણે લીધું : \r\n" + 
					"(૧) પેનલ્ટી ફોર ક્વાર્ટરલી રીટર્ન ડિફોલ્ટરની યાદી \r\n" + 
					"	(૨) આ કચેરીની નોટીસ ક્રમાંક : ગુજરેરા/વહટ/વસુલાત/	  /૨૦    તા.   /     /૨૦ \r\n" + 
					"	(૩) રીયલ એસ્ટેટ (વિનિયમ અને વિકાસ) અધિનિયમ, ૨૦૧૬ ની કલમ-૪૦ \r\n" + 
					"-:: હુકમ ::- \r\n" + 
					"",marFont);
			document.add(p12);
			document.add(Chunk.NEWLINE);
			
			Paragraph p13= new Paragraph("પ્રમોટરશ્રી ………<Name of Promoter>…… દ્વારા તેમના રીયલ એસ્ટેટ પ્રોજેક્ટ .......<Project name>.............. નું ધી રીયલ એસ્ટેટ (રેગ્યુલેશન એન્ડ ડેવલપમેન્ટ) એક્ટ, ૨૦૧૬ હેઠળ રજીસ્ટ્રેશન કરવામાં આવેલ છે. જેનો રજીસ્ટ્રેશન નંબર .......<Approval Number>.......... છે.  ",marFont);
			document.add(p13);
			document.add(Chunk.NEWLINE);
			
			Paragraph p14= new Paragraph("ધી રીયલ એસ્ટેટ (રેગ્યુલેશન એન્ડ ડેવલપમેન્ટ) એક્ટ, ૨૦૧૬ ની કલમ-૧૧(૧)(ઈ) અને ગુજરાત રીયલ એસ્ટેટ (રેગ્યુલેશન એન્ડ ડેવલપમેન્ટ) રૂલ્સ, ૨૦૧૬ ના રૂલ-૧૦(ડી) ની જોગવાઈઓ તથા ગુજરેરાના તા.૧૫/૦૧/૨૦૧૮ ના ઓર્ડર-૫ ની સૂચનાઓનો ભંગ કરતાં રીયલ એસ્ટેટ (રેગ્યુલેશન એન્ડ ડેવલપમેન્ટ) એક્ટની કલમ-૬૩ મુજબ કાર્યવાહી શરૂ કરી આ કચેરીના તા………<Judgement Date>……. ના હુકમ નં. .. ………<Judgement Number>……... અન્વયે પ્રમોટરને રૂ. ..<Penalty Amt>.... (અંકે રૂપિયા .........<in words>........) નો દંડ કરી દંડની રકમ હુકમ તારીખથી દિન-૩૦ માં અથવા પ્રોજેક્ટ પૂર્ણ થવાની અંતિમ તારીખ બેમાંથી જે વહેલાં હોય ત્યાર સુધીમાં ઓનલાઈન ભરવા આદેશ કરવામાં આવેલ હતો.",marFont);
			document.add(p14);
			document.add(Chunk.NEWLINE);
			
			Paragraph p15= new Paragraph("ઉપરોક્ત આદેશમાં નિર્દિષ્ટ કરેલ સમયમર્યાદામાં દંડની રકમ ભરપાઈ નહિ કરતાં આમુખ-(૧) માં મળેલ બાકીદારોની યાદીને ધ્યાને લઈ પ્રમોટર્સને બાકી દંડની રકમ નોટીસ મળ્યેથી દિન-૭ માં ઓનલાઈન ચલનથી જમા કરાવી અત્રે જાણ કરવા આમુખ-(૨) માં જણાવેલ રજી.એ.ડી. થી નોટીસ આપવામાં આવેલ હતી તથા તેઓએ આપેલ ઇ-મેઈલ એડ્રેસ પર પણ નોટીસ મોકલી આપવામાં આવેલ હતી. તેમ છતાં, બાકી રકમ ભરપાઈ નહિ કરતાં તેઓને ટેલિફોનિક સંદેશ દ્વારા પણ જાણ કરવામાં આવેલ હતી. .",marFont);
			document.add(p15);
			document.add(Chunk.NEWLINE);
			
			Paragraph p16= new Paragraph("ઉપરની વિગતે પ્રમોટર્સને બાકી દંડની રકમ ભરવા નોટીસ આપવા છતાં બાકી દંડની રકમ ભરપાઈ કરવા માંગતા નથી તેમ સ્પષ્ટપણે ફલિત થાય છે અને એક્ટ તથા નિયમોની જોગવાઈઓનો સરેઆમ ભંગ કરેલ હોવાનું માનવાને પૂરતું કારણ જણાઈ આવે છે. ",marFont);
			document.add(p16);
			document.add(Chunk.NEWLINE);
			
			Paragraph p17= new Paragraph("રીયલ એસ્ટેટ (વિનિયમ અને વિકાસ) અધિનિયમ, ૨૦૧૬ ની કલમ-૪૦ ની જોગવાઈ મુજબ જો કોઈ પ્રમોટર ઉપર લાદવામાં આવેલ વ્યાજ કે દંડ અથવા વળતર ચૂકવવામાં નિષ્ફળ નીવડે, તો એવા પ્રમોટર, એલોટી કે રીયલ એસ્ટેટ એજન્ટ પાસેથી જમીન મહેસૂલના લેણાંની રીતે કે નિયત કરાય તે રીતે તે વસુલ કરવાને પાત્ર રહેશે. ",marFont);
			document.add(p17);
			document.add(Chunk.NEWLINE);
			
			Paragraph p18= new Paragraph("ઉપરોક્ત સમગ્ર હકીકત તથા અધિનિયમ-૨૦૧૬ ની કલમ-૪૦ ધ્યાને લેતાં પ્રમોટર્સ દ્વારા રીયલ એસ્ટેટ પ્રોજેક્ટ .....<Project Name>... મોજે. .....<Moje>.... તા. ....<Taluka>..... જી. .....<District>.... ના સર્વે નં. .......<Survey No.>......, ટી.પી. સ્કીમ નં. ....<TP No,>..... ફા. પ્લોટ નં. ..<FP No,>.... ક્ષેત્રફળ .....<Total Land Area>..... ની જમીન ઉપર તૈયાર થઈ રહેલ હોઈ તે જમીનના રેવન્યુ રેકર્ડમાં ગુજરાત રીયલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી, ગાંધીનગરના રૂ.....<Penalty Amount>.......... (અંકે રૂપિયા ..........<Penalty amount in words>............) ના બોજા (ચાર્જ) ની નોંધ કરવા આથી હુકમ કરવામાં આવે છે. \r\n" + 
					"	વધુમાં, સદરહુ બોજાની નોંધ મામલતદારશ્રી, ……………………… એ રેવન્યુ રેકર્ડ કરી નં. ૬ ની નોંધ આ કચેરીને સત્વરે મોકલી આપવા જણાવવામાં આવે છે. ",marFont);
			document.add(p18);
			document.add(Chunk.NEWLINE);
			
			Paragraph p19= new Paragraph("સચિવ \r\n" + 
					"ગુજરાત  રીયલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી,\r\n" + 
					"ગાંધીનગર\r\n" + 
					"",marFont);
			p19.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p19);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Paragraph p20= new Paragraph("By R.P.A.D. \r\n" + 
					"પ્રતિ, \r\n" + 
					"પ્રમોટર નામ...<Promoter Name>.............................\r\n" + 
					"પ્રમોટર એડ્રેસ...<Promoter address with pincode>............\r\n" + 
					"ઇ-મેઇલ:- .<Email address>.......................",marFont);
			document.add(p20);
			
			
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			Paragraph p21= new Paragraph("નકલ રવાના : \r\n" + 
					"મામલતદારશ્રી, ………………………….., જી. .............................. તરફ ઉક્ત હુકમની નોંધ રેવન્યુ રેકર્ડમાં કરી અત્રે જાણ કરવા વિનંતી સહ. \r\n" + 
					"નકલ સવિનય રવાના : \r\n" + 
					"કલેક્ટરશ્રી, …………………. તરફ જાણ તથા જરૂરી કાર્યવાહી સારૂ. \r\n" + 
					"",marFont);
			document.add(p21);
			
			document.close();
			writer.close();
		} catch (Exception e) {
		}
		
		
	}

	@Override
	public void generateNoticeFour(ProjectAfsModel model, HttpServletResponse response, Long projetId, Long kycId,
			String property, byte[] bt) {
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		document.setMargins(40, 40, 40, 40);
        this.reraLogo=bt;
	       Font marFont = FontFactory.getFont("static/font/ARIALUNI.TTF",BaseFont.IDENTITY_H,true);
		try {
			
			Format f = new SimpleDateFormat("dd-MM-yyyy");
			String strDate = f.format(new Date());
			ProjectRegistrationModel project = new ProjectRegistrationModel();
			project = RestTamplateUtility.projectDtlP(projetId, property);
			Optional.of(project).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename="+"AFS Document.pdf");
			OutputStream out = response.getOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new GreyBorder());
			document.open();
			Image image = Image.getInstance(AllotteeAfsServiceImpl.reraLogo);
			 image.setAbsolutePosition(42f, 720f);
		     image.scaleAbsolute(85, 80);
			document.add(image);
			
			
			
			Paragraph h1 = new Paragraph("ગુજરાત રીયલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી", marFont);
			
			Paragraph h3 = new Paragraph("ચોથો માળ, સહયોગ સંકુલ,", marFont);
			Paragraph  h4 = new Paragraph("સેકટર-૧૧, ગાંધીનગર – ૩૮૨૦૧૦", marFont);
			Paragraph  h5 = new Paragraph("ટેલીફોન નંબર:- (૦૭૯)૨૩૨-૫૮૬૫૯", marFont);
			Paragraph  h6 = new Paragraph("વેબ સાઈટ:- https://gujrera.gujarat.gov.in", marFont);
			
			h1.setAlignment(Paragraph.ALIGN_RIGHT);
			h3.setAlignment(Paragraph.ALIGN_RIGHT);
			h4.setAlignment(Paragraph.ALIGN_RIGHT);
			h5.setAlignment(Paragraph.ALIGN_RIGHT);
			h6.setAlignment(Paragraph.ALIGN_RIGHT);
			
			document.add(h1);
			document.add(h3);
			document.add(h4);
			document.add(h5);
			document.add(h6);
			document.add(Chunk.NEWLINE);
			
			Paragraph p11= new Paragraph("ક્રમાંક:-ગુજરેરા/વહટ/વસુલાત/                                   /૨૦		       				તા.                        /     /૨૦",marFont);
			document.add(p11);
			
		
		
			document.add(Chunk.NEWLINE);
			Paragraph p12= new Paragraph("નોટીસ",marFont);
			document.add(p12);
			p12.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(Chunk.NEWLINE);
			
			Paragraph p13= new Paragraph("આથી પ્રમોટર્સ/ડેવલપર્સશ્રી …………………<Name of Promoter>………………………………ને રીયલ એસ્ટેટ (નિયમન અને વિકાસ) અધિનયમ-૨૦૧૬ ની કલમ ૪૦(૧) તથા ગુજરાત રીયલ એસ્ટેટ (નિયમન અને વિકાસ) (રીયલ એસ્ટેટ નિયમનકારી સત્તા મંડળને લગતી બાબતો) નિયમો-૨૦૧૬ ના નિયમ-૯ ની જોગવાઇ અન્વયે આ નોટીસ આપી જણાવવામાં આવે છે કે ",marFont);
			document.add(p13);
			document.add(Chunk.NEWLINE);
			
			Paragraph p14= new Paragraph("રીયલ એસ્ટેટ (નિયમન અને વિકાસ) અધિનયમ-૨૦૧૬ ની કલમ-પ તથા ગુજરાત રીયલ એસ્ટેટ (નિયમ અને વિકાસ) સામાન્ય નિયમો-૨૦૧૭ ના નિયમ-૩ તથા ૬ ની જોગવાઇ મુજબ આપના પ્રોજેકટ .<name of project>......... મોજે .........<Moje>..................તાલુકો......<Taluka>......... જીલ્લો .......<District>.......... ના સર્વે/બ્લોક નંબર.......<Survey Number>........ ટી.પી સ્કીમ નંબર .......<TP No>......... ફાઇનલ પ્લોટ નંબર ........<FP No.>...... ક્ષેત્રફળ ...<Total land area>......ચો.મી ની જમીન ઉપર મંજુર કરી રેરા રજી.નં ………<RERA registration number>………. તા…<Date of Registration>……………….થી નોધણી કરવામા આવેલ છે",marFont);
			document.add(p14);
			document.add(Chunk.NEWLINE);
			
			Paragraph p15= new Paragraph("સદરહું કાયદાની તથા નિયમોની જોગવાઇ મુજબ શરતોનું પાલન નહી કરતાં આ કચેરીના હુકમ નંબર.......< Judgement order number>.......................... તા......<Judgement Order Date>................. અન્વયે શરત ભંગ કરેલ હોવાનું સાબીત માની આપને /આપના પ્રોજેકટની કાર્યવાહી સંદર્ભે રુા ……<Penalty amount>……….. (અંકે રૂ. .........<Penalty amount in words>.........................................................)નો દંડ કરવામાં આવેલ છે અને સદરહું દંડનું ચલણ પણ ઓન લાઇન જનરેટ કરવામાં આવેલ છે.સદરહુ દંડની રકમ હુકમમાં જણાવ્યા મુજબ નિયત સમયમાં ભરપાઇ કરેલ નથી જે ઘણી ગંભીર બાબત છે અને ઓથોરીટીના હુકમનો અનાદર કરવામાં આવેલ છે તેમ માનવાને પુરતુ કારણ છે.",marFont);
			document.add(p15);
			document.add(Chunk.NEWLINE);
			
			Paragraph p16= new Paragraph("સબબ આપની બાકી રકમ આ નોટીસ મળેથી દિન-૭ માં ઓન લાઇન ચલણથી જમા કરાવી અત્રે જાણ કરવા નોંધ લેશો. જો તેમ કરવામાં કસુર થશે તો અધિનિયમની કલમ-૪૦ અથવા નિયમોના નિયમ-૯ અન્વયે સદરહું રકમ જમીન મહેસુલ ની બાકી રકમ ગણી ગુજરાત જમીન મહેસુલ.સંહિતા-૧૮૭૯ ની જોગવાઇઓ મુજબ વસુલાતની કાર્યવાહી કરવામાં આવશે. તેમજ સદરહું રકમના બોજાની નોંધ રેવન્યુ રેકર્ડમાં કરાવવા આગળની કાર્યવાહી કરવામાં આવશે ત્યાર બાદ આપની કોઇ રજુઆત ધ્યાને લેવામાં આવશે નહી. જેની નોંધ લેવી ",marFont);
			document.add(p16);
			document.add(Chunk.NEWLINE);
			
			
			document.add(Chunk.NEWLINE);
			
	
			Paragraph p20= new Paragraph("...<Digital Sign>..\r\n" + 
					"      સચિવ\r\n" + 
					"By R.P.A.D.                                                                                ગુજરાત રીયલ એસ્ટેટ રેગ્યુલેટરી ઓથોરીટી\r\n" + 
					"                                                                                        ગાંધીનગર\r\n" + 
					"પ્રતિ,\r\n" + 
					"શ્રી\r\n" + 
					"પ્રમોટર નામ...<Promoter Name>.............................\r\n" + 
					"પ્રમોટર એડ્રેસ...<Promoter address with pincode>............\r\n" + 
					"ઇ-મેઇલ:- .<Email address>.......................\r\n" + 
					"",marFont);
			document.add(p20);
			
			
			
			
			document.close();
			writer.close();
		} catch (Exception e) {
		}
		
	}
}
