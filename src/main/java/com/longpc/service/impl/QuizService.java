package com.longpc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longpc.dto.QuizDTO;
import com.longpc.entity.QuizEntity;
import com.longpc.repository.IQuizRepository;
import com.longpc.service.IQuizService;

@Service
public class QuizService implements IQuizService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	IQuizRepository quizRepository;
	public boolean createQuiz(QuizDTO quizDTO) throws Exception {
		return quizRepository.createQuiz(modelMapper.map(quizDTO, QuizEntity.class));
	}
	public List<QuizDTO> getQuizOfSubject(String subjectId) throws Exception{
		List<QuizEntity> listEntities= quizRepository.getQuizOfSubject(subjectId);
		List<QuizDTO> listDTOs= new ArrayList<QuizDTO>();
		for(QuizEntity entity:listEntities) {
			listDTOs.add(modelMapper.map(entity,QuizDTO.class));
		}
		return listDTOs;
	}
	public QuizDTO findById(String id) throws Exception {
		QuizEntity quizEntity= quizRepository.findById(id);
		if(quizEntity!=null) {
			return modelMapper.map(quizEntity,QuizDTO.class);
		}
		return null;
	}
}
