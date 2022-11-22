package denall.admin.was.model.mapping;

import denall.admin.was.model.dto.OneononeInquiryBoardDto;
import denall.admin.was.model.entity.CategoryAdmin;
import denall.admin.was.model.entity.OneononeInquiry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OneononeInquiryBoardMapper {
    @Mapping(source = "categoryAdmin.customercenterCategoryCode" , target = "customercenterCategoryCode")
    @Mapping(source = "oneononeInquiry.channelDivisioncode" , target = "channelDivisioncode")
    @Mapping(source = "categoryAdmin.employeeInterfaceReceive.userName" , target = "userName")
    @Mapping(source = "oneononeInquiry.nomemberInquiry.writerName" , target = "writerName")
    @Mapping(source = "oneononeInquiry.registProcessorId" , target = "registProcessorId")
    @Mapping(source = "oneononeInquiry.registProcessDayandtime" , target = "registProcessDayandtime")
    OneononeInquiryBoardDto toDto(OneononeInquiry oneononeInquiry, CategoryAdmin categoryAdmin);





}

