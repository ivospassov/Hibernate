package BillsPaymentSystemDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main_05 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        User user = new User("Michael", "Jordan", "mjordan@gmail.com", "asdmpaafs");
        CreditCard creditCard = new CreditCard(214122, user, "Visa", 12, 27);

        entityManager.persist(user);
        entityManager.persist(creditCard);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
