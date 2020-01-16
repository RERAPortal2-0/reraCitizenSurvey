package in.gov.rera.citizen.survey.common.model;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.stereotype.Component;
@Component
public class DmsModel implements Serializable {
private static final long serialVersionUID = 1463124430214748765L;
	
	private Long docId;//input parameter
	
	private Long documentId;

	private String folderId;

	private Integer version;

	private String fileName;

	private String caption;

	private String docinfo;

	private String mimeType;

	private String documentName;

	private String documentType;

	private String documentStatus;

	private Calendar uploadedOn;

	private Integer totalPages;
	private String uid;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDocinfo() {
		return docinfo;
	}

	public void setDocinfo(String docinfo) {
		this.docinfo = docinfo;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public Calendar getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Calendar uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
    
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	
	public Long getDocId() {
		return docId;
	}
}
