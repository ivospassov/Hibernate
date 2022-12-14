package SalesDB;

import GringottsDB.WizardDeposit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Main_02 {
    public static void main(String[] args) {

        /*Create a database for storing data about sales using the Code First approach. The database should have the following tables:
            · product (id, name, quantity, price)
            · customer (id, name, email, credit_card_number)
            · store_location (id, location_name)
            · sale (id, product_id, customer_id, store_location_id, date)
            The relationships between the tables are as follows:
            · Sale has one product and a product can be sold in many sales
            · Sale has one customer and a customer can participate in many sales
            · Sale has one store location and one store location can have many sales*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Customer customer = new Customer("John", "2189474628");
        Product product = new Product("TV", 1, new BigDecimal("2300"));
        StoreLocation storeLocation = new StoreLocation("Technopolis");
        Set<Product> products = new HashSet<>();
        products.add(product);
        Sale sale = new Sale(customer, products, storeLocation, LocalDateTime.now());

        entityManager.persist(customer);
        entityManager.persist(product);
        entityManager.persist(storeLocation);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
