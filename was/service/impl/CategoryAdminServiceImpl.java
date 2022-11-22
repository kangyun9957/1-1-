package denall.admin.was.service.impl;

import com.querydsl.core.Tuple;
import denall.admin.was.model.dto.CategoryAdminDto;
import denall.admin.was.model.entity.CategoryAdmin;
import denall.admin.was.model.mapping.CategoryAdminMapper;
import denall.admin.was.repository.CategoryAdminRepository;
import denall.admin.was.service.CategoryAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import static denall.admin.was.model.entity.QCategoryAdmin.categoryAdmin;


@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryAdminServiceImpl implements CategoryAdminService {

    private final CategoryAdminRepository categoryAdminRepository;

    private final CategoryAdminMapper categoryAdminMapper;



    @Override
    public List<CategoryAdminDto> getCategoryAdminList(List<String> serviceTypes){
        List<CategoryAdminDto> categoryAdmins = categoryAdminRepository.getCategoryAdminList(serviceTypes);
        return categoryAdmins;
    }

    // TODO result 값의 의도를 모르겠음. 아래와 같이 하면 계속 덮어씀(완료)
    @Override
    public void saveCategoryList(List<CategoryAdminDto> categoryAdminDtos) {

        for(CategoryAdminDto categoryAdminDto : categoryAdminDtos){
            if(StringUtils.equals(categoryAdminDto.getFlag(),"I")){
                CategoryAdmin categoryAdmin = categoryAdminMapper.toEntity(categoryAdminDto);
               categoryAdminRepository.save(categoryAdmin);
            }
            if(StringUtils.equals(categoryAdminDto.getFlag(),"U")){
               categoryAdminRepository.modifyCategoryList(categoryAdminDto);
            }
            if(StringUtils.equals(categoryAdminDto.getFlag(),"R")){
               categoryAdminRepository.deleteCategoryList(categoryAdminDto);
            }
        }

    }

    @Override
    public int checkBeforeSave(CategoryAdminDto categoryAdminDto){
       return categoryAdminRepository.checkBeforeSave(categoryAdminDto);
    }
}
