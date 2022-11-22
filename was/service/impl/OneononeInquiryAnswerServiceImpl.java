package denall.admin.was.service.impl;

import com.querydsl.core.Tuple;
import denall.admin.was.model.dto.AnswerDto;
import denall.admin.was.model.entity.OneononeInquiryAnswer;
import denall.admin.was.model.mapping.OneononeInquiryAnswerMapper;
import denall.admin.was.repository.OneononeInquiryAnswerRepository;
import denall.admin.was.repository.OneononeInquiryRepository;
import denall.admin.was.service.OneononeInquiryAnswerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import static denall.admin.was.model.entity.QCategoryAdmin.categoryAdmin;
import static denall.admin.was.model.entity.QOneononeInquiry.oneononeInquiry;
import static denall.admin.was.model.entity.QOneononeInquiryAnswer.oneononeInquiryAnswer;

@Service
@RequiredArgsConstructor
public class OneononeInquiryAnswerServiceImpl implements OneononeInquiryAnswerService {




    private final OneononeInquiryAnswerRepository oneononeInquiryAnswerRepository;

    private final OneononeInquiryRepository oneononeInquiryRepository;


    private final OneononeInquiryAnswerMapper oneononeInquiryAnswerMapper;
    // TODO 중복 코드 발생, 개선 필요 (완료) > 변경된 거 새로 한번 볼 수 있도록... Repository 중복코드 제거..
    @Override
    public AnswerDto getAnswer(String oneononeInquiryNumber, String answerState) {
        AnswerDto result = oneononeInquiryRepository.getInquiry(oneononeInquiryNumber);
        if(StringUtils.equals(answerState,"ST05")) {
            result.addAnswer(oneononeInquiryAnswerRepository.getAnswer(oneononeInquiryNumber));
        }
        return result;
    }

    @Override
    public Long addAnswerAndModifyAnswerState(AnswerDto answerDto) {
        if(StringUtils.equals(answerDto.getAnswerState(),"ST05")){
            return oneononeInquiryAnswerRepository.modifyCompleteAnswer(answerDto);
        }
        OneononeInquiryAnswer oneononeInquiryAnswer = oneononeInquiryAnswerMapper.toEntity(answerDto);
        oneononeInquiryAnswerRepository.save(oneononeInquiryAnswer).intValue();
        return oneononeInquiryAnswerRepository.modifyAnswerState(answerDto);
    }
}
