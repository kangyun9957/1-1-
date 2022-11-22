package denall.admin.was.service.impl;


import denall.admin.was.model.dto.OneononeFilterDto;
import denall.admin.was.model.dto.OneononeInquiryBoardDto;
import denall.admin.was.repository.impl.OneononeInquiryRepositoryImpl;
import denall.admin.was.service.OneononeInquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OneononeInquiryServiceImpl implements OneononeInquiryService {

	private final OneononeInquiryRepositoryImpl oneononeInquiryRepository;

	public Page<OneononeInquiryBoardDto> getList(OneononeFilterDto oneononeFilterDto, Pageable pageable) {
		return oneononeInquiryRepository.getList(oneononeFilterDto, pageable);
	}
}
