package com.xzm.course.service;

import com.xzm.course.manager.LoginStatusManager;
import com.xzm.course.manager.UserManager;
import com.xzm.course.model.bo.AuthInfoBO;
import com.xzm.course.model.bo.LoginStatusBO;
import com.xzm.course.model.constant.UserType;
import com.xzm.course.model.vo.response.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService extends BaseService {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserManager userManager;

    @Autowired
    private LoginStatusManager loginStatusManager;

    public ResultVO login(String username, String password, Integer userType) {
        AuthInfoBO authInfo = userManager.getAuthInfoByUsername(username, userType);
        if (authInfo == null) {
            return failedResult("用户不存在");
        }
        if (!password.equals(authInfo.getPassword())) {
            return failedResult("密码错误");
        }

        if (authInfo.getUserType().equals(UserType.STUDENT)) {
            userManager.updateStudentLastLoginTime(username);
        }

        LoginStatusBO statusBO = LoginStatusBO.fromAuthInfo(authInfo);
        loginStatusManager.setLoginStatus(session, statusBO);

        return result(statusBO);
    }

    public ResultVO getLoginStatus() {
        LoginStatusBO statusBO = loginStatusManager.getLoginStatus(session);
        return result(statusBO);
    }

    public ResultVO logout() {
        loginStatusManager.setLoginStatus(session, null);
        return result("注销成功");
    }
}
