package denall.admin.was.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAdminDto implements Serializable {

    int number;
    boolean open=true;
    String serviceType;
    String manager;
    String administratorId;
    String categoryName;
    String registProcessDayandtime;
    long categoryCounts;
    String flag;
    String previousAdministratorId;
    String previousCategoryName;
}
