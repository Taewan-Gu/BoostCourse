package com.ntscorp.intern.reservation.model;

import java.time.LocalDateTime;

public class FileInfo {
	private String fileName;
	private String saveFileName;
	private String contentType;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public FileInfo(String fileName, String saveFileName, String contentType) {
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.contentType = contentType;
		LocalDateTime currentDateTime = LocalDateTime.now();
		this.createDate = currentDateTime;
		this.modifyDate = currentDateTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "FileInfo [fileName=" + fileName + ", saveFileName=" + saveFileName + ", contentType=" + contentType
			+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}