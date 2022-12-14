package University_systemDB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main_03 {
    public static void main(String[] args) {
        /*Your task is to create a database for a University System, using the Code First approach. In the database,
        we should keep information about students, teachers and courses. The database should have the following tables:
            · Student (id, first name, last name, phone number, average grade, attendance)
            · Teacher (id, first name, last name, phone number, email, salary per hour)
            · Course (id, name, description, start date, end date, credits)
        The relationships between the tables are as follows:
            · Each student can be enrolled in many courses and in each course many students can be enrolled
            · A teacher can teach in many courses but one course can be taught only by one teacher
            Use class hierarchy to reduce code duplication*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 12, 10);
        LocalTime startTime = LocalTime.of(17, 0, 0);
        LocalTime endTime = LocalTime.of(22, 0, 0);


        Student student1 = new Student("Michael", "Jordan", "+448885748321", 5.25f, 4);
        Student student2 = new Student("Philipp", "Johnson", "+40128482924", 5.83f, 1);
        Set<Student> students = new HashSet<>();
        students.add(student1);
        students.add(student2);

        Teacher teacher = new Teacher("Mischelle", "Boardeux", "+30846319853",
                "mboardeux@yahoo.com", new BigDecimal("3000"));

        Course course = new Course("Java DB", "Introduction to Java databases", LocalDateTime.of(startDate, startTime),
                LocalDateTime.of(endDate, endTime), 15, teacher, students);


        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(teacher);
        entityManager.persist(course);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
