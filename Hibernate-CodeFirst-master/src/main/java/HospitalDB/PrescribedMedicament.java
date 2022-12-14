package HospitalDB;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "prescribed_medicament")
public class PrescribedMedicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "medicament", targetEntity = Patient.class)
    private Set<Patient> patients;

    public PrescribedMedicament() {}

    public PrescribedMedicament(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
