package denall.admin.was.service;

import denall.admin.was.model.dto.AnswerDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OneononeInquiryAnswerService {


    AnswerDto getAnswer(String oneononeInquiryNumber, String answerState);


    @Transactional(readOnly = false)
    Long addAnswerAndModifyAnswerState(AnswerDto answerDto);

}
