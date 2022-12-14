package hibernate_intro;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {

        /*Write a program that finds all employees, whose first name starts with a pattern given as an input from the console.
        Print their first and last names, their job title and salary in the format given in the example below.
            - Hint: The expected results of next exercises are with update of salaries in ex 10.*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();

        entityManager
                .createQuery("FROM Employee e WHERE first_name LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + "%")
                .getResultStream()
                .forEach(employee -> System.out.printf("%s %s - %s - ($%.2f)\n",
                        employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
