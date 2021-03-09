package com.longpc.repository.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longpc.entity.QuestionEntity;
import com.longpc.repository.IQuestionRepository;
import com.longpc.util.PagingUtil;

@Repository
public class QuestionRepository implements IQuestionRepository{

	@Autowired
	SessionFactory sessionFactory;
	public boolean createQuestion(QuestionEntity questionEntity) throws Exception {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		try {
			questionEntity.setId(UUID.randomUUID().toString());
			questionEntity.setStatus("ACTIVE");
			session.save(questionEntity);
			transaction.commit();
			return true;
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	public List<QuestionEntity> search(QuestionEntity questionEntity,int offset) throws Exception {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		try {
			if(questionEntity!=null) {
				if(questionEntity.getStatus()!=null&&!questionEntity.getStatus().isEmpty()) {
					session.enableFilter("status").setParameter("status",questionEntity.getStatus());
				}
				if(questionEntity.getSubjectId()!=null&&!questionEntity.getSubjectId().isEmpty()) {
					session.enableFilter("subjectId").setParameter("subjectId",questionEntity.getSubjectId());
				}
				if(questionEntity.getContent()!=null&&!questionEntity.getContent().isEmpty()) {
					session.enableFilter("content").setParameter("content","%"+questionEntity.getContent()+"%");
				}
			}
			
			StringBuilder hql=new StringBuilder("from QuestionEntity ");
			Query <QuestionEntity> query= session.createQuery(hql.toString(),QuestionEntity.class);
			PagingUtil pagingUtil= new PagingUtil();
			query.setFirstResult(offset);
			query.setMaxResults(pagingUtil.SIZE);
			List<QuestionEntity>test= query.list();
			
			transaction.commit();
			return test;
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
	public List<QuestionEntity> search(String quizId) throws Exception {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		try {
			StringBuilder hql=new StringBuilder("select q from QuestionQuizEntity qq, QuestionEntity q where qq.quizId=:quizId and qq.questionId=q.id ");
			Query <QuestionEntity> query= session.createQuery(hql.toString(),QuestionEntity.class);
			query.setParameter("quizId", quizId);
			List<QuestionEntity>test= query.list();
			transaction.commit();
			return test;
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
	public long countAll(QuestionEntity questionEntity) throws Exception{
		Session session=sessionFactory.openSession();
		Transaction transaction=session.getTransaction();
		try {
			if(questionEntity!=null) {
				if(questionEntity.getStatus()!=null&&!questionEntity.getStatus().isEmpty()) {
					session.enableFilter("status").setParameter("status",questionEntity.getStatus());
				}
				if(questionEntity.getSubjectId()!=null&&!questionEntity.getSubjectId().isEmpty()) {
					session.enableFilter("subjectId").setParameter("subjectId",questionEntity.getSubjectId());
				}
				if(questionEntity.getContent()!=null&&!questionEntity.getContent().isEmpty()) {
					session.enableFilter("content").setParameter("content","%"+questionEntity.getContent()+"%");
				}
			}
			StringBuilder hql=new StringBuilder("select count (q.id) from QuestionEntity q");
			transaction.begin();
			long count=0;
			try {
				count =session.createQuery(hql.toString(),Long.class).getSingleResult();
			}catch (Exception e) {
				e.printStackTrace();
				count=0;
			}
			transaction.commit();
			return count;
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
