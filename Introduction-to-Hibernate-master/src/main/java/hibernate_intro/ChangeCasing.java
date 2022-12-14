package hibernate_intro;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ChangeCasing {
    public static void main(String[] args) {

        /*Use the soft_uni database. Persist all towns from the database. Detach those whose name length is more than 5 symbols.
        Then transform the names of all attached towns to uppercase and save them to the database.*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query queryFromTown = entityManager.createQuery("FROM Town t WHERE char_length(name) < 5");

        List<Town> resultList = queryFromTown.getResultList();

        for (Town town : resultList) {
            String townName = town.getName().toUpperCase();
            town.setName(townName);
            entityManager.persist(town);
            System.out.println(town.toString());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
