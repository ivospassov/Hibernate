package hibernate_intro;

import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class GetEmployeeWithProject {
    public static void main(String[] args) {

        /*Get an employee by his/her id. Print only his/her first name, last name, job title and projects (only their names).
        The projects should be ordered by name (ascending). The output should be printed in the format given in the example.*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        int searchedId = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager
                .createQuery("FROM Employee e WHERE id = :id", Employee.class)
                .setParameter("id", searchedId)
                .getSingleResult();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(String.format("%s %s - %s", employee.getFirstName(), employee.getLastName(), employee.getJobTitle()))
                .append(System.lineSeparator());

        Set<Project> projects = new HashSet<>(employee.getProjects());
        Set<String> projectNames = new TreeSet<>();

        for (Project project : projects) {
            projectNames.add(project.getName());
        }

        for (String nameOfProject : projectNames) {
            stringBuilder
                    .append(String.format("      %s", nameOfProject))
                    .append(System.lineSeparator());
        }

        System.out.println(stringBuilder);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
