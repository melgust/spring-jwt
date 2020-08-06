package com.example.demo.utils;

import java.util.List;

public class ApiResponse {

	private int status;
	private String message;
	private List<?> data;
	private String singleValue;

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getData() {
		return this.data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getSingleValue() {
		return this.singleValue;
	}

	public void setSingleValue(String singleValue) {
		this.singleValue = singleValue;
	}

}