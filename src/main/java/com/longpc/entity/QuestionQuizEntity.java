package com.longpc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tblQuestionQuiz")
public class QuestionQuizEntity implements Serializable{
	@Id
	@Column(name="quiz_id")
	private String quizId;
	@Id
	@Column(name="question_id")
	private String questionId;
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	
}
