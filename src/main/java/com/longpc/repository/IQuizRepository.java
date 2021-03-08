package com.longpc.repository;

import java.util.List;

import com.longpc.entity.QuizEntity;

public interface IQuizRepository {
	public boolean createQuiz(QuizEntity quizEntity) throws Exception;
	public List<QuizEntity> getQuizOfSubject(String subjectId) throws Exception;
	public QuizEntity findById(String id) throws Exception;
}
