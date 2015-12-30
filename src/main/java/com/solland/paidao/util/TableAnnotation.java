package com.solland.paidao.util;

import java.io.Serializable;

public class TableAnnotation implements Serializable {
	private static final long serialVersionUID = 1L;
	//id
	private String ORDINAL_POSITION ; 
	//名字
	private String COLUMN_NAME ;
	//注释
	private String COLUMN_COMMENT ;
	public String getORDINAL_POSITION() {
		return ORDINAL_POSITION;
	}
	public void setORDINAL_POSITION(String oRDINALPOSITION) {
		ORDINAL_POSITION = oRDINALPOSITION;
	}
	public String getCOLUMN_NAME() {
		return COLUMN_NAME;
	}
	public void setCOLUMN_NAME(String cOLUMNNAME) {
		COLUMN_NAME = cOLUMNNAME;
	}
	public String getCOLUMN_COMMENT() {
		return COLUMN_COMMENT;
	}
	public void setCOLUMN_COMMENT(String cOLUMNCOMMENT) {
		COLUMN_COMMENT = cOLUMNCOMMENT;
	}
	
	
	
}
