package denall.admin.was.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryAdminPk implements Serializable {
    private String customercenterCategoryCode;
    private String companyCode;
    private String administratorId;

}
