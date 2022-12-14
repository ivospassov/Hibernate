package hibernate_intro;

import entities.Employee;

import javax.persistence.*;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {

        //Use the soft_uni database. Write a program that checks if a given employee name is contained in the database.

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String[] searchedFullName = scanner.nextLine().split("\\s+");
        String firstName = searchedFullName[0];
        String lastName = searchedFullName[1];

        try {
            entityManager.createQuery("FROM Employee e WHERE first_name = :firstName " +
                            "AND last_name = :lastName", Employee.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .getSingleResult();
            System.out.println("yes");
        } catch (NoResultException exception) {
            System.out.println("no");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
