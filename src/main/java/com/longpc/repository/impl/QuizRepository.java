package com.longpc.repository.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longpc.entity.QuestionQuizEntity;
import com.longpc.entity.QuizEntity;
import com.longpc.repository.IQuizRepository;
@Repository
public class QuizRepository implements IQuizRepository {
	@Autowired
	SessionFactory sessionFactory;
	public boolean createQuiz(QuizEntity quizEntity) throws Exception{
		Session session= sessionFactory.openSession();
		String hqlGetListQuestionId="Select ques.id "
				+ "from QuestionEntity ques "
				+ "where ques.subjectId =:subjectId and ques.status='ACTIVE' "
				+ "order by NEWID()";
		Transaction transaction= session.beginTransaction();
		try {
			quizEntity.setId(UUID.randomUUID().toString());
			quizEntity.setCreateDate(new Date(System.currentTimeMillis()));
			session.save(quizEntity);
			Query<Object> query=session.createQuery(hqlGetListQuestionId);
			query.setMaxResults(quizEntity.getNumQuestion());
			query.setParameter("subjectId", quizEntity.getSubjectId());
			List<Object>listId= query.list();
			for(Object o:listId) {
				QuestionQuizEntity questionQuizEntity= new QuestionQuizEntity();
				questionQuizEntity.setQuizId(quizEntity.getId());
				questionQuizEntity.setQuestionId(o.toString());
				session.save(questionQuizEntity);
			}
			transaction.commit();
			return true;
		}catch(Exception e){
			if(transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	public List<QuizEntity> getQuizOfSubject(String subjectId) throws Exception{
		Session session=sessionFactory.openSession();
		Transaction transaction=session.getTransaction();
		String hql="from QuizEntity where subjectId = :subjectId";
		try {
			transaction.begin();
			Query<QuizEntity> query=session.createQuery(hql,QuizEntity.class);
			query.setParameter("subjectId", subjectId);
			List<QuizEntity> resultt= query.list();
			transaction.commit();
			return resultt;
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	public QuizEntity findById(String id) throws Exception {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.getTransaction();
		try {
			transaction.begin();
			QuizEntity quizEntity= session.find(QuizEntity.class, id);
			transaction.commit();
			return quizEntity;
		}catch(Exception e) {
			e.printStackTrace();
			if(transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
}
