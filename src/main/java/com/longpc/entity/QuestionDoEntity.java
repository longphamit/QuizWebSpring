package com.longpc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblQuestionDo")
public class QuestionDoEntity {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="question_id")
	private String questionId;
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
	@Column(name="subject_id")
	private String subjectId;
	@Column(name="choose_answer")
	private String chooseAnswer;
	@Column(name="quiz_do_id")
	private String quizDoId;
	
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
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getChooseAnswer() {
		return chooseAnswer;
	}
	public void setChooseAnswer(String chooseAnswer) {
		this.chooseAnswer = chooseAnswer;
	}
	public String getQuizDoId() {
		return quizDoId;
	}
	public void setQuizDoId(String quizDoId) {
		this.quizDoId = quizDoId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
