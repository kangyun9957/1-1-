package denall.admin.was.repository;

import com.querydsl.core.Tuple;
import denall.admin.was.model.dto.AnswerDto;
import denall.admin.was.model.entity.OneononeInquiryAnswer;


public interface OneononeInquiryAnswerRepository {
    Long save(OneononeInquiryAnswer oneononeInquiryAnswer);

    AnswerDto getAnswer(String oneononeInquiryNumber);

    Long modifyCompleteAnswer(AnswerDto answerDto);

    Long modifyAnswerState(AnswerDto answerDto);


}
