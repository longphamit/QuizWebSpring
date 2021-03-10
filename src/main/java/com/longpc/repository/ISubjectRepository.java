package com.longpc.repository;

import java.util.List;

import com.longpc.entity.SubjectEntity;

public interface ISubjectRepository {
	public List<SubjectEntity> findAll()throws Exception;
	public boolean createSubject(SubjectEntity subjectEntity) throws Exception;
}
