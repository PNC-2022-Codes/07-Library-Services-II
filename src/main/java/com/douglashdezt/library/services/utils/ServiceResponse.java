package com.douglashdezt.library.services.utils;

public class ServiceResponse<Type> {
	private boolean status;
	private Type data;
	
	public ServiceResponse(boolean status, Type data) {
		super();
		this.status = status;
		this.data = data;
	}

	public ServiceResponse(boolean status) {
		super();
		this.status = status;
	}

	public ServiceResponse(Type data) {
		super();
		this.data = data;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Type getData() {
		return data;
	}

	public void setData(Type data) {
		this.data = data;
	}
}
