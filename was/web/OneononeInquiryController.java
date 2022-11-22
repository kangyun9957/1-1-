package denall.admin.was.web;

import denall.admin.was.model.dto.*;
import denall.admin.was.service.impl.CategoryAdminServiceImpl;
import denall.admin.was.service.impl.OneononeInquiryAnswerServiceImpl;
import denall.admin.was.service.impl.OneononeInquiryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/com/OneononeInquiry")
@Slf4j
public class OneononeInquiryController {

	private final OneononeInquiryServiceImpl oneononeInquiryServiceImpl;

	private final OneononeInquiryAnswerServiceImpl oneononeInquiryAnswerServiceImpl;

	private final CategoryAdminServiceImpl categoryAdminServiceImpl;

	@GetMapping("/getList")
	public ResponseEntity<ResponseDto> getList(OneononeFilterDto oneononeFilterDto, Pageable pageable){
		Page<OneononeInquiryBoardDto> oneononeInquiryList = oneononeInquiryServiceImpl.getList(oneononeFilterDto, pageable);

		return ResponseDto.ok(oneononeInquiryList, (int) oneononeInquiryList.getTotalElements());
	}

	@GetMapping("/getCategoryList")
	public ResponseEntity<ResponseDto> getCategoryList(@RequestParam List<String> serviceTypeList){
		List<CategoryAdminDto> categoryAdminDtos = categoryAdminServiceImpl.getCategoryAdminList(serviceTypeList);

		return ResponseDto.ok(categoryAdminDtos,categoryAdminDtos.size());
	}

	@PostMapping("/saveCategoryList")
	public ResponseEntity<ResponseDto> saveCategoryList(@RequestBody List<CategoryAdminDto> categoryAdminDtos){
		if(categoryAdminDtos.isEmpty() || categoryAdminDtos==null){
			return ResponseDto.ok(0);
		}
		
		categoryAdminServiceImpl.saveCategoryList(categoryAdminDtos);
		return ResponseDto.ok("success");
	}
	@PostMapping("/checkBeforeSaveCategoryList")
	public ResponseEntity<ResponseDto> checkBeforeSaveCategoryList(@RequestBody CategoryAdminDto categoryAdminDto){

		return ResponseDto.ok(categoryAdminServiceImpl.checkBeforeSave(categoryAdminDto));
	}

	@GetMapping("/getAnswer")
	public ResponseEntity<ResponseDto> getAnswer(@RequestParam String oneononeInquiryNumber, @RequestParam String answerState){
		AnswerDto answerCompleteDto = oneononeInquiryAnswerServiceImpl.getAnswer(oneononeInquiryNumber,answerState);
		return ResponseDto.ok(answerCompleteDto);
	}


	@PostMapping("/addAnswer")
	public ResponseEntity<ResponseDto> addAnswer(@RequestBody AnswerDto answerDto){
		int result= oneononeInquiryAnswerServiceImpl.addAnswerAndModifyAnswerState(answerDto).intValue();
		return ResponseDto.ok(result);
	}
}
