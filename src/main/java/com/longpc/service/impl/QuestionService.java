package com.longpc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longpc.dto.QuestionDTO;
import com.longpc.dto.QuizDTO;
import com.longpc.dto.SearchQuestionDTO;
import com.longpc.entity.QuestionEntity;
import com.longpc.repository.IQuestionRepository;
import com.longpc.repository.impl.QuestionRepository;
import com.longpc.service.IQuestionService;

@Service
public class QuestionService implements IQuestionService{
	@Autowired
	IQuestionRepository questionRepository;
	@Autowired
	ModelMapper modelMapper;
	public boolean createQuestion(QuestionDTO questionDTO) throws Exception {
		QuestionEntity entity=modelMapper.map(questionDTO, QuestionEntity.class);
		return questionRepository.createQuestion(entity);
	}
	public List<QuestionDTO> search() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public List<QuestionDTO> search(SearchQuestionDTO searchQuestionDTO,int offset) throws Exception {
		QuestionEntity entity=null;
		if(searchQuestionDTO!=null) {
			entity= modelMapper.map(searchQuestionDTO,QuestionEntity.class);
		}
		List<QuestionEntity> listEntities= questionRepository.search(entity,offset);
		if(listEntities!=null) {
			List<QuestionDTO> listDTOs= new ArrayList<QuestionDTO>();
			for(QuestionEntity e:listEntities) {
				listDTOs.add(modelMapper.map(e,QuestionDTO.class));
			}
			return listDTOs;
		}
		return null;
	}
	public long countAll(SearchQuestionDTO searchQuestionDTO) throws Exception {
		return questionRepository.countAll(modelMapper.map(searchQuestionDTO, QuestionEntity.class));
	}
	public List<QuestionDTO> search(String quizId) throws Exception {
		List<QuestionEntity> listEntities= questionRepository.search(quizId);
		if(listEntities!=null) {
			List<QuestionDTO> listDTOs= new ArrayList<QuestionDTO>();
			for(QuestionEntity e:listEntities) {
				listDTOs.add(modelMapper.map(e,QuestionDTO.class));
			}
			return listDTOs;
		}
		return null;
	}

}
