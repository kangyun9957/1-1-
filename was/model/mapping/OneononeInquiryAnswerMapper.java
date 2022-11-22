package denall.admin.was.model.mapping;

import denall.admin.was.model.entity.CategoryAdmin;
import denall.admin.was.model.entity.OneononeInquiry;
import denall.admin.was.model.dto.AnswerDto;
import denall.admin.was.model.entity.OneononeInquiryAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OneononeInquiryAnswerMapper extends StructMapper<AnswerDto, OneononeInquiryAnswer>{

    @Override
    @Mapping(source = "answerDto.answerContent" , target = "content")
    @Mapping(source = "answerDto.answerManagerId", target = "registProcessorId")
    @Mapping(target = "processProgramId" , constant = "/inquiry/add")
    @Mapping(source = "answerDto.answerDay", target = "registProcessDayandtime")
    OneononeInquiryAnswer toEntity(AnswerDto answerDto);

    @Override
    @Mapping(source = "oneononeInquiryAnswer.content" , target = "answerContent")
    @Mapping(source = "oneononeInquiryAnswer.registProcessDayandtime" , target = "answerDay")
    @Mapping(source = "oneononeInquiryAnswer.updateProcessorId" , target = "modifyManager")
    @Mapping(source = "oneononeInquiryAnswer.registProcessDayandtime" , target = "lastAnswerDay")
    AnswerDto toDto(OneononeInquiryAnswer oneononeInquiryAnswer);
}
