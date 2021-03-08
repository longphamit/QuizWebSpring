package com.longpc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name="tblQuestionsResource")
@FilterDefs(value = { 
		@FilterDef(name="status",parameters = {@ParamDef(name="status", type = "string")}),
		@FilterDef(name="subjectId",parameters = {@ParamDef(name="subjectId",type = "string")} ),
		@FilterDef(name="content",parameters =  {@ParamDef(name="content",type = "string")})
		} )
@Filters(value = {
		@Filter(name="status",condition = "status=:status") ,
		@Filter(name="subjectId",condition = "subject_id=:subjectId") ,
		@Filter(name="content",condition = "question_content like :content") 
		
})
public class QuestionEntity {
	@Column(name="id")
	@Id
	private String id;
	@Column(name="question_content")
	private String content;
	@Column(name="answer_a")
	private String answerA;
	@Column(name="answer_b")
	private String answerB;
	@Column(name="answer_d")
	private String answerD;
	@Column(name="answer_c")
	private String answerC;
	@Column(name="right_answer")
	private String rightAnswer;
	@Column(name="status")
	private String status;
	@Column(name="subject_id")
	private String subjectId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
}
