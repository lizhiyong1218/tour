package com.lzy.tour.common;

import java.io.Serializable;

import com.lzy.tour.enums.ResponseStatusEnum;


public class ApiResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/*接口数据*/
	private Object data;
	/*接口消息*/
	private String msg;
	/*状态*/
	private ResponseStatusEnum status;
	
	public ApiResponse(ResponseStatusEnum status,String msg){
		this.status=status;
		this.msg=msg;
	}
	
	public ApiResponse(){
		
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ResponseStatusEnum getStatus() {
		return status;
	}
	public void setStatus(ResponseStatusEnum status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ApiResponse [data=" + data + ", msg=" + msg + ", status="
				+ status + "]";
	}

}
