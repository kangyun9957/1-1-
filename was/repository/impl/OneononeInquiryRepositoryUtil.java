package denall.admin.was.repository.impl;

import denall.admin.was.model.entity.QCategoryAdmin;
import denall.admin.was.model.entity.QOneononeInquiry;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class OneononeInquiryRepositoryUtil {

    static QOneononeInquiry oneononeInquiry = QOneononeInquiry.oneononeInquiry;
    static QCategoryAdmin categoryAdmin = QCategoryAdmin.categoryAdmin;

    public static BooleanExpression eqServiceType(String serviceType){
        if (StringUtils.equals("", serviceType)){
            return null;
        }
        return oneononeInquiry.channelDivisioncode.eq(serviceType);
    }

    public static BooleanExpression inServiceTypes(List<String> serviceTypes){
        if(serviceTypes.isEmpty()){
            return null;
        }
        return categoryAdmin.channelDivisioncode.in(serviceTypes);
    }

    public static BooleanExpression inCategoryTypes(List<String> categoryList){
        if(categoryList.isEmpty()){
            return null;
        }
        return oneononeInquiry.customercenterCategoryCode.in(categoryList);
    }

    public static BooleanExpression inAnswerStates(List<String> answerStateList){
        if(answerStateList.isEmpty()){
            return null;
        }
        return oneononeInquiry.customercenterProcessStatcode.in(answerStateList);
    }

    public static BooleanExpression containSearchCategory(String searchCategory,String searchKeyword){

        if(StringUtils.equals("SR01", searchCategory)){
            return oneononeInquiry.registProcessorId.contains(searchKeyword);
        }
        if(StringUtils.equals("SR21", searchCategory)){
            return oneononeInquiry.title.contains(searchKeyword);
        }
        return (oneononeInquiry.registProcessorId.contains(searchKeyword).or(oneononeInquiry.title.contains(searchKeyword)));
    }

    public static BooleanExpression betweenReferenceDay(String startDay, String endDay){

        if(!StringUtils.equals(startDay, "") && !StringUtils.equals(endDay,"")){
            return oneononeInquiry.registProcessDayandtime.between(startDay,endDay);
        }

        return null;

    }

    public static OrderSpecifier<String> ReferenceDayFirst(String referenceDay){

        StringPath aliasQuantity = Expressions.stringPath("lastAnswerDay");

        if(StringUtils.equals("최종문의일",referenceDay)){
            return aliasQuantity.desc();
        }
        return oneononeInquiry.registProcessDayandtime.desc();
    }

    public static OrderSpecifier<String> ReferenceDaySecond(String referenceDay){

        StringPath aliasQuantity = Expressions.stringPath("lastAnswerDay");

        if(StringUtils.equals("최종문의일",referenceDay)){
            return aliasQuantity.desc();
        }
        return oneononeInquiry.registProcessDayandtime.desc();
    }


}
