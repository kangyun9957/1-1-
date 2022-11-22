package denall.admin.was.repository;

import denall.admin.was.model.dto.AnswerDto;
import denall.admin.was.model.dto.OneononeFilterDto;
import denall.admin.was.model.dto.OneononeInquiryBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface OneononeInquiryRepository {

    public Page<OneononeInquiryBoardDto> getList(OneononeFilterDto oneononeFilterDto, Pageable pageable);

    AnswerDto getInquiry(String oneononeInquiryNumber);

}
