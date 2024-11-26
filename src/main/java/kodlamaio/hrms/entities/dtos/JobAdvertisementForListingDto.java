package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementForListingDto {
    private UUID id;
    private String companyName;
    private String jobPositionName;
    private int openPositionCount;
    private Date createDate;
    private Date lastApplyDate;
}
