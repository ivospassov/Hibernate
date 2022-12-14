package HospitalDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Main_04 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        PrescribedMedicament medicament = new PrescribedMedicament("Vitamin C");
        Set<PrescribedMedicament> medicaments = new HashSet<>();
        medicaments.add(medicament);

        Set<Visitation> visitations = new HashSet<>();
        Visitation visitation = new Visitation(LocalDateTime.of(2020, 11, 12, 13, 44), "No more visitations needed.");
        visitations.add(visitation);

        Set<Diagnose> diagnoses = new HashSet<>();
        Diagnose diagnose = new Diagnose("High temperature", "Drink 4 tablets per day");
        diagnoses.add(diagnose);

        Patient patient = new Patient("Michael", "Jordon", "someEmail@gmail.com", LocalDate.of(1989, 12, 24),
                null, false, medicaments, diagnoses, visitations);

        entityManager.persist(medicament);
        entityManager.persist(visitation);
        entityManager.persist(diagnose);
        entityManager.persist(patient);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
