package com.longpc.service;

import java.util.List;

import com.longpc.dto.QuizDTO;
import com.longpc.entity.QuizEntity;

public interface IQuizService {
	public boolean createQuiz(QuizDTO quizDTO) throws Exception;
	public List<QuizDTO> getQuizOfSubject(String subjectId) throws Exception;
	public QuizDTO findById(String id) throws Exception;
}
