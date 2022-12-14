package hibernate_intro;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingANewAddressAndUpdatingEmployee {
    public static void main(String[] args) {

        //Create a new address with text. Set that address to an employee with a last name, given as an input.

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        String addressText = scanner.nextLine();

        //Write a query to retrieve an employee based on the given last name
        Employee searchedEmployee = entityManager.createQuery("FROM Employee e WHERE last_name = :last_name", Employee.class)
                .setParameter("last_name", lastName)
                .getSingleResult();

        //Insert the new address into the database
        Address newAddress = new Address();
        newAddress.setText(addressText);
        entityManager.persist(newAddress);

        //Attach the new address onto the above employee
        searchedEmployee.setAddress(newAddress);
        entityManager.persist(searchedEmployee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
