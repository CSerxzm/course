package com.xzm.course.controller.student;

import com.xzm.course.config.themis.annotation.Student;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.vo.request.StudentInfoFormVO;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.student.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Student
@RequestMapping("/student/info")
@RestController
public class InfoController extends BaseController {

    @Autowired
    private InfoService infoService;

    @GetMapping
    public ResultVO get() {
        return infoService.get();
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated StudentInfoFormVO formVO) {
        return infoService.update(formVO);
    }
}
