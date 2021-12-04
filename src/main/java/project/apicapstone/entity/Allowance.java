package project.apicapstone.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.apicapstone.common.entity.BaseEntity;
import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_allowance")
public class Allowance  {
    @Id
    @Column
    private String allowanceId;
    @Column
    private String allowanceName;
    @Column
    private String type;

    // relationship emply - dependant 1-N
    @ManyToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    @JoinColumn(name = "contract_id")
    private Contract contract;

}
