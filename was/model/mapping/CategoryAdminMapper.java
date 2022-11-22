package denall.admin.was.model.mapping;

import denall.admin.was.model.dto.CategoryAdminDto;
import denall.admin.was.model.entity.CategoryAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryAdminMapper extends StructMapper<CategoryAdminDto,CategoryAdmin> {


    @Mapping(source = "categoryAdmin.channelDivisioncode", target = "serviceType")
    @Mapping(source = "categoryAdmin.employeeInterfaceReceive.userName", target = "manager")
    @Mapping(source = "categoryAdmin.employeeInterfaceReceive.employeeNumber", target = "administratorId")
    @Mapping(source = "categoryAdmin.customercenterCategoryCode", target = "categoryName")
    CategoryAdminDto toDto(CategoryAdmin categoryAdmin);


    @Override
    @Mapping(source = "categoryAdminDto.categoryName" , target = "customercenterCategoryCode")
    @Mapping(target = "companyCode" , constant = "OSSTEM")
    @Mapping(source = "categoryAdminDto.serviceType" , target = "channelDivisioncode")
    @Mapping(source = "categoryAdminDto.administratorId" , target = "registProcessorId")
    @Mapping(target = "registProcessDayandtime", expression = "java(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern(\"yyyyMMdd\")))")
    @Mapping(target = "processProgramId" , constant = "dataGrip")
    CategoryAdmin toEntity(CategoryAdminDto categoryAdminDto);


}

