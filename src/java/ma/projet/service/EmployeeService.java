/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Employee;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class EmployeeService implements IDao<Employee>{
    
    @Override
    public boolean create(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employee o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employee getById(int id) {
        Employee employee  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employee  = (Employee) session.get(Employee.class, id);
        session.getTransaction().commit();
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List <Employee> employees = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employees  = session.createQuery("from Employee").list();
        session.getTransaction().commit();
        return employees;
    }
    
    
    public List<Object[]> nbemployee(){
        List<Object[]> employees = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employees  = session.createQuery("select count(m.employee), m.employee from Employee m group by m.employee").list();
        session.getTransaction().commit();
        return employees;
    }
    
   
    
    
}
