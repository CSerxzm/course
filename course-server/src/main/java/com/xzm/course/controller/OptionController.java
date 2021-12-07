package com.xzm.course.controller;

import com.xzm.course.config.themis.annotation.Admin;
import com.xzm.course.model.vo.request.BoolOptionVO;
import com.xzm.course.model.vo.response.ResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/option")
@RestController
public class OptionController extends BaseController {

    @GetMapping("/allowStudentSelect")
    public ResultVO getAllowStudentSelect() {
        return result("成功");
    }

    @Admin(Admin.STUDENT_COURSE_MANAGE)
    @PutMapping("/allowStudentSelect")
    public ResultVO setAllowStudentSelect(@RequestBody @Validated BoolOptionVO option) {
        return result("成功");
    }

    @GetMapping("/allowTeacherGrade")
    public ResultVO getAllowTeacherGrade() {
        return result("成功");
    }

    @Admin(Admin.STUDENT_COURSE_MANAGE)
    @PutMapping("/allowTeacherGrade")
    public ResultVO setAllowTeacherGrade(@RequestBody @Validated BoolOptionVO option) {
        return result("成功");
    }
}
