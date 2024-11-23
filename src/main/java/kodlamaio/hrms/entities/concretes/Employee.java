package kodlamaio.hrms.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;

    @Column(name="`firstName`",nullable=false)
    private String firstName;

    @Column(name="`lastName`",nullable=false)
    private String lastName;

    @Column(name="`nationalityId`",unique=true,nullable=false)
    private String nationalityId;

    @Column(name="`birthOfDate`",nullable=false)
    private Date birthOfDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="`userId`")
    private User user;

    public Employee(String firstName, String lastName, String nationalityId, Date birthOfDate, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalityId = nationalityId;
        this.birthOfDate = birthOfDate;
        this.user = user;
    }
}
