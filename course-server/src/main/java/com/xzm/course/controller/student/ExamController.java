package com.xzm.course.controller.student;

import com.xzm.course.config.themis.annotation.Student;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.student.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/exam")
@RestController
public class ExamController extends BaseController {

    @Autowired
    private ExamService examService;

    @RequestMapping("/list")
    public ResultVO list() {
        return examService.list();
    }
}
