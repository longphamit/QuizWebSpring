package com.longpc.service;


import java.util.List;
import java.util.Map;

import com.longpc.dto.QuestionDTO;
import com.longpc.dto.SearchQuestionDTO;

public interface IQuestionService {
	public boolean createQuestion(QuestionDTO questionDTO) throws Exception;
	public List<QuestionDTO> search(SearchQuestionDTO searchQuestionDTO,int offset) throws Exception;
	public List<QuestionDTO> search(String quizId ) throws Exception;
	public long countAll(SearchQuestionDTO searchQuestionDTO) throws Exception;
}
