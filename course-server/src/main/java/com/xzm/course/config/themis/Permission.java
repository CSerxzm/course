package com.xzm.course.config.themis;

import com.xzm.course.model.constant.UserType;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Permission {

    private Integer userType = UserType.NO;
    private Integer permission = 0;
    private Boolean needLogin = true;

    public Permission() {

    }

    public Permission(Integer userType) {
        this.userType = userType;
    }

    public Permission(Integer userType, Integer permission) {
        this.userType = userType;
        this.permission = permission;
    }

    public Permission(Boolean needLogin) {
        this.needLogin = needLogin;
    }
}
