package com.longpc.repository.impl;






import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longpc.entity.UserEntity;
import com.longpc.repository.IUserRepository;

@Repository
public class UserRepository implements IUserRepository {
	@Autowired
	private SessionFactory sessionFactory;
	public UserEntity findById(String email,String password) throws Exception{
		Transaction transaction=null;
		String hql="From UserEntity where email =:email and password =:password";
		Session session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		try {
			
			Query<UserEntity> query=session.createQuery(hql,UserEntity.class);
			query.setParameter("email",email);
			query.setParameter("password",password);
			UserEntity userEntity= query.getSingleResult();
			transaction.commit();
			return userEntity;
		}catch (Exception e) {
			if(transaction!=null&& e instanceof NoResultException==false) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
	public UserEntity createUser(UserEntity userEntity) throws Exception{
		Transaction transaction=null;
		Session session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		try {
			session.save(userEntity);
			transaction.commit();
			return userEntity;
		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			throw e;
		}finally {
			session.close();
		}
	}
}
