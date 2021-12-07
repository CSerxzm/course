package com.xzm.course.controller.student;

import com.xzm.course.config.themis.annotation.Student;
import com.xzm.course.controller.BaseController;
import com.xzm.course.model.vo.response.ResultVO;
import com.xzm.course.service.student.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Student
@RequestMapping("/student/timetable")
@RestController("student_timeTableController")
public class TimetableController extends BaseController {

    @Autowired
    private TimetableService timetableService;

    @RequestMapping
    public ResultVO get() {
        return timetableService.get();
    }
}
