package com.potinga.spring.springboot.springboot_rest.dao;


import com.potinga.spring.springboot.springboot_rest.entity.Employee;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EployeeDaoImpl implements EmployeeDAO {
    @Autowired
    private EntityManager entityManager;

    @Override

    public List<Employee> getAllEmployees() {
        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("from Employee",
                Employee.class);
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query=session.createQuery("delete from Employee" + "where id =:employeeId");
        Query<Employee> query = session.createQuery("delete from Employee where id = :employeeId");

        query.setParameter("employeeId",id);
        query.executeUpdate();
    }
}
