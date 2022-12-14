package hibernate_intro;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {

        //Write a program that gets the first name of all employees who have salary over 50 000.

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query from_employee = entityManager.createQuery("FROM Employee e");

        List<Employee> resultList = from_employee.getResultList();

        for (Employee employee : resultList) {
            BigDecimal salary = employee.getSalary();

            if (salary.compareTo(BigDecimal.valueOf(50000)) > 0) {
                System.out.println(employee.getFirstName());
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
