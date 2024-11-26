package kodlamaio.hrms.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="Employers")
@AllArgsConstructor
@NoArgsConstructor
public class Employer {
    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="companyName", nullable=false)
    private String companyName;

    @Column(name="phoneNumber", nullable=false)
    private String phoneNumber;

    @Column(name="website", nullable = false)
    private String website;

    @Column(name="verifiedBySystem", nullable = false)
    private boolean verifiedBySystem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", unique = true, nullable = false)
    private User user;

    @OneToMany(mappedBy="employer",fetch = FetchType.LAZY)
    private List<JobAdvertisement> jobAdvertisements;


    public Employer(String companyName, String phoneNumber, String website, boolean verifiedBySystem) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.verifiedBySystem = verifiedBySystem;
    }

    public Employer(UUID id, String companyName, String phoneNumber, String website, boolean verifiedBySystem, User user) {
        this.id = id;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.verifiedBySystem = verifiedBySystem;
        this.user = user;
    }
}
