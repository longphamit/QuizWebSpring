package com.longpc.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class QuizDoSessionDTO implements Serializable{
	private  String quizId;
	private int numQuestion;
	private List<QuestionDTO> listQuestion;
	private HashMap<String,QuestionDTO> hashQuestion;
	private String currentTimeDo;
	private QuizDTO quizDTO;
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
	public HashMap<String, QuestionDTO> getHashQuestion() {
		return hashQuestion;
	}
	public void setHashQuestion(HashMap<String, QuestionDTO> hashQuestion) {
		this.hashQuestion = hashQuestion;
	}
	public QuizDTO getQuizDTO() {
		return quizDTO;
	}
	public void setQuizDTO(QuizDTO quizDTO) {
		this.quizDTO = quizDTO;
	}
	
	
}