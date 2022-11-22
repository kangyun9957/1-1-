package denall.admin.was.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "TB_EMP_IR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(EmployeeInterfaceReceivePk.class)
public class EmployeeInterfaceReceive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CMPN_CD")
    private String companyCode;

    @Id
    @Column(name="EMP_NO")
    private String employeeNumber;

    @Column(name="EHR_ORG_CD")
    private String enterprisehumanresourceOrganizationCode;

    @Column(name="EFCT_STRT_DT")
    private String effectiveStartDate;

    @Column(name="USER_ID")
    private String userId;

    @Column(name="USER_NM")
    private String userName;

    @Column(name="LANG_CD")
    private String langguageCode;

    @Column(name="EHR_DTY_CD")
    private String enterprisehumanresourceDutyCode;

    @Column(name="EHR_JBGR_CD")
    private String enterprisehumanresourceJobgradeCode;

    @Column(name="EHR_JBGP_CD")
    private String enterprisehumanresourceJobgroupCode;

    @Column(name="USER_TLNO")
    private String userTelephonenumber;

    @Column(name="USER_HP_NO")
    private String userHandphoneNumber;

    @Column(name="USER_EML_ADDR")
    private String userEmailAddress;

    @Column(name="ENTR_DT")
    private String enterDate;

    @Column(name="RSGN_DT")
    private String resignDate;

    @Column(name="EFCT_END_DT")
    private String effectiveEndDate;

    @Column(name="USER_TYCD")
    private String userTypecode;

    @Column(name="USER_STACD")
    private String userStatcode;

    @Column(name="CCPS_YN")
    private String ccpsYn;

    @Column(name="RMK")
    private String remark;

    @Column(name="PROC_PRGM_ID")
    private String processProgramId;

    @Column(name="RGST_PROCR_ID")
    private String registProcessorrId;

    @Column(name="RGST_PROC_DTM")
    private String registProcessDayandtime;

    @Column(name="UPDT_PROCR_ID")
    private String updateProcessorId;

    @Column(name="UPDT_PROC_DTM")
    private String updateProcessDayandtime;

}
