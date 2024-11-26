package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementForAddDto {
    private String jobDescription;
    private double minSalary;
    private double maxSalary;
    private int openPositionCount;
    private Date lastApplyDate;
    private boolean isActive;
    private int cityId;
    private UUID jobPositionId;
    private UUID employerId;
}
