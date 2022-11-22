package denall.admin.was.model.mapping;

import denall.admin.was.model.dto.OneononeInquiryBoardDto;
import denall.admin.was.model.entity.OneononeInquiry;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OneonOneInquiryMapper extends StructMapper<OneononeInquiryBoardDto, OneononeInquiry> {

}
