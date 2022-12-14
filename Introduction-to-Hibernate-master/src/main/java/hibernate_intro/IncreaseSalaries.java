package hibernate_intro;

import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {

        //TODO NOT FINISHED - WRONG OUTPUT ORDER

        /*Write a program that increases the salaries of all employees, who are in the Engineering, Tool Design,
        Marketing or Information Services departments by 12%. Then print the first name, the last name and the salary
        for the employees, whose salary was increased.*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        String engineeringDepartment = "Engineering";
        String toolDesignDepartment = "Tool Design";
        String marketingDepartment = "Marketing";
        String informationServicesDepartment = "Information Services";

        List<String> departments = new ArrayList<>();
        departments.add(engineeringDepartment);
        departments.add(toolDesignDepartment);
        departments.add(marketingDepartment);
        departments.add(informationServicesDepartment);

        List<Employee> extractAllEmployees = entityManager
                .createQuery("FROM Employee e", Employee.class)
                .getResultList();

        extractAllEmployees
                .stream()
                .filter(employee -> departments.contains(employee.getDepartment().getName()))
                .forEach(employee -> {
                    BigDecimal updatedSalary = employee.getSalary().multiply(BigDecimal.valueOf(1.12));
                    employee.setSalary(updatedSalary);

                    entityManager.persist(employee);

                    System.out.printf("%s %s ($%.2f)\n",
                            employee.getFirstName(),
                            employee.getLastName(),
                            updatedSalary);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
