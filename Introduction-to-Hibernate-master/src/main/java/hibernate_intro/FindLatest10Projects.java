package hibernate_intro;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class FindLatest10Projects {
    public static void main(String[] args) {

        /*Write a program that prints the last 10 started projects. Print their name, description, start and
        end date and sort them by name lexicographically. For the output, check the format from the example.*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager
                .createQuery("FROM Project p ORDER BY id DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    String stringBuilder = "Project name: " + project.getName() +
                            System.lineSeparator() +
                            "        Project Description: " + project.getDescription() +
                            System.lineSeparator() +
                            "        Project Start Date: " + project.getStartDate() +
                            System.lineSeparator() +
                            "        Project End Date: " + project.getStartDate();

                    System.out.println(stringBuilder);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
