package kodlamaio.hrms.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "jobAdvertisements")
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "jobDescription", nullable = false)
    private String jobDescription;

    @Column(name = "minSalary", nullable = false)
    private double minSalary;

    @Column(name = "maxSalary", nullable = false)
    private double maxSalary;

    @Column(name="openPositionCount",nullable=false)
    private int openPositionCount;

    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @Column(name = "lastApplyDate", nullable = false)
    private Date lastApplyDate;

    @Column(name = "isActive", nullable = false)
    private boolean isActive;

    @Column(name = "isApproved", nullable = false)
    private boolean isApproved;

    @Column(name = "cityId", nullable = false)
    private int cityId;

    @ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="jobPositionId")
    private JobPosition jobPosition;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "employerId")
    private Employer employer;

    public JobAdvertisement(String jobDescription,
                            double minSalary, double maxSalary,
                            int openPositionCount,
                            Date createDate, Date lastApplyDate,
                            boolean isActive, boolean isApproved,
                            int cityId,
                            JobPosition jobPosition,
                            Employer employer) {
        this.jobDescription = jobDescription;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.openPositionCount = openPositionCount;
        this.createDate = createDate;
        this.lastApplyDate = lastApplyDate;
        this.isActive = isActive;
        this.isApproved = isApproved;
        this.cityId = cityId;
        this.jobPosition = jobPosition;
        this.employer = employer;
    }

    @PrePersist
    public void prePersist() {
        if (this.createDate == null) {
            this.createDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        }
    }
}
