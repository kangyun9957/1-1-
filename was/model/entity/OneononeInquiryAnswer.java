package denall.admin.was.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_CUSTMCT_OOO_INQRY_ANS")
public class OneononeInquiryAnswer {

    @Id
    @Column(name = "OOO_INQRY_NO")
    private String oneononeInquiryNumber;

    @Column(name = "CN")
    private String content;

    @Column(name = "PROC_PRGM_ID")
    private String processProgramId;

    @Column(name = "RGST_PROCR_ID")
    private String registProcessorId;

    @Column(name = "RGST_PROC_DTM")
    private String registProcessDayandtime;

    @Column(name = "UPDT_PROCR_ID")
    private String updateProcessorId;

    @Column(name = "UPDT_PROC_DTM")
    private String updateProcessDayandtime;

    @Builder
    public OneononeInquiryAnswer(String oneononeInquiryNumber, String content, String processProgramId, String registProcessorId, String registProcessDayandtime) {
        this.oneononeInquiryNumber = oneononeInquiryNumber;
        this.content = content;
        this.processProgramId = processProgramId;
        this.registProcessorId = registProcessorId;
        this.registProcessDayandtime = registProcessDayandtime;
    }

}
