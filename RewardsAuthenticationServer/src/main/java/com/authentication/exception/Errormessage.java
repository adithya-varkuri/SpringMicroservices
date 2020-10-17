package com.authentication.exception;

import java.util.Date;

public class Errormessage {

	private Date timestamp;
	private String msg;

	public Errormessage() {

	}

	public Errormessage(Date timestamp, String msg) {
		this.msg = msg;
		this.timestamp = timestamp;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
