package hibernate_intro;

import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {

        /*Write a program that finds the max salary for each department. Filter the departments, which max salaries
        are not in the range between 30000 and 70000.*/

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Department> departments = entityManager.createQuery("FROM Department d ", Department.class).getResultList();

        BigDecimal lowerBound = new BigDecimal("30000");
        BigDecimal upperBound = new BigDecimal("70000");
        for (Department department : departments) {
            String departmentName = department.getName();
            BigDecimal biggestSalary = department.getEmployees()
                    .stream()
                    .max(Comparator.comparing(Employee::getSalary))
                    .get()
                    .getSalary();

            if (biggestSalary.compareTo(lowerBound) < 0
                    || biggestSalary.compareTo(upperBound) > 0) {
                System.out.printf("%s %.2f\n", departmentName, biggestSalary);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
