package University_systemDB;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends Person{
    @Column(name = "average_grade")
    private Float averageGrade;

    @Basic
    private Integer attendance;

    @ManyToMany(mappedBy = "students", targetEntity = Course.class)
    private Set<Course> courses;

    public Student() {}

    public Student(String firstName, String lastName, String phoneNumber, Float averageGrade, Integer attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }

    public Float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }
}
