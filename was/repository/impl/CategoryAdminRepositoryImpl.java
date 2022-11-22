package denall.admin.was.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.RelationalPath;
import com.querydsl.sql.SQLQueryFactory;
import denall.admin.was.model.dto.CategoryAdminDto;
import denall.admin.was.model.entity.CategoryAdmin;
import denall.admin.was.repository.CategoryAdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import onf.data.jpa.querydsl.QueryDslUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static denall.admin.was.model.entity.QCategoryAdmin.categoryAdmin;
import static denall.admin.was.model.entity.QEmployeeInterfaceReceive.employeeInterfaceReceive;
import static denall.admin.was.model.entity.QOneononeInquiry.oneononeInquiry;
import static denall.admin.was.repository.impl.OneononeInquiryRepositoryUtil.inServiceTypes;

@Slf4j
@Repository("categoryAdminRepository")
@RequiredArgsConstructor
public class CategoryAdminRepositoryImpl implements CategoryAdminRepository {

    private final SQLQueryFactory sqlQueryFactory;

    private final JPAQueryFactory jpaQueryFactory;

    public Long save(CategoryAdmin entity) {
        RelationalPath<CategoryAdmin> relationalPath = QueryDslUtils.asRelational(categoryAdmin);

        return sqlQueryFactory.insert(relationalPath)
                .values(entity.getCustomercenterCategoryCode(), entity.getCompanyCode(), entity.getAdministratorId(), entity.getChannelDivisioncode(), "/com/OneononeInquiry/saveCategoryList", entity.getRegistProcessorId(), entity.getRegistProcessDayandtime(), entity.getUpdateProcessorId(), entity.getUpdateProcessDayandtime())
                .execute();
    }

    public List<CategoryAdminDto> getCategoryAdminList(List<String> serviceTypes) {

        List<CategoryAdminDto> result = jpaQueryFactory
                .select(Projections.fields(CategoryAdminDto.class,
                        categoryAdmin.channelDivisioncode.as("serviceType"),
                        categoryAdmin.employeeInterfaceReceive.userName.as("manager"),
                        categoryAdmin.administratorId,
                        categoryAdmin.customercenterCategoryCode.as("categoryName"),
                        categoryAdmin.registProcessDayandtime,
                        Expressions.as(
                        JPAExpressions.select(oneononeInquiry.count())
                                .from(oneononeInquiry)
                                .where(oneononeInquiry.customercenterCategoryCode.eq(categoryAdmin.customercenterCategoryCode)),
                        "categoryCounts"))
                )
                .from(categoryAdmin)
                .join(categoryAdmin.employeeInterfaceReceive,employeeInterfaceReceive)
                .where(
                        inServiceTypes(serviceTypes)
                )
                .fetch();


        return result;
    }



    public Long modifyCategoryList(CategoryAdminDto categoryAdminDto) {
        return jpaQueryFactory
                .update(categoryAdmin)
                .set(categoryAdmin.customercenterCategoryCode,categoryAdminDto.getCategoryName())
                .set(categoryAdmin.administratorId,categoryAdminDto.getAdministratorId())
                .where(
                        categoryAdmin.administratorId.eq(categoryAdminDto.getPreviousAdministratorId()),
                        categoryAdmin.customercenterCategoryCode.eq(categoryAdminDto.getPreviousCategoryName()))
                .execute();
    }

    public Long deleteCategoryList(CategoryAdminDto categoryAdminDto) {
        return jpaQueryFactory
                .delete(categoryAdmin)
                .where(categoryAdmin.customercenterCategoryCode.eq(categoryAdminDto.getCategoryName()),
                        categoryAdmin.administratorId.eq(categoryAdminDto.getAdministratorId()))
                .execute();
    }

    public int checkBeforeSave(CategoryAdminDto categoryAdminDto){
        return jpaQueryFactory
                .selectFrom(categoryAdmin)
                .where(categoryAdmin.customercenterCategoryCode.eq(categoryAdminDto.getCategoryName()),
                        categoryAdmin.administratorId.eq(categoryAdminDto.getAdministratorId()),
                        categoryAdmin.companyCode.eq("))
                .fetch().size();


    }



}
