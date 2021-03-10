package com.longpc.service;

import java.util.List;

import com.longpc.dto.SubjectDTO;

public interface ISubjectService {
	public List<SubjectDTO> findAll() throws Exception;
	public boolean createSubject(SubjectDTO subjectDTO) throws Exception;
}
