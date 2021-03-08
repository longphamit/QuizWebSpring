package com.longpc.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblQuizes")
public class QuizEntity {
	@Id
	@Column
	private String id;
	@Column
	private String name;
	@Column(name="from_date")
	private Date fromDate;
	@Column(name="time_take")
	private long timeTake;
	@Column(name="to_date")
	private Date toDate;
	@Column(name="num_question")
	private int numQuestion;
	@Column(name="subject_id")
	private String subjectId;
	@Column(name="create_date")
	private Date createDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public long getTimeTake() {
		return timeTake;
	}
	public void setTimeTake(long timeTake) {
		this.timeTake = timeTake;
	}
	
}
