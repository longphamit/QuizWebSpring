package com.longpc.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longpc.entity.QuestionEntity;

public interface IQuestionRepository {
	public boolean createQuestion(QuestionEntity questionEntity) throws Exception;
	public List<QuestionEntity> search(QuestionEntity questionEntity,int offset) throws Exception;
	public List<QuestionEntity> search(String quizId) throws Exception;
	public long countAll(QuestionEntity questionEntity) throws Exception;
}
