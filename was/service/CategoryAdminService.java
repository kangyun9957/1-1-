package denall.admin.was.service;

import denall.admin.was.model.dto.CategoryAdminDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
public interface CategoryAdminService {

    List<CategoryAdminDto> getCategoryAdminList(List<String> serviceTypes);

    @Transactional(readOnly = false)
    void saveCategoryList(List<CategoryAdminDto> categoryAdminDtos);

    int checkBeforeSave(CategoryAdminDto categoryAdminDto);




}
