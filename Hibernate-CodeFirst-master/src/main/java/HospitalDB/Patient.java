package HospitalDB;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Basic
    private String picture;

    @Column(name = "has_medical_insurance", nullable = false)
    private Boolean hasMedicalInsurance;

    @ManyToMany
    @JoinTable(name = "patients_medicament",
    joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<PrescribedMedicament> medicament;

    @OneToMany(mappedBy = "patient", targetEntity = Diagnose.class)
    private Set<Diagnose> diagnose;

    @OneToMany(mappedBy = "patient", targetEntity = Visitation.class)
    private Set<Visitation> visitations;

    public Patient() {}

    public Patient(String firstName, String lastName, String email, LocalDate dateOfBirth, String picture,
                   Boolean hasMedicalInsurance, Set<PrescribedMedicament> medicament, Set<Diagnose> diagnose, Set<Visitation> visitations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.hasMedicalInsurance = hasMedicalInsurance;
        this.medicament = medicament;
        this.diagnose = diagnose;
        this.visitations = visitations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(Boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }

    public Set<PrescribedMedicament> getMedicament() {
        return medicament;
    }

    public void setMedicament(Set<PrescribedMedicament> medicament) {
        this.medicament = medicament;
    }

    public Set<Visitation> getVisitation() {
        return visitations;
    }

    public void setVisitation(Set<Visitation> visitation) {
        this.visitations = visitation;
    }

    public Set<Diagnose> getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Set<Diagnose> diagnose) {
        this.diagnose = diagnose;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }
}
