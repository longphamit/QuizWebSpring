package com.longpc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longpc.dto.SubjectDTO;
import com.longpc.entity.SubjectEntity;
import com.longpc.repository.ISubjectRepository;
import com.longpc.service.ISubjectService;

@Service
public class SubjectService implements ISubjectService {
	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ISubjectRepository subjectRepository;
	public List<SubjectDTO> findAll() throws Exception {
		List<SubjectEntity> listResultEntities= subjectRepository.findAll();
		List<SubjectDTO> listResultDTOs=new ArrayList<SubjectDTO>();
		if(listResultEntities==null) {
			return null;
		}
		for(SubjectEntity e:listResultEntities) {
			listResultDTOs.add(modelMapper.map(e,SubjectDTO.class));
		}
		return listResultDTOs;
	}

}
