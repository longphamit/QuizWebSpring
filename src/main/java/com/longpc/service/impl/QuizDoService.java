package com.longpc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longpc.dto.QuestionDTO;
import com.longpc.dto.QuizDoSessionDTO;
import com.longpc.entity.QuestionDoEntity;
import com.longpc.entity.QuizDoEntity;
import com.longpc.repository.IQuizDoRepository;
import com.longpc.service.IQuizDoService;

@Service
public class QuizDoService implements IQuizDoService {
	@Autowired
	IQuizDoRepository quizDoRepository;
	@Autowired
	ModelMapper modelMapper;
	public boolean saveQuizDo(QuizDoSessionDTO quizDoSessionDTO) throws Exception {
		QuizDoEntity quizDoEntity= modelMapper.map(quizDoSessionDTO,QuizDoEntity.class);
		HashMap<String,QuestionDoEntity>  hashQuestionDoEntity= new HashMap<String, QuestionDoEntity>();
		for(Map.Entry<String,QuestionDTO> entry:quizDoSessionDTO.getHashQuestion().entrySet()) {
			QuestionDoEntity questionDoEntity= modelMapper.map(entry.getValue(),QuestionDoEntity.class);
			questionDoEntity.setQuizDoId(quizDoSessionDTO.getQuizId());
			hashQuestionDoEntity.put(entry.getKey(),questionDoEntity);
		}
		quizDoRepository.saveQuizDo(quizDoEntity, hashQuestionDoEntity);
		return true;
	}
}
