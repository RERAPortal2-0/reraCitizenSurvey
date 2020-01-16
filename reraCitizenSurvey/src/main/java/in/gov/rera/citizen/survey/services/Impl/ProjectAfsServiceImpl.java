package in.gov.rera.citizen.survey.services.Impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.rtf.RTFEditorKit;
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
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import in.gov.rera.citizen.survey.common.RestTamplateUtility;
import in.gov.rera.citizen.survey.constants.ReraConstants;
import in.gov.rera.citizen.survey.dao.ProjectAfsDao;
import in.gov.rera.citizen.survey.model.AfsClauseModel;
import in.gov.rera.citizen.survey.model.ProjectAfsClauseModel;
import in.gov.rera.citizen.survey.model.ProjectAfsModel;
import in.gov.rera.citizen.survey.model.ProjectRegistrationModel;
import in.gov.rera.citizen.survey.services.AfsClauseService;
import in.gov.rera.citizen.survey.services.ProjectAfsService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:message/common.properties")
@Service
@Transactional
public class ProjectAfsServiceImpl implements ProjectAfsService {

	private static final Logger logger = LogManager.getLogger(ProjectAfsServiceImpl.class);
	@Autowired
	ProjectAfsDao afsDao;

	@Autowired
	AfsClauseService masterServ;

	@Value("${URL_PROJECT_REG}")
	String projectDtlUrl;

	@Autowired
	Environment env;

	@Override
	public ProjectAfsModel saveProjectAfs(ProjectAfsModel model) {
		return afsDao.save(model);
	}

	@Override
	public ProjectAfsModel findById(Long id) {
		return afsDao.findById(id).get();
	}

	@Override
	public List<ProjectAfsModel> findByProjectId(Long projectId) {
		List<ProjectAfsModel> list = new ArrayList<ProjectAfsModel>();
		List<ProjectAfsModel> latestlist = new ArrayList<ProjectAfsModel>();
		list = afsDao.findByProjectId(projectId);
		if (list.size() > 0) {
			for (ProjectAfsModel model : list) {
				if (model.getStatus().equals("NEW") || model.getStatus().equals(ReraConstants.PENDING_WITH_AUTH)
						|| model.getStatus().equals(ReraConstants.APPROVED)) {
					latestlist.add(model);
				}
			}
		} else {
			try {
				ProjectRegistrationModel project = RestTamplateUtility.projectDtlP(projectId, projectDtlUrl);
				Optional.of(project).orElseThrow(() -> new ResourceAccessException("NOT FOUND"));
				ProjectAfsModel model = new ProjectAfsModel();
				model.setProjectId(projectId);
				model.setProjectName(project.getProjectDetailsModel().getProjectName());
				model.setProjectType(project.getProjectDetailsModel().getProjectType());
				model.setPromoterEmailId(project.getPromoteremailId());
				model.setPromoterId(String.valueOf(project.getPromoterId()));
				model.setMobileNo(project.getPromoterMobileNo());
				model.setPromoterName(project.getPromoterName());
				model.setStatus(ReraConstants.NEW);
				List<AfsClauseModel> l = masterServ.findAll();
				List<ProjectAfsClauseModel> chList = new ArrayList<ProjectAfsClauseModel>();
				for (AfsClauseModel p : l) {
					ProjectAfsClauseModel chModel = new ProjectAfsClauseModel();
					chModel.setClauseName(p.getClauseName());
					chModel.setClauseCode(p.getClauseCode());
					chModel.setClauseDtl(p.getClauseDtl());
					chModel.setAction("");
					//chModel.setAuthorityStatus(ReraConstants.NEW);
					chList.add(chModel);
				}
				model.setAfsClauseList(chList);
				model = afsDao.save(model);
				latestlist.add(model);
			} catch (Exception e) {
			}
		}

		return latestlist;
	}

	@Override
	public void deleteById(Long id) {

	}

	@Override
	public List<ProjectAfsModel> findByStatus(String status) {
		return afsDao.findByStatus(status);
	}

	Document document=new Document();
	
	@Override
	public void generateProjeAfsPdf(ProjectAfsModel model, HttpServletResponse response,Long projetId, String property) {
             
		ProjectRegistrationModel project=new ProjectRegistrationModel();
		
		Document document = new Document();
              document.setPageSize(PageSize.A4);
      		  document.setMargins(40, 40, 40, 40);
              response.setContentType("application/pdf");
      		  response.setHeader("Content-Disposition", "attachment;filename=" + "Project-AFS" + ".pdf");
      		Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
      		Font clauseFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
      		Font font1 =      new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    		Font boldFont =   new Font(Font.FontFamily.TIMES_ROMAN, 9f, Font.BOLD);
    		
    		Font fontUL = new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.UNDERLINE); 
    		Font itlaicfont = new Font(Font.FontFamily.TIMES_ROMAN, 11f, Font.ITALIC); 
              try {
            	  project = RestTamplateUtility.projectDtlP(projetId,property);
            	  Optional.of(project).orElseThrow(() -> new ResourceAccessException(env.getProperty("NOT_FOUND")));
            	  OutputStream out = response.getOutputStream();
            	  PdfWriter writer = PdfWriter.getInstance(document, out);
            		writer.setPageEvent(new GreyBorder());
            		this.document=document;
            		document.open();
              Paragraph p1 = new Paragraph(project.getProjectDetailsModel().getProjectName(),font1);
              p1.setAlignment(Paragraph.ALIGN_CENTER);
              document.add(p1);
              document.add(Chunk.NEWLINE);
              if(project.getProjectDetailsModel().getStateName()!=null) {
                  Paragraph p5 = new Paragraph("State : "+project.getProjectDetailsModel().getStateName() ,normalFont);
                  p5.setAlignment(Paragraph.ALIGN_RIGHT);
                  document.add(p5);
                  }
              if(project.getProjectDetailsModel().getDistName()!=null) {
                  Paragraph p5 = new Paragraph("District : "+project.getProjectDetailsModel().getDistName() ,normalFont);
                  p5.setAlignment(Paragraph.ALIGN_RIGHT);
                  document.add(p5);
                  }
              if(project.getProjectDetailsModel().getPinCode()!=null) {
                  Paragraph p5 = new Paragraph("Pincode : "+project.getProjectDetailsModel().getPinCode() ,normalFont);
                  p5.setAlignment(Paragraph.ALIGN_RIGHT);
                  document.add(p5);
                  }
              if(project.getProjectDetailsModel().getProjectAddress()!=null) {
                  Paragraph p4 = new Paragraph("Address : "+project.getProjectDetailsModel().getProjectAddress() ,normalFont);
                  p4.setAlignment(Paragraph.ALIGN_RIGHT);
                  document.add(p4);
                  }
              if(project.getProjRegNo()!=null) {
                  Paragraph p2 = new Paragraph("Project REG. No : "+project.getProjRegNo() ,normalFont);
                  p2.setAlignment(Paragraph.ALIGN_RIGHT);
                  document.add(p2);
                  }
                  if(project.getProjectAckNo()!=null) {
                      Paragraph p3 = new Paragraph("Project ACK. No : "+project.getProjectAckNo() ,normalFont);
                      p3.setAlignment(Paragraph.ALIGN_RIGHT);
                      document.add(p3);
                      }
                  
                  document.add(Chunk.NEWLINE);
                  document.add(Chunk.NEWLINE);
              for(ProjectAfsClauseModel m:model.getAfsClauseList())
              {
            	  Paragraph p = new Paragraph(""+m.getClauseName() ,boldFont);
                  p.setAlignment(Paragraph.ALIGN_LEFT);
                  document.add(p);
                  String str = m.getClauseDtl();
                  str = str.replaceAll("\\<.*?\\>", "");
                  
                  Paragraph p5 = new Paragraph(""+str ,clauseFont);
                  p5.setAlignment(Paragraph.ALIGN_LEFT);
                  document.add(p5);
                  document.add(Chunk.NEWLINE);
              }
              
              document.add(Chunk.NEWLINE);
              document.add(Chunk.NEWLINE);
              if(project.getPromoterName()!=null) {
                  Paragraph p5 = new Paragraph("Promoter Name : "+project.getPromoterName() ,normalFont);
                  p5.setAlignment(Paragraph.ALIGN_LEFT);
                  document.add(p5);
                  }
              if(project.getPromoterType()!=null) {
                  Paragraph p5 = new Paragraph("Promoter Type : "+project.getPromoterType() ,normalFont);
                  p5.setAlignment(Paragraph.ALIGN_LEFT);
                  document.add(p5);
                  }
              if(project.getPromoteremailId()!=null) {
                  Paragraph p5 = new Paragraph("Promoter EmailId : "+project.getPromoteremailId() ,normalFont);
                  p5.setAlignment(Paragraph.ALIGN_LEFT);
                  document.add(p5);
                  }
              if(project.getPromoterMobileNo()!=null) {
                  Paragraph p5 = new Paragraph("Promoter Mobile No. : "+project.getPromoterMobileNo() ,normalFont);
                  p5.setAlignment(Paragraph.ALIGN_LEFT);
                  document.add(p5);
                  }
              document.add(Chunk.NEWLINE);
              document.add(Chunk.NEWLINE);
              String qrCodeText = project.getProjectAckNo()
  					+ ",\n"
  					+ project.getProjectDetailsModel().getProjectName()
  					+ ",\n"
  					+ project.getProjectDetailsModel().getProjectType();
  			BarcodeQRCode barcodeQRCode = new BarcodeQRCode(qrCodeText, 1000,
  					1000, null);
  			Image codeQrImage = barcodeQRCode.getImage();
  			codeQrImage.scaleAbsolute(90, 90);
              document.add(codeQrImage);
              document.close();
              writer.close();
              }
              catch(Exception e)
              {
            	  
              }
		
	}
	
	
	private Paragraph addPara(Paragraph pr, String cnt, Font font) throws Exception {
		if (pr == null) {
			pr = new Paragraph();
			pr.setAlignment(Paragraph.ALIGN_LEFT);
		}
		pr.add(new Chunk(cnt, font));
		return pr;

	}

	private void addPara(String cnt, Font font3) throws Exception {

		Paragraph pr = new Paragraph(cnt, font3);
		pr.setAlignment(Paragraph.ALIGN_LEFT);
		document.add(pr);

	}

	private void addParaRT(String cnt, Font font3) throws Exception {
		Paragraph pr = new Paragraph(cnt, font3);
		pr.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(pr);

	}

	private void addParaCenter(String cnt, Font font3) throws Exception {
		Paragraph pr = new Paragraph(cnt, font3);
		pr.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(pr);

	}

	private PdfPCell createCell(String cnt, Font font3) {
		Paragraph paragraph = new Paragraph();
		paragraph.setAlignment(Paragraph.ALIGN_LEFT);
		paragraph.add(new Chunk(cnt, font3));
		paragraph.setIndentationLeft(25);
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(Rectangle.BOX);
		return cell;
	}

	private void addLineBreak() throws Exception {
		document.add(Chunk.NEWLINE);

	}

}

class GreyBorder extends PdfPageEventHelper {

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		try {
			PdfContentByte canvas = writer.getDirectContentUnder();
			Rectangle rect = new Rectangle(20, 20, 580, 830);
			rect.setBorder(Rectangle.BOX); //
			rect.setBorderColor(new BaseColor(192, 0, 0));
			rect.setBorderColor(BaseColor.GRAY);
			rect.setBorderWidth(2);
			canvas.rectangle(rect);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
