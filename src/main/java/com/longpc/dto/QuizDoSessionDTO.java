package com.longpc.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class QuizDoSessionDTO implements Serializable{
	private  String quizId;
	private int numQuestion;
	private List<QuestionDTO> listQuestion;
	private LinkedHashMap<String,QuestionDTO> hashQuestion;
	private String currentTimeDo;
	private QuizDTO quizDTO;
	private double score;
	private long startTime;
	private String doBy;
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getDoBy() {
		return doBy;
	}
	public void setDoBy(String doBy) {
		this.doBy = doBy;
	}
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	
	public String getCurrentTimeDo() {
		return currentTimeDo;
	}
	public void setCurrentTimeDo(String currentTimeDo) {
		this.currentTimeDo = currentTimeDo;
	}
	public int getNumQuestion() {
		return numQuestion;
	}
	public void setNumQuestion(int numQuestion) {
		this.numQuestion = numQuestion;
	}
	public List<QuestionDTO> getListQuestion() {
		return listQuestion;
	}
	public void setListQuestion(List<QuestionDTO> listQuestion) {
		this.listQuestion = listQuestion;
	}
	
	public QuizDTO getQuizDTO() {
		return quizDTO;
	}
	public void setQuizDTO(QuizDTO quizDTO) {
		this.quizDTO = quizDTO;
	}
	public LinkedHashMap<String,QuestionDTO> getHashQuestion() {
		return hashQuestion;
	}
	public void setHashQuestion(LinkedHashMap<String,QuestionDTO> hashQuestion) {
		this.hashQuestion = hashQuestion;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	
}
