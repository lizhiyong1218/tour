package com.lzy.tour.interceptor.exception;

public class NotLoginException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public NotLoginException(String msg){
		this.msg=msg;
	}
	
	public NotLoginException(){
		
	}
	
}
