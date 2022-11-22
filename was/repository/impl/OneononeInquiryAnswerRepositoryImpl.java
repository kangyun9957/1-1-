package denall.admin.was.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.SQLQueryFactory;
import denall.admin.was.model.dto.AnswerDto;
import denall.admin.was.model.entity.OneononeInquiryAnswer;
import denall.admin.was.repository.OneononeInquiryAnswerRepository;
import onf.data.jpa.querydsl.QueryDslUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static denall.admin.was.model.entity.QCategoryAdmin.categoryAdmin;
import static denall.admin.was.model.entity.QEmployeeInterfaceReceive.employeeInterfaceReceive;
import static denall.admin.was.model.entity.QOneononeInquiryAnswer.oneononeInquiryAnswer;
import static denall.admin.was.model.entity.QOneononeInquiry.oneononeInquiry
        ;
@Slf4j
@Repository("oneononeInquiryAnswerRepository")
@RequiredArgsConstructor
public class OneononeInquiryAnswerRepositoryImpl implements OneononeInquiryAnswerRepository {

    private final SQLQueryFactory sqlQueryFactory;

    private final JPAQueryFactory jpaQueryFactory;

    public Long save(OneononeInquiryAnswer entity) {
        RelationalPath<OneononeInquiryAnswer> relationalPath = QueryDslUtils.asRelational(oneononeInquiryAnswer);

        return sqlQueryFactory.insert(relationalPath)
                .values(entity.getOneononeInquiryNumber(), entity.getContent(), "/com/OneononeInquiry/addAnswer", entity.getRegistProcessorId(), entity.getRegistProcessDayandtime(), entity.getUpdateProcessorId(), entity.getUpdateProcessDayandtime())
                .execute();
    }

    public AnswerDto getAnswer(String oneononeInquiryNumber) {
        return jpaQueryFactory
                .select(Projections.fields(AnswerDto.class,
                        oneononeInquiryAnswer.registProcessDayandtime.as("answerDay"),
                        oneononeInquiryAnswer.updateProcessorId.as("modifyManagerId"),
                        employeeInterfaceReceive.userName.as("modifyManager"),
                        oneononeInquiryAnswer.updateProcessDayandtime.as("lastAnswerDay"),
                        oneononeInquiryAnswer.content.as("answerContent")
                ))
                .from(oneononeInquiryAnswer)
                .join(employeeInterfaceReceive)
                .on(employeeInterfaceReceive.employeeNumber.eq(oneononeInquiryAnswer.updateProcessorId))
                .where(oneononeInquiryAnswer.oneononeInquiryNumber.eq(oneononeInquiryNumber))
                .fetchOne();
    }



    public Long modifyCompleteAnswer(AnswerDto answerDto){
        return jpaQueryFactory
                .update(oneononeInquiryAnswer)
                .set(oneononeInquiryAnswer.updateProcessorId,answerDto.getAnswerManagerId())
                .set(oneononeInquiryAnswer.updateProcessDayandtime, LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .where(oneononeInquiryAnswer.oneononeInquiryNumber.eq(answerDto.getOneononeInquiryNumber()))
                .execute();
    }

    public Long modifyAnswerState(AnswerDto answerDto){

        return jpaQueryFactory
                .update(oneononeInquiry)
                .set(oneononeInquiry.customercenterProcessStatcode,"ST05")
                .where(oneononeInquiry.oneononeInquiryNumber.eq(answerDto.getOneononeInquiryNumber()))
                .execute();
    }
}
