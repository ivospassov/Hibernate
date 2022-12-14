package hibernate_intro;

import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {

        /*Extract all employees from the Research and Development department. Order them by salary (in ascending order),
        then by id (in ascending order). Print only their first name, last name, department name and salary.*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        //Find the id of the "Research and Development" department
        Department departmentRnD = entityManager.createQuery("FROM Department d WHERE name = :name ", Department.class)
                .setParameter("name", "Research and Development").getSingleResult();

        Query selectByDepartment = entityManager.createQuery("FROM Employee e " +
                "WHERE department_id = :department_id " +
                "ORDER BY e.salary, e.id", Employee.class)
                .setParameter("department_id", departmentRnD.getId());

        List<Employee> employees = selectByDepartment.getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s from Research and Development - $%.2f\n",
                    employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
