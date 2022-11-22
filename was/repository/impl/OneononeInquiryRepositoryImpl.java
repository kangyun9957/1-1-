package denall.admin.was.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import denall.admin.was.model.dto.AnswerDto;
import denall.admin.was.model.dto.OneononeFilterDto;
import denall.admin.was.model.dto.OneononeInquiryBoardDto;
import denall.admin.was.model.entity.*;
import denall.admin.was.repository.OneononeInquiryRepository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static denall.admin.was.model.entity.QCategoryAdmin.categoryAdmin;
import static denall.admin.was.model.entity.QEmployeeInterfaceReceive.employeeInterfaceReceive;
import static denall.admin.was.model.entity.QNomemberInquiry.nomemberInquiry;
import static denall.admin.was.model.entity.QOneononeInquiry.oneononeInquiry;
import static denall.admin.was.repository.impl.OneononeInquiryRepositoryUtil.*;
@Slf4j
@Repository("oneononeInquiry")
@RequiredArgsConstructor
public class OneononeInquiryRepositoryImpl implements OneononeInquiryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    QOneononeInquiry selfJoinInquiry = new QOneononeInquiry("selfJoinInquiry");

    public Page<OneononeInquiryBoardDto> getList(OneononeFilterDto oneononeFilterDto, Pageable pageable) {
        log.debug("#### Offset {}", (pageable.getPageNumber()-1)*pageable.getPageSize());
        log.debug("#### PageSize {}", pageable.getPageSize());

        List<OneononeInquiryBoardDto> result=jpaQueryFactory
                .select(Projections.fields(OneononeInquiryBoardDto.class,
                        oneononeInquiry.oneononeInquiryNumber,
                        oneononeInquiry.channelDivisioncode,
                        oneononeInquiry.customercenterCategoryCode,
                        oneononeInquiry.title,
                        oneononeInquiry.customercenterProcessStatcode,
                        oneononeInquiry.registProcessorId,
                        oneononeInquiry.registProcessDayandtime,
                        categoryAdmin.administratorId,
                        categoryAdmin.employeeInterfaceReceive.userName,
                        oneononeInquiry.nomemberInquiry.writerName,
                        Expressions.as(
                                JPAExpressions.select(oneononeInquiry.registProcessDayandtime)
                                        .from(selfJoinInquiry)
                                        .where( oneononeInquiry.reltoneononeInquiryNumber.isNotNull(),
                                                oneononeInquiry.oneononeInquiryNumber.eq(selfJoinInquiry.oneononeInquiryNumber)),
                                "lastAnswerDay")
                ))
                .distinct()
                .from(oneononeInquiry)
                .join(categoryAdmin)
                .on(oneononeInquiry.customercenterCategoryCode.eq(categoryAdmin.customercenterCategoryCode))
                .join(categoryAdmin.employeeInterfaceReceive,employeeInterfaceReceive)
                .join(oneononeInquiry.nomemberInquiry,nomemberInquiry)
                .where(

                        eqServiceType(oneononeFilterDto.getServiceType()),
                        inAnswerStates(oneononeFilterDto.getAnswerStateList()),
                        inCategoryTypes(oneononeFilterDto.getCategoryTypeList()),
                        containSearchCategory(oneononeFilterDto.getSearchCategory(), oneononeFilterDto.getSearchKeyword()),
                        betweenReferenceDay(oneononeFilterDto.getStartDay(),oneononeFilterDto.getEndDay())

                )
                // TODO 수정 필요
                //.orderBy(ReferenceDayFirst(oneononeFilterDto.getReferenceDay()),ReferenceDaySecond(oneononeFilterDto.getReferenceDay()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // TODO 카운트 쿼리 개선 필요
        int totalCount = jpaQueryFactory
                .select(Projections.fields(OneononeInquiryBoardDto.class,
                        oneononeInquiry.oneononeInquiryNumber,
                        oneononeInquiry.channelDivisioncode,
                        oneononeInquiry.customercenterCategoryCode,
                        oneononeInquiry.title,
                        oneononeInquiry.customercenterProcessStatcode,
                        oneononeInquiry.registProcessorId,
                        oneononeInquiry.registProcessDayandtime,
                        categoryAdmin.administratorId,
                        categoryAdmin.employeeInterfaceReceive.userName,
                        oneononeInquiry.nomemberInquiry.writerName,
                        Expressions.as(
                                JPAExpressions.select(oneononeInquiry.registProcessDayandtime)
                                        .from(selfJoinInquiry)
                                        .where( oneononeInquiry.reltoneononeInquiryNumber.isNotNull(),
                                                oneononeInquiry.oneononeInquiryNumber.eq(selfJoinInquiry.oneononeInquiryNumber)),
                                "lastAnswerDay")
                ))
                .distinct()
                .from(oneononeInquiry)
                .join(categoryAdmin)
                .on(oneononeInquiry.customercenterCategoryCode.eq(categoryAdmin.customercenterCategoryCode))
                .join(categoryAdmin.employeeInterfaceReceive,employeeInterfaceReceive)
                .join(oneononeInquiry.nomemberInquiry,nomemberInquiry)
                .where(

                        eqServiceType(oneononeFilterDto.getServiceType()),
                        inAnswerStates(oneononeFilterDto.getAnswerStateList()),
                        inCategoryTypes(oneononeFilterDto.getCategoryTypeList()),
                        containSearchCategory(oneononeFilterDto.getSearchCategory(), oneononeFilterDto.getSearchKeyword()),
                        betweenReferenceDay(oneononeFilterDto.getStartDay(),oneononeFilterDto.getEndDay())

                )
                .fetch()
                .size();

        return new PageImpl<>(result, pageable, totalCount);



    }

    public AnswerDto getInquiry(String oneononeInquiryNumber){

        return jpaQueryFactory
                .select(Projections.fields(AnswerDto.class,
                        oneononeInquiry.oneononeInquiryNumber,
                        oneononeInquiry.channelDivisioncode.as("serviceType"),
                        oneononeInquiry.registProcessorId,
                        oneononeInquiry.nomemberInquiry.writerEmail,
                        oneononeInquiry.content.as("inquiryContent"),
                        oneononeInquiry.registProcessDayandtime,
                        oneononeInquiry.customercenterCategoryCode.as("categoryType"),
                        oneononeInquiry.title.as("inquiryTitle"),
                        oneononeInquiry.customercenterProcessStatcode.as("answerState"),
                        categoryAdmin.employeeInterfaceReceive.userName.as("answerManager"),
                        categoryAdmin.administratorId.as("answerManagerId")))
                .from(oneononeInquiry)
                .join(oneononeInquiry.nomemberInquiry)
                .join(categoryAdmin)
                .on(oneononeInquiry.customercenterCategoryCode.eq(categoryAdmin.customercenterCategoryCode))
                .join(categoryAdmin.employeeInterfaceReceive,employeeInterfaceReceive)
                .where(oneononeInquiry.oneononeInquiryNumber.eq(oneononeInquiryNumber))
                .fetchOne();
    }





}
