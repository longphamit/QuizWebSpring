package com.longpc.repository.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longpc.entity.QuestionDoEntity;
import com.longpc.entity.QuizDoEntity;
import com.longpc.repository.IQuizDoRepository;

@Repository
public class QuizDoRepository implements IQuizDoRepository {
	@Autowired
	SessionFactory sessionFactory;
	public boolean saveQuizDo(QuizDoEntity quizDoEntity,HashMap<String,QuestionDoEntity> hashQuestion) throws Exception{
		Session session= sessionFactory.openSession();
		Transaction transaction=session.getTransaction();
		try {
			transaction.begin();
			quizDoEntity.setId(UUID.randomUUID().toString());
			session.save(quizDoEntity);
			for(Map.Entry<String,QuestionDoEntity> entry:hashQuestion.entrySet()) {
				entry.getValue().setId(UUID.randomUUID().toString());
				entry.getValue().setQuizDoId(quizDoEntity.getId());
				session.save(entry.getValue());
				
			}
			transaction.commit();
			return true;
		}catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
		
		
	}
}
