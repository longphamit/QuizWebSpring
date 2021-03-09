package com.longpc.repository;

import java.util.HashMap;

import com.longpc.entity.QuestionDoEntity;
import com.longpc.entity.QuizDoEntity;

public interface IQuizDoRepository {
	public boolean saveQuizDo(QuizDoEntity quizDoEntity,HashMap<String,QuestionDoEntity> hashQuestion) throws Exception;
}
