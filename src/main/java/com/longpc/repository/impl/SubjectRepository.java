package com.longpc.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longpc.entity.SubjectEntity;
import com.longpc.repository.ISubjectRepository;

@Repository
@Transactional
public class SubjectRepository implements ISubjectRepository {
	@Autowired
	SessionFactory sessionFactory;
	public List<SubjectEntity> findAll() throws Exception {
		String hql="select s from SubjectEntity s";
		Session session=sessionFactory.openSession();
		Transaction transaction=session.getTransaction();
		List<SubjectEntity> result=null;
		try {
			transaction.begin();
			result = session.createQuery(hql,SubjectEntity.class).list();
			transaction.commit();
			return result;// lay dung y chua			
		}catch (Exception e) {
			if(null!=transaction) {
				transaction.rollback();
			}
			throw e;
		}
		finally {
			session.close();
		}
		
	}

}
