package denall.admin.was.util;


import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class StringSplitUtil {

	private StringSplitUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static List<String> splitFilter(String str){

		List<String> list = new ArrayList<>(Arrays.asList(str.split(" ")));

		//테스트 후 지울 예정
		boolean result = list.stream().anyMatch(index -> index.equals("전체"));
		if(result) {
			list.clear();
			return list;
		}

		return list;
	}


}
