package com.xzm.course.service.student;

import com.xzm.course.manager.student.InfoManager;
import com.xzm.course.model.entity.StudentEntity;
import com.xzm.course.model.vo.request.StudentInfoFormVO;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.BaseService;
import com.xzm.course.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class InfoService extends BaseService {

    @Autowired
    private UserService userService;

    @Autowired
    private InfoManager infoManager;

    public ResultVO get() {
        return result(infoManager.getStudentInfoByStudentId(getUserId()));
    }

    public ResultVO update(@RequestBody @Validated StudentInfoFormVO studentInfoForm) {
        StudentEntity student = infoManager.getStudentById(getUserId());

        String password = studentInfoForm.getPassword();
        if (password == null || password.equals("")) {
            password = student.getPassword();
        } else {
            password = password;
        }

        BeanUtils.copyProperties(studentInfoForm, student);
        student.setPassword(password);
        infoManager.updateStudent(student);

        return result("更新成功");
    }
}
