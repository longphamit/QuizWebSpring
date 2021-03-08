package com.longpc.dto;

import java.io.Serializable;

public class MessageDTO implements Serializable{
	private boolean status;
	private String content;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
