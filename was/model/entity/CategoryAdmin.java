package denall.admin.was.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TB_CUSTMCT_CTGR_ADMR")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@IdClass(CategoryAdminPk.class)
public class CategoryAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CUSTMCT_CTGR_CD")
    private String customercenterCategoryCode;

    @Id
    @Column(name = "CMPN_CD")
    private String companyCode;

    @Id
    @Column(name="ADMR_ID")
    private String administratorId;

    @Column(name="CHNL_DVCD")
    private String channelDivisioncode;

    @Column(name="PROC_PRGM_ID")
    private String processProgramId;

    @Column(name="RGST_PROCR_ID")
    private String registProcessorId;

    @Column(name="RGST_PROC_DTM")
    private String registProcessDayandtime;

    @Column(name="UPDT_PROCR_ID")
    private String updateProcessorId;

    @Column(name="UPDT_PROC_DTM")
    private String updateProcessDayandtime;





    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="CMPN_CD", insertable = false, updatable = false)
    @JoinColumn(name = "ADMR_ID", insertable = false, updatable = false)
    private EmployeeInterfaceReceive employeeInterfaceReceive;

}
