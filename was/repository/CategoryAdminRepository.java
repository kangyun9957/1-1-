package denall.admin.was.repository;

import com.querydsl.core.Tuple;
import denall.admin.was.model.dto.CategoryAdminDto;
import denall.admin.was.model.entity.CategoryAdmin;

import java.util.List;

public interface CategoryAdminRepository {
    Long save(CategoryAdmin categoryAdmin);
    List<CategoryAdminDto> getCategoryAdminList(List<String> serviceType);
    Long modifyCategoryList(CategoryAdminDto categoryAdminDto);
    Long deleteCategoryList(CategoryAdminDto categoryAdminDto);
    int checkBeforeSave(CategoryAdminDto categoryAdminDto);


}
