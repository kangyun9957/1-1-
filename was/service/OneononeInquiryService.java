package denall.admin.was.service;

import denall.admin.was.model.dto.OneononeFilterDto;
import denall.admin.was.model.dto.OneononeInquiryBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
public interface OneononeInquiryService {

    Page<OneononeInquiryBoardDto> getList(OneononeFilterDto oneononeFilterDto, Pageable pageable);
}
