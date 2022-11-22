package denall.admin.was.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_CUSTMCT_NMEM_INQRY")
@Getter
@NoArgsConstructor
public class NomemberInquiry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="CUSTM_INQRY_NO")
    private String customerInquiryNumber;

    @Column(name="WRTR_NM")
    private String writerName;

    @Column(name="WRTR_EML")
    private String writerEmail;

    @Column(name="WRTR_TLNO")
    private String writerTelephonenumber;

    @Column(name="WRTR_HP_NO")
    private String writerHandphoneNumber;

    @Column(name="WRTR_CNTCPL")
    private String writerContactplace;

    @Column(name="INDV_INFO_AGRE_YN")
    private String individualInformationAgreementYesorno;

    @Column(name="PROC_PRGM_ID")
    private String processProgamId;

    @Column(name="RGST_PROCR_ID")
    private String registProcessorId;

    @Column(name="RGST_PROC_DTM")
    private String registProcessDayandtime;

    @Column(name="UPDT_PROCR_ID")
    private String updateProcessorId;

    @Column(name="UPDT_PROC_DTM")
    private String updateProcessDayandtime;

    @OneToOne(mappedBy = "nomemberInquiry")
    private OneononeInquiry oneononeInquiry;


}
