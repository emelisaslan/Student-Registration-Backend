package com.webapp.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private Object fieldValue;
	
	public StudentNotFoundException( String fieldName, Object fieldValue) {
		super( "Student not found with " + fieldName + ":" + fieldValue);
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
}
