package hibernate_intro;

import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {

        //TODO UNFINISHED - "PARENT ROW CANNOT BE UPDATED OR DELETED"

        /*Write a program that deletes a town, which name is given as an input. The program should delete all addresses
        that are in the given town. Print on the console the number of addresses that were deleted. Check the example for
        the output format.*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        Town town = entityManager
                .createQuery("FROM Town t WHERE name = :name", Town.class)
                .setParameter("name", townName).getSingleResult();

        int townId = town.getId();

        entityManager.remove(town);

        int deleteAddressesCount = entityManager
                .createQuery("FROM Address a WHERE town_id = :town_id", Address.class)
                .setParameter("town_id", townId)
                .getResultList().size();

        entityManager
                .createQuery("FROM Address a WHERE town_id = :town_id", Address.class)
                        .setParameter("town_id", townId)
                                .getResultStream()
                                        .forEach(entityManager::remove);

        System.out.printf("%s %s in %s deleted.\n",
                deleteAddressesCount,
                deleteAddressesCount == 1 ? "address" : "addresses",
                townName);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
