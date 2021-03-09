package com.longpc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblQuizDo")
public class QuizDoEntity {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="do_by")
	private String doBy;
	@Column(name="create_date")
	private Date create_date;
	@Column(name="quiz_id")
	private String quizId;
	@Column(name="score")
	private double score;
	@Column(name="right_correct")
	private int rightCorrect;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDoBy() {
		return doBy;
	}
	public void setDoBy(String doBy) {
		this.doBy = doBy;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getRightCorrect() {
		return rightCorrect;
	}
	public void setRightCorrect(int rightCorrect) {
		this.rightCorrect = rightCorrect;
	}
	
}
