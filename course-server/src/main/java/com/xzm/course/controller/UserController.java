package com.xzm.course.controller;

import com.xzm.course.model.vo.request.LoginVO;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVO login(@Validated @RequestBody LoginVO loginVO) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();
        return userService.login(username, password, userType);
    }

    @RequestMapping("/login/status")
    public ResultVO getLoginStatus() {
        return userService.getLoginStatus();
    }

    @RequestMapping("/logout")
    public ResultVO logout() {
        return userService.logout();
    }
}
