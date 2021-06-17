package com.moroccangeek.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moroccangeek.springdemo.entity.Customer;

@Repository
public class CustomerDAOImp implements CustomerDAO {

	//need to inject the Session Factory, wich was defined as a bean in XML file
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Create a Query using HQL
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", 
											Customer.class);
		
		//execute Query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer c) {
		//get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		//save Customer in the session
		session.saveOrUpdate(c);
	}

	@Override
	public Customer getCustomer(int id) {
		//get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Customer.class, id);
	}

	@Override
	public void deleteCustomer(int id) {
		//get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		Customer c = session.get(Customer.class, id);
		
		session.delete(c);

		/*@SuppressWarnings("unchecked")
		Query<Customer> theQuery = 
				session.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", id);
		
		theQuery.executeUpdate();*/
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		//get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Query<Customer> theQuery = 
				session.createQuery("from Customer where lower(firstName) like :theSearchName"
						+ " OR lower(lastName) like :theSearchName");
		
		theQuery.setParameter("theSearchName", "%"+theSearchName+"%");
		
		return theQuery.getResultList();
	}
}