package denall.admin.was.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "TB_CUSTMCT_OOO_INQRY")
@Getter
@NoArgsConstructor
public class OneononeInquiry implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OOO_INQRY_NO")
	private String oneononeInquiryNumber;

	@Column(name="CHNL_DVCD")
	private String channelDivisioncode;

	@Column(name="CUSTMCT_CTGR_CD")
	private String customercenterCategoryCode;

	@Column(name="RELT_OOO_INQRY_NO")
	private String reltoneononeInquiryNumber;

	@Column(name="CUSTMCT_PROC_STACD")
	private String customercenterProcessStatcode;

	@Column(name="WRTR_MEM_YN")
	private String writerMemberYesorno;

	@Column(name="TTL")
	private String title;

	@Column(name="CN")
	private String content;

	@Column(name="ATCH_FILE_YN")
	private String attachFileYesorno;

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

	@OneToOne
	@JoinColumn(name="OOO_INQRY_NO",insertable = false, updatable = false)
	private NomemberInquiry nomemberInquiry;








}
