/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import ma.projet.beans.Employee;
import ma.projet.beans.Service;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ServiceService implements IDao<Service> {

    @Override
    public boolean create(Service o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Service o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Service o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Service getById(int id) {
        Service service = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        service = (Service) session.get(Service.class, id);
        session.getTransaction().commit();
        return service;
    }

    @Override
    public List<Service> getAll() {
        List<Service> services = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services = session.createQuery("from Service").list();
        session.getTransaction().commit();
        return services;
    }

    public List<Object[]> nbservice() {
        List<Object[]> services = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services = session.createQuery("select count(s.service), s.service from Service s ").list();
        session.getTransaction().commit();
        return services;
    }

    public List<Employee> getManagersByService(int serviceId) {
        List<Employee> managers = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            // Assuming there is a 'Service' entity with a 'List<Employee>' property named 'managers'
            Service service = (Service) session.get(Service.class, serviceId);

            if (service != null) {
                // Access the managers associated with the service
                managers = service.getEmployees();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            // Handle exceptions as needed
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

        return managers;
    }

    public List<Employee> getEmployeesByManager(int managerId) {
        List<Employee> employees = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            // Assuming there is an 'Employee' entity with a 'List<Employee>' property named 'subordinates'
            Employee manager = (Employee) session.get(Employee.class, managerId);

            if (manager != null) {
                // Access the employees associated with the manager
                employees = manager.getEmployees();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            // Handle exceptions as needed
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

        return employees;
    }

}
