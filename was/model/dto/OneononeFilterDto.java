package denall.admin.was.model.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Setter
public class OneononeFilterDto {

	String serviceType;
	List<String> categoryTypeList;
	List<String> answerStateList;
	String referenceDay;
	String startDay;
	String endDay;
	String searchCategory;
	String searchKeyword;


}