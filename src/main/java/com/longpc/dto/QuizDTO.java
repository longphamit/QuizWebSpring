package com.longpc.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QuizDTO implements Serializable{
	private String id;
	private String name;
	private Date fromDate;
	private Date toDate;
	private long timeTake;
	private int numQuestion;
	private String subjectId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public long getTimeTake() {
		return timeTake;
	}
	public void setTimeTake(long timeTake) {
		this.timeTake = timeTake;
	}
	public int getNumQuestion() {
		return numQuestion;
	}
	public void setNumQuestion(int numQuestion) {
		this.numQuestion = numQuestion;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
