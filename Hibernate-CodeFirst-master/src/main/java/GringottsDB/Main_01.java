package GringottsDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main_01 {
    public static void main(String[] args) {

        /*Your task is to create table wizard_deposits using the Code First approach. The table should contain the following fields:
            · id – Primary Key (number in range [1, 231-1].
            · first_name – Text field with max length of 50 symbols.
            · last_name - Text field with max length of 60 symbols. Required
            · notes – Text field with max length of 1000 symbols
            · age – Required
            · magic_wand_creator – Text field with max length of 100 symbols
            · magic_wand_size – Number in range [1, 215-1]
            · deposit_group - Text field with max length of 20 symbols
            · deposit_start_date – Date and time field
            · deposit_amount – Floating point number field
            · deposit_interest - Floating point number field
            · deposit_charge - Floating point number field
            · deposit_expiration_date – Date and time field
            · is_deposit_expired – Boolean field*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        WizardDeposit wizardDeposit = new WizardDeposit("Harry", "Potter",
                "Hermione, here is the magic deposit you asked me for",
                21, "Hogwarts Bank", (short)100, "Magic group", LocalDateTime.now(), new BigDecimal("1000"),
                new BigDecimal("1.50"), new BigDecimal("0.10"), LocalDateTime.of(2023, 12, 31, 23, 59),
                false);
        entityManager.persist(wizardDeposit);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
